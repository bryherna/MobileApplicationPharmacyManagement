package com.example.interfaces_para_login.Entidades;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String nombreTipoUsuario;
    private String perApellidosNombres;
    private String password_usuario;
    private int estadoUsuario;

    public Usuario(int idUsuario, String nombreUsuario, String nombreTipoUsuario, String perApellidosNombres, String password_usuario, int estadoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombreTipoUsuario = nombreTipoUsuario;
        this.perApellidosNombres = perApellidosNombres;
        this.password_usuario = password_usuario;
        this.estadoUsuario = estadoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public String getPerApellidosNombres() {
        return perApellidosNombres;
    }

    public void setPerApellidosNombres(String perApellidosNombres) {
        this.perApellidosNombres = perApellidosNombres;
    }

    public String getPassword_usuario() {
        return password_usuario;
    }

    public void setPassword_usuario(String password_usuario) {
        this.password_usuario = password_usuario;
    }

    public int getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(int estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }
}
