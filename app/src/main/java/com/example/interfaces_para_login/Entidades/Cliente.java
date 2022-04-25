package com.example.interfaces_para_login.Entidades;

public class Cliente {
    private int idPersona;
    private String nomPais;
    private String perApellidosNombres;
    private String perFechaNac;
    private String perDni;
    private int perEstado;
    private String perTelefono;
    private String perDireccion;
    private String perSexo;
    private String perCorreo;

    private  int idCliente;
    private String fechaInicio;

    public Cliente(int idPersona, String nomPais, String perApellidosNombres, String perFechaNac, String perDni, int perEstado, String perTelefono, String perDireccion, String perSexo, String perCorreo, int idCliente, String fechaInicio) {
        this.idPersona = idPersona;
        this.nomPais = nomPais;
        this.perApellidosNombres = perApellidosNombres;
        this.perFechaNac = perFechaNac;
        this.perDni = perDni;
        this.perEstado = perEstado;
        this.perTelefono = perTelefono;
        this.perDireccion = perDireccion;
        this.perSexo = perSexo;
        this.perCorreo = perCorreo;
        this.idCliente = idCliente;
        this.fechaInicio = fechaInicio;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNomPais() {
        return nomPais;
    }

    public void setNomPais(String nomPais) {
        this.nomPais = nomPais;
    }

    public String getPerApellidosNombres() {
        return perApellidosNombres;
    }

    public void setPerApellidosNombres(String perApellidosNombres) {
        this.perApellidosNombres = perApellidosNombres;
    }

    public String getPerFechaNac() {
        return perFechaNac;
    }

    public void setPerFechaNac(String perFechaNac) {
        this.perFechaNac = perFechaNac;
    }

    public String getPerDni() {
        return perDni;
    }

    public void setPerDni(String perDni) {
        this.perDni = perDni;
    }

    public int getPerEstado() {
        return perEstado;
    }

    public void setPerEstado(int perEstado) {
        this.perEstado = perEstado;
    }

    public String getPerTelefono() {
        return perTelefono;
    }

    public void setPerTelefono(String perTelefono) {
        this.perTelefono = perTelefono;
    }

    public String getPerDireccion() {
        return perDireccion;
    }

    public void setPerDireccion(String perDireccion) {
        this.perDireccion = perDireccion;
    }

    public String getPerSexo() {
        return perSexo;
    }

    public void setPerSexo(String perSexo) {
        this.perSexo = perSexo;
    }

    public String getPerCorreo() {
        return perCorreo;
    }

    public void setPerCorreo(String perCorreo) {
        this.perCorreo = perCorreo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
