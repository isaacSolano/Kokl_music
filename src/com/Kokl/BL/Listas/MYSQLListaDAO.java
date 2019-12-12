package com.Kokl.BL.Listas;

import com.Kokl.BL.Canciones.ICancionDAO;
import com.Kokl.BL.Canciones.MYSQLCancionDAO;
import com.Kokl.BL.Canciones.Obj.Cancion;
import com.Kokl.BL.Listas.Obj.Lista;
import com.Kokl.DL.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class MYSQLListaDAO implements IListaDAO {
	private String req;

	public boolean registrarLista(Lista nuevaLista) throws Exception {
		boolean err = false;
		Lista listaEncontrada = obtenerById(nuevaLista.getIdLista());

		if(listaEncontrada == null){
			req = "INSERT INTO kokl.listas (idLista, nombreUsuario, fechaCreacion, nombreLista, calificacion)VALUES('"+nuevaLista.getIdLista()+"','"+nuevaLista.getNombreUsuario()+"','"+nuevaLista.getFechaCreacion()+"','"+nuevaLista.getNombreLista()+"',"+nuevaLista.getCalificacion()+")";
			Connector.getConnector().POST(req);
		}else{
			err = true;
		}

		return err;
	}

	public Lista obtenerById(String idLista) throws Exception {
		Lista listaEncontrada = null;
		ArrayList<Lista> listaListasReproduccion = obtenerListas();

		for(Lista lista : listaListasReproduccion){
			if(lista.getIdLista().equals(idLista)){
				listaEncontrada = lista;
			}
		}

		return listaEncontrada;
	}

	public ArrayList<Lista> obtenerListas() throws Exception {
		ArrayList<Lista> listaListasReproduccion = new ArrayList<>();

		req = "SELECT * FROM kokl.listas";
		ResultSet resListas = Connector.getConnector().GET(req);

		while(resListas.next()) {
			Lista nuevaLista = new Lista(resListas.getString("idLista"), resListas.getString("nombreUsuario"), resListas.getString("fechaCreacion"), resListas.getString("nombreLista"), resListas.getInt("calificacion"));
			listaListasReproduccion.add(nuevaLista);
		}

		return listaListasReproduccion;
	}

	public ArrayList<String> obtenerCancionesLista(String idLista) throws Exception {
		ArrayList<String> listaIdCancionesLista = new ArrayList<>();
		req = "select * from kokl.canciones_lista where idLista='"+idLista+"'";

		ResultSet res = Connector.getConnector().GET(req);

		while(res.next()){
			listaIdCancionesLista.add(res.getString("idCancion"));
		}

		return listaIdCancionesLista;
	}

	public boolean registrarCancionLista(String idCancion, String idLista, String idRelacion) throws Exception {
		boolean relacion = obtenerRelacionCancionLista(idLista, idCancion);

		if(!relacion){
			req = "INSERT INTO kokl.canciones_lista(idLista,idCancion,idRelacion)VALUES('"+idLista+"','"+idCancion+"','"+idRelacion+"')";
			Connector.getConnector().POST(req);
		}

		return relacion;
	}

	public boolean obtenerRelacionCancionLista(String idLista, String idCancion) throws Exception {
		boolean err = false;
		req = "SELECT * FROM kokl.canciones_lista";
		ResultSet res = Connector.getConnector().GET(req);

		while(res.next()){
			if(res.getString("idLista").equals(idLista) && res.getString("idCancion").equals(idCancion)){
				err = true;
			}
		}

		return err;
	}

	public void actualizarLista(Lista lista) throws Exception{
		req = "UPDATE kokl.listas SET nombreUsuario= '"+lista.getNombreUsuario()+"', fechaCreacion='"+lista.getFechaCreacion()+"', nombreLista='"+lista.getNombreLista()+"', calificacion="+lista.getCalificacion()+" WHERE idLista='"+lista.getIdLista()+"'";
		Connector.getConnector().POST(req);
	}

}