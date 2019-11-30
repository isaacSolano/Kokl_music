package com.Kokl.UI.Perfil;

import com.Kokl.TL.Usuarios.Perfil.PerfilController;
import com.Kokl.TL.Canciones.RegistrarCanciones.RegistrarCancionesController;

import com.Kokl.UI.Rutas;
import com.Kokl.UI.Usuarios.Navegacion.Navegacion;

public class Perfil {
	protected static PerfilController gestorPerfilUsuarios = new PerfilController();
	protected static RegistrarCancionesController gestorRegistroCanciones = new RegistrarCancionesController();

	protected static Navegacion navegacion = new Navegacion();
	protected static Rutas rutas = new Rutas();
}
