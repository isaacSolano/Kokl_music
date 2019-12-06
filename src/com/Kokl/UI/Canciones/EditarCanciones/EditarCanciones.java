package com.Kokl.UI.Canciones.EditarCanciones;

import com.Kokl.TL.Canciones.EditarCanciones.EditarCancionesController;
import com.Kokl.UI.Perfil.Perfil;
import com.Kokl.UI.Validaciones;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class EditarCanciones extends Perfil {
	Validaciones validaciones = new Validaciones();
	EditarCancionesController gestorEditarCanciones = new EditarCancionesController();

	String nombreUsuarioActivo = gestorPerfilUsuarios.getUsuarioActivo().getNombreUsuario(),
			idCancion;

	public GridPane panelPrincipal, panelContenido, panelError;
	public TextField inputNombre, inputGenero, inputArtista, inputCompositor, inputAlbum, inputCalificacion;
	public DatePicker inputFechaLanzamiento;

	public void setFormulario(String infoCancion) throws Exception {
		GridPane panelNavegacion = navegacion.crearPanelNavegacion(nombreUsuarioActivo);
		panelPrincipal.add(panelNavegacion, 1, 1);

		inputNombre.setText(infoCancion.split("_")[0]);
		inputGenero.setText(infoCancion.split("_")[1]);
		inputArtista.setText(infoCancion.split("_")[2]);
		inputCompositor.setText(infoCancion.split("_")[3]);
		inputFechaLanzamiento.setValue(LocalDate.parse(infoCancion.split("_")[4]));
		inputAlbum.setText(infoCancion.split("_")[5]);
		inputCalificacion.setText(infoCancion.split("_")[6]);

		idCancion = infoCancion.split("_")[8];
	}

	public void editarCancion(javafx.event.ActionEvent e) throws Exception {
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
					album = inputAlbum.getText();

			int calificacion = Integer.parseInt(inputCalificacion.getText());

			boolean err = gestorEditarCanciones.editarCancion(nombre, genero, artista, compositor, fechaLanzamiento, album, calificacion, idCancion);

			if(err){
				Alert alertErrEditar = new Alert(Alert.AlertType.ERROR);
				alertErrEditar.setTitle("Error");
				alertErrEditar.setHeaderText("Error al editar la información.");
				alertErrEditar.setContentText("La canción no se púdo editar intente más tarde.");

				DialogPane dialogErrEditar = alertErrEditar.getDialogPane();
				dialogErrEditar.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
				dialogErrEditar.getStyleClass().add("alert");

				alertErrEditar.showAndWait();
			}else{
				Alert alertInfEditar = new Alert(Alert.AlertType.INFORMATION);
				alertInfEditar.setTitle("Editado");
				alertInfEditar.setHeaderText("La canción se editó correctamente.");
				alertInfEditar.setContentText("Su información ha sido almacenada en el sistema.");

				DialogPane dialogInfEditar = alertInfEditar.getDialogPane();
				dialogInfEditar.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
				dialogInfEditar.getStyleClass().add("alert");

				alertInfEditar.showAndWait();

				rutas.redirigirPerfilIndex(nombreUsuarioActivo, e);
			}
		}
	}

	public void redirigirPerfil(javafx.event.ActionEvent e) throws Exception {

		rutas.redirigirPerfilIndex(nombreUsuarioActivo, e);
	}
}
