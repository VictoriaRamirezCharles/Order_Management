package com.micasita.order_management_app.controllers.productos;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.micasita.order_management_app.R;
import com.micasita.order_management_app.db.BBDD_Order;
import com.micasita.order_management_app.db.ConstructorBaseDatos;
import com.micasita.order_management_app.models.GeneralModel;
import com.micasita.order_management_app.models.Productos;

public class EditProductoDialog extends Dialog {
    GeneralModel generalModel;
    EditText edt_nombre, edt_codigo, edt_precio,edt_canttidad,edt_descripcion;
    ConstructorBaseDatos ct;
    BBDD_Order db;
    Button edit_producto;

    public EditProductoDialog(@NonNull Context context, BBDD_Order db, ConstructorBaseDatos ct, GeneralModel generalModel) {
        super(context);
        this.generalModel =  generalModel;
        this.db = db;
        this.ct = ct;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_producto);
        setupLayout();
        edt_nombre = findViewById(R.id.ed_nombre_producto_edit);
        edt_canttidad = findViewById(R.id.cantidad_producto_edit);
        edt_descripcion = findViewById(R.id.ed_descripcion_producto_edit);
        edt_precio = findViewById(R.id.ed_precio_producto_edit);
        edit_producto = findViewById(R.id.btn_edit_producto_data);

        edt_nombre.setText(generalModel.getProductos().getNombre());
        edt_canttidad.setText(String.valueOf(generalModel.getProductos().getCantidad()));
        edt_descripcion.setText(generalModel.getProductos().getDescripcion());
        edt_precio.setText(generalModel.getProductos().getPrecio());

        edit_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProducto();
            }
        });
    }
    private void setupLayout() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
    }


    private void editProducto() {
        if(edt_nombre.getText().toString().isEmpty()
                || edt_precio.getText().toString().isEmpty()
                || edt_descripcion.getText().toString().isEmpty()
                ||  edt_canttidad.getText().toString().isEmpty()   ){

            Toast.makeText(getContext(), "No puede tener campos vacios", Toast.LENGTH_SHORT).show();

        } else {
            generalModel.getProductos().setId(generalModel.getProductos().getId());
            generalModel.getProductos().setNombre(edt_nombre.getText().toString());
            generalModel.getProductos().setDescripcion(edt_descripcion.getText().toString());
            generalModel.getProductos().setPrecio(edt_precio.getText().toString());
            generalModel.getProductos().setCantidad(Integer.valueOf(edt_canttidad.getText().toString()));
            ct.updateProducto();
            ProductosFragment.getInstance().cargarProductos();
            ProductosFragment.getInstance().editProductoDialog.dismiss();

        }
    }
}
