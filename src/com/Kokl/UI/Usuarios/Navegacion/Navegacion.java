package com.Kokl.UI.Usuarios.Navegacion;

import com.Kokl.UI.Perfil.Perfil;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class Navegacion extends Perfil {
	Random rdm = new Random();


	public GridPane crearPanelNavegacion(String nombreUsuarioActivo) throws Exception {
		int instance = gestorPerfilUsuarios.setUsuarioActivo(nombreUsuarioActivo);

		GridPane panelNavegacion= new GridPane();

		Button btnPerfil = new Button("Inicio");
		btnPerfil.setOnAction( e -> {
			try {
				rutas.redirigirPerfilIndex(nombreUsuarioActivo, e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

        if(instance == 1) {
            Button btnAgregarCanciones = new Button("Agregar canción");
            btnAgregarCanciones.setOnAction( e -> {
				try {
					agregarCancion(nombreUsuarioActivo, e);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
            panelNavegacion.add(btnAgregarCanciones, 2, 1);
        }

        Button btnNuevaLista = new Button("Agregar lista");
//        agregar action event

		Button btnListarCanciones = new Button("Listar canciones");
		btnListarCanciones.setOnAction( e -> {
			try{
				rutas.redirigirListarCanciones(nombreUsuarioActivo, e);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

        Button btnCerrarSesion = new Button("Cerrar sesión");
        btnCerrarSesion.setOnAction( e -> {
            try {
                cerrarSesion(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button btnCambiarContrasena = new Button("Cambiar contraseña");
        btnCambiarContrasena.setOnAction( e -> {
        	try{
        		cambiarContrasena(e);
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		});

        panelNavegacion.add(btnPerfil, 1, 1);
        panelNavegacion.add(btnNuevaLista, 3, 1);
        panelNavegacion.add(btnListarCanciones, 4, 1);
        panelNavegacion.add(btnCambiarContrasena, 5, 1);
        panelNavegacion.add(btnCerrarSesion, 6, 1);

        panelNavegacion.getStyleClass().add("menu");

        return panelNavegacion;
    }

    public void agregarCancion(String nombreUsuarioActivo, javafx.event.ActionEvent e) throws Exception {
		rutas.redirigirRegistrarCancion(nombreUsuarioActivo, e);
	}

    public void cerrarSesion(javafx.event.ActionEvent event) throws IOException {
        Alert alertConfCerrarSesion = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfCerrarSesion.setTitle("Verificar");
        alertConfCerrarSesion.setHeaderText(null);
        alertConfCerrarSesion.setContentText("¿Desea cerrar sesión?");

        DialogPane dialogConfCerrarSesion = alertConfCerrarSesion.getDialogPane();
        dialogConfCerrarSesion.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
        dialogConfCerrarSesion.getStyleClass().add("alert");

        Optional<ButtonType> resAlert = alertConfCerrarSesion.showAndWait();

        if(resAlert.get() == ButtonType.OK){
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

    public void cambiarContrasena(javafx.event.ActionEvent event) throws Exception {
		TextInputDialog alertInVerificarNombreUsuario = new TextInputDialog();
		alertInVerificarNombreUsuario.setHeaderText("Verifiquemos su cuenta.");
		alertInVerificarNombreUsuario.setContentText("Ingrese su nombre de usuario:");

		DialogPane dialogInVerificarNombreUsuario = alertInVerificarNombreUsuario.getDialogPane();
		dialogInVerificarNombreUsuario.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
		dialogInVerificarNombreUsuario.getStyleClass().add("alert");

		Optional<String> resAlert = alertInVerificarNombreUsuario.showAndWait();

		String inputNombreUsuario = alertInVerificarNombreUsuario.getEditor().getText();

		if(resAlert.isPresent()){
			String contrasenaTemp = String.format("%06d", (rdm.nextInt(999999)));
			boolean err = gestorPerfilUsuarios.cambiarContrasenaTemp(inputNombreUsuario, contrasenaTemp);

			if(err){
				Alert alertErrCambioContrasena = new Alert(Alert.AlertType.ERROR);
				alertErrCambioContrasena.setTitle("Error");
				alertErrCambioContrasena.setHeaderText("Error al verificar su información.");
				alertErrCambioContrasena.setContentText("El nombre de usuario no coincide con el usuario activo.");

				DialogPane dialogErrCambioContrasena = alertErrCambioContrasena.getDialogPane();
				dialogErrCambioContrasena.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
				dialogErrCambioContrasena.getStyleClass().add("alert");

				alertErrCambioContrasena.showAndWait();
			}else{
				Alert alertErrCambioContrasena = new Alert(Alert.AlertType.INFORMATION);
				alertErrCambioContrasena.setTitle("Registrado");
				alertErrCambioContrasena.setHeaderText("Su contraseña se cambio exitosamente.");
				alertErrCambioContrasena.setContentText("Su contraseña temporal se envió a su correo electrónico, se le solicitará en el siguiente ingreso.");

				DialogPane dialogErrCambioContrasena = alertErrCambioContrasena.getDialogPane();
				dialogErrCambioContrasena.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
				dialogErrCambioContrasena.getStyleClass().add("alert");

				alertErrCambioContrasena.showAndWait();
			}
		}
	}
}
