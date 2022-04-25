package com.example.interfaces_para_login.Entidades;

public class Producto {
    private int idProducto;
    private String nomPresentacion;
    private String nomLaboratorio;
    private String nomProducto;
    private Double precioVenta;
    private String descripcion;
    private int stock;
    private String caducidad;
    private String lote;

    public Producto(int idProducto, String nomPresentacion, String nomLaboratorio, String nomProducto, Double precioVenta, String descripcion, int stock, String caducidad, String lote) {
        this.idProducto = idProducto;
        this.nomPresentacion = nomPresentacion;
        this.nomLaboratorio = nomLaboratorio;
        this.nomProducto = nomProducto;
        this.precioVenta = precioVenta;
        this.descripcion = descripcion;
        this.stock = stock;
        this.caducidad = caducidad;
        this.lote = lote;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNomPresentacion() {
        return nomPresentacion;
    }

    public void setNomPresentacion(String nomPresentacion) {
        this.nomPresentacion = nomPresentacion;
    }

    public String getNomLaboratorio() {
        return nomLaboratorio;
    }

    public void setNomLaboratorio(String nomLaboratorio) {
        this.nomLaboratorio = nomLaboratorio;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
