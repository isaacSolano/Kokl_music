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

		for(String cancion : listaInfoCanciones){
			String nombreCancion = cancion.split("_")[0],
					usuario = cancion.split("_")[1],
					id = cancion.split("_")[2];

			if(usuario.equals("0")){
				GridPane panelCancion = new GridPane();
				Label txtNombreCancion = new Label(nombreCancion);
				Button btnAgregarCatalogo = new Button("Agregar a mi catálogo");

				panelCancion.setPadding(new Insets(20, 0, 0, 0));
				txtNombreCancion.setPadding(new Insets(0, 20, 0, 0));

				btnAgregarCatalogo.setOnAction( e-> {
					try {
						agregarCatalogo(e, nombreUsuarioActivo, id);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});

				panelCancion.add(txtNombreCancion, 1, 1);
				panelCancion.add(btnAgregarCatalogo, 2, 1);

				panelCancion.setAlignment(Pos.CENTER);

				panelTienda.add(panelCancion, 1, rowIndex);
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