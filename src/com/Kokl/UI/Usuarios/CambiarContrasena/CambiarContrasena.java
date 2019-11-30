package com.Kokl.UI.Usuarios.CambiarContrasena;

import com.Kokl.UI.Validaciones;
import com.Kokl.UI.Perfil.Perfil;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class CambiarContrasena extends Perfil {
	Validaciones gestorValidaciones = new Validaciones();

	public TextField inputcontrasenaTemp, inputContrasena, inputConfContrasena;
	public GridPane panelErr, panelErrorContrasena, panelErrorConfContrasena;

	public boolean verificarErrContrasena() {
		boolean err = gestorValidaciones.validarContrasena(inputContrasena);

		if(err){
            panelErrorContrasena.getChildren().clear();

            Label errContrasena = new Label("Tome en cuenta que la contrasena debe contener:\nEntre 8 a 12 dígitos\nUna letra mayúscula\nUna letra minúscula\nAl menos un caracter especial");
            errContrasena.setStyle("-fx-text-fill: #D73C2C");

            panelErrorContrasena.add(errContrasena, 1, 9);

            err = true;
        }else{
            panelErrorContrasena.getChildren().clear();
        }

        return err;
	}

	public boolean verificarErrConfContrasena() {
		boolean err = false;

		if(!inputContrasena.getText().equals(inputConfContrasena.getText())){
			panelErrorConfContrasena.getChildren().clear();

			Label errConfContrasena = new Label("La contraseña y su confirmación no coinciden");
			errConfContrasena.setStyle("-fx-text-fill: #D73C2C");

			panelErrorConfContrasena.add(errConfContrasena, 1, 9);

			err = true;
		}else{
			panelErrorConfContrasena.getChildren().clear();
		}

		return err;
	}

	public void cambiarContrasena(javafx.event.ActionEvent event) throws Exception {
		boolean err = false,
				errCambio = false;
		String nombreUsuarioActivo = gestorPerfilUsuarios.getUsuarioActivo().getNombreUsuario();

		if(verificarErrConfContrasena() && verificarErrContrasena()){
			panelErr.getChildren().clear();

			Label errCambiarContrasena = new Label("Verifíque que la información sea correcta.");
			errCambiarContrasena.setStyle("-fx-text-fill: #D73C2C");

			panelErr.add(errCambiarContrasena, 1, 9);

			err = true;
		}else{
			panelErr.getChildren().clear();
		}

		if(!err){
			String contrasenaTemp = inputcontrasenaTemp.getText(),
					nuevaContrasena = inputContrasena.getText();
			errCambio = gestorPerfilUsuarios.cambiarNuevaContrasena(contrasenaTemp, nuevaContrasena);
		}

		if(errCambio){
			Alert alertErrCambio = new Alert(Alert.AlertType.ERROR);
			alertErrCambio.setTitle("Error");
			alertErrCambio.setHeaderText("Error al verificar su información.");
			alertErrCambio.setContentText("La contraseña ingresada no es la misma enviada a su correo electrónico.");

			DialogPane dialogErrCambio = alertErrCambio.getDialogPane();
			dialogErrCambio.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogErrCambio.getStyleClass().add("alert");

			alertErrCambio.showAndWait();
		}else{
			Alert alertInfCambio = new Alert(Alert.AlertType.INFORMATION);
			alertInfCambio.setTitle("Registrado");
			alertInfCambio.setHeaderText("La contrasena se registró correctamente.");
			alertInfCambio.setContentText("Su nueva contrasena ha sido almacenada en el sistema.");

			DialogPane dialogInfCambio = alertInfCambio.getDialogPane();
			dialogInfCambio.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogInfCambio.getStyleClass().add("alert");

			alertInfCambio.showAndWait();

			rutas.redirigirPerfilIndex(nombreUsuarioActivo, event);
		}
	}

	public void redirigirInicio(ActionEvent event) throws IOException {
		gestorPerfilUsuarios.cerrarSesion();
		rutas.redirigirInicio(event);

		Alert alertInfCerrarSesion = new Alert(Alert.AlertType.INFORMATION);
		alertInfCerrarSesion.setHeaderText(null);
		alertInfCerrarSesion.setContentText("Gracias por su visita");

		DialogPane dialogInfCerrarSesion = alertInfCerrarSesion.getDialogPane();
		dialogInfCerrarSesion.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
		dialogInfCerrarSesion.getStyleClass().add("alert");

		alertInfCerrarSesion.showAndWait();
	}
}
