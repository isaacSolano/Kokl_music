package com.Kokl.UI.Usuarios.RegistrarUsuarios;

import com.Kokl.UI.Rutas;
import com.Kokl.UI.Validaciones;
import com.Kokl.TL.Usuarios.RegistrarUsuarios.RegistrarUsuariosController;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class RegistrarUsuarios {
    Rutas rutas = new Rutas();
    Validaciones gestorValidaciones = new Validaciones();
    RegistrarUsuariosController gestor = new RegistrarUsuariosController();
    Random rdm = new Random();


    public TextField inputNombre, inputApellidos, inputEdad, inputIdentificacion, inputCorreoElectronico, inputNombreUsuario;

    public ComboBox inputPais;

    public PasswordField inputContrasena;

    public GridPane panelErrorEdad, panelErrorContrasena, panelErrorIdentificacion, panelError;

    public void redirigirInicio(javafx.event.ActionEvent event) throws IOException {
        rutas.redirigirInicio(event);
    }

    public boolean verificarEdad() {
        boolean err = false;

        if(!inputEdad.getText().matches("[0-9]*")){
            panelErrorEdad.getChildren().clear();

            Label errEdad = new Label("Utilice valores numéricos unicamente");
            errEdad.setStyle("-fx-text-fill: #D73C2C");

            inputEdad.setStyle("-fx-border-color: transparent transparent #D73C2C transparent;");
            inputEdad.setStyle("-fx-text-fill: #D73C2C");

            panelErrorEdad.add(errEdad, 1, 9);

            err = true;
        }else{
            panelErrorEdad.getChildren().clear();

            inputEdad.setStyle("-fx-border-color: transparent transparent #fbeed7 transparent");
            inputEdad.setStyle("-fx-text-fill: #fbeed7");
        }

        return err;
    }


    public boolean verificarContrasena() {
        boolean err = gestorValidaciones.validarContrasena(inputContrasena);

        if(err){
            panelErrorContrasena.getChildren().clear();

            Label errContrasena = new Label("Tome en cuenta que la contrasena debe contener:\nEntre 8 a 12 dígitos\nUna letra mayúscula\nUna letra minúscula\nAl menos un caracter especial");
            errContrasena.setStyle("-fx-text-fill: #D73C2C");

            panelErrorContrasena.add(errContrasena, 1, 21);

            err = true;
        }else{
            panelErrorContrasena.getChildren().clear();
        }

        return err;
    }

    public boolean verificarIdentificacion(){
        boolean err = false;

        if(!inputIdentificacion.getText().matches("[0-9]*")){
            panelErrorIdentificacion.getChildren().clear();

            Label errIdentificacion = new Label("Utilice valores numéricos únicamente");
            errIdentificacion.setStyle("-fx-text-fill: #D73C2C");

            inputIdentificacion.setStyle("-fx-border-color: transparent transparent #D73C2C transparent;");
            inputIdentificacion.setStyle("-fx-text-fill: #D73C2C");

            panelErrorIdentificacion.add(errIdentificacion, 1,14);

            err = true;
        }else{
            panelErrorIdentificacion.getChildren().clear();

            inputIdentificacion.setStyle("-fx-border-color: transparent transparent #fbeed7 transparent");
            inputIdentificacion.setStyle("-fx-text-fill: #fbeed7");
        }

        return err;
    }

    public void registrarUsuario(javafx.event.ActionEvent event) throws Exception {
        boolean errGeneral = false;

        if(verificarEdad() || verificarContrasena() || verificarIdentificacion()){
            errGeneral = true;
        }

        boolean errInputNombre = gestorValidaciones.validarTexto(inputNombre),
                errInputApellidos = gestorValidaciones.validarTexto(inputApellidos),
                errInputEdad = gestorValidaciones.validarTexto(inputEdad),
                errInputPais = gestorValidaciones.validarComboBox(inputPais),
                errInputIdentificacion = gestorValidaciones.validarTexto(inputIdentificacion),
                errInputCorreoElectronico = gestorValidaciones.validarTexto(inputCorreoElectronico),
                errInputContrasena = gestorValidaciones.validarTexto(inputContrasena),
                errInputNombreUsuario = gestorValidaciones.validarTexto(inputNombreUsuario);

        if(errInputNombre || errInputApellidos || errInputEdad || errInputPais || errInputIdentificacion || errInputCorreoElectronico || errInputContrasena || errInputNombreUsuario){
            errGeneral = true;
        }

        if(errGeneral){
            panelError.getChildren().clear();

            Label txtErrGeneral = new Label("Verifíque que la información sea correcta.");
            txtErrGeneral.setStyle("-fx-text-fill: #D73C2C");

            panelError.add(txtErrGeneral, 1, 27);
        }else{
            panelError.getChildren().clear();

            String nombre = inputNombre.getText(),
                    apellidos = inputApellidos.getText(),
                    nombreUsuario = inputNombreUsuario.getText(),
                    contrasena = inputContrasena.getText(),
                    correoElectronico = inputCorreoElectronico.getText(),
                    pais = (String) inputPais.getValue(),
                    codigoActivacion = String.format("%06d", (rdm.nextInt(999999)));

            int edad = Integer.parseInt(inputEdad.getText()),
                identificacion = Integer.parseInt(inputIdentificacion.getText()),
                cantidadUsuarios = gestor.obtenerCantidadUsuarios(),
                errRegistro = -1;

            boolean registrar = false;

            Alert alertErrRegistro = new Alert(Alert.AlertType.ERROR);
            alertErrRegistro.setTitle("Error");
            alertErrRegistro.setHeaderText("Error al verificar su información.");

            DialogPane dialogErrRegistro = alertErrRegistro.getDialogPane();
            dialogErrRegistro.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
            dialogErrRegistro.getStyleClass().add("alert");

            if(cantidadUsuarios == 0){
                Alert alertConfAdmin = new Alert(Alert.AlertType.CONFIRMATION);
                alertConfAdmin.setTitle("Confirmar");
                alertConfAdmin.setHeaderText("¿Registrar como administrador?");
                alertConfAdmin.setContentText("¿Desea que el usuario " + nombre + " se registre como administrador?");

                DialogPane dialogConfAdmin = alertConfAdmin.getDialogPane();
                dialogConfAdmin.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
                dialogConfAdmin.getStyleClass().add("alert");

                Optional<ButtonType> confRegistro = alertConfAdmin.showAndWait();

                if(confRegistro.get() == ButtonType.OK){
                    registrar = true;
                }else{
                    alertErrRegistro.setContentText("No se púdo confirmar el registro.");
                    alertErrRegistro.showAndWait();
                }
            }else{
                registrar = true;
            }

            if(registrar){
                gestor.enviarCodigoActivacion(correoElectronico, codigoActivacion);
                errRegistro = gestor.registrarUsuario(nombre, apellidos, nombreUsuario, contrasena, correoElectronico, codigoActivacion, pais, identificacion, edad, cantidadUsuarios);
            }

            switch (errRegistro){
                case 1:
                    alertErrRegistro.setContentText("El usuario no cumple la edad mínima requerida.");
                    alertErrRegistro.showAndWait();
                    break;

                case 2:
                    alertErrRegistro.setContentText("El nombre de usuario ya está registrado en el sistema.");
                    alertErrRegistro.showAndWait();
                    break;

                case 0:
                    Alert alertInfRegistro = new Alert(Alert.AlertType.INFORMATION);
                    alertInfRegistro.setTitle("Registrado");
                    alertInfRegistro.setHeaderText("El usuario se registró correctamente.");
                    alertInfRegistro.setContentText("Su información ha sido almacenada en el sistema, inicie sesión.");

                    DialogPane dialogInfRegistro = alertInfRegistro.getDialogPane();
                    dialogInfRegistro.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
                    dialogInfRegistro.getStyleClass().add("alert");

                    alertInfRegistro.showAndWait();

                    rutas.redirigirInicio(event);
                    break;
            }
        }
    }
}