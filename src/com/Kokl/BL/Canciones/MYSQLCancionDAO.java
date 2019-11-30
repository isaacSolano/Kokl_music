package com.Kokl.BL.Canciones;

import com.Kokl.BL.Canciones.Obj.Cancion;
import com.Kokl.DL.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MYSQLCancionDAO implements ICancionDAO {
	private String req;

	public boolean registrarCancion(Cancion cancion) throws Exception {
		boolean err = false;
		Cancion cancionEncontrada = obtenerById(cancion.getNombre());

		if(cancionEncontrada == null){
			req = "INSERT INTO kokl.canciones (nombre, genero, artista, compositor, fechaLanzamiento, album, calificacion, usuario) VALUES ('"+cancion.getNombre()+"', '"+cancion.getGenero()+"', '"+cancion.getArtista()+"', '"+cancion.getCompositor()+"', '"+cancion.getFechaLanzamiento()+"', '"+cancion.getAlbum()+"', "+cancion.getCalificacion()+", '"+cancion.getUsuario()+"')";
			Connector.getConnector().POST(req);
		}else{
			err = true;
		}

		return err;
	}

	public Cancion obtenerById(String nombre) throws Exception {
		Cancion cancionEncontrada = null;
		ArrayList<Cancion> listaCanciones = obtenerCanciones();

		if(listaCanciones != null){
			for(Cancion cancion : listaCanciones){
				if(cancion.getNombre().equals(nombre)){
					cancionEncontrada = cancion;
				}
			}
		}

		return cancionEncontrada;
	}

	public ArrayList<Cancion> obtenerCanciones() throws Exception {
		ArrayList<Cancion> listaCanciones = new ArrayList<>();
		req = "SELECT * FROM kokl.canciones";

		ResultSet res = Connector.getConnector().GET(req);
		Cancion cancion;

		while (res.next()){
			cancion = new Cancion(res.getString("nombre"), res.getString("genero"), res.getString("artista"), res.getString("compositor"), res.getString("fechaLanzamiento"), res.getString("album"), res.getInt("calificacion"), res.getString("usuario"));
			listaCanciones.add(cancion);
		}

		return listaCanciones;
	}
}
