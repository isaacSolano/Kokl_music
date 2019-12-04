package com.Kokl.BL.Canciones;

import com.Kokl.BL.Canciones.Obj.Cancion;
import com.Kokl.DL.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MYSQLCancionDAO implements ICancionDAO {
	private String req;

	public boolean registrarCancion(Cancion nuevaCancion) throws Exception {
		boolean err = false;
		ArrayList<Cancion> listaCanciones = getCanciones();

		if(listaCanciones != null) {
			for (Cancion cancion : listaCanciones) {
				if (cancion.equals(nuevaCancion)) {
					err = true;
				}
			}
		}

		if(!err){
			req = "INSERT INTO kokl.canciones (nombre, genero, artista, compositor, fechaLanzamiento, album, calificacion, usuario, id) VALUES ('" + nuevaCancion.getNombre() + "', '" + nuevaCancion.getGenero() + "', '" + nuevaCancion.getArtista() + "', '" + nuevaCancion.getCompositor() + "', '" + nuevaCancion.getFechaLanzamiento() + "', '" + nuevaCancion.getAlbum() + "', " + nuevaCancion.getCalificacion() + ", '" + nuevaCancion.getUsuario() + "', " + nuevaCancion.getId() + ")";
			Connector.getConnector().POST(req);
		}

			return err;
		}

	public Cancion getById(String id) throws Exception {
		Cancion cancionEncontrada = null;
		ArrayList<Cancion> listaCanciones = getCanciones();

		if(listaCanciones != null){
			for(Cancion cancion : listaCanciones){
				if(cancion.getId().equals(id)){
					cancionEncontrada = cancion;
				}
			}
		}

		return cancionEncontrada;
	}

	public ArrayList<Cancion> getCanciones() throws Exception {
		ArrayList<Cancion> listaCanciones = new ArrayList<>();
		req = "SELECT * FROM kokl.canciones";

		ResultSet res = Connector.getConnector().GET(req);
		Cancion cancion;

		while (res.next()){
			cancion = new Cancion(res.getString("nombre"), res.getString("genero"), res.getString("artista"), res.getString("compositor"), res.getString("fechaLanzamiento"), res.getString("album"), res.getInt("calificacion"), res.getString("usuario"), res.getString("id"));
			listaCanciones.add(cancion);
		}

		return listaCanciones;
	}

	public boolean removerCancion(String id){
		boolean err = false;
		req = "DELETE FROM kokl.canciones WHERE id='" + id +"'";

		try {
			Connector.getConnector().POST(req);
		} catch (Exception e) {
			e.printStackTrace();
			err = true;
		}

		return err;
	}
}
