package com.example.interfaces_para_login.Entidades;

public class Laboratorio {
    private int idLaboratorio;
    private String nomLaboratorio;

    public Laboratorio(int idLaboratorio, String nomLaboratorio) {
        this.idLaboratorio = idLaboratorio;
        this.nomLaboratorio = nomLaboratorio;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNomLaboratorio() {
        return nomLaboratorio;
    }

    public void setNomLaboratorio(String nomLaboratorio) {
        this.nomLaboratorio = nomLaboratorio;
    }
}
