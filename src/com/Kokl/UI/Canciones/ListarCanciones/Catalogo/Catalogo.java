package com.Kokl.UI.Canciones.ListarCanciones.Catalogo;

import com.Kokl.TL.Canciones.ListarCanciones.ListarCancionesController;
import com.Kokl.TL.Listas.ListarListas.ListarListasController;
import com.Kokl.UI.Rutas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Catalogo {
	ListarListasController gestorListarListas = new ListarListasController();
	ListarCancionesController gestorListaCanciones = new ListarCancionesController();
	Rutas rutas = new Rutas();

	public GridPane crearPanelCatalogoAdmCanciones(String nombreUsuarioActivo) throws Exception {
		ArrayList<String> listaInfoCanciones = gestorListaCanciones.getCanciones();

		GridPane panelCatalogo = new GridPane();
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

		panelCatalogo.add(txtTituloNombreCancion, 1, 2);
		panelCatalogo.add(txtTituloGenero, 2, 2);
		panelCatalogo.add(txtTituloFechaLanzamiento, 3, 2);
		panelCatalogo.add(txtTituloAlbum, 4, 2);
		panelCatalogo.add(txtTituloCalificacion, 5, 2);
		panelCatalogo.add(txtTituloAcciones, 6, 2);

		for(String cancion : listaInfoCanciones){
			String nombreCancion = cancion.split("_")[0],
					genero = cancion.split("_")[1],
					fechaLanzamiento = cancion.split("_")[4],
					album = cancion.split("_")[5],
					calificacion = cancion.split("_")[6],
					usuarioCancion = cancion.split("_")[7],
					idCancion = cancion.split("_")[8];

			if(usuarioCancion.equals(nombreUsuarioActivo)){
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

				panelCatalogo.add(txtNombreCancion, 1, rowIndex);
				panelCatalogo.add(txtGenero, 2, rowIndex);
				panelCatalogo.add(txtFechaLanzamiento, 3, rowIndex);
				panelCatalogo.add(txtAlbum, 4, rowIndex);
				panelCatalogo.add(txtCalificacion, 5, rowIndex);

				Button btnRemover = new Button("Remover");
				btnRemover.setOnAction(e -> {
					try {
						removerCancion(e, usuarioCancion, idCancion);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});

				panelCatalogo.add(btnRemover, 6, rowIndex);
				panelCatalogo.setAlignment(Pos.CENTER);
				panelCatalogo.setStyle("-fx-padding: 10 0 0 0");

				rowIndex++;
			}
		}

		return panelCatalogo;
	}

	public GridPane crearPanelCatalogoAdmLista(String nombreUsuarioActivo, String idLista) throws Exception {
		ArrayList<String> listaInfoCanciones = gestorListaCanciones.getCanciones();

		GridPane panelCatalogo = new GridPane();
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

		panelCatalogo.add(txtTituloNombreCancion, 1, 2);
		panelCatalogo.add(txtTituloGenero, 2, 2);
		panelCatalogo.add(txtTituloFechaLanzamiento, 3, 2);
		panelCatalogo.add(txtTituloAlbum, 4, 2);
		panelCatalogo.add(txtTituloCalificacion, 5, 2);
		panelCatalogo.add(txtTituloAcciones, 6, 2);

		for(String cancion : listaInfoCanciones){
			String nombreCancion = cancion.split("_")[0],
					genero = cancion.split("_")[1],
					fechaLanzamiento = cancion.split("_")[4],
					album = cancion.split("_")[5],
					calificacion = cancion.split("_")[6],
					usuarioCancion = cancion.split("_")[7],
					idCancion = cancion.split("_")[8];

			if(usuarioCancion.equals(nombreUsuarioActivo)){
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

				Button btnAgregarLista = new Button("Agregar a lista");
				btnAgregarLista.setOnAction(e -> {
					try {
						agregarCancionLista(idCancion, idLista, nombreUsuarioActivo);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});

				panelCatalogo.add(txtNombreCancion, 1, rowIndex);
				panelCatalogo.add(txtGenero, 2, rowIndex);
				panelCatalogo.add(txtFechaLanzamiento, 3, rowIndex);
				panelCatalogo.add(txtAlbum, 4, rowIndex);
				panelCatalogo.add(txtCalificacion, 5, rowIndex);
				panelCatalogo.add(btnAgregarLista, 6, rowIndex);

				panelCatalogo.setAlignment(Pos.CENTER);
				rowIndex++;
			}
		}

		return panelCatalogo;
	}

	private void agregarCancionLista(String idCancion, String idLista, String nombreUsuarioActivo) throws Exception {
		boolean err = gestorListarListas.agregarCancionLista(idCancion, idLista, nombreUsuarioActivo);

		if(err){
			Alert alertErrAgregarCancionLista = new Alert(Alert.AlertType.ERROR);
			alertErrAgregarCancionLista.setTitle("Error");
			alertErrAgregarCancionLista.setHeaderText("Error al agregar la canción.");
			alertErrAgregarCancionLista.setContentText("La canción ya está en la lista seleccionada.");

			DialogPane dialogErrAgregarCancionLista = alertErrAgregarCancionLista.getDialogPane();
			dialogErrAgregarCancionLista.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogErrAgregarCancionLista.getStyleClass().add("alert");

			alertErrAgregarCancionLista.showAndWait();
		}else{
			Alert alertInfAgregarCancionLista = new Alert(Alert.AlertType.INFORMATION);
			alertInfAgregarCancionLista.setTitle("Agregada");
			alertInfAgregarCancionLista.setHeaderText("La canción se agregó correctamente.");
			alertInfAgregarCancionLista.setContentText("La canción se agregó a la lista.");

			DialogPane dialogInfAgregarCancionLista = alertInfAgregarCancionLista.getDialogPane();
			dialogInfAgregarCancionLista.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogInfAgregarCancionLista.getStyleClass().add("alert");

			alertInfAgregarCancionLista.showAndWait();
		}
	}

	private void removerCancion(javafx.event.ActionEvent e, String nombreUsuarioActivo, String idCancion) throws Exception {
		boolean errRemoverCancion = gestorListaCanciones.removerCancion(idCancion);
		boolean errRemoverCancionLista = gestorListaCanciones.removerCancionLista(idCancion);

		if(errRemoverCancion || errRemoverCancionLista){
			Alert alertErrRemover = new Alert(Alert.AlertType.ERROR);
			alertErrRemover.setTitle("Error");
			alertErrRemover.setHeaderText("Error al remover la canción.");
			alertErrRemover.setContentText("La canción no se pudo remover de su catálogo, intente más tarde.");

			DialogPane dialogErrRemover = alertErrRemover.getDialogPane();
			dialogErrRemover.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogErrRemover.getStyleClass().add("alert");

			alertErrRemover.showAndWait();
		}else {
			Alert alertInfRemover = new Alert(Alert.AlertType.INFORMATION);
			alertInfRemover.setTitle("Removida");
			alertInfRemover.setHeaderText("La canción se removió correctamente.");
			alertInfRemover.setContentText("Su información ha sido removida del sistema.");

			DialogPane dialogInfRemover = alertInfRemover.getDialogPane();
			dialogInfRemover.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogInfRemover.getStyleClass().add("alert");

			alertInfRemover.showAndWait();
		}

		rutas.redirigirListarCanciones(nombreUsuarioActivo, e);
	}
}