package com.Kokl.UI.Usuarios.PerfilIndex;

import com.Kokl.BL.Usuarios.Obj.Usuario;

import com.Kokl.UI.Perfil.Perfil;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Optional;

public class PerfilIndex extends Perfil {
    public GridPane panelPrincipal, panelContenido;

    public void setUsuarioActivo(String nombreUsuarioActivo, javafx.event.ActionEvent event) throws Exception {
        GridPane panelNavegacion = navegacion.crearPanelNavegacion(nombreUsuarioActivo);
        Label etiquetaBienvenida = new Label("Buenveni@ " + nombreUsuarioActivo);

        panelPrincipal.add(panelNavegacion, 1, 1);
        panelContenido.add(etiquetaBienvenida, 1, 2);

        verificarCodigoActivacion(event);
        verificarContrasenaTemp(event);
    }

    public void verificarCodigoActivacion(javafx.event.ActionEvent event) throws Exception {
        String codigoActivacion = gestorPerfilUsuarios.getCodigoVerificacion();

        if(!codigoActivacion.equals("Verificado")){
            TextInputDialog alertInVerificarCodigo = new TextInputDialog();
            alertInVerificarCodigo.setHeaderText("Verifiquemos su cuenta.");
            alertInVerificarCodigo.setContentText("Ingrese el código de verificación enviado a su correo electrónico:");

            DialogPane dialogInVerificarCodigo = alertInVerificarCodigo.getDialogPane();
            dialogInVerificarCodigo.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
            dialogInVerificarCodigo.getStyleClass().add("alert");

            Optional<String> resAlert = alertInVerificarCodigo.showAndWait();

            String inputCodigoActivacion = alertInVerificarCodigo.getEditor().getText();
            boolean errVerificarCodigoActivacion = gestorPerfilUsuarios.verificarCodigoActivacion(inputCodigoActivacion);

            if(errVerificarCodigoActivacion || !resAlert.isPresent()){
                Alert alertErrCodigoVerificacion = new Alert(Alert.AlertType.ERROR);
                alertErrCodigoVerificacion.setTitle("Error");
                alertErrCodigoVerificacion.setHeaderText("Error al verificar su código.");
                alertErrCodigoVerificacion.setContentText("No se pudo verificar el código de activación, intente de nuevo.");

                DialogPane dialogErrCodigoVerificacion = alertErrCodigoVerificacion.getDialogPane();
                dialogErrCodigoVerificacion.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
                dialogErrCodigoVerificacion.getStyleClass().add("alert");

                alertErrCodigoVerificacion.showAndWait();

                gestorPerfilUsuarios.cerrarSesion();
                rutas.redirigirInicio(event);
            }
        }
    }

    public void verificarContrasenaTemp(javafx.event.ActionEvent event) throws IOException{
        Usuario usuarioActivo = gestorPerfilUsuarios.getUsuarioActivo();

        if(usuarioActivo.getContrasenaTemp()){
            rutas.redirigirCambiarContrasena(event);
        }
    }
}