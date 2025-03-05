//ANOT
/*
 * MAX(ID); O MAX RETORNA O MAIOR VALOR DE UMA COLUNA ESPECIFICA
 * - NESSE CASO RETORNA O MAIOR VALOR DA COLUNA ID;
 * 
 * CASO HAJA UMA BUSCA EM UMA TABELA COM UM ELEMENTO COM
 * CHAVE ESTRANGEIRA É PRECISO USAR  INNER JOIN;
 * 
 * 
 * */


package br.com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.system.jdbc.ConexaoBanco;
import br.com.system.model.Clientes;
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
			String sql = "INSERT INTO tb_vendas (cliente_id,data_venda,total_venda,observacoes) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, obj.getClientes().getId());
			stmt.setString(2, obj.getData_venda()); 
			stmt.setDouble(3, obj.getTotal_venda());
			stmt.setString(4, obj.getObservacoes());
			stmt.execute();
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
	
	
	//METODO LISTAR HISTÓRICO DE VENDA
	public List<Vendas>HistoricoDeVendas(LocalDate data_inicio, LocalDate data_fim){
		try {
			List<Vendas>listaHistorico = new ArrayList<>();
			String sql = "SELECT v.id,c.nome,date_format(v.data_venda, '%d/%m/%Y') AS data_formatada,"
					+ " v.total_venda,v.observacoes FROM tb_vendas AS v INNER JOIN"
					+ " tb_clientes AS c ON(v.cliente_id=c.id) WHERE v.data_venda BETWEEN?AND?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, data_inicio.toString());
			stmt.setString(2, data_fim.toString());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Vendas v = new Vendas();
				Clientes c = new Clientes();
				v.setId(rs.getInt("v.id"));
				c.setNome(rs.getString("c.nome"));
				v.setClientes(c);
				v.setData_venda(rs.getString("data_formatada"));
				v.setTotal_venda(rs.getDouble("v.total_venda"));
				v.setObservacoes(rs.getString("v.observacoes"));
				
				listaHistorico.add(v);
			}
			
			return listaHistorico;
			
		} catch (SQLException e) {
			throw new RuntimeException("ERRO. Erro ao criar o hirtórico de Vendas! \n " + e);
		}
	}
	
	public double TotalDoDia (LocalDate data_venda) {
		try {
			double total_do_dia = 0;
			String sql = "SELECT SUM(total_venda) AS total FROM tb_vendas WHERE data_venda=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, data_venda.toString()); //CONVERSÃO DA DATA PARA VENDA
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				total_do_dia = rs.getDouble("total");
			}
			
			return total_do_dia;
		} catch (Exception e) {
			throw new RuntimeException("ERRO. Erro ao retornar a posição do dia! \n"+e);
		}
	
	}
	
	
	
}
