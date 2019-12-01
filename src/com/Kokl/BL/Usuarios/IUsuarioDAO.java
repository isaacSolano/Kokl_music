package com.Kokl.BL.Usuarios;

import com.Kokl.BL.Usuarios.Obj.Admin;
import com.Kokl.BL.Usuarios.Obj.Usuario;

import java.util.ArrayList;

public interface IUsuarioDAO {
//    declarar los metodos de consulta que deben ser implementados en las clases que lo `implementen`

	public int registrarUsuario(Usuario usuario) throws Exception;
	public Usuario getById(String nombreUsuario) throws Exception;
	public void modificarUsuario(Usuario usuario) throws Exception;
	public ArrayList<Usuario> getUsuarios()throws Exception;;
	public int getCantidadUsuarios() throws Exception;
	public Admin getAdmin() throws Exception;
}
