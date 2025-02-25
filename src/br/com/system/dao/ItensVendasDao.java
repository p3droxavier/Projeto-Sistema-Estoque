package br.com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.system.jdbc.ConexaoBanco;
import br.com.system.model.ItensVendas;


public class ItensVendasDao {
	private Connection conn;
	
	//METODO CONSTRUTOR
	public ItensVendasDao() {
		this.conn = new ConexaoBanco().getConnection();
	}
	
	public void Salvar(ItensVendas obj) {
		try {
			String sql = "INSERT INTO tb_itensvendas (venda_id,produto_id,qtd,subtotal) "
					+ "VALUE(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, obj.getVendas().getId());
			stmt.setInt(2, obj.getProdutos().getId());
			stmt.setInt(3, obj.getQtd());
			stmt.setDouble(4, obj.getSubtotal());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException("ERRO. Erro ao salvar itens da minha venda! ");
		}
	}
	
}