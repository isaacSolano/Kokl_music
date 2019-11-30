package com.Kokl.UI.Usuarios.InicioSesion;

import com.Kokl.UI.Rutas;
import com.Kokl.UI.Validaciones;
import com.Kokl.TL.Usuarios.IniciarSesion.IniciarSesionController;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class InicioSesion {
    Rutas rutas = new Rutas();
    Validaciones gestorValidaciones = new Validaciones();
    IniciarSesionController gestor = new IniciarSesionController();

    public TextField inputNombreUsuario;
    public PasswordField inputContrasena;

    public GridPane panelError;

    public void iniciarSesion(javafx.event.ActionEvent event) throws Exception {
        boolean errInputNombreUsuario = gestorValidaciones.validarTexto(inputNombreUsuario),
                errInputContrasena = gestorValidaciones.validarTexto(inputContrasena),
                errGeneral = false;

        if(errInputNombreUsuario || errInputContrasena){
            panelError.getChildren().clear();

            Label txtErrGeneral = new Label("Verifique que la informacion sea correcta");

            txtErrGeneral.setStyle("-fx-text-fill: #D73C2C");

            panelError.add(txtErrGeneral, 1, 18);

            errGeneral = true;
        }

        if(!errGeneral){
            int statInicioSesion = gestor.iniciarSesion(inputNombreUsuario.getText(), inputContrasena.getText());

            Alert alertErrInicioSesion = new Alert(Alert.AlertType.ERROR);
            alertErrInicioSesion.setTitle("Error");
            alertErrInicioSesion.setHeaderText("Error al validar su información.");

            DialogPane dialogErrInicioSesion = alertErrInicioSesion.getDialogPane();
            dialogErrInicioSesion.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
            dialogErrInicioSesion.getStyleClass().add("alert");

            switch(statInicioSesion){
                case 0:
                    rutas.redirigirPerfilIndex(inputNombreUsuario.getText(), event);
                    break;

                case 1:
                    alertErrInicioSesion.setContentText("El usuario no se encuentra registrado.");

                    ButtonType confirmar = new ButtonType("Registrar usuario", ButtonBar.ButtonData.YES);
                    ButtonType volver = new ButtonType("Volver", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alertErrInicioSesion.getButtonTypes().setAll(confirmar, volver);

                    alertErrInicioSesion.showAndWait().ifPresent(confirmacion -> {
                        if(confirmacion.getButtonData() == ButtonType.YES.getButtonData()){
                            try {
                                rutas.redirigirRegistrarUsuario(event);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    break;

                case 2:
                    alertErrInicioSesion.setContentText("La contraseña ingresada es incorrecta.");

                    alertErrInicioSesion.showAndWait();
                    break;
            }
        }
    }

    public void redirigirInicio(javafx.event.ActionEvent event) throws IOException {
        rutas.redirigirInicio(event);
    }
}