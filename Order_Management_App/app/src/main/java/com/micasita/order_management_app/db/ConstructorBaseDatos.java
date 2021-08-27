package com.micasita.order_management_app.db;

import android.content.ContentValues;
import android.content.Context;

import com.micasita.order_management_app.models.Asignaciones;
import com.micasita.order_management_app.models.Empleados;
import com.micasita.order_management_app.models.GeneralModel;
import com.micasita.order_management_app.models.Ordenes;
import com.micasita.order_management_app.models.Productos;

import java.util.ArrayList;

public class ConstructorBaseDatos {

    private Context context;
    private GeneralModel generalModel;
    private  BBDD_Order db;

    public ConstructorBaseDatos(Context context,BBDD_Order db, GeneralModel generalModel) {

        this.context = context;
        this.generalModel = generalModel;
        this.db = db;
    }

    public ArrayList<Ordenes> cbtenerDatosOrdenes() {
        return db.obtenerOrdenes();
    }

    public ArrayList<Productos> cbtenerDatosProductos() {

        return db.obtenerProductos();
    }
    public ArrayList<Asignaciones> cbtenerDatosAsignaciones() {

        return db.obtenerAsignaciones();
    }
    public Ordenes obtenerOorden() {

        return db.obtenerOrden();
    }
    public Productos obtenerProducto(int id) {

        return db.obtenerProducto(id);
    }

    public void insertarEmpleado() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_CODIGO, generalModel.getEmpleados().getCodigo());
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_NOMBRE, generalModel.getEmpleados().getNombre());
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_DIRECCION, generalModel.getEmpleados().getDireccion());
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_TELEFONO, generalModel.getEmpleados().getTelefono());
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_EMAIL, generalModel.getEmpleados().getEmail());
        db.insertarEmpleados(contentValues);
    }
    public void insertarProducto() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TBL_PRODUCTOS_NOMBRE, generalModel.getProductos().getNombre());
        contentValues.put(ConstantesBaseDatos.TBL_PRODUCTOS_CANTIDAD, generalModel.getProductos().getCantidad());
        contentValues.put(ConstantesBaseDatos.TBL_PRODUCTOS_PRECIO, generalModel.getProductos().getPrecio());
        contentValues.put(ConstantesBaseDatos.TBL_PRODUCTOS_DESCRIPCION, generalModel.getProductos().getDescripcion());
        db.insertarProducto(contentValues);
    }
    public void insertarOrden() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_FECHA, generalModel.getOrdenes().getFecha());
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_STATUS, generalModel.getOrdenes().getStatus());
        db.insertarOrden(contentValues);
    }
    public void insertarOrdenDetalle() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_DETALLE_ORDEN_ID, generalModel.getOrdenes().getId());
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_DETALLE_PRECIO, generalModel.getOrdenes().getPrecio());
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_DETALLE_PRODUCTO_ID, generalModel.getOrdenes().getIdProducto());
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_DETALLE_CANTIDAD, generalModel.getOrdenes().getCantidad());
        db.insertarOrdenDetalle(contentValues);
    }

    public void insertarAsignacion() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TBL_ASIGNACIONES_EMPLEADOID, generalModel.getAsignaciones().getEmpleadoId());
        contentValues.put(ConstantesBaseDatos.TBL_ASIGNACIONES_FECHA, generalModel.getAsignaciones().getFecha());
        contentValues.put(ConstantesBaseDatos.TBL_ASIGNACIONES_ORDENID, generalModel.getAsignaciones().getOrdenId());
        contentValues.put(ConstantesBaseDatos.TBL_ASIGNACIONES_EMPLEADO_NOMBRE, generalModel.getAsignaciones().getEmpleadoNombre());
        db.insertarAsignacion(contentValues);
    }

    public void deleteEmpleado(int id){
        db.deleteEmpleado(id);
    }

    public void deleteOrden(int id){
        db.deleteOrden(id);
    }
    public void deleteAsignacion(int id){
        db.deleteAsignacion(id);
    }

    public void deleteProducto(int id){
        db.deleteProducto(id);
    }

    public void updateEmpleado() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_ID,generalModel.getEmpleados().getIdEmpleado());
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_CODIGO, generalModel.getEmpleados().getCodigo());
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_NOMBRE, generalModel.getEmpleados().getNombre());
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_DIRECCION, generalModel.getEmpleados().getDireccion());
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_TELEFONO, generalModel.getEmpleados().getTelefono());
        contentValues.put(ConstantesBaseDatos.TBL_EMPLEADOS_EMAIL, generalModel.getEmpleados().getEmail());
        db.updateEmpleado(contentValues);

    }
    public void updateOrden() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_ID, generalModel.getOrdenes().getId());
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_STATUS, generalModel.getOrdenes().getStatus());
        db.updateOrden(contentValues);

    }
    public void updateOrdenDetalle() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_DETALLE_ID, generalModel.getOrdenes().getOrdenDetalleId());
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_DETALLE_ORDEN_ID, generalModel.getOrdenes().getId());
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_DETALLE_PRECIO, generalModel.getOrdenes().getPrecio());
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_DETALLE_PRODUCTO_ID, generalModel.getOrdenes().getIdProducto());
        contentValues.put(ConstantesBaseDatos.TBL_ORDENES_DETALLE_CANTIDAD, generalModel.getOrdenes().getCantidad());
        db.updateOrdenDetalle(contentValues);

    }
    public void updateProducto() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TBL_PRODUCTOS_ID, generalModel.getProductos().getId());
        contentValues.put(ConstantesBaseDatos.TBL_PRODUCTOS_NOMBRE, generalModel.getProductos().getNombre());
        contentValues.put(ConstantesBaseDatos.TBL_PRODUCTOS_CANTIDAD, generalModel.getProductos().getCantidad());
        contentValues.put(ConstantesBaseDatos.TBL_PRODUCTOS_PRECIO, generalModel.getProductos().getPrecio());
        contentValues.put(ConstantesBaseDatos.TBL_PRODUCTOS_DESCRIPCION, generalModel.getProductos().getDescripcion());
        db.updateProducto(contentValues);

    }

    public void updateAsignacion() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TBL_ASIGNACIONES_ID, generalModel.getAsignaciones().getId());
        contentValues.put(ConstantesBaseDatos.TBL_ASIGNACIONES_EMPLEADOID, generalModel.getAsignaciones().getEmpleadoId());
        contentValues.put(ConstantesBaseDatos.TBL_ASIGNACIONES_FECHA, generalModel.getAsignaciones().getFecha());
        contentValues.put(ConstantesBaseDatos.TBL_ASIGNACIONES_ORDENID, generalModel.getAsignaciones().getOrdenId());
        db.updateAsignacion(contentValues);

    }
}
