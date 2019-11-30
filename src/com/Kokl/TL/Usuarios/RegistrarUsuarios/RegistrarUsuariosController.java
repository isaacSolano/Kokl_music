package com.Kokl.TL.Usuarios.RegistrarUsuarios;

import com.Kokl.BL.Factory.DAOFactory;
import com.Kokl.BL.Usuarios.*;
import com.Kokl.BL.Usuarios.Obj.*;

public class RegistrarUsuariosController {
    private IUsuarioDAO usuarioDAO;
    private DAOFactory factory;

    public RegistrarUsuariosController(){
        factory = DAOFactory.getDaoFactory(1);
        usuarioDAO = factory.getUsuarioDAO();
    }

    public int obtenerCantidadUsuarios() throws Exception {
        int cantidadUsuarios = usuarioDAO.obtenerCantidadUsuarios();

        return cantidadUsuarios;
    }

    public int registrarUsuario(String nombre, String apellidos, String nombreUsuario, String contrasena, String correoElectronico, String codigoActivacion, String pais, int identificacion, int edad, int cantidadUsuarios) throws Exception {
        int err = 0;

        if(edad > 18){
            Usuario nuevoUsuario;

            if(cantidadUsuarios == 0){
                nuevoUsuario = new Admin(nombre, apellidos, nombreUsuario, contrasena, correoElectronico, codigoActivacion, false);
            }else{
                nuevoUsuario = new Cliente(nombre, apellidos, nombreUsuario, contrasena, correoElectronico, false,codigoActivacion, pais, identificacion, edad);
            }

            err = usuarioDAO.registrarUsuario(nuevoUsuario);
        }else{
            err = 1;
        }

        return err;
    }

    public void enviarCodigoActivacion(String correoElectronico, String codigoActivacion) {
//        logica.enviarCodigoActivacion(correoElectronico, codigoActivacion);
    }
}