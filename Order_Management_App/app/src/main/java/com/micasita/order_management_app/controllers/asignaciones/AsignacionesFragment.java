package com.micasita.order_management_app.controllers.asignaciones;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.micasita.order_management_app.R;
import com.micasita.order_management_app.controllers.productos.EditProductoDialog;
import com.micasita.order_management_app.controllers.productos.ProductosFragment;
import com.micasita.order_management_app.databinding.FragmentAsignacionesBinding;
import com.micasita.order_management_app.db.BBDD_Order;
import com.micasita.order_management_app.db.ConstructorBaseDatos;
import com.micasita.order_management_app.models.Asignaciones;
import com.micasita.order_management_app.models.GeneralModel;
import com.micasita.order_management_app.models.Productos;

import java.util.ArrayList;


public class AsignacionesFragment extends Fragment {

    private FragmentAsignacionesBinding binding;
    ConstructorBaseDatos ct;
    BBDD_Order db;
    GeneralModel generalModel;
    private Context context;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.generalModel = new GeneralModel();
        this.db = new BBDD_Order(getContext());
        this.ct = new ConstructorBaseDatos(getContext(),db,generalModel);
        binding = FragmentAsignacionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cargarAsignaciones();


        return root;
    }
    public void cargarAsignaciones(){

        final ProgressDialog dialog = ProgressDialog.show(getContext(), "PROCESANDO", "Se esta procesando su solicitud. Favor espere.", true);

        ArrayList<Asignaciones> asignaciones = ct.cbtenerDatosAsignaciones();
        if(asignaciones != null){
            binding.asignacionesListView.setAdapter(new AsignacionesFragment.AsignacionesAdapter(getContext(), asignaciones));
        }
        dialog.dismiss();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private class AsignacionesAdapter extends BaseAdapter
    {

        private final Context context;
        private final LayoutInflater inflater;
        private  ArrayList<Asignaciones> asignaciones;

        public AsignacionesAdapter(Context context, ArrayList<Asignaciones> asignaciones) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.asignaciones = asignaciones;
        }


        @Override
        public int getCount() {
            return asignaciones.size();
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
                convertView =  inflater.inflate(R.layout.item_asignaciones, parent, false);

            ((TextView)convertView.findViewById(R.id.asignacion_empleado)).setText(this.asignaciones.get(position).getEmpleadoNombre());
            ((TextView)convertView.findViewById(R.id.asignacion_lista_orden)).setText(String.valueOf("ORDEN-"+this.asignaciones.get(position).getOrdenId()));




            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return convertView;
        }


    }
}