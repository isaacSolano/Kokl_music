package com.Kokl.UI.Listas.AgregarCancionesLista;

import com.Kokl.UI.Canciones.ListarCanciones.Catalogo.Catalogo;
import com.Kokl.UI.Perfil.Perfil;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AgregarCancionesLista extends Perfil {
	Catalogo catalogo = new Catalogo();

	static String idLista = "";

	public GridPane panelPrincipal;
	public GridPane panelContenido;

	public void setAgregarCancionesLista(String idAgregarLista) throws Exception {
		idLista = idAgregarLista;

		String nombreUsuarioActivo = gestorPerfilUsuarios.getUsuarioActivo().getNombreUsuario();

		GridPane panelNavegacion = navegacion.crearPanelNavegacion(nombreUsuarioActivo);
		GridPane panelCatalogo = catalogo.crearPanelCatalogoAdmLista(nombreUsuarioActivo, idAgregarLista);
		Label txtMensaje = new Label("Agregue canciones a su lista");

		panelCatalogo.setAlignment(Pos.CENTER);
		panelContenido.setAlignment(Pos.CENTER);

		panelPrincipal.add(panelNavegacion, 1, 1);
		panelContenido.add(txtMensaje, 1, 2);
		panelContenido.add(panelCatalogo, 1, 3);
	}
}
