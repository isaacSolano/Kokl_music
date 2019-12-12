package com.Kokl.BL.Canciones;

import com.Kokl.BL.Canciones.Obj.Cancion;

import java.util.ArrayList;

public interface ICancionDAO {
	boolean registrarCancion(Cancion cancion) throws Exception;
	Cancion getById(String nombre) throws Exception;
	ArrayList<Cancion> getCanciones() throws Exception;
	boolean removerCancion(String id) throws Exception;
	boolean editarCancion(Cancion cancionEncontrada);
	boolean removerCancionLista(String idCancion) throws Exception;
}
