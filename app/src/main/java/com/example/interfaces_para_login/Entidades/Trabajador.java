package com.example.interfaces_para_login.Entidades;

public class Trabajador {
    private int idTrabajador;
    private String perDni;

    public Trabajador(int idTrabajador, String perDni) {
        this.idTrabajador = idTrabajador;
        this.perDni = perDni;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getPerDni() {
        return perDni;
    }

    public void setPerDni(String perDni) {
        this.perDni = perDni;
    }
}
