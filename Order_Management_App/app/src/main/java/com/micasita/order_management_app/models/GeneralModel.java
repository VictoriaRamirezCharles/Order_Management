package com.micasita.order_management_app.models;

public class GeneralModel {

    private Empleados empleados;
    private Ordenes ordenes;
    private Asignaciones asignaciones;
    private Productos productos;

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public Ordenes getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(Ordenes ordenes) {
        this.ordenes = ordenes;
    }

    public Asignaciones getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(Asignaciones asignaciones) {
        this.asignaciones = asignaciones;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }
}
