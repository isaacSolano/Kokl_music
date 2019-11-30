package com.Kokl.DL;

import java.sql.*;

public class AccesoDB {

	private Connection CONN;
	private Statement STMT;

	public AccesoDB(String DRIVER, String URL, String USER, String PASS) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		CONN = DriverManager.getConnection(URL, USER, PASS);
		STMT = CONN.createStatement();
	}

	// Consultas => INSERT - UPDATE - DELETE
	public void POST(String query) throws Exception {
		STMT.executeUpdate(query);
	}

	// Consultas => SELECT
	public ResultSet GET(String query) throws Exception {
		ResultSet res = STMT.executeQuery(query);
		return res;
	}
}