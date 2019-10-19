package com.HashHub.UI.Usuarios.Index;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
        primaryStage.setTitle("HashHub music");
        primaryStage.setScene(new Scene(root, 700, 675));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
