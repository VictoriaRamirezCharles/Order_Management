package com.micasita.order_management_app.controllers.empleados;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.micasita.order_management_app.R;
import com.micasita.order_management_app.models.Empleados;
import com.micasita.order_management_app.utils.Post;
import com.micasita.order_management_app.utils.Response;

public class EditEmpleadoDialog extends Dialog {

    EditText edt_nombre,edt_direccion,edt_telefono,edt_email;
    Empleados model;
    Button btn_edit;
    public EditEmpleadoDialog(@NonNull Context context, Empleados model) {
        super(context);
        this.model = model;


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_empleado);
        setupLayout();
        edt_nombre = findViewById(R.id.ed_nombre_empleado);
        edt_direccion = findViewById(R.id.ed_direccion_empleado);
        edt_telefono = findViewById(R.id.ed_telefono_empleado);
        edt_email = findViewById(R.id.email_empleado);
        btn_edit = findViewById(R.id.btn_edit_empleado_data);
        TextView codigoLabel = findViewById(R.id.codigo_empleado);
        codigoLabel.setText("EDITAR EMPLEADO-"+model.getCodigo());

        edt_nombre.setText(model.getNombre());
        edt_direccion.setText(model.getDireccion());
        edt_telefono.setText(model.getTelefono());
        edt_email.setText(model.getEmail());

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(   edt_nombre.getText().toString().isEmpty()
                   || edt_direccion.getText().toString().isEmpty()
                   || edt_telefono.getText().toString().isEmpty()
                   || edt_email.getText().toString().isEmpty()
                ){
                    Toast.makeText(getContext(), "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
                }else{
                    if(internet_connection())
                    {
                        editEmpleado();
                    }else{

                        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                                "Error de Conexion" , Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(ContextCompat.getColor(getContext(),
                                R.color.purple_200));

                        snackbar.setAction("Intente de Nuevo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editEmpleado();
                            }
                        }).show();
                    }
                }


            }
        });
    }
    private void setupLayout() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
    }
    public boolean internet_connection()
    {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
    public void editEmpleado()
    {
        final ProgressDialog dialog = ProgressDialog.show(getContext(), "PROCESANDO", "Se esta procesando su solicitud. Favor espere.", true);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[5];
                field[0] = "Nombre";
                field[1] = "Direccion";
                field[2] = "Telefono";
                field[3] = "Email";
                field[4] = "IdEmpleado";

                String[] data = new String[5];
                data[0] = edt_nombre.getText().toString();
                data[1] = edt_direccion.getText().toString();
                data[2] = edt_telefono.getText().toString();
                data[3] = edt_email.getText().toString();
                data[4] = String.valueOf(model.getIdEmpleado());

                Post postData = new Post("https://192.168.100.2/Order_Web_Service/empleados/update", "PUT", field, data);
                if (postData.startPost()) {
                    if (postData.onComplete()) {
                        String result = postData.getResult();

                        Log.i("Post", result);
                        dialog.dismiss();
                        Toast.makeText(getContext(), new Response().Message(result), Toast.LENGTH_SHORT).show();

                        EmpleadosFragment.getInstance().cargarEmpleados();
                        EmpleadosFragment.getInstance().editEmpleadoDialog.dismiss();
                        EmpleadosFragment.getInstance().scrollMyListViewToBottom();
                    }

                }


            }
        });
    }
}
