//vídeo 7 - criando formulário de clientes em java (inicio)

package br.com.system.jdbc;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.SQLException;



public class TesteDeConexao {
	
	 public static void main(String[] args) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	
	        conn = new ConexaoBanco().getConnection();
	        conn.setAutoCommit(false); //DESABILITA AUTOCOMMIT. GARANTE O CONTROLE DA TRANSAÇÃO
	       
	        String sql = "insert into tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) "
	                + "values ('Pedro da Silva', '123456789', '123.456.789-00', 'joao@mail.com', '123456789', '987654321', '12345-678', 'Rua A', 123, 'Apto 101', 'Bairro B', 'Cidade C', 'Estado D')";
	        
	        stmt = conn.prepareStatement(sql);
	        
	        int rowsAffected = stmt.executeUpdate();  
	        
	        if (rowsAffected > 0) {
	            System.out.println("Cliente inserido com sucesso");
	            conn.commit();//CONFIRMA TRANSAÇÃO
	        } else {
	            System.out.println("Erro ao inserir cliente");
	        }
	        
	    } catch (SQLException erro) {
	        try {
	            if (conn != null) {
	                conn.rollback(); // Desfaz a transação em caso de erro
	            }
	        } catch (SQLException rollbackError) {
	            rollbackError.printStackTrace();
	        }
	        erro.printStackTrace();
	    } finally {
	        // Fecha os recursos para evitar vazamentos
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


}
