package com.Kokl.BL.Factory;

import com.Kokl.BL.Canciones.ICancionDAO;
import com.Kokl.BL.Canciones.MYSQLCancionDAO;
import com.Kokl.BL.Usuarios.IUsuarioDAO;
import com.Kokl.BL.Usuarios.MYSQLUsuarioDAO;

public class MYSQLDAOFactory extends DAOFactory{

	public IUsuarioDAO getUsuarioDAO() {
		return new MYSQLUsuarioDAO();
	}

	public ICancionDAO getCancionDAO() {
		return new MYSQLCancionDAO();
	}
}
