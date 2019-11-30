package com.Kokl.TL.Usuarios.Perfil;

import com.Kokl.BL.Factory.DAOFactory;
import com.Kokl.BL.Usuarios.IUsuarioDAO;
import com.Kokl.BL.Usuarios.Obj.*;

public class PerfilController {
	private IUsuarioDAO usuarioDAO;
	private DAOFactory factory;

    Usuario usuarioActivo = null;

    public PerfilController(){
		factory = DAOFactory.getDaoFactory(1);
		usuarioDAO = factory.getUsuarioDAO();
	}

    public int setUsuarioActivo(String nombreUsuarioActivo) throws Exception {
        int instance = 0;
        usuarioActivo = usuarioDAO.obtenerById(nombreUsuarioActivo);

        if(usuarioActivo instanceof Admin){
            instance = 1;
        }

        return instance;
    }

    public Usuario getUsuarioActivo(){
		return usuarioActivo;
	}

    public void cerrarSesion() {
        usuarioActivo = null;
    }

    public String obtenerCodigoVerificacion() {
        String codigoVerificacion = usuarioActivo.getCodigoActivacion();

        return codigoVerificacion;
    }

    public boolean verificarCodigoActivacion(String inputCodigoActivacion) throws Exception {
        boolean err = false;

        if(usuarioActivo.getCodigoActivacion().equals(inputCodigoActivacion)){
            usuarioActivo.setCodigoActivacion("Verificado");

            usuarioDAO.modificarUsuario(usuarioActivo);
        }else{
            err = true;
        }

        return err;
    }

	public boolean cambiarContrasenaTemp(String inputNombreUsuario, String contrasenaTemp) throws Exception {
    	boolean err = false;

    	if(usuarioActivo.getNombreUsuario().equals(inputNombreUsuario)){
			usuarioActivo.setContrasena(contrasenaTemp);
			usuarioActivo.setContrasenaTemp(!usuarioActivo.getContrasenaTemp());

			usuarioDAO.modificarUsuario(usuarioActivo);
		}else{
    		err = true;
		}

    	return err;
	}

	public boolean cambiarNuevaContrasena(String contrasenaTemp, String nuevaContrasena) throws Exception {
		boolean err = false;

		if(!usuarioActivo.getContrasena().equals(contrasenaTemp)){
			err = true;
		}else{
			usuarioActivo.setContrasena(nuevaContrasena);
			usuarioActivo.setContrasenaTemp(!usuarioActivo.getContrasenaTemp());

			usuarioDAO.modificarUsuario(usuarioActivo);
		}

		return err;
    }
}