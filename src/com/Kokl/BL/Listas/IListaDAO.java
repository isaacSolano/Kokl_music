package com.Kokl.BL.Listas;

import com.Kokl.BL.Listas.Obj.Lista;

import java.util.ArrayList;

public interface IListaDAO {
	boolean registrarLista(Lista nuevaLista) throws Exception;
	Lista obtenerById(String idLista) throws Exception;
	ArrayList<Lista> obtenerListas() throws Exception;
	ArrayList<String> obtenerCancionesLista(String idLista) throws Exception;
	boolean registrarCancionLista(String idCancion, String idLista, String idRelacion) throws Exception;
	boolean obtenerRelacionCancionLista(String idLista, String idCancion) throws Exception;
	void actualizarLista(Lista lista) throws Exception;
}