package com.Kokl.UI.Canciones.ListarCanciones.Catalogo;

import com.Kokl.TL.Canciones.ListarCanciones.ListarCancionesController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Catalogo{
	ListarCancionesController gestorListaCanciones = new ListarCancionesController();

	public GridPane crearPanelCatalogo(String nombreUsuarioActivo) throws Exception {
		ArrayList<String> listaInfoCanciones = gestorListaCanciones.getCanciones();

		GridPane panelCatalogo = new GridPane();
		int rowIndex = 3;

		for(String cancion : listaInfoCanciones){
			String nombreCancion = cancion.split("_")[0],
					usuario = cancion.split("_")[1];

			if(usuario.equals(nombreUsuarioActivo)){
				GridPane panelCancion = new GridPane();
				Label txtNombreCancion = new Label(nombreCancion);
				Button btnRemover = new Button("Remover");
				btnRemover.setOnAction(e -> removerCancion(nombreCancion));

				panelCancion.setPadding(new Insets(20, 0, 0, 0));
				txtNombreCancion.setPadding(new Insets(0, 20, 0, 0));

				panelCancion.add(txtNombreCancion, 1, 1);
				panelCancion.add(btnRemover, 2, 1);

				panelCatalogo.add(panelCancion, 1, rowIndex);
				rowIndex++;
			}
		}

		return panelCatalogo;
	}

	private void removerCancion(String nombreCancion) {
		System.out.println(nombreCancion);
	}
}