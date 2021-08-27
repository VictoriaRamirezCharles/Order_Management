package com.micasita.order_management_app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.micasita.order_management_app.models.Asignaciones;
import com.micasita.order_management_app.models.Ordenes;
import com.micasita.order_management_app.models.Productos;

import java.util.ArrayList;

public class BBDD_Order extends SQLiteOpenHelper {

    public BBDD_Order(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATEBASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryCrearTablaAsignaciones = "CREATE TABLE "         + ConstantesBaseDatos.TBL_ASIGNACIONES + "(" +
                ConstantesBaseDatos.TBL_ASIGNACIONES_ID              + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TBL_ASIGNACIONES_ORDENID         + " INTEGER,  " +
                ConstantesBaseDatos.TBL_ASIGNACIONES_EMPLEADOID      + " INTEGER,  " +
                ConstantesBaseDatos.TBL_ASIGNACIONES_FECHA           + " TEXT,  " +
                ConstantesBaseDatos.TBL_ASIGNACIONES_EMPLEADO_NOMBRE + " TEXT  " +
                ")";

        String queryCrearTablaOrdenes = "CREATE TABLE "              + ConstantesBaseDatos.TBL_ORDENES + "(" +
                ConstantesBaseDatos.TBL_ORDENES_ID                   + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TBL_ORDENES_FECHA                + " TEXT,  " +
                ConstantesBaseDatos.TBL_ORDENES_STATUS               + " INTEGER  " +
                ")";

        String queryCrearTablaOrdenesDetalle = "CREATE TABLE "       + ConstantesBaseDatos.TBL_ORDENES_DETALLE + "(" +
                ConstantesBaseDatos.TBL_ORDENES_DETALLE_ID           + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TBL_ORDENES_DETALLE_ORDEN_ID     + " INTEGER,  " +
                ConstantesBaseDatos.TBL_ORDENES_DETALLE_PRODUCTO_ID  + " INTEGER,  " +
                ConstantesBaseDatos.TBL_ORDENES_DETALLE_PRECIO       + " TEXT,  " +
                ConstantesBaseDatos.TBL_ORDENES_DETALLE_CANTIDAD     + " TEXT,  " +
                "CONSTRAINT fk_ordenes" +
                " FOREIGN KEY("+ConstantesBaseDatos.TBL_ORDENES_DETALLE_ORDEN_ID+")"+
                "REFERENCES "+ConstantesBaseDatos.TBL_ORDENES+"("+ConstantesBaseDatos.TBL_ORDENES_ID+")"+
                "ON DELETE CASCADE"+
                ")";

        String queryCrearTablaProductos = "CREATE TABLE "            + ConstantesBaseDatos.TBL_PRODUCTOS + "(" +
                ConstantesBaseDatos.TBL_PRODUCTOS_ID                 + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TBL_PRODUCTOS_NOMBRE             + " TEXT,  " +
                ConstantesBaseDatos.TBL_PRODUCTOS_DESCRIPCION        + " TEXT,  " +
                ConstantesBaseDatos.TBL_PRODUCTOS_CANTIDAD           + " INTEGER,  " +
                ConstantesBaseDatos.TBL_PRODUCTOS_PRECIO             + " TEXT  " +
                ")";

        String queryCrearTablaEmpleados = "CREATE TABLE "            + ConstantesBaseDatos.TBL_EMPLEADOS + "(" +
                ConstantesBaseDatos.TBL_EMPLEADOS_ID                 + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TBL_EMPLEADOS_NOMBRE             + " TEXT,  " +
                ConstantesBaseDatos.TBL_EMPLEADOS_DIRECCION          + " TEXT,  " +
                ConstantesBaseDatos.TBL_EMPLEADOS_TELEFONO           + " TEXT,  " +
                ConstantesBaseDatos.TBL_EMPLEADOS_EMAIL              + " TEXT  " +
                ")";

        sqLiteDatabase.execSQL(queryCrearTablaAsignaciones);
        sqLiteDatabase.execSQL(queryCrearTablaOrdenes);
        sqLiteDatabase.execSQL(queryCrearTablaOrdenesDetalle);
        sqLiteDatabase.execSQL(queryCrearTablaProductos);
        sqLiteDatabase.execSQL(queryCrearTablaEmpleados);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TBL_PRODUCTOS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TBL_ORDENES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TBL_ASIGNACIONES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TBL_ORDENES_DETALLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TBL_EMPLEADOS);
    }

    public void insertarOrden(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TBL_ORDENES, null, contentValues);
        db.close();
    }

    public void insertarOrdenDetalle(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TBL_ORDENES_DETALLE, null, contentValues);
        db.close();
    }

    public void insertarProducto(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TBL_PRODUCTOS, null, contentValues);
        db.close();
    }

    public void insertarAsignacion(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TBL_ASIGNACIONES, null, contentValues);
        db.close();
    }

    public void insertarEmpleados(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TBL_EMPLEADOS, null, contentValues);
        db.close();
    }
    public ArrayList<Ordenes> obtenerOrdenes() {

        ArrayList<Ordenes> ordenes = new ArrayList<>();

        String query = "SELECT o."+  ConstantesBaseDatos.TBL_ORDENES_ID+",o."+ConstantesBaseDatos.TBL_ORDENES_FECHA+",od."+ConstantesBaseDatos.TBL_ORDENES_DETALLE_PRECIO+",od."+ConstantesBaseDatos.TBL_ORDENES_DETALLE_CANTIDAD+",o."+ConstantesBaseDatos.TBL_ORDENES_STATUS+",p."+ConstantesBaseDatos.TBL_PRODUCTOS_NOMBRE+" FROM "+ConstantesBaseDatos.TBL_ORDENES+" o INNER JOIN "+ConstantesBaseDatos.TBL_ORDENES_DETALLE+" od on o."+ConstantesBaseDatos.TBL_ORDENES_ID+" = od."+   ConstantesBaseDatos.TBL_ORDENES_DETALLE_ORDEN_ID+"" +
                                            " INNER JOIN "+ConstantesBaseDatos.TBL_PRODUCTOS+" p on od."+ConstantesBaseDatos.TBL_ORDENES_DETALLE_PRODUCTO_ID+" = p."+ConstantesBaseDatos.TBL_PRODUCTOS_ID+"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Ordenes ordenActual = new Ordenes();
            ordenActual.setId(registros.getInt(0));
            ordenActual.setFecha(registros.getString(1));
            ordenActual.setPrecio(registros.getString(2));
            ordenActual.setCantidad(registros.getInt(3));
            ordenActual.setNombreProducto(registros.getString(5));
            ordenActual.setStatus(registros.getInt(4));
            ordenes.add(ordenActual);
        }

        return ordenes;
    }

    public ArrayList<Productos> obtenerProductos() {

        ArrayList<Productos> productos = new ArrayList<>();

        String query = "SELECT * FROM "+ConstantesBaseDatos.TBL_PRODUCTOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Productos productoActual = new Productos();
            productoActual.setId(registros.getInt(0));
            productoActual.setNombre(registros.getString(1));
            productoActual.setDescripcion(registros.getString(2));
            productoActual.setPrecio(registros.getString(4));
            productoActual.setCantidad(registros.getInt(3));


            productos.add(productoActual);
        }

        return productos;
    }
    public Ordenes obtenerOrden() {

        String query = "SELECT * FROM "+ConstantesBaseDatos.TBL_ORDENES +" ORDER BY "+ConstantesBaseDatos.TBL_ORDENES_ID+" DESC LIMIT 1;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        Ordenes ordenActual = new Ordenes();
        while(registros.moveToNext()) {

            ordenActual.setId(registros.getInt(0));
            ordenActual.setFecha(registros.getString(1));
            ordenActual.setStatus(registros.getInt(2));
        }
        return ordenActual;
    }

    public Productos obtenerProducto(int id) {

        String query = "SELECT * FROM "+ConstantesBaseDatos.TBL_PRODUCTOS +" WHERE "+ConstantesBaseDatos.TBL_PRODUCTOS_ID+"="+id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        Productos productoActual = new Productos();
        while(registros.moveToNext()) {

            productoActual.setId(registros.getInt(0));
            productoActual.setNombre(registros.getString(1));
            productoActual.setDescripcion(registros.getString(2));
            productoActual.setCantidad(registros.getInt(3));
            productoActual.setPrecio(registros.getString(4));
        }
        return productoActual;
    }

    public ArrayList<Asignaciones> obtenerAsignaciones() {

        ArrayList<Asignaciones> asignaciones = new ArrayList<>();

        String query = "SELECT * FROM "+ConstantesBaseDatos.TBL_ASIGNACIONES+"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Asignaciones asignacionActual = new Asignaciones();
            asignacionActual.setId(registros.getInt(0));
            asignacionActual.setOrdenId(registros.getInt(1));
            asignacionActual.setEmpleadoId(registros.getInt(2));
            asignacionActual.setEmpleadoNombre(registros.getString(4));
            asignaciones.add(asignacionActual);
        }

        return asignaciones;
    }

    public boolean deleteProducto(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ConstantesBaseDatos.TBL_PRODUCTOS, ConstantesBaseDatos.TBL_PRODUCTOS_ID + "=" + id, null) > 0;
    }

    public boolean deleteOrden(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ConstantesBaseDatos.TBL_ORDENES, ConstantesBaseDatos.TBL_ORDENES_ID + "=" + id, null) > 0;
    }

    public boolean deleteAsignacion(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ConstantesBaseDatos.TBL_ASIGNACIONES, ConstantesBaseDatos.TBL_ASIGNACIONES_ID + "=" + id, null) > 0;
    }

    public boolean deleteEmpleado(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ConstantesBaseDatos.TBL_EMPLEADOS, ConstantesBaseDatos.TBL_EMPLEADOS_ID + "=" + id, null) > 0;
    }

    public boolean updateProducto(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(ConstantesBaseDatos.TBL_PRODUCTOS, contentValues, ConstantesBaseDatos.TBL_PRODUCTOS_ID + "=" + contentValues.getAsInteger(ConstantesBaseDatos.TBL_PRODUCTOS_ID), null) > 0;
    }


    public boolean updateAsignacion(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(ConstantesBaseDatos.TBL_ASIGNACIONES, contentValues, ConstantesBaseDatos.TBL_ASIGNACIONES_ID + "=" + contentValues.getAsInteger(ConstantesBaseDatos.TBL_PRODUCTOS_ID), null) > 0;
    }
    public boolean updateOrden(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(ConstantesBaseDatos.TBL_ORDENES, contentValues, ConstantesBaseDatos.TBL_ORDENES_ID + "=" + contentValues.getAsInteger(ConstantesBaseDatos.TBL_ORDENES_ID), null) > 0;
    }
    public boolean updateOrdenDetalle(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(ConstantesBaseDatos.TBL_ORDENES_DETALLE, contentValues, ConstantesBaseDatos.TBL_ORDENES_DETALLE_ID + "=" + contentValues.getAsInteger(ConstantesBaseDatos.TBL_ORDENES_DETALLE_ID), null) > 0;
    }
    public boolean updateEmpleado(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(ConstantesBaseDatos.TBL_EMPLEADOS, contentValues, ConstantesBaseDatos.TBL_EMPLEADOS_ID + "=" + contentValues.getAsInteger(ConstantesBaseDatos.TBL_EMPLEADOS_ID), null) > 0;
    }
}