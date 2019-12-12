package com.Kokl.UI.Canciones.ListarCanciones.Tienda;

import com.Kokl.TL.Canciones.ListarCanciones.ListarCancionesController;
import com.Kokl.UI.Rutas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Tienda {
	Rutas rutas = new Rutas();
	ListarCancionesController gestorListaCanciones = new ListarCancionesController();

	public GridPane crearPanelTienda(String nombreUsuarioActivo) throws Exception {
		ArrayList<String> listaInfoCanciones = gestorListaCanciones.getCanciones();
		GridPane panelTienda = new GridPane();
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

		panelTienda.add(txtTituloNombreCancion, 1, 2);
		panelTienda.add(txtTituloGenero, 2, 2);
		panelTienda.add(txtTituloFechaLanzamiento, 3, 2);
		panelTienda.add(txtTituloAlbum, 4, 2);
		panelTienda.add(txtTituloCalificacion, 5, 2);
		panelTienda.add(txtTituloAcciones, 6, 2);

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

				Button btnAgregarCatalogo = new Button("Agregar a mi catálogo");

				btnAgregarCatalogo.setOnAction( e-> {
					try {
						agregarCatalogo(e, nombreUsuarioActivo, idCancion);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});

				txtNombreCancion.setStyle("-fx-padding: 10");
				txtGenero.setStyle("-fx-padding: 10");
				txtFechaLanzamiento.setStyle("-fx-padding: 10");
				txtAlbum.setStyle("-fx-padding: 10");
				txtCalificacion.setStyle("-fx-padding: 10");

				panelTienda.add(txtNombreCancion, 1, rowIndex);
				panelTienda.add(txtGenero, 2, rowIndex);
				panelTienda.add(txtFechaLanzamiento, 3, rowIndex);
				panelTienda.add(txtAlbum, 4, rowIndex);
				panelTienda.add(txtCalificacion, 5, rowIndex);
				panelTienda.add(btnAgregarCatalogo, 6, rowIndex);

				panelTienda.setAlignment(Pos.CENTER);
				rowIndex++;
			}
		}

		return panelTienda;
	}

	private void agregarCatalogo(javafx.event.ActionEvent event, String nombreUsuarioActivo, String id) throws Exception {
		boolean err = gestorListaCanciones.agregarCatalogo(nombreUsuarioActivo, id);

		if(err){
			Alert alertErrAgregar = new Alert(Alert.AlertType.ERROR);
			alertErrAgregar.setTitle("Error");
			alertErrAgregar.setHeaderText("Error al agregar la canción.");
			alertErrAgregar.setContentText("La canción ya está en su catálogo.");

			DialogPane dialogErrAgregar = alertErrAgregar.getDialogPane();
			dialogErrAgregar.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogErrAgregar.getStyleClass().add("alert");

			alertErrAgregar.showAndWait();
		}else{
			Alert alertInfAgregar = new Alert(Alert.AlertType.INFORMATION);
			alertInfAgregar.setTitle("Agregada");
			alertInfAgregar.setHeaderText("La canción se agregó correctamente.");
			alertInfAgregar.setContentText("Su información ha sido almacenada en el sistema.");

			DialogPane dialogInfAgregar = alertInfAgregar.getDialogPane();
			dialogInfAgregar.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogInfAgregar.getStyleClass().add("alert");

			alertInfAgregar.showAndWait();

			rutas.redirigirListarCanciones(nombreUsuarioActivo, event);
		}
	}
}