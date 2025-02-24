package br.com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import br.com.system.jdbc.ConexaoBanco;
import br.com.system.model.Vendas;

public class VendasDao {
	private Connection conn;
	
	//METODOS CONSTRUTORES
	public VendasDao() {
	this.conn = new ConexaoBanco().getConnection();
	}
	
	public void Salvar(Vendas obj) {
		try {
			String sql = "INSERT INTO tb_vendas (cliente_id,data_venda,total_venda, observacoes) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, obj.getClientes().getId());
			stmt.setString(2, obj.getData_venda()); 
			stmt.setDouble(3, obj.getTotal_venda());
			stmt.setString(4, obj.getObservacoes());
			stmt.close();
			JOptionPane.showMessageDialog(null, "Venda realizada com sucesso! ");
		} catch (Exception e) {
			throw new RuntimeException("ERRO ao realizar a venda! \n" + e);
		}
	}
	
}
