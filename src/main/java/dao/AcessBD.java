package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class AcessBD {

	public static Connection getConnection() {
		
		String urlConnection = "jdbc:mysql://localhost/itf_web";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(urlConnection, "root", "");
			System.out.println("CONECTADO COM SUCESSO");
			return conn;

		} catch (Exception e) {
			System.out.println("FALHA AO SE CONECTAR");
			return null;
		} 
	}
 
}
