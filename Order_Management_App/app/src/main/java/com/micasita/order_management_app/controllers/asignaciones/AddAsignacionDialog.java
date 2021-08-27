package com.micasita.order_management_app.controllers.asignaciones;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.micasita.order_management_app.R;
import com.micasita.order_management_app.controllers.empleados.EmpleadosFragment;
import com.micasita.order_management_app.controllers.ordenes.OrdenesFragment;
import com.micasita.order_management_app.controllers.productos.ProductosFragment;
import com.micasita.order_management_app.db.BBDD_Order;
import com.micasita.order_management_app.db.ConstructorBaseDatos;
import com.micasita.order_management_app.models.Asignaciones;
import com.micasita.order_management_app.models.Empleados;
import com.micasita.order_management_app.models.GeneralModel;
import com.micasita.order_management_app.models.Productos;
import com.micasita.order_management_app.utils.Fetch;

import java.util.ArrayList;

public class AddAsignacionDialog extends Dialog {
    GeneralModel generalModel;
    EditText edt_nombre, edt_codigo, edt_precio, edt_canttidad, edt_descripcion;
    ConstructorBaseDatos ct;
    BBDD_Order db;
    Button asignar;
    Spinner empleadoSpiner;

    public AddAsignacionDialog(@NonNull Context context, BBDD_Order db, ConstructorBaseDatos ct, GeneralModel generalModel) {
        super(context);
        this.generalModel = generalModel;
        this.db = db;
        this.ct = ct;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_asignacion);
        setupLayout();
        empleadoSpiner = findViewById(R.id.empleados_spinner);
        asignar = findViewById(R.id.btn_asignar_orden);
        cargarEmpleados();

        asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAsignacion();
            }
        });

    }

    private void setupLayout() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
    }

    public boolean internet_connection() {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public void cargarEmpleados() {
        if (internet_connection()) {
            final ProgressDialog dialog = ProgressDialog.show(getContext(), "PROCESANDO", "Se esta procesando su solicitud. Favor espere.", true);

            Handler handler = new Handler();

            handler.post(new Runnable() {
                @Override
                public void run() {

                    Fetch fetchData = new Fetch("https://192.168.100.2/Order_Web_Service/empleados/get-all");
                    if (fetchData.startFetch()) {
                        if (fetchData.onComplete()) {
                            String result = fetchData.getResult();
                            Log.i("FetchData", result);
                            dialog.dismiss();
                            ArrayList<Empleados> empleados = new Gson().fromJson(result, EmpleadosFragment.EmpleadoResponse.class).getData();

                            ArrayList empleadoName = new ArrayList();
                            ArrayList ids = new ArrayList();
                            for (int i = 0; i < empleados.size(); i++) {
                                empleadoName.add(empleados.get(i).getNombre());
                                ids.add(empleados.get(i).getIdEmpleado());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_spinner_item, empleadoName);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            empleadoSpiner.setAdapter(adapter);
                            empleadoSpiner.setTag(ids);

                        }
                    }
                }
            });
        } else {
            final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    "Error de Conexion", Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(ContextCompat.getColor(getContext(),
                    R.color.purple_200));

            snackbar.setAction("Intente de Nuevo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargarEmpleados();
                }
            }).show();
        }
    }


    private void addAsignacion() {
        {
            ArrayList empleadosIds = (ArrayList) empleadoSpiner.getTag();
            int empleadoId = (int) empleadosIds.get(empleadoSpiner.getSelectedItemPosition());

            Asignaciones asignacion = new Asignaciones();
            asignacion.setEmpleadoId(Integer.valueOf(empleadoId));
            asignacion.setOrdenId(generalModel.getOrdenes().getId());
            String empleado = empleadoSpiner.getSelectedItem().toString();
            asignacion.setEmpleadoNombre(empleado);

            generalModel.setAsignaciones(asignacion);
            ct.insertarAsignacion();

              OrdenesFragment.getInstance().addAsignacionDialog.dismiss();
            Toast.makeText(getContext(), "Asignacion agregada", Toast.LENGTH_SHORT).show();

        }
    }
}