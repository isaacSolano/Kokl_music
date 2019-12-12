package com.Kokl.UI.Listas.ListarListas;

import com.Kokl.TL.Listas.ListarListas.ListarListasController;
import com.Kokl.UI.Perfil.Perfil;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ListarListas extends Perfil {
	ListarListasController gestorListarListas = new ListarListasController();

	public GridPane setListas(String nombreUsuarioActivo) throws Exception {
		GridPane panelListas = new GridPane();
		panelListas.setStyle("-fx-padding: 10 0 0 0");
		ArrayList<String> listaInfoListas = gestorListarListas.obtenerInfoListasUsuario(nombreUsuarioActivo);
		int rowIndex = 2;

		Label txtTituloFechaCreacion = new Label("Fecha de creación"),
				txtTituloNombreLista = new Label("Nombre de la lista"),
				txtTituloCalificacion = new Label("Calificación"),
				txtTituloAcciones = new Label("Acciones");

		txtTituloFechaCreacion.setStyle("-fx-padding: 10");
		txtTituloNombreLista.setStyle("-fx-padding: 10");
		txtTituloCalificacion.setStyle("-fx-padding: 10");
		txtTituloAcciones.setStyle("-fx-padding: 10");

		panelListas.add(txtTituloFechaCreacion, 1, 1);
		panelListas.add(txtTituloNombreLista, 2, 1);
		panelListas.add(txtTituloCalificacion, 3, 1);
		panelListas.add(txtTituloAcciones, 4, 1);

		for(String lista : listaInfoListas){
			String fechaCreacion = lista.split("_")[0],
					nombreLista = lista.split("_")[1],
					calificacion = lista.split("_")[2],
					idLista = lista.split("_")[3];

			Button btnAgregarCancionLista = new Button("Agregar canciones");
			btnAgregarCancionLista.setOnAction(e -> {
				try {
					agregarCanciones(e, idLista);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});

			Button btnListarCancionesLista = new Button("Ver canciones");
			btnListarCancionesLista.setOnAction(e -> {
				try {
					listarCancionesLista(e, idLista);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});

			Label txtFechaCreacion = new Label(fechaCreacion),
					txtNombreLista = new Label(nombreLista),
					txtCalificacion = new Label(calificacion);

			txtFechaCreacion.setStyle("-fx-padding: 10");
			txtNombreLista.setStyle("-fx-padding: 10");
			txtCalificacion.setStyle("-fx-padding: 10");

			panelListas.add(txtFechaCreacion, 1, rowIndex);
			panelListas.add(txtNombreLista, 2, rowIndex);
			panelListas.add(txtCalificacion, 3, rowIndex);

			panelListas.add(btnAgregarCancionLista, 4, rowIndex);
			panelListas.add(btnListarCancionesLista, 5, rowIndex);


			rowIndex++;

		}

		return panelListas;
	}

	private void listarCancionesLista(javafx.event.ActionEvent e, String idLista) throws Exception {
		rutas.redirigirlistarCancionesLista(e, idLista);
	}

	private void agregarCanciones(javafx.event.ActionEvent event, String idLista) throws Exception {
		rutas.redirigirAgregarCancionesLista(event, idLista);
	}
}
