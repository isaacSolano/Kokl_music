package com.Kokl.UI.Listas.RegistrarLista;

import com.Kokl.TL.Listas.AgregarLista.AgregarListaController;
import com.Kokl.UI.Perfil.Perfil;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

public class RegistrarLista extends Perfil {
	AgregarListaController gestorAgregarLista = new AgregarListaController();
	Random rdm = new Random();

	public void registrarLista(javafx.event.ActionEvent e, String nombreUsuarioActivo) throws Exception {
		TextInputDialog alertInAgregarLista = new TextInputDialog();
		alertInAgregarLista.setHeaderText("Agregar lista de reproducción.");
		alertInAgregarLista.setContentText("Ingrese el nombre de la lista de reproducción:");

		DialogPane dialogInAgregarLista = alertInAgregarLista.getDialogPane();
		dialogInAgregarLista.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
		dialogInAgregarLista.getStyleClass().add("alert");

		Optional<String> resAlert = alertInAgregarLista.showAndWait();
		String nombreLista = alertInAgregarLista.getEditor().getText();

		if(!resAlert.isPresent() || nombreLista.equals("")){
			Alert alertErrInAgregarLista = new Alert(Alert.AlertType.ERROR);
			alertErrInAgregarLista.setTitle("Error");
			alertErrInAgregarLista.setHeaderText("Error al registrar la lista.");
			alertErrInAgregarLista.setContentText("Por favor ingrese un nombre válido.");

			DialogPane dialogErrInAgregarLista = alertErrInAgregarLista.getDialogPane();
			dialogErrInAgregarLista.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
			dialogErrInAgregarLista.getStyleClass().add("alert");

			alertErrInAgregarLista.showAndWait();
		}else{
			String idLista = String.format("%06d", (rdm.nextInt(999999)));
			String patronFecha = "yyyy-MM-dd";
			SimpleDateFormat formatoFecha = new SimpleDateFormat(patronFecha);

			String fechaCreacion = formatoFecha.format(new Date());
			int calificacion = 0;

			boolean err = gestorAgregarLista.agregarLista(nombreUsuarioActivo, nombreLista, idLista, fechaCreacion, calificacion);

			if(!err){
				Alert alertInfAgregarLista = new Alert(Alert.AlertType.INFORMATION);
				alertInfAgregarLista.setTitle("Agregada");
				alertInfAgregarLista.setHeaderText("La lista se agregó correctamente.");
				alertInfAgregarLista.setContentText("La lista se agregó al sistema.");

				DialogPane dialogInfAgregarLista = alertInfAgregarLista.getDialogPane();
				dialogInfAgregarLista.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
				dialogInfAgregarLista.getStyleClass().add("alert");

				alertInfAgregarLista.showAndWait();

				rutas.redirigirAgregarCancionesLista(e, idLista);
			}else{
				Alert alertErrAgregarLista = new Alert(Alert.AlertType.ERROR);
				alertErrAgregarLista.setTitle("Error");
				alertErrAgregarLista.setHeaderText("Error al registrar la lista.");
				alertErrAgregarLista.setContentText("Hubo un error en el proceso, intente más tarde.");

				DialogPane dialogErrAgregarLista = alertErrAgregarLista.getDialogPane();
				dialogErrAgregarLista.getStylesheets().add(getClass().getResource("/com/Kokl/UI/DialogStyles.css").toExternalForm());
				dialogErrAgregarLista.getStyleClass().add("alert");

				alertErrAgregarLista.showAndWait();
			}
		}

	}
}