package com.Kokl.UI.Usuarios.Index;

import com.Kokl.UI.Rutas;
import java.io.IOException;

public class Index {
    Rutas rutas = new Rutas();

    public void redirigirInicioSesion(javafx.event.ActionEvent event) throws IOException {
        rutas.redirigirIniciarSesion(event);
    }

    public void redirigirRegistrarse(javafx.event.ActionEvent event) throws IOException{
        rutas.redirigirRegistrarUsuario(event);
    }
}