package com.Kokl.UI;

import com.Kokl.UI.Canciones.EditarCanciones.EditarCanciones;
import com.Kokl.UI.Canciones.ListarCanciones.Principal.Principal;
import com.Kokl.UI.Canciones.RegistrarCanciones.RegistrarCanciones;
import com.Kokl.UI.Listas.AgregarCancionesLista.AgregarCancionesLista;
import com.Kokl.UI.Listas.ListarCancionesLista.ListarCancionesLista;
import com.Kokl.UI.Usuarios.PerfilIndex.PerfilIndex;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Rutas {

    public void redirigirInicio(javafx.event.ActionEvent event) throws IOException {
        Parent inicio = FXMLLoader.load(getClass().getResource("/com/Kokl/UI/Usuarios/Index/Index.fxml"));
        Scene inicioEscena = new Scene(inicio, 800, 775);

        Stage ventana =(Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("Kokl_music");
        ventana.setScene(inicioEscena);
        ventana.show();
    }

    public void redirigirIniciarSesion(javafx.event.ActionEvent event) throws IOException {
        Parent inicioSesion = FXMLLoader.load(getClass().getResource("/com/Kokl/UI/Usuarios/IniciarSesion/InicioSesion.fxml"));
        Scene inicioSesionEscena = new Scene(inicioSesion, 800, 775);

        Stage ventana =(Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("Inicio sesion");
        ventana.setScene(inicioSesionEscena);
        ventana.show();
    }

    public void redirigirRegistrarUsuario(javafx.event.ActionEvent event) throws IOException{
        Parent registroUsuarios = FXMLLoader.load(getClass().getResource("/com/Kokl/UI/Usuarios/RegistrarUsuarios/RegistrarUsuarios.fxml"));
        Scene registroUsuariosEscena = new Scene(registroUsuarios, 800, 775);

        Stage ventana = (Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("Registro usuarios");
        ventana.setScene(registroUsuariosEscena);
        ventana.show();
    }

    public void redirigirPerfilIndex(String nombreUsuario, javafx.event.ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Kokl/UI/Usuarios/PerfilIndex/PerfilIndex.fxml"));
        Parent perfil = loader.load();

        PerfilIndex controller = loader.getController();
        controller.setUsuarioActivo(nombreUsuario, event);

        Scene perfilEscena = new Scene(perfil, 800, 775);

        Stage ventana = (Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("Perfil");
        ventana.setScene(perfilEscena);
        ventana.show();
    }

	public void redirigirCambiarContrasena(javafx.event.ActionEvent event) throws IOException {
		Parent cambiarContrasena = FXMLLoader.load(getClass().getResource("/com/Kokl/UI/Usuarios/CambiarContrasena/CambiarContrasena.fxml"));
		Scene cambiarContrasenaEscena = new Scene(cambiarContrasena, 800, 775);

		Stage ventana = (Stage)((Node) event.getSource()).getScene().getWindow();
		ventana.setTitle("Cambie su contraseña");
		ventana.setScene(cambiarContrasenaEscena);
		ventana.show();
	}

	public void redirigirRegistrarCancion(String nombreUsuarioActivo, javafx.event.ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Kokl/UI/Canciones/RegistrarCanciones/RegistrarCanciones.fxml"));
        Parent registrarCancion = loader.load();

        Scene registrarCancionEscena = new Scene(registrarCancion, 800, 775);

        Stage ventana = (Stage)((Node) event.getSource()).getScene().getWindow();
        RegistrarCanciones controller = loader.getController();
        controller.setNavegacion(nombreUsuarioActivo, ventana);

        ventana.setTitle("Registro canciones");
        ventana.setScene(registrarCancionEscena);
        ventana.show();
    }

    public void redirigirListarCanciones(String nombreUsuarioActivo, javafx.event.ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Kokl/UI/Canciones/ListarCanciones/Principal/Principal.fxml"));
        Parent listarCanciones = loader.load();

        Principal controller = loader.getController();
        controller.setPaneles(nombreUsuarioActivo);

        Scene listarCancionesEscena = new Scene(listarCanciones, 800, 775);

        Stage ventana = (Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("Lista canciones");
        ventana.setScene(listarCancionesEscena);
        ventana.show();
    }

	public void redirigirEditarCancion(javafx.event.ActionEvent event, String infoCancion) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Kokl/UI/Canciones/EditarCanciones/EditarCanciones.fxml"));
        Parent editarCanciones = loader.load();

        EditarCanciones controller = loader.getController();
        controller.setFormulario(infoCancion);

        Scene editarCancionesEscena = new Scene(editarCanciones, 800, 775);

        Stage ventana = (Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("Editar canciones");
        ventana.setScene(editarCancionesEscena);
        ventana.show();
    }

    public void redirigirAgregarCancionesLista(javafx.event.ActionEvent event, String idLista) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Kokl/UI/Listas/AgregarCancionesLista/AgregarCancionesLista.fxml"));
        Parent agregarCancionesLista = loader.load();

        AgregarCancionesLista controller = loader.getController();
        controller.setAgregarCancionesLista(idLista);

        Scene agregarCancionesListaEscena = new Scene(agregarCancionesLista, 800, 775);

        Stage ventana = (Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("Agregar canciones");
        ventana.setScene(agregarCancionesListaEscena);
        ventana.show();
    }

    public void redirigirlistarCancionesLista(ActionEvent event, String idLista) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Kokl/UI/Listas/ListarCancionesLista/ListarCancionesLista.fxml"));
        Parent listarCancionesLista = loader.load();

        ListarCancionesLista controller = loader.getController();
        controller.setListaCancionesLista(idLista);

        Scene listarCancionesListaEscena = new Scene(listarCancionesLista, 800, 775);

        Stage ventana = (Stage)((Node) event.getSource()).getScene().getWindow();
        ventana.setTitle("Agregar canciones");
        ventana.setScene(listarCancionesListaEscena);
        ventana.show();
    }
}