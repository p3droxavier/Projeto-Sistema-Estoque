//ANOT
/*
 ->LINHA 142 => A CONSULTA DIZ QUE O FOR_ID(TB_PRODUTOS) É IGUAL AO ID(TB_FORNECEDORES)
   -ONDE SELECIONA O QUE QUER SER MOSTRADO NA LISTA 'p.id,p.descricao,p.preco,p.qtd_estoque,f.nome'
   
   -JOGANDO O VALOR(f.setNome(rs.getString("f.nome"));) DENTRO DO OBJETO PRODUTO.
   
   
   
  ->LINHA 196 => QTD_NOVA, SE REFERE AO CAMPO QUE SERA ADICIONADO A NOVA QUANTIA
    - ID SE REFERE ID DO PRODUTO(IRA ALTERAR COM BASE NO ID DO PRODUTO)
 */


package br.com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.system.jdbc.ConexaoBanco;
import br.com.system.model.Fornecedores;
import br.com.system.model.Produtos;

public class ProdutosDao {
	private Connection conn;
	
	//CONSTRUTOR
	public ProdutosDao () {
		
		this.conn = new ConexaoBanco().getConnection();
	}
	
	
	//METODO DE SALVAR PRODUTOS
	public void Salvar(Produtos obj) {
		try {
			String sql = "INSERT INTO tb_produtos (descricao, preco, qtd_estoque, for_id)"
					+ "VALUES (?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, obj.getDescricao());
			stmt.setDouble(2, obj.getPreco());
			stmt.setInt(3, obj.getQtd_estoque());
			stmt.setInt(4, obj.getFornecedores().getId());

			stmt.executeUpdate();

			stmt.close();
			   
			JOptionPane.showMessageDialog(null, "Produto salvo com sucesso! ");
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao salvar o produto! " + erro);
		}
	}

	
	//METODO EXCLUIR PRODUTOS
	public void Excluir(Produtos obj) {
		try {
			String sql = "DELETE FROM tb_produtos WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, obj.getId());
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Produto excluido com sucesso! ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO. Erro ao excluir o produto! " + e);
		}
	}
	
	
	//METODO EDITAR PRODUTOS
	public void Editar(Produtos obj) {
		try {
			String sql = "UPDATE tb_produtos SET descricao=?, preco=?, qtd_estoque=?, for_id=? WHERE id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getDescricao());
			stmt.setDouble(2, obj.getPreco());
			stmt.setInt(3, obj.getQtd_estoque());
			stmt.setInt(4, obj.getFornecedores().getId());
			stmt.setInt(5, obj.getId());
			
			stmt.executeUpdate();

			stmt.close();
				   
			JOptionPane.showMessageDialog(null, "Produto editado com sucesso! ");
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao editar o produto! " + erro);
		}
	}
	
	
	//METODO DE BUSCAR PRODUTOS PELO NOME
	public Produtos BuscarProdutos(String nome) {
		try {
			String sql = "SELECT p.id,p.descricao,p.preco,p.qtd_estoque,f.nome "
					+ "FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON(p.for_id=f.id) WHERE p.descricao=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			
			Produtos obj = new Produtos();
			Fornecedores f = new Fornecedores();
			
			if(rs.next()) {
				obj.setId(rs.getInt("p.id"));
				obj.setDescricao(rs.getString("p.descricao"));
				obj.setPreco(rs.getDouble("p.preco"));
				obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
				
				f.setNome(rs.getString("f.nome")); 
				//PASSANDO 'f' PARA DENTRO DO OBJETO DE PRODUTOS
				obj.setFornecedores(f);
				
			}
			return obj;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao buscar Produto! " +  erro);
		}
		return null;
	}
	
	
	//METODO DE BUSCAR PRODUTOS PELO ID USADO NO 'FORMSVENDA'
	public Produtos BuscarProdutosID(int id) {
		try {
			String sql = "SELECT p.id,p.descricao,p.preco,p.qtd_estoque,f.nome "
					+ "FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON(p.for_id=f.id) WHERE p.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			Produtos obj = new Produtos();
			Fornecedores f = new Fornecedores();
			
			if(rs.next()) {
				obj.setId(rs.getInt("p.id"));
				obj.setDescricao(rs.getString("p.descricao"));
				obj.setPreco(rs.getDouble("p.preco"));
				obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
				
				f.setNome(rs.getString("f.nome")); 
				//PASSANDO 'f' PARA DENTRO DO OBJETO DE PRODUTOS
				obj.setFornecedores(f);
				
			}
			return obj;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao buscar Produto! " +  erro);
		}
		return null;
	}
	
	
	//METODO LISTAR PRODUTOS (JUNTANDO 2 TABELAS)
	public List<Produtos>Listar(){
		List<Produtos> lista = new ArrayList<>();
		try {
			String sql = "SELECT p.id,p.descricao,p.preco,p.qtd_estoque,f.nome "
					+ "FROM tb_produtos AS p INNER JOIN tb_fornecedores AS f ON(p.for_id=f.id)";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery();
			//PERCORRERA TODA A LISTA
			while(rs.next()) {
				Produtos obj = new Produtos();
				Fornecedores f = new Fornecedores();
				obj.setId(rs.getInt("p.id"));
				obj.setDescricao(rs.getString("p.descricao"));
				obj.setPreco(rs.getDouble("p.preco"));
				obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
				
				f.setNome(rs.getString("f.nome"));
				obj.setFornecedores(f);
				
				lista.add(obj);
			}
			return lista; 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO, ao criar a lista: " + "\n" + e);
		}
		return null;
	}

	
	//METODO FILTRAR PRODUTOS
	public List<Produtos>Filtrar(String nome){
		List<Produtos> lista = new ArrayList<>();
		try {
			String sql = "SELECT p.id,p.descricao,p.preco,p.qtd_estoque,f.nome FROM tb_produtos AS p INNER JOIN"
					+ " tb_fornecedores AS f ON(p.for_id=f.id) WHERE p.descricao LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			
			//PERCORRERA TODA A LISTA
			while(rs.next()) {
				Produtos obj = new Produtos();
				Fornecedores f = new Fornecedores();
				obj.setId(rs.getInt("p.id"));
				obj.setDescricao(rs.getString("p.descricao"));
				obj.setPreco(rs.getDouble("p.preco"));
				obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
				
				f.setNome(rs.getString("f.nome"));
				obj.setFornecedores(f);
				
				
				lista.add(obj);
			}
			return lista; 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO, ao filtrar o funcionário: " + e);
		}
		return null;
	}
	
	
	//ADIÇÃO AO ESTOQUE
	public void adicionarEstoque(int id, int qtd_nova) {
		try {
			String sql = "UPDATE tb_produtos SET qtd_estoque=? WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, qtd_nova);
			stmt.setInt(2, id);
			stmt.execute();
			stmt.close();
			JOptionPane.showMessageDialog(null, "Adicionado com sucesso no estoque! ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO. Erro ao adicionar no estoque!" + " \n " + e);
		}
	}
	
	
	//BAIXA DO ESTOQUE(REGISTRO DE SAÍDA)
	public void baixaEstoque(int id, int qtd_nova) {
		try {
			String sql = "UPDATE tb_produtos SET qtd_estoque=? WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, qtd_nova);
			stmt.setInt(2, id);
			stmt.execute();
			stmt.close();
			JOptionPane.showMessageDialog(null, "Baixa do estoque efetuada com sucesso! ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO. Erro ao dar baixa no estoque!" + " \n " + e);
		}
	}
}
