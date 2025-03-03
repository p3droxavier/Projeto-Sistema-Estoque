//ANOT PROJECT
/*
 * PERTO DA LINHA 45 EM 'METODO DE LISTAR OS ITENS DA VENDA'
 * - PASSA O VENDA_ID COMO PARAMETRO POIS IRA LISTAR COM BASE NO ID DA VENDA
 * 
 * */

package br.com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.system.jdbc.ConexaoBanco;
import br.com.system.model.ItensVendas;
import br.com.system.model.Produtos;


public class ItensVendasDao {
	private Connection conn;
	 
	//METODO CONSTRUTOR
	public ItensVendasDao() {
		this.conn = new ConexaoBanco().getConnection();
	}
	
	//METODO DE SALVAR ITENS VENDAS
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
	
	
	//METODO DE LISTAR OS ITENS VENDAS EM 'FormsDetalhesVenda' APOS REDIRECIONAMENTO
	public List<ItensVendas>ListarItens(int venda_id){
		try {
			List<ItensVendas> lista = new ArrayList<>();
			String sql = "SELECT p.id,p.descricao,i.qtd,p.preco,i.subtotal FROM tb_itensvendas AS i INNER JOIN "
					+ "tb_produtos AS p ON(i.produto_id=p.id) WHERE i.venda_id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, venda_id);
			ResultSet rs = stmt.executeQuery(); 
			
			//PERCORRE TODA A LISTA DA CONSULTA
			while(rs.next()) {
				ItensVendas item = new ItensVendas();
				Produtos p = new Produtos();
				p.setId(rs.getInt("p.id")); //PEGA O ELEMENTO 
				item.setProdutos(p);// JOGA PRA DENTRO DO ITEM
				p.setDescricao(rs.getString("p.descricao"));//PEGA O ELEMENTO 
				item.setProdutos(p);// JOGA PRA DENTRO DO ITEM
				item.setQtd(rs.getInt("i.qtd"));
				p.setPreco(rs.getDouble("p.preco"));//PEGA O ELEMENTO 
				item.setProdutos(p);// JOGA PRA DENTRO DO ITEM
				item.setSubtotal(rs.getInt("i.subtotal"));
				
				lista.add(item);
			}
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException("ERRO. Erro ao criar a lista de itens da venda! \n" + e);
		}	
		
	}
	
	
	
	
	
	
	
	
	
	
	
}