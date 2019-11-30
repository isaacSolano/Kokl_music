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

	public Cancion(String nombre, String genero, String artista, String compositor, String fechaLanzamiento, String album, int calificacion, String usuario) {
		this.nombre = nombre;
		this.genero = genero;
		this.artista = artista;
		this.compositor = compositor;
		this.fechaLanzamiento = fechaLanzamiento;
		this.album = album;
		this.calificacion = calificacion;
		this.usuario = usuario;
	}

	public String toString() {
		String infoCancion = "Nombre de la cancion: " + this.nombre + this.genero + this.artista + this.compositor + this.fechaLanzamiento + this.album + this.calificacion;

		return infoCancion;
	}

	public boolean equals(Cancion cancion){
		boolean err = false;

		if(cancion.getNombre().equals(cancion.getNombre())){
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
}