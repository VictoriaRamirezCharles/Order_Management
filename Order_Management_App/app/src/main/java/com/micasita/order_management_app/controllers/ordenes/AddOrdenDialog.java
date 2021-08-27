    package com.micasita.order_management_app.controllers.ordenes;

    import android.app.Dialog;
    import android.app.ProgressDialog;
    import android.content.Context;
    import android.os.Bundle;
    import android.view.View;
    import android.view.WindowManager;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;

    import com.micasita.order_management_app.R;
    import com.micasita.order_management_app.controllers.productos.ProductosFragment;
    import com.micasita.order_management_app.db.BBDD_Order;
    import com.micasita.order_management_app.db.ConstructorBaseDatos;
    import com.micasita.order_management_app.models.GeneralModel;
    import com.micasita.order_management_app.models.Ordenes;
    import com.micasita.order_management_app.models.Productos;

    import java.text.DateFormat;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Date;

    public class AddOrdenDialog extends Dialog {
        GeneralModel generalModel;
        EditText edt_precio, edt_cantidad, edt_cantidad_producto;
        Spinner productosSpiner;
        ConstructorBaseDatos ct;
        BBDD_Order db;
        Button add_order,terminarOrden;
     ;

        public AddOrdenDialog(@NonNull Context context, BBDD_Order db, ConstructorBaseDatos ct, GeneralModel generalModel) {
            super(context);
            this.generalModel =  generalModel;
            this.db = db;
            this.ct = ct;

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_add_orden);
            setupLayout();
            edt_cantidad_producto = findViewById(R.id.cantidad_producto);
            edt_cantidad = findViewById(R.id.edt_cantidad);
            edt_precio = findViewById(R.id.edt_precio);
            add_order = findViewById(R.id.btnAddOrden);
            productosSpiner = findViewById(R.id.productos_spinner);
            edt_cantidad_producto.setText("5");
            cargarProductos();



            add_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   if(edt_cantidad.getText().toString().isEmpty()){
                       Toast.makeText(getContext(), "No puede tener campos vacios", Toast.LENGTH_SHORT).show();
                   }else{
                       int cantidad = Integer.valueOf(edt_cantidad.getText().toString());
                       int stock = Integer.valueOf(edt_cantidad_producto.getText().toString());
                       if(cantidad!=0){
                           if(stock!=0){
                               if(cantidad>stock){
                                   Toast.makeText(getContext(), "La cantidad solicitada no puede ser mayor al stock", Toast.LENGTH_SHORT).show();
                               }else
                               {
                                   addOrden();
                               }
                           }else{
                               Toast.makeText(getContext(), "Este articulo no esta disponible", Toast.LENGTH_SHORT).show();
                           }

                       }else{
                           Toast.makeText(getContext(), "La cantidad solicitada no puede ser igual a 0", Toast.LENGTH_SHORT).show();
                       }
                   }



                }
            });



            productosSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    ArrayList productos = (ArrayList) productosSpiner.getTag();
                    int productoId = (int) productos.get(productosSpiner.getSelectedItemPosition());
                    Productos producto = ct.obtenerProducto(productoId);
                    edt_precio.setText(producto.getPrecio());
                    edt_cantidad_producto.setText(String.valueOf(producto.getCantidad()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {

                }

            });
        }
        private void setupLayout() {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            getWindow().setAttributes(lp);
        }
        public void cargarProductos(){

            ArrayList<Productos> productos = ct.cbtenerDatosProductos();
            ArrayList productsName = new ArrayList();
            ArrayList ids = new ArrayList();
            for (int i = 0; i < productos.size(); i++) {
                productsName.add(productos.get(i).getNombre());
                ids.add(productos.get(i).getId());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_spinner_item, productsName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            productosSpiner.setAdapter(adapter);
            productosSpiner.setTag(ids);

        }

        private void addOrden(){

                Ordenes ordenes = new Ordenes();
                ordenes.setStatus(1);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                ordenes.setFecha(dateFormat.format(date));
                generalModel.setOrdenes(ordenes);
                ct.insertarOrden();

                ArrayList productosIds = (ArrayList) productosSpiner.getTag();
                int productoId = (int) productosIds.get(productosSpiner.getSelectedItemPosition());
                Ordenes orden = ct.obtenerOorden();
                orden.setCantidad(Integer.valueOf(edt_cantidad.getText().toString()));
                orden.setPrecio(edt_precio.getText().toString());
                orden.setId(orden.getId());
                orden.setIdProducto(productoId);
                generalModel.setOrdenes(orden);
                ct.insertarOrdenDetalle();
                Productos producto = ct.obtenerProducto(productoId);
                producto.setCantidad(producto.getCantidad()-Integer.valueOf(edt_cantidad.getText().toString()));
                generalModel.setProductos(producto);
                ct.updateProducto();
                OrdenesFragment.getInstance().cargarOrdenes();
                OrdenesFragment.getInstance().addOrdenDialog.dismiss();
                OrdenesFragment.getInstance().scrollMyListViewToBottom();

        }

    }
