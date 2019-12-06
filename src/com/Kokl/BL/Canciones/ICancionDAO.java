package com.Kokl.BL.Canciones;

import com.Kokl.BL.Canciones.Obj.Cancion;

import java.util.ArrayList;

public interface ICancionDAO {
	public boolean registrarCancion(Cancion cancion) throws Exception;
	public Cancion getById(String nombre) throws Exception;
	public ArrayList<Cancion> getCanciones() throws Exception;
	public boolean removerCancion(String id) throws Exception;
	public boolean editarCancion(Cancion cancionEncontrada);
}
