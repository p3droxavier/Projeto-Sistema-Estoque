//ANOT
/*
 * MAX(ID); O MAX RETORNA O MAIOR VALOR DE UMA COLUNA ESPECIFICA
 * - NESSE CASO RETORNA O MAIOR VALOR DA COLUNA ID;
 * */


package br.com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import br.com.system.jdbc.ConexaoBanco;
import br.com.system.model.Vendas;

public class VendasDao {
	private Connection conn;
	
	//METODOS CONSTRUTORES
	public VendasDao() {
		this.conn = new ConexaoBanco().getConnection();
	}
	
	
	//METODO PARA SALVAR A VENDA
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
	
	//METODO QUE RETORNA O ID DA ULTIMA VENDA
	public int RetornoDoIdVenda() {
		try {
			int ultimoId = 0;
			String sql = "SELECT MAX(id) id FROM tb_vendas";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Vendas v = new Vendas();
				v.setId(rs.getInt("id"));
				 ultimoId = v.getId();
			}
			return ultimoId;
		} catch (Exception e) {
			throw new RuntimeException("ERRO. Erro ao retornar o ultimo id da venda! ");
		}
	}
}
