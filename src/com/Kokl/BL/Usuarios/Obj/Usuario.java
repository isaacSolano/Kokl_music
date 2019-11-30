package com.Kokl.BL.Usuarios.Obj;

public class Usuario {
    private String nombre;
    private String apellidos;
    private String nombreUsuario;
    private String contrasena;
    private String correoElectronico;
    private String codigoActivacion;
    private boolean contrasenaTemp;

    public Usuario(String nombre, String apellidos, String nombreUsuario, String contrasena, String correoElectronico, String codigoActivacion, boolean contrasenaTemp) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.codigoActivacion = codigoActivacion;
        this.contrasenaTemp = contrasenaTemp;
    }

    public String toString() {
        String infoUsuario = "Nombre: " + this.nombre + ", apellidos: " + this.apellidos + ", nombre de usuario: " +  this.nombreUsuario + ", correo electronico: " + this.correoElectronico + ", codigo de activacion: " + this.codigoActivacion;

        return infoUsuario;
    }

    public boolean equals(Usuario usuario){
        boolean err = false;

        if(this.nombreUsuario.equals(usuario.getNombreUsuario())){
            err = true;
        }

        return err;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCodigoActivacion() {
        return codigoActivacion;
    }

    public void setCodigoActivacion(String codigoActivacion) {
        this.codigoActivacion = codigoActivacion;
    }

    public boolean getContrasenaTemp() {
        return contrasenaTemp;
    }

    public void setContrasenaTemp(boolean contrasenaTemp) {
        this.contrasenaTemp = contrasenaTemp;
    }
}
