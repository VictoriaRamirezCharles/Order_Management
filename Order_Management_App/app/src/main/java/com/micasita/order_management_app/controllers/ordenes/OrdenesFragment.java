package com.micasita.order_management_app.controllers.ordenes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.micasita.order_management_app.R;
import com.micasita.order_management_app.controllers.asignaciones.AddAsignacionDialog;
import com.micasita.order_management_app.controllers.empleados.EditEmpleadoDialog;
import com.micasita.order_management_app.controllers.productos.EditProductoDialog;
import com.micasita.order_management_app.controllers.productos.ProductosFragment;
import com.micasita.order_management_app.databinding.FragmentOrdenesBinding;
import com.micasita.order_management_app.db.BBDD_Order;
import com.micasita.order_management_app.db.ConstructorBaseDatos;
import com.micasita.order_management_app.models.GeneralModel;
import com.micasita.order_management_app.models.Ordenes;

import java.util.ArrayList;

public class OrdenesFragment extends Fragment {

    private FragmentOrdenesBinding binding;
    ConstructorBaseDatos ct;
    BBDD_Order db;
    GeneralModel generalModel;
    private Context context;
    private static OrdenesFragment INSTANCE;
    AddOrdenDialog addOrdenDialog;
    public AddAsignacionDialog  addAsignacionDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentOrdenesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        INSTANCE = this;


        this.generalModel = new GeneralModel();
        this.db = new BBDD_Order(getContext());
        this.ct = new ConstructorBaseDatos(getContext(),db,generalModel);
        cargarOrdenes();
        binding.btnAddOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrdenDialog = new AddOrdenDialog(getContext(),db,ct,generalModel);
                addOrdenDialog.show();

            }
        });


        return root;
    }
    public void scrollMyListViewToBottom() {
        binding.ordenesListView.post(new Runnable() {
            @Override
            public void run() {
                binding.ordenesListView.setSelection(binding.ordenesListView.getAdapter().getCount()- 1);

            }
        });
    }

    public static OrdenesFragment getInstance() {
        return INSTANCE;
    }
    public void cargarOrdenes(){

        final ProgressDialog dialog = ProgressDialog.show(getContext(), "PROCESANDO", "Se esta procesando su solicitud. Favor espere.", true);

        ArrayList<Ordenes> ordenes = ct.cbtenerDatosOrdenes();
        if(ordenes != null){
            binding.ordenesListView.setAdapter(new OrdenesAdapter(getContext(), ordenes));
        }
        dialog.dismiss();

    }
    private class OrdenesAdapter extends BaseAdapter
    {

        private final Context context;
        private final LayoutInflater inflater;
        private  ArrayList<Ordenes> ordenes;

        public OrdenesAdapter(Context context, ArrayList<Ordenes> ordenes) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.ordenes = ordenes;
        }


        @Override
        public int getCount() {
            return ordenes.size();
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
                convertView =  inflater.inflate(R.layout.item_ordenes, parent, false);

            ((TextView)convertView.findViewById(R.id.ordenId)).setText("ORDEN-"+this.ordenes.get(position).getId());
            ((TextView)convertView.findViewById(R.id.ordenFecha)).setText(this.ordenes.get(position).getFecha());

            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0; i<ordenes.size();i++){
                if(ordenes.get(i).getId() == ordenes.get(position).getId()){
                    stringBuilder.append(ordenes.get(i).getNombreProducto()+ " CANTIDAD "+ordenes.get(i).getCantidad());
                }
                String finalString = stringBuilder.toString();
                ((TextView)convertView.findViewById(R.id.orden_resumen)).setText(finalString);
            }


            ((Button)convertView.findViewById(R.id.btn_add_asignar)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    generalModel.setOrdenes(ordenes.get(position));
                    addAsignacionDialog = new AddAsignacionDialog(getContext(),db,ct,generalModel);
                    addAsignacionDialog.show();
                }
            });
            ((Button)convertView.findViewById(R.id.btn_delete_orden)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopUp(position);
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return convertView;
        }

        public void PopUp(int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("¿Realmente desea eliminar esta Orden?")
                    .setTitle("Eliminar Cliente")
                    .setCancelable(false)
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ct.deleteOrden(ordenes.get(position).getId());
                            Toast.makeText(context, "Orden eliminado correctamente", Toast.LENGTH_LONG).show();
                            cargarOrdenes();

                        }
                    });
            builder.show();



        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
