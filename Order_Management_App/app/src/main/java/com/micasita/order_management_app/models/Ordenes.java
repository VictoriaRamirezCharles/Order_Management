package com.micasita.order_management_app.models;

public class Ordenes {

    private int Id;
    private String Fecha;
    private String Precio;
    private String Codigo;
    private int Cantidad;
    private int OrdenDetalleId;

    public int getOrdenDetalleId() {
        return OrdenDetalleId;
    }

    public void setOrdenDetalleId(int ordenDetalleId) {
        OrdenDetalleId = ordenDetalleId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    private int Status;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    private int IdProducto;
    private  String NombreProducto;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }
}
