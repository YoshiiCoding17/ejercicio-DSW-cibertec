package com.semana02.ejercicio05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDBConexion {

    
	// Accede a las clases del mysqlconnectorXXXX.jar
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Metodo para crear conexiones
	public static Connection getConexion() {
		Connection salida = null;
		try {
			salida = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sistema_prestamo_2024_05_01_security?serverTimezone=America/Lima", 
					"root",
					"root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salida;
	}
}
