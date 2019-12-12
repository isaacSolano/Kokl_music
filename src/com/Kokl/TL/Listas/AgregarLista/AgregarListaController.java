package com.Kokl.TL.Listas.AgregarLista;

import com.Kokl.BL.Factory.DAOFactory;
import com.Kokl.BL.Listas.IListaDAO;
import com.Kokl.BL.Listas.Obj.Lista;

public class AgregarListaController {
	private IListaDAO listasDAO;
	private DAOFactory factory;

	public AgregarListaController(){
		factory = DAOFactory.getDaoFactory(1);
		listasDAO = factory.getListasDAO();
	}

	public boolean agregarLista(String nombreUsuario, String nombreLista, String idLista, String fechaCreacion, int calificacion) throws Exception {
		Lista nuevaLista = new Lista(idLista, nombreUsuario, fechaCreacion, nombreLista, calificacion);

		boolean err = listasDAO.registrarLista(nuevaLista);

		return err;
	}
}