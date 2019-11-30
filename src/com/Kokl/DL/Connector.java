package com.Kokl.DL;

import java.io.*;

public class Connector {

	private static AccesoDB conectorDB;
	private static String CONEXION_BD = "CONEXION_BD.txt";

	private static String URL_MYSQL, DRIVER_PATH, USER, PASS;

	public static  AccesoDB getConnector() throws Exception{
		if(conectorDB == null) {
			setKeys();
			conectorDB = new AccesoDB(DRIVER_PATH, URL_MYSQL, USER, PASS);
		}
		return conectorDB;
	}

	private static void setKeys() throws IOException {
		FileReader imprimir = new FileReader(CONEXION_BD);
		BufferedReader lector = new BufferedReader(imprimir);
		String datosAccesoD = lector.readLine();

		String[] infoAccesoBD = datosAccesoD.split("_");

		URL_MYSQL = infoAccesoBD[0];
		DRIVER_PATH = infoAccesoBD[1];
		USER = infoAccesoBD[2];
		PASS = infoAccesoBD[3];
	}
}
