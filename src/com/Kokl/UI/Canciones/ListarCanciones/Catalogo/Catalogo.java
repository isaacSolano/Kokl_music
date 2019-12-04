package com.Kokl.UI.Canciones.ListarCanciones.Catalogo;

import com.Kokl.TL.Canciones.ListarCanciones.ListarCancionesController;
import com.Kokl.UI.Rutas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Catalogo {
	ListarCancionesController gestorListaCanciones = new ListarCancionesController();
	Rutas rutas = new Rutas();

	public GridPane crearPanelCatalogo(String nombreUsuarioActivo) throws Exception {
		ArrayList<String> listaInfoCanciones = gestorListaCanciones.getCanciones();

		GridPane panelCatalogo = new GridPane();
		int rowIndex = 3;

		for(String cancion : listaInfoCanciones){
			String nombreCancion = cancion.split("_")[0],
					usuario = cancion.split("_")[1],
					id = cancion.split("_")[2];

			if(usuario.equals(nombreUsuarioActivo)){
				GridPane panelCancion = new GridPane();
				Label txtNombreCancion = new Label(nombreCancion);
				Button btnRemover = new Button("Remover");
				btnRemover.setOnAction(e -> {
					try {
						removerCancion(e, usuario, id);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});

				panelCancion.setPadding(new Insets(20, 0, 0, 0));
				txtNombreCancion.setPadding(new Insets(0, 20, 0, 0));

				panelCancion.add(txtNombreCancion, 1, 1);
				panelCancion.add(btnRemover, 2, 1);
				panelCancion.setAlignment(Pos.CENTER);

				panelCatalogo.add(panelCancion, 1, rowIndex);
				rowIndex++;
			}
		}

		return panelCatalogo;
	}

	private void removerCancion(javafx.event.ActionEvent e, String nombreUsuarioActivo, String id) throws Exception {
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

		rutas.redirigirListarCanciones(nombreUsuarioActivo ,e);
	}
}