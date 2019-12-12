package com.Kokl.UI.Canciones.ListarCanciones.Principal;

import com.Kokl.UI.Canciones.ListarCanciones.Admin.Admin;
import com.Kokl.UI.Canciones.ListarCanciones.Tienda.Tienda;
import com.Kokl.UI.Canciones.ListarCanciones.Catalogo.Catalogo;
import com.Kokl.UI.Perfil.Perfil;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;

public class Principal extends Perfil {
	Admin admin = new Admin();
	Tienda tienda = new Tienda();
	Catalogo catalogo = new Catalogo();

	String nombreUsuarioAdmin;

	public GridPane panelAdmin;
	public GridPane panelTienda;
	public GridPane panelCatalogo;
	public GridPane panelPrincipal;
	public GridPane panelContenido;


	public Button btnTienda = new Button("Tienda");
	public Button btnCatalogo = new Button("Catalogo");

	public void setPaneles(String nombreUsuarioActivo) throws Exception {
		nombreUsuarioAdmin = gestorPerfilUsuarios.getAdmin().getNombreUsuario();

		GridPane panelNavegacion = navegacion.crearPanelNavegacion(nombreUsuarioActivo);
		panelPrincipal.add(panelNavegacion, 1, 1);

		if(nombreUsuarioActivo.equals(nombreUsuarioAdmin)){
			setPanelAdmin(nombreUsuarioActivo);
		}else{
			setPanelesCliente(nombreUsuarioActivo);
		}
	}

	public void setPanelAdmin(String nombreUsuarioActivo) throws Exception {
		panelAdmin = admin.crearPanelAdmin(nombreUsuarioActivo);

		panelContenido.add(panelAdmin, 1, 2);
	}

	public void setPanelesCliente(String nombreUsuarioActivo) throws Exception {
		GridPane panelOpciones = new GridPane();
		String tipo = "admCanciones";

		btnTienda.setOnAction( e -> mostrarPanelTienda());
		btnCatalogo.setOnAction(e -> mostrarPanelCatalogo());

		panelOpciones.add(btnTienda, 1, 2);
		panelOpciones.add(btnCatalogo, 2, 2);

		panelTienda = tienda.crearPanelTienda(nombreUsuarioActivo);
		panelCatalogo = catalogo.crearPanelCatalogoAdmCanciones(nombreUsuarioActivo);

		panelOpciones.setAlignment(Pos.CENTER);
		panelTienda.setAlignment(Pos.CENTER);
		panelCatalogo.setAlignment(Pos.CENTER);

		btnTienda.getStyleClass().add("selected");

		panelContenido.add(panelTienda, 1, 3);
		panelContenido.add(panelOpciones, 1, 2);
	}

	private void mostrarPanelTienda() {
		btnTienda.getStyleClass().add("selected");
		btnCatalogo.getStyleClass().remove("selected");

		panelContenido.getChildren().remove(panelCatalogo);
		panelContenido.add(panelTienda, 1, 3);
	}

	private void mostrarPanelCatalogo() {
		btnCatalogo.getStyleClass().add("selected");
		btnTienda.getStyleClass().remove("selected");

		panelContenido.getChildren().remove(panelTienda);
		panelContenido.add(panelCatalogo, 1, 3);
	}
}