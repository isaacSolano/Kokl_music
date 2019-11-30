package com.Kokl.BL.Canciones;

import com.Kokl.BL.Canciones.Obj.Cancion;

import java.util.ArrayList;

public interface ICancionDAO {
	public boolean registrarCancion(Cancion cancion) throws Exception;
	public Cancion obtenerById(String nombre) throws Exception;
	public ArrayList<Cancion> obtenerCanciones() throws Exception;
}
