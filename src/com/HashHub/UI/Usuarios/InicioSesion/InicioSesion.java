package com.HashHub.UI.Usuarios.InicioSesion;

import com.HashHub.UI.Rutas;
import java.io.IOException;

public class InicioSesion {
    Rutas rutas = new Rutas();

    public void redirigirInicio(javafx.event.ActionEvent event) throws IOException {
        rutas.redirigirInicio(event);
    }
}
