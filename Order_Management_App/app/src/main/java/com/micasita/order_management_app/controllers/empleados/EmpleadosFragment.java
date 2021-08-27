package com.micasita.order_management_app.controllers.empleados;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.micasita.order_management_app.R;
import com.micasita.order_management_app.databinding.FragmentEmpleadosBinding;
import com.micasita.order_management_app.models.Empleados;
import com.micasita.order_management_app.utils.Fetch;
import com.micasita.order_management_app.utils.Post;
import com.micasita.order_management_app.utils.Response;

import java.util.ArrayList;


public class EmpleadosFragment extends Fragment {

    private FragmentEmpleadosBinding binding;
    private static EmpleadosFragment INSTANCE;
    AddEmpleadoDialog addEmpleadoDialog;
    EditEmpleadoDialog editEmpleadoDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        INSTANCE = this;
        binding = FragmentEmpleadosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cargarEmpleados();

        binding.btnAddEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(internet_connection()){
                    addEmpleadoDialog = new AddEmpleadoDialog(getContext());
                    addEmpleadoDialog.show();
                }else{
                    final Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Error de Conexion" , Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(ContextCompat.getColor(getContext(),
                            R.color.purple_200));

                    snackbar.setAction("Intente de Nuevo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }

            }
        });
        return root;
    }

    public static EmpleadosFragment getInstance() {
        return INSTANCE;
    }

    public void scrollMyListViewToBottom() {
        binding.empleadoListView.post(new Runnable() {
            @Override
            public void run() {
                binding.empleadoListView.setSelection(binding.empleadoListView.getAdapter().getCount()- 1);

            }
        });
    }
    public void cargarEmpleados()
    {
        if(internet_connection()) {
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
                            ArrayList<Empleados> empleados = new Gson().fromJson(result, EmpleadoResponse.class).getData();
                            binding.empleadoListView.setAdapter(new EmpleadosAdapter(getContext(), empleados));


                        }
                    }
                }
            });
        }
        else {
            final Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "Error de Conexion" , Snackbar.LENGTH_LONG);
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

    public boolean internet_connection()
    {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private class EmpleadosAdapter extends BaseAdapter
    {

        private final Context context;
        private final LayoutInflater inflater;
        private final ArrayList<Empleados> empleados;

        public EmpleadosAdapter(Context context, ArrayList<Empleados> comments) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.empleados = comments;
        }


        @Override
        public int getCount() {
            return empleados.size();
        }

        @Override
        public Object getItem(int position) {
            return new Object();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView =  inflater.inflate(R.layout.item_empleados, parent, false);


            ((TextView)convertView.findViewById(R.id.empleado_lista_nombre)).setText(this.empleados.get(position).getCodigo()+ "-"+this.empleados.get(position).getNombre());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editEmpleadoDialog = new EditEmpleadoDialog(getContext(),empleados.get(position));
                    editEmpleadoDialog.show();
                }
            });

            convertView.findViewById(R.id.empleado_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopUp(position);
                }
            });

            return convertView;
        }
        public void PopUp(int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("¿Realmente desea eliminar este empleado?")
                    .setTitle("Eliminar Empleado")
                    .setCancelable(false)
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            eliminarEmpleado(empleados.get(position).getIdEmpleado());

                        }
                    });
            builder.show();

        }
    }
    public class EmpleadoResponse
    {
        @SerializedName("Data")
        private ArrayList<Empleados> data;

        public ArrayList<Empleados> getData() {
            return data;
        }

        public void setData(ArrayList<Empleados> data) {
            this.data = data;
        }
    }

    public void eliminarEmpleado(int empleadoId)
    {
        final ProgressDialog dialog = ProgressDialog.show(getContext(), "PROCESANDO", "Se esta procesando su solicitud. Favor espere.", true);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[0];
                String[] data = new String[0];

                Post postData = new Post("https://192.168.100.2/Order_Web_Service/empleados/delete/"+empleadoId, "DELETE", field, data);
                if (postData.startPost()) {
                    if (postData.onComplete()) {
                        String result = postData.getResult();

                        Log.i("Post", result);
                        dialog.dismiss();
                        Toast.makeText(getContext(), new Response().Message(result), Toast.LENGTH_SHORT).show();

                        EmpleadosFragment.getInstance().cargarEmpleados();
                    }

                }


            }
        });
    }
}
