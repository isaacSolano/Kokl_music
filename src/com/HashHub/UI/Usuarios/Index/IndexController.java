package com.HashHub.UI.Usuarios.Index;

import com.HashHub.UI.Rutas;
import java.io.IOException;

public class IndexController {
    Rutas rutas = new Rutas();

    public void redirigirInicioSesion(javafx.event.ActionEvent event) throws IOException {
        rutas.redirigirInicioSesion(event);
    }

    public void redirigirRegistrarse(javafx.event.ActionEvent event) throws IOException{
        rutas.redirigirRegistrarUsuario(event);
    }
}