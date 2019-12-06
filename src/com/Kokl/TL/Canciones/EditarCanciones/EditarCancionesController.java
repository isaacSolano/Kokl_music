package com.Kokl.TL.Canciones.EditarCanciones;

import com.Kokl.BL.Canciones.ICancionDAO;
import com.Kokl.BL.Canciones.Obj.Cancion;
import com.Kokl.BL.Factory.DAOFactory;

public class EditarCancionesController {
	private ICancionDAO cancionesDAO;
	private DAOFactory factory;

	public EditarCancionesController(){
		factory = DAOFactory.getDaoFactory(1);
		cancionesDAO = factory.getCancionDAO();
	}
	public boolean editarCancion(String nombre, String genero, String artista, String compositor, String fechaLanzamiento, String album, int calificacion, String idCancion) throws Exception {
		boolean err;
		Cancion cancionEncontrada = cancionesDAO.getById(idCancion);

		if(cancionEncontrada != null){
			cancionEncontrada.setNombre(nombre);
			cancionEncontrada.setGenero(genero);
			cancionEncontrada.setArtista(artista);
			cancionEncontrada.setCompositor(compositor);
			cancionEncontrada.setFechaLanzamiento(fechaLanzamiento);
			cancionEncontrada.setAlbum(album);
			cancionEncontrada.setCalificacion(calificacion);

			err = cancionesDAO.editarCancion(cancionEncontrada);
		}else{
			err = true;
		}

		return err;
	}
}
