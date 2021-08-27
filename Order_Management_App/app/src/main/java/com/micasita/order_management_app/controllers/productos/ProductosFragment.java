package com.micasita.order_management_app.controllers.productos;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.micasita.order_management_app.R;
import com.micasita.order_management_app.databinding.FragmentEmpleadosBinding;
import com.micasita.order_management_app.databinding.FragmentProductosBinding;
import com.micasita.order_management_app.db.BBDD_Order;
import com.micasita.order_management_app.db.ConstructorBaseDatos;
import com.micasita.order_management_app.models.GeneralModel;
import com.micasita.order_management_app.models.Productos;

import java.util.ArrayList;

public class ProductosFragment extends Fragment {

    private FragmentProductosBinding binding;
    ConstructorBaseDatos ct;
    BBDD_Order db;
    GeneralModel generalModel;
    private Context context;
    private static ProductosFragment INSTANCE;
    AddProductoDialog addProductoDialog;
    EditProductoDialog editProductoDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        INSTANCE = this;
        binding = FragmentProductosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        this.generalModel = new GeneralModel();
        this.db = new BBDD_Order(getContext());
        this.ct = new ConstructorBaseDatos(getContext(),db,generalModel);
        cargarProductos();
        binding.btnAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductoDialog = new AddProductoDialog(getContext(),db,ct,generalModel);
                addProductoDialog.show();

            }
        });


        return root;
    }
    public static ProductosFragment getInstance() {
        return INSTANCE;
    }

    public void scrollMyListViewToBottom() {
        binding.productoListView.post(new Runnable() {
            @Override
            public void run() {
                binding.productoListView.setSelection(binding.productoListView.getAdapter().getCount()- 1);

            }
        });
    }
    public void cargarProductos(){

        final ProgressDialog dialog = ProgressDialog.show(getContext(), "PROCESANDO", "Se esta procesando su solicitud. Favor espere.", true);

        ArrayList<Productos> productos = ct.cbtenerDatosProductos();
        if(productos != null){
            binding.productoListView.setAdapter(new ProductosAdapter(getContext(), productos));
        }
        dialog.dismiss();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private class ProductosAdapter extends BaseAdapter
    {

        private final Context context;
        private final LayoutInflater inflater;
        private  ArrayList<Productos> productos;

        public ProductosAdapter(Context context, ArrayList<Productos> productos) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.productos = productos;
        }


        @Override
        public int getCount() {
            return productos.size();
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
                convertView =  inflater.inflate(R.layout.item_productos, parent, false);

            ((TextView)convertView.findViewById(R.id.producto_lista_nombre)).setText(this.productos.get(position).getNombre());
            ((TextView)convertView.findViewById(R.id.producto_lista_cantidad)).setText(String.valueOf(this.productos.get(position).getCantidad()));

            ((ImageView)convertView.findViewById(R.id.producto_delete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopUp(position);
                }
            });


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    generalModel.setProductos(productos.get(position));
                    editProductoDialog = new EditProductoDialog(getContext(),db,ct,generalModel);
                    editProductoDialog.show();
                }
            });

            return convertView;
        }

        public void PopUp(int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("¿Realmente desea eliminar este cliente?")
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
                            ct.deleteProducto(productos.get(position).getId());
                            cargarProductos();
                            Toast.makeText(context, "Producto eliminado correctamente", Toast.LENGTH_LONG).show();


                        }
                    });
            builder.show();



        }
    }

}
