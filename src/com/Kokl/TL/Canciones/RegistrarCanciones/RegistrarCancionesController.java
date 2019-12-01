package com.Kokl.TL.Canciones.RegistrarCanciones;

import com.Kokl.BL.Canciones.Obj.Cancion;
import com.Kokl.BL.Factory.DAOFactory;
import com.Kokl.BL.Canciones.ICancionDAO;

public class RegistrarCancionesController {
	private ICancionDAO cancionesDAO;
	private DAOFactory factory;

	public RegistrarCancionesController(){
		factory = DAOFactory.getDaoFactory(1);
		cancionesDAO = factory.getCancionDAO();
	}

	public boolean registrarCancion(String nombre, String genero, String artista, String compositor, String fechaLanzamiento, String album, int calificacion, String nombreUsuarioActivo, String idCancion) throws Exception {
		Cancion nuevaCancion = new Cancion(nombre, genero, artista, compositor, fechaLanzamiento, album, calificacion, nombreUsuarioActivo, idCancion);

		boolean err = cancionesDAO.registrarCancion(nuevaCancion);

		return err;
	}
}
