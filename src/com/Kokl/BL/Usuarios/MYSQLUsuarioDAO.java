package com.Kokl.BL.Usuarios;

import com.Kokl.BL.Usuarios.Obj.*;
import com.Kokl.DL.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MYSQLUsuarioDAO implements IUsuarioDAO{
	private String req;

	public int registrarUsuario(Usuario usuario) throws Exception {
		int err = 0;

		Usuario usuarioEncontrado = getById(usuario.getNombreUsuario());

		if(usuarioEncontrado == null){
			if(usuario instanceof Cliente){
				Cliente usuarioCliente = (Cliente) usuario;
				req = "INSERT INTO kokl.cliente (nombreUsuario, nombre, apellidos, contrasena, correoElectronico, codigoActivacion, contrasenaTemp, pais, identificacion, edad) VALUES('"+usuarioCliente.getNombreUsuario()+"', '"+usuarioCliente.getNombre()+"', '"+usuarioCliente.getApellidos()+"','"+usuarioCliente.getContrasena()+"', '"+usuarioCliente.getCorreoElectronico()+"', '"+usuarioCliente.getCodigoActivacion()+"', "+usuarioCliente.getContrasenaTemp()+", '"+usuarioCliente.getPais()+"', "+usuarioCliente.getIdentificacion()+", "+usuarioCliente.getEdad()+")";
			}else if(usuario instanceof Admin){
				Admin usuarioAdmin = (Admin) usuario;
				req = "INSERT INTO kokl.admin (nombre, apellidos, nombreUsuario, contrasena, correoElectronico, codigoActivacion, contrasenaTemp) VALUES('"+usuarioAdmin.getNombre()+"', '"+usuarioAdmin.getApellidos()+"', '"+usuarioAdmin.getNombreUsuario()+"','"+usuarioAdmin.getContrasena()+"', '"+usuarioAdmin.getCorreoElectronico()+"', '"+usuarioAdmin.getCodigoActivacion()+"', "+usuarioAdmin.getContrasenaTemp()+")";
			}
			Connector.getConnector().POST(req);
		}else{
			err = 2;
		}

		return err;
	}

	public Usuario getById(String nombreUsuario) throws Exception {
		Usuario usuarioEncontrado = null;
		ArrayList<Usuario> listaUsuarios = getUsuarios();

		if(listaUsuarios != null){
			for(Usuario usuario : listaUsuarios){
				if(usuario.getNombreUsuario().equals(nombreUsuario)){
					usuarioEncontrado = usuario;
				}
			}
		}

		return usuarioEncontrado;
	}

	public void modificarUsuario(Usuario usuario) throws Exception {
		if(usuario instanceof Cliente){
			Cliente usuarioCliente = (Cliente) usuario;

			req = "UPDATE kokl.cliente SET nombre = '"+usuarioCliente.getNombre()+"', apellidos = '"+usuarioCliente.getApellidos()+"', contrasena = '"+usuarioCliente.getContrasena()+"', `correoElectronico` = '"+usuarioCliente.getCorreoElectronico()+"', codigoActivacion = '"+usuarioCliente.getCodigoActivacion()+"', contrasenaTemp = "+usuarioCliente.getContrasenaTemp()+", pais = '"+usuarioCliente.getPais()+"', identificacion = "+usuarioCliente.getIdentificacion()+", edad = "+usuarioCliente.getEdad()+" WHERE nombreUsuario = '"+usuarioCliente.getNombreUsuario()+"'";
			Connector.getConnector().POST(req);
		}else if(usuario instanceof Admin){
			Admin usuarioAdmin = (Admin) usuario;

			req = "UPDATE kokl.admin SET nombre = '"+usuarioAdmin.getNombre()+"', apellidos = '"+usuarioAdmin.getApellidos()+"', contrasena = '"+usuarioAdmin.getContrasena()+"', `correoElectronico` = '"+usuarioAdmin.getCorreoElectronico()+"', codigoActivacion = '"+usuarioAdmin.getCodigoActivacion()+"', contrasenaTemp = "+usuarioAdmin.getContrasenaTemp()+" WHERE nombreUsuario = '"+usuarioAdmin.getNombreUsuario()+"'";
			Connector.getConnector().POST(req);
		}
	}

	public ArrayList<Usuario> getUsuarios() throws Exception {
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		req = "SELECT * FROM kokl.cliente";

		ResultSet res = Connector.getConnector().GET(req);
		Usuario usuario;

		while(res.next()){
			usuario = new Cliente(res.getString("nombre"), res.getString("apellidos"), res.getString("nombreUsuario"), res.getString("contrasena"), res.getString("correoElectronico"), res.getBoolean("contrasenaTemp"), res.getString("codigoActivacion"), res.getString("pais"), res.getInt("identificacion"), res.getInt("edad"));
			listaUsuarios.add(usuario);
		}

		req = "SELECT * FROM kokl.admin";
		res = Connector.getConnector().GET(req);

		while(res.next()){
			usuario = new Admin(res.getString("nombre"), res.getString("apellidos"), res.getString("nombreUsuario"), res.getString("contrasena"), res.getString("correoElectronico"), res.getString("codigoActivacion"), res.getBoolean("contrasenaTemp"));
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}

	public int getCantidadUsuarios() throws Exception{
		int cantidadUsuarios = getUsuarios().size();

		return cantidadUsuarios;
	}

	public Admin getAdmin() throws Exception {
		Admin admin = null;
		ArrayList<Usuario> listaUsuarios = getUsuarios();

		for(Usuario usuario : listaUsuarios){
			if(usuario instanceof Admin){
				admin = (Admin) usuario;
			}
		}

		return admin;
	}
}