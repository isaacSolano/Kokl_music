package com.Kokl.UI.Canciones.RegistrarCanciones;

import com.Kokl.UI.Perfil.Perfil;
import com.Kokl.UI.Validaciones;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class RegistrarCanciones extends Perfil{
	Validaciones validaciones = new Validaciones();

	public GridPane panelPrincipal, panelContenido, panelError;
	public TextField inputNombre, inputGenero, inputArtista, inputCompositor, inputAlbum, inputCalificacion;
	public DatePicker inputFechaLanzamiento;

	public void setNavegacion(String nombreUsuario) throws Exception {
		GridPane panelNavegacion = navegacion.crearPanelNavegacion(nombreUsuario);

		panelPrincipal.add(panelNavegacion, 1, 1);
	}

	public void registrarCancion(javafx.event.ActionEvent event) throws Exception {
		boolean errInputNombre = validaciones.validarTexto(inputNombre),
				errInputGenero = validaciones.validarTexto(inputGenero),
				errInputArtista = validaciones.validarTexto(inputArtista),
				errInputCompositor = validaciones.validarTexto(inputCompositor),
				errInputFechaLanzamiento = validaciones.validarFecha(inputFechaLanzamiento),
				errInputAlbum= validaciones.validarTexto(inputAlbum),
				errInputCalificacion = validaciones.validarTexto(inputCalificacion);

		if(errInputNombre || errInputGenero || errInputArtista || errInputCompositor || errInputFechaLanzamiento || errInputAlbum || errInputCalificacion){
			Label txtErrGeneral = new Label("Verifíque que la información sea correcta.");
			txtErrGeneral.setStyle("-fx-text-fill: #D73C2C");
			panelError.add(txtErrGeneral, 1, 20);
		}else{
			String nombre = inputNombre.getText(),
					genero = inputGenero.getText(),
					artista = inputArtista.getText(),
					compositor = inputArtista.getText(),
					fechaLanzamiento = inputFechaLanzamiento.getValue().toString(),
					album = inputAlbum.getText(),
					usuarioActivo = gestorPerfilUsuarios.getUsuarioActivo().getNombreUsuario();
			int calificacion = Integer.parseInt(inputCalificacion.getText());

			boolean err = gestorRegistroCanciones.registrarCancion(nombre, genero, artista, compositor, fechaLanzamiento, album, calificacion, usuarioActivo);

			if(err){
				Alert alertErrRegistro = new Alert(Alert.AlertType.ERROR);
				alertErrRegistro.setTitle("Error");
				alertErrRegistro.setHeaderText("Error al registrar la información.");
				alertErrRegistro.setContentText("La canción ya esta registrada.");

				DialogPane dialogErrRegistro = alertErrRegistro.getDialogPane();
				dialogErrRegistro.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
				dialogErrRegistro.getStyleClass().add("alert");

				alertErrRegistro.showAndWait();
			}else{
				Alert alertInfRegistro = new Alert(Alert.AlertType.INFORMATION);
				alertInfRegistro.setTitle("Registrado");
				alertInfRegistro.setHeaderText("La canción se registró correctamente.");
				alertInfRegistro.setContentText("Su información ha sido almacenada en el sistema.");

				DialogPane dialogInfRegistro = alertInfRegistro.getDialogPane();
				dialogInfRegistro.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
				dialogInfRegistro.getStyleClass().add("alert");

				alertInfRegistro.showAndWait();

				rutas.redirigirPerfilIndex(gestorPerfilUsuarios.getUsuarioActivo().getNombreUsuario() ,event);
			}
		}
	}

	public void redirigirPerfil(javafx.event.ActionEvent event) throws Exception {
		String nombreUsuarioActivo = gestorPerfilUsuarios.getUsuarioActivo().getNombreUsuario();
		rutas.redirigirPerfilIndex(nombreUsuarioActivo ,event);
	}
}