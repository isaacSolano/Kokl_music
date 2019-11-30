package com.Kokl.BL.Usuarios.Obj;

public class Admin extends Usuario {
    public Admin(String nombre, String apellidos, String nombreUsuario, String contrasena, String correoElectronico, String codigoActivacion, boolean contrasenaTemp) {
        super(nombre, apellidos, nombreUsuario, contrasena, correoElectronico, codigoActivacion, contrasenaTemp);
    }

    public String toString() {
        String infoAdmin = super.toString();

        return  infoAdmin;
    }

    public boolean equals(Cliente cliente){
        boolean err = super.equals(cliente);

        return err;
    }
}
