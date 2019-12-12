package com.Kokl.BL.Factory;

import com.Kokl.BL.Canciones.ICancionDAO;
import com.Kokl.BL.Listas.IListaDAO;
import com.Kokl.BL.Usuarios.IUsuarioDAO;

public abstract class DAOFactory {
	public static DAOFactory getDaoFactory(int tipo){
		switch (tipo){
			case 1: return new MYSQLDAOFactory();

			default: return null;
		}
	}

	public abstract IUsuarioDAO getUsuarioDAO();
	public abstract ICancionDAO getCancionDAO();
	public abstract IListaDAO getListasDAO();
}