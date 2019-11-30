package com.Kokl.BL.Usuarios.Obj;

public class Cliente extends Usuario {
    private String pais;
    private int identificacion;
    private int edad;

    public Cliente(String nombre, String apellidos, String nombreUsuario, String contrasena, String correoElectronico, boolean contrasenaTemp, String codigoActivacion, String pais, int identificacion, int edad) {
        super(nombre, apellidos, nombreUsuario, contrasena, correoElectronico, codigoActivacion, contrasenaTemp);
        this.pais = pais;
        this.identificacion = identificacion;
        this.edad = edad;
    }

    public String toString() {
        String infoCliente = super.toString() + ", pais de procedencia " + this.pais + ", identificacion " + this.identificacion + ", edad " + this.edad;

        return infoCliente;
    }

    public boolean equals(Cliente cliente){
        boolean err = super.equals(cliente);

        return err;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}