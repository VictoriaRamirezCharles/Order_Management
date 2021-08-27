package com.micasita.order_management_app.models;

public class Asignaciones {

    private int Id;
    private int OrdenId;
    private int EmpleadoId;
    private String EmpleadoNombre;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getOrdenId() {
        return OrdenId;
    }

    public void setOrdenId(int ordenId) {
        OrdenId = ordenId;
    }

    public int getEmpleadoId() {
        return EmpleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        EmpleadoId = empleadoId;
    }

    public String getEmpleadoNombre() {
        return EmpleadoNombre;
    }

    public void setEmpleadoNombre(String empleadoNombre) {
        EmpleadoNombre = empleadoNombre;
    }

    public String getCodigoOrden() {
        return CodigoOrden;
    }

    public void setCodigoOrden(String codigoOrden) {
        CodigoOrden = codigoOrden;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    private String CodigoOrden;
    private String Fecha;
}
