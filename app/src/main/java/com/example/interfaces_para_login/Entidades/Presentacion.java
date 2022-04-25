package com.example.interfaces_para_login.Entidades;

public class Presentacion {
    private int idPresentacion;
    private String nomPresentacion;

    public Presentacion(int idPresentacion, String nomPresentacion) {
        this.idPresentacion = idPresentacion;
        this.nomPresentacion = nomPresentacion;
    }

    public int getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(int idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public String getNomPresentacion() {
        return nomPresentacion;
    }

    public void setNomPresentacion(String nomPresentacion) {
        this.nomPresentacion = nomPresentacion;
    }
}
