package com.HashHub.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Rutas {

    public void redirigirInicio(javafx.event.ActionEvent event) throws IOException {
        Parent inicio = FXMLLoader.load(getClass().getResource("/com/HashHub/UI/Usuarios/Index/Index.fxml"));
        Scene inicioEscena = new Scene(inicio, 700, 675);

        Stage ventana =(Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("HashHub Music");
        ventana.setScene(inicioEscena);
        ventana.show();
    }

    public void redirigirInicioSesion(javafx.event.ActionEvent event) throws IOException {
        Parent inicioSesion = FXMLLoader.load(getClass().getResource("/com/HashHub/UI/Usuarios/InicioSesion/InicioSesion.fxml"));
        Scene inicioSesionEscena = new Scene(inicioSesion, 700, 675);

        Stage ventana =(Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("Inicio sesion");
        ventana.setScene(inicioSesionEscena);
        ventana.show();
    }

    public void redirigirRegistrarUsuario(javafx.event.ActionEvent event) throws IOException{
        Parent registrarse = FXMLLoader.load(getClass().getResource("/com/HashHub/UI/Usuarios/RegistrarUsuarios/RegistrarUsuarios.fxml"));
        Scene registrarseEscena = new Scene(registrarse, 700, 675);

        Stage ventana = (Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("Registro usuarios");
        ventana.setScene(registrarseEscena);
        ventana.show();
    }
}
