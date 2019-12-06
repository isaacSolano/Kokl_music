package com.Kokl.BL.Canciones.Obj;

public class Cancion {
	private String nombre;
	private String genero;
	private String artista;
	private String compositor;
	private String fechaLanzamiento;
	private String album;
	private int calificacion;
	private String usuario;
	private String id;

	public Cancion(String nombre, String genero, String artista, String compositor, String fechaLanzamiento, String album, int calificacion, String usuario, String id) {
		this.nombre = nombre;
		this.genero = genero;
		this.artista = artista;
		this.compositor = compositor;
		this.fechaLanzamiento = fechaLanzamiento;
		this.album = album;
		this.calificacion = calificacion;
		this.usuario = usuario;
		this.id = id;
	}

	public String toString() {
		String infoCancion = this.nombre + "_" + this.genero + "_" + this.artista + "_" + this.compositor + "_" + this.fechaLanzamiento + "_" + this.album + "_" + this.calificacion + "_" + this.usuario + "_" + this.id;

		return infoCancion;
	}

	public boolean equals(Cancion cancion){
		boolean err = false;

		if(cancion.getNombre().equals(this.nombre) && cancion.getUsuario().equals(this.usuario)){
			err = true;
		}

		return err;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getCompositor() {
		return compositor;
	}

	public void setCompositor(String compositor) {
		this.compositor = compositor;
	}

	public String getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(String fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}