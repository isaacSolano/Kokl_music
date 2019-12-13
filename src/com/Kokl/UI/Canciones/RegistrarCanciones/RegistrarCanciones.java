package com.Kokl.UI.Canciones.RegistrarCanciones;

import com.Kokl.TL.Canciones.RegistrarCanciones.RegistrarCancionesController;
import com.Kokl.UI.Perfil.Perfil;
import com.Kokl.UI.Validaciones;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class RegistrarCanciones extends Perfil{
	Validaciones validaciones = new Validaciones();
	RegistrarCancionesController gestorRegistroCanciones = new RegistrarCancionesController();
	Random rdm = new Random();

	FileChooser selectorArchivos;
	File archivoMusicaOrigin;
	Stage ventanaPrincipal;

	public GridPane panelPrincipal, panelContenido, panelError, panelSelectorArchivos;
	public TextField inputNombre, inputGenero, inputArtista, inputCompositor, inputAlbum, inputCalificacion;
	public DatePicker inputFechaLanzamiento;

	public void setNavegacion(String nombreUsuario, Stage ventana) throws Exception {
		GridPane panelNavegacion = navegacion.crearPanelNavegacion(nombreUsuario);
		panelPrincipal.add(panelNavegacion, 1, 1);

		ventanaPrincipal = ventana;
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
					compositor = inputCompositor.getText(),
					fechaLanzamiento = inputFechaLanzamiento.getValue().toString(),
					album = inputAlbum.getText(),
					usuarioActivo = gestorPerfilUsuarios.getUsuarioActivo().getNombreUsuario(),
					admin = gestorPerfilUsuarios.getAdmin().getNombreUsuario(),
					idUsuarioCancion,
					idCancion = String.format("%06d", (rdm.nextInt(999999)));

			if(admin.equals(usuarioActivo)){
				idUsuarioCancion = "0";
			}else{
				idUsuarioCancion = usuarioActivo;
			}

			int calificacion = Integer.parseInt(inputCalificacion.getText());

			Path originPath = Paths.get(archivoMusicaOrigin.getAbsolutePath());
			Path destPath = Paths.get(System.getProperty("user.dir")+File.separator+inputNombre.getText()+".mp3");

			Files.copy(originPath, destPath, StandardCopyOption.REPLACE_EXISTING);

			boolean err = gestorRegistroCanciones.registrarCancion(nombre, genero, artista, compositor, fechaLanzamiento, album, calificacion, idUsuarioCancion, idCancion);

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

	public void subirArchivo(javafx.event.ActionEvent event) throws Exception{
		selectorArchivos = new FileChooser();
		FileChooser.ExtensionFilter filtroArchivos = new FileChooser.ExtensionFilter("Archivos de música (*.mp3)", "(.mp3)");
		selectorArchivos.getExtensionFilters().add(filtroArchivos);

		archivoMusicaOrigin = selectorArchivos.showOpenDialog(ventanaPrincipal);
	}

	public void redirigirPerfil(javafx.event.ActionEvent event) throws Exception {
		String nombreUsuarioActivo = gestorPerfilUsuarios.getUsuarioActivo().getNombreUsuario();
		rutas.redirigirPerfilIndex(nombreUsuarioActivo ,event);
	}
}