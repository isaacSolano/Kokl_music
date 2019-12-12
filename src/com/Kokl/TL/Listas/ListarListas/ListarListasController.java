package com.Kokl.TL.Listas.ListarListas;

import com.Kokl.BL.Canciones.ICancionDAO;
import com.Kokl.BL.Canciones.Obj.Cancion;
import com.Kokl.BL.Factory.DAOFactory;
import com.Kokl.BL.Listas.IListaDAO;
import com.Kokl.BL.Listas.Obj.Lista;

import java.util.ArrayList;
import java.util.Random;

public class ListarListasController {
	private IListaDAO listasDAO;
	private ICancionDAO cancionesDAO;
	private DAOFactory factory;

	Random rdm = new Random();

	public ListarListasController(){
		factory = DAOFactory.getDaoFactory(1);
		listasDAO = factory.getListasDAO();
		cancionesDAO = factory.getCancionDAO();
	}
	public ArrayList<Lista> obtenerListasUsuario(String nombreUsuarioActivo) throws Exception {
		ArrayList<Lista> listaTodasListas = listasDAO.obtenerListas(),
						listaListasUsuario = new ArrayList<>();

		for(Lista lista : listaTodasListas){
			if(lista.getNombreUsuario().equals(nombreUsuarioActivo)){
				ArrayList<Cancion> listaCancionesLista = new ArrayList<>();
				ArrayList<String> listaIdCanciones = listasDAO.obtenerCancionesLista(lista.getIdLista());

				for(String idCancion : listaIdCanciones){
					Cancion nuevaCancion= cancionesDAO.getById(idCancion);
					listaCancionesLista.add(nuevaCancion);
				}
				lista.setListaCanciones(listaCancionesLista);
				listaListasUsuario.add(lista);
			}
		}

		return listaListasUsuario;
	}

	public ArrayList<String> obtenerInfoListasUsuario(String nombreUsuarioActivo) throws Exception {
		ArrayList<String> listaInfoListas = new ArrayList<>();
		ArrayList<Lista> listaListas= obtenerListasUsuario(nombreUsuarioActivo);

		for(Lista lista : listaListas){
			listaInfoListas.add(lista.toString());
		}

		return listaInfoListas;
	}

	public ArrayList<String> obtenerInfoCancionesLista(String idLista, String nombreUsuarioActivo) throws Exception {
		ArrayList<String> listaInfoCanciones= new ArrayList<>();
		ArrayList<Lista> listaListas = obtenerListasUsuario(nombreUsuarioActivo);

		for(Lista lista : listaListas){
			if(lista.getIdLista().equals(idLista)){
				ArrayList<Cancion> listaCanciones = lista.getListaCanciones();
				if(listaCanciones != null){
					for(Cancion cancion : lista.getListaCanciones()){
						listaInfoCanciones.add(cancion.toString());
					}
				}
			}
		}

		return listaInfoCanciones;
	}

	public boolean agregarCancionLista(String idCancion, String idLista, String nombreUsuarioActivo) throws Exception {
		String idRelacion = String.format("%06d", (rdm.nextInt(999999)));
		boolean err = listasDAO.registrarCancionLista(idCancion, idLista, idRelacion);

		if(!err){
			Lista lista = listasDAO.obtenerById(idLista);

			ArrayList<String> listaCancionesLista = obtenerInfoCancionesLista(idLista, nombreUsuarioActivo);
			int califSum = 0,
				divisor = 0;

			if(listaCancionesLista != null){
				for(String infoCancionLista : listaCancionesLista){
					califSum += Integer.parseInt(infoCancionLista.split("_")[6]);
					divisor++;
				}

				lista.setCalificacion(califSum /divisor);
			}

			listasDAO.actualizarLista(lista);
		}

		return  err;
	}
}
