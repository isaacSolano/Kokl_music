package com.Kokl.BL.Listas.Obj;

import com.Kokl.BL.Canciones.Obj.Cancion;

import java.util.ArrayList;

public class Lista {
	private String idLista;
	private String nombreUsuario;
	private String fechaCreacion;
	private String nombreLista;
	private int calificacion;
	private ArrayList<Cancion> listaCanciones;

	public Lista(String idLista, String nombreUsuario, String fechaCreacion, String nombreLista, int calificacion) {
		this.idLista = idLista;
		this.nombreUsuario = nombreUsuario;
		this.fechaCreacion = fechaCreacion;
		this.nombreLista = nombreLista;
		this.calificacion = calificacion;
	}

	public String toString(){
		String infoLista = this.fechaCreacion + "_" + this.nombreLista + "_" + this.calificacion + "_" + this.idLista;

		return infoLista;
	}

	public boolean equals(Lista nuevaLista){
		boolean err = false;

		for(Cancion cancionLista : this.listaCanciones){
			for(Cancion cancionNuevaLista : nuevaLista.getListaCanciones()){
				if(this.idLista.equals(nuevaLista.getIdLista()) && cancionLista.getId().equals(cancionNuevaLista.getId())){
					err = true;
				}
			}
		}

		return err;
	}

	public String getIdLista() {
		return idLista;
	}

	public void setIdLista(String idLista) {
		this.idLista = idLista;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}
}
