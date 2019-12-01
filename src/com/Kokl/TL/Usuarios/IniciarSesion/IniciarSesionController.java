package com.Kokl.TL.Usuarios.IniciarSesion;

import com.Kokl.BL.Factory.DAOFactory;
import com.Kokl.BL.Usuarios.*;
import com.Kokl.BL.Usuarios.Obj.*;

public class IniciarSesionController {
    private IUsuarioDAO usuarioDAO;
    private DAOFactory factory;

    public IniciarSesionController(){
        factory = DAOFactory.getDaoFactory(1);
        usuarioDAO = factory.getUsuarioDAO();
    }
    public int iniciarSesion(String nombreUsuario, String contrasena) throws Exception {
        int err = 0;
        Usuario getUsuario = usuarioDAO.getById(nombreUsuario);

        if(getUsuario == null){
            err= 1;
        }else{
            if(!getUsuario.getContrasena().equals(contrasena)){
                err = 2;
            }
        }

        return err;
    }
}
