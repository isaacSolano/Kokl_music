package com.Kokl.UI.Canciones;

import javafx.scene.media.*;

import java.io.File;

public class ReproductorCancion {
	public MediaPlayer reproductorCancion;

	public boolean reproducirCancion(String nombreCancion) {
		boolean err = false;

		String URL = System.getProperty("user.dir")+File.separator+ nombreCancion +".mp3";
		System.out.println(URL);
		Media cancion = new Media(new File(URL).toURI().toString());
		reproductorCancion = new MediaPlayer(cancion);
		reproductorCancion.play();

		return err;
	}

	public boolean pausarCancion(String idCancion) {
		boolean err = false;

		reproductorCancion.pause();

		return err;
	}
}
