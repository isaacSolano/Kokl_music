package com.Kokl.TL.Canciones.ListarCanciones;

import com.Kokl.BL.Canciones.Obj.Cancion;
import com.Kokl.BL.Factory.DAOFactory;
import com.Kokl.BL.Canciones.ICancionDAO;

import java.util.ArrayList;
import java.util.Random;

public class ListarCancionesController {
	Random rdm = new Random();

	private ICancionDAO cancionesDAO;
	private DAOFactory factory;

	public ListarCancionesController(){
		factory = DAOFactory.getDaoFactory(1);
		cancionesDAO = factory.getCancionDAO();
	}

	public ArrayList<String> getCanciones() throws Exception {
		ArrayList<Cancion> listaCanciones = cancionesDAO.getCanciones();
		ArrayList<String> listaInfoCanciones = new ArrayList<>();

		for(Cancion cancion : listaCanciones){
			listaInfoCanciones.add(cancion.toString());
		}

		return listaInfoCanciones;
	}

	public boolean agregarCatalogo(String nombreCancion, String nombreUsuarioActivo) throws Exception {
		boolean err;

		Cancion cancionEncontrada = cancionesDAO.getById(nombreCancion);
		cancionEncontrada.setUsuario(nombreUsuarioActivo);
		cancionEncontrada.setId(String.format("%06d", (rdm.nextInt(999999))));

		err = cancionesDAO.registrarCancion(cancionEncontrada);

		return err;
	}
}