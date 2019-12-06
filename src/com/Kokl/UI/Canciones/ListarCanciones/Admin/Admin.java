package com.Kokl.UI.Canciones.ListarCanciones.Admin;

import com.Kokl.TL.Canciones.ListarCanciones.ListarCancionesController;
import com.Kokl.UI.Rutas;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class Admin {
	ListarCancionesController gestorListaCanciones = new ListarCancionesController();
	Rutas rutas = new Rutas();

	public GridPane crearPanelAdmin(String nombreUsuarioActivo) throws Exception {
		GridPane panelAdmin = new GridPane();
		ArrayList<String> listaInfoCanciones = gestorListaCanciones.getCanciones();
		int rowIndex = 0;

		for(String cancion : listaInfoCanciones){
			String nombreCancion = cancion.split("_")[0],
					usuario = cancion.split("_")[7],
					id = cancion.split("_")[8];

			if(usuario.equals("0")){
				GridPane panelCancion = new GridPane();
				Label txtNombreCancion = new Label(nombreCancion);
				Button btnEditar = new Button("Editar");
				Button btnRemover = new Button("Remover");

				btnEditar.setOnAction(e -> {
					try {
						editarCancion(e, cancion);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});
				btnRemover.setOnAction(e -> {
					try {
						removerCancion(e, id, nombreUsuarioActivo);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});

				panelCancion.setPadding(new Insets(20, 0, 0, 0));
				txtNombreCancion.setPadding(new Insets(0, 20, 0, 0));
				btnEditar.setPadding(new Insets(0, 20, 0, 0));

				panelCancion.add(txtNombreCancion, 1, 1);
				panelCancion.add(btnEditar, 2, 1);
				panelCancion.add(btnRemover, 3, 1);
				panelCancion.setAlignment(Pos.CENTER);


				panelAdmin.add(panelCancion, 1, rowIndex);
				rowIndex++;
			}
		}

		return panelAdmin;
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
