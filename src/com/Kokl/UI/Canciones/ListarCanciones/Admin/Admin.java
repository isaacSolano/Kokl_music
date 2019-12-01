package com.Kokl.UI.Canciones.ListarCanciones.Admin;

import com.Kokl.TL.Canciones.ListarCanciones.ListarCancionesController;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class Admin {
	ListarCancionesController gestorListaCanciones = new ListarCancionesController();

	public GridPane crearPanelAdmin() {
		GridPane panelAdmin = new GridPane();

		Label ejemplo = new Label("Prueba");
		panelAdmin.add(ejemplo, 1, 1);
		return panelAdmin;
	}
}
