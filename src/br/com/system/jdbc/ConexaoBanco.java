package br.com.system.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoBanco {
	final private String host = "jdbc:mysql://localhost/db_sistema_estoque";
	final private String user = "root";
	final private String passwd = "";
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(host,user,passwd);
		} catch (SQLException erro) {
			erro.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO: Ao se conectar com o banco de dados" + "\n" + erro);
		}
		return null;
	}
}
