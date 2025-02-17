package com.Kokl.UI.Listas.ListarCancionesLista;

import com.Kokl.TL.Listas.ListarListas.ListarListasController;
import com.Kokl.UI.Canciones.ReproductorCancion;
import com.Kokl.UI.Perfil.Perfil;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ListarCancionesLista extends Perfil {
	ListarListasController gestorListarListas = new ListarListasController();
	ReproductorCancion gestorReproductorCancion = new ReproductorCancion();


	String usuarioActivo = gestorPerfilUsuarios.getUsuarioActivo().getNombreUsuario();

	public GridPane panelPrincipal;
	public GridPane panelContenido;

	public void setListaCancionesLista(String idLista) throws Exception {
		GridPane panelNavegacion = navegacion.crearPanelNavegacion(usuarioActivo);
		GridPane panelCanciones = new GridPane();
		ArrayList<String> listaInfoCanciones = gestorListarListas.obtenerInfoCancionesLista(idLista, usuarioActivo);
		int rowIndex = 3;

		Label txtTituloNombreCancion = new Label("Nombre"),
				txtTituloGenero = new Label("Género"),
				txtTituloFechaLanzamiento = new Label("Lanzamiento"),
				txtTituloAlbum = new Label("Album"),
				txtTituloCalificacion = new Label("Calificación"),
				txtTituloAccion = new Label("Acciones");

		txtTituloNombreCancion.setStyle("-fx-padding: 10");
		txtTituloGenero.setStyle("-fx-padding: 10");
		txtTituloFechaLanzamiento.setStyle("-fx-padding: 10");
		txtTituloAlbum.setStyle("-fx-padding: 10");
		txtTituloCalificacion.setStyle("-fx-padding: 10");

		panelCanciones.add(txtTituloNombreCancion, 1, 2);
		panelCanciones.add(txtTituloGenero, 2, 2);
		panelCanciones.add(txtTituloFechaLanzamiento, 3, 2);
		panelCanciones.add(txtTituloAlbum, 4, 2);
		panelCanciones.add(txtTituloCalificacion, 5, 2);
		panelCanciones.add(txtTituloAccion, 6, 2);

		for(String cancion : listaInfoCanciones){
			String nombreCancion = cancion.split("_")[0],
					genero = cancion.split("_")[1],
					fechaLanzamiento = cancion.split("_")[4],
					album = cancion.split("_")[5],
					calificacion = cancion.split("_")[6];

			Label txtNombreCancion = new Label(nombreCancion),
					txtGenero = new Label(genero),
					txtFechaLanzamiento = new Label(fechaLanzamiento),
					txtAlbum = new Label(album),
					txtCalificacion = new Label(calificacion);

			txtNombreCancion.setStyle("-fx-padding: 10");
			txtGenero.setStyle("-fx-padding: 10");
			txtFechaLanzamiento.setStyle("-fx-padding: 10");
			txtAlbum.setStyle("-fx-padding: 10");
			txtCalificacion.setStyle("-fx-padding: 10");


			Button btnReproducir = new Button("Reproducir");
			Button btnPausar = new Button("Pausar");

			btnReproducir.setOnAction(e -> {
				try{
					reproducirCancion(e, nombreCancion);
				}catch (Exception ex){
					ex.printStackTrace();
				}
			});
			btnPausar.setOnAction((e -> {
				try{
					pausarCancion(e, nombreCancion);
				}catch (Exception ex){
					ex.printStackTrace();
				}
			}));

			panelCanciones.add(txtNombreCancion, 1, rowIndex);
			panelCanciones.add(txtGenero, 2, rowIndex);
			panelCanciones.add(txtFechaLanzamiento, 3, rowIndex);
			panelCanciones.add(txtAlbum, 4, rowIndex);
			panelCanciones.add(txtCalificacion, 5, rowIndex);
			panelCanciones.add(btnReproducir, 6, rowIndex);
			panelCanciones.add(btnPausar, 7, rowIndex);

			panelCanciones.setAlignment(Pos.CENTER);

			rowIndex++;
		}

		panelPrincipal.add(panelNavegacion, 1, 1);
		panelContenido.add(panelCanciones, 1, 2);
	}

	private void pausarCancion(ActionEvent e, String nombreCancion) {
		boolean err = gestorReproductorCancion.pausarCancion(nombreCancion);
	}

	private void reproducirCancion(ActionEvent e, String nombreCancion) {
		boolean err = gestorReproductorCancion.reproducirCancion(nombreCancion);
	}
}
