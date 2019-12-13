package com.Kokl.UI.Canciones.ListarCanciones.Admin;

import com.Kokl.TL.Canciones.ListarCanciones.ListarCancionesController;
import com.Kokl.UI.Canciones.ReproductorCancion;
import com.Kokl.UI.Rutas;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Admin {
	ReproductorCancion gestorReproductorCancion = new ReproductorCancion();
	ListarCancionesController gestorListaCanciones = new ListarCancionesController();
	Rutas rutas = new Rutas();

	public GridPane crearPanelAdmin(String nombreUsuarioActivo) throws Exception {
		ArrayList<String> listaInfoCanciones = gestorListaCanciones.getCanciones();

		GridPane panelAdmin = new GridPane();
		int rowIndex = 3;

		Label txtTituloNombreCancion = new Label("Nombre"),
				txtTituloGenero = new Label("Género"),
				txtTituloFechaLanzamiento = new Label("Lanzamiento"),
				txtTituloAlbum = new Label("Album"),
				txtTituloCalificacion = new Label("Calificación"),
				txtTituloAcciones = new Label("Acciones");

		txtTituloNombreCancion.setStyle("-fx-padding: 10");
		txtTituloGenero.setStyle("-fx-padding: 10");
		txtTituloFechaLanzamiento.setStyle("-fx-padding: 10");
		txtTituloAlbum.setStyle("-fx-padding: 10");
		txtTituloCalificacion.setStyle("-fx-padding: 10");
		txtTituloAcciones.setStyle("-fx-padding: 10");

		panelAdmin.add(txtTituloNombreCancion, 1, 2);
		panelAdmin.add(txtTituloGenero, 2, 2);
		panelAdmin.add(txtTituloFechaLanzamiento, 3, 2);
		panelAdmin.add(txtTituloAlbum, 4, 2);
		panelAdmin.add(txtTituloCalificacion, 5, 2);
		panelAdmin.add(txtTituloAcciones, 6, 2);

		for(String cancion : listaInfoCanciones){
			String nombreCancion = cancion.split("_")[0],
					genero = cancion.split("_")[1],
					fechaLanzamiento = cancion.split("_")[4],
					album = cancion.split("_")[5],
					calificacion = cancion.split("_")[6],
					usuarioCancion = cancion.split("_")[7],
					idCancion = cancion.split("_")[8];

			if(usuarioCancion.equals("0")){
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

				Button btnEditar = new Button("Editar");
				Button btnRemover = new Button("Remover");
				Button btnReproducir = new Button("Reproducir");
				Button btnPausar = new Button("Pausar");

				btnEditar.setOnAction(e -> {
					try {
						editarCancion(e, cancion);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});
				btnRemover.setOnAction(e -> {
					try {
						removerCancion(e, idCancion, nombreUsuarioActivo);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});
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

				panelAdmin.add(txtNombreCancion, 1, rowIndex);
				panelAdmin.add(txtGenero, 2, rowIndex);
				panelAdmin.add(txtFechaLanzamiento, 3, rowIndex);
				panelAdmin.add(txtAlbum, 4, rowIndex);
				panelAdmin.add(txtCalificacion, 5, rowIndex);
				panelAdmin.add(btnEditar, 6, rowIndex);
				panelAdmin.add(btnRemover, 7, rowIndex);
				panelAdmin.add(btnReproducir, 8, rowIndex);
				panelAdmin.add(btnPausar, 9, rowIndex);

				panelAdmin.setAlignment(Pos.CENTER);

				rowIndex++;
			}
		}

		return panelAdmin;
	}

	private void pausarCancion(ActionEvent e, String nombreCancion) {
		boolean err = gestorReproductorCancion.pausarCancion(nombreCancion);
	}

	private void reproducirCancion(ActionEvent e, String nombreCancion) {
		boolean err = gestorReproductorCancion.reproducirCancion(nombreCancion);
	}

	private void editarCancion(javafx.event.ActionEvent e, String infoCancion) throws Exception {
		rutas.redirigirEditarCancion(e, infoCancion);
	}

	private void removerCancion(javafx.event.ActionEvent event, String id, String nombreUsuarioActivo) throws Exception {
		boolean err = gestorListaCanciones.removerCancion(id);

		if(err){
			Alert alertErrRemover = new Alert(Alert.AlertType.ERROR);
			alertErrRemover.setTitle("Error");
			alertErrRemover.setHeaderText("Error al remover la canción.");
			alertErrRemover.setContentText("La canción no se pudo remover de su catálogo, intente más tarde.");

			DialogPane dialogErrRemover = alertErrRemover.getDialogPane();
			dialogErrRemover.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogErrRemover.getStyleClass().add("alert");

			alertErrRemover.showAndWait();
		}else{
			Alert alertInfRemover = new Alert(Alert.AlertType.INFORMATION);
			alertInfRemover.setTitle("Removida");
			alertInfRemover.setHeaderText("La canción se removió correctamente.");
			alertInfRemover.setContentText("Su información ha sido removida del sistema.");

			DialogPane dialogInfRemover = alertInfRemover.getDialogPane();
			dialogInfRemover.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogInfRemover.getStyleClass().add("alert");

			alertInfRemover.showAndWait();

			rutas.redirigirListarCanciones(nombreUsuarioActivo, event);
		}
	}
}
