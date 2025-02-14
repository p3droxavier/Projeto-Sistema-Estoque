//ANOT
/*
  CORRIGINDO ERROS METODO SALVAR(LINHA 41, 42, 43) 
   NO 'GET SET'(PRODUTOS), OS TRES ESTÃO DIFERENTES DE 'STRING'
   PRECISA MUDAR NO DAO TBM.
   -LINHA 43 = 'SETINT', POIS ESTA PEGANDO O ID
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
import br.com.system.model.Clientes;
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
	public void Excluir(Clientes obj) {
		try {
			String sql = "DELETE FROM tb_clientes WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, obj.getId());
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO, ao excluir o cliente!" + e);
		}
	}
	
	
	//METODO EDITAR PRODUTOS
	public void Editar(Clientes obj) {
		try {
			String sql = "UPDATE tb_clientes SET nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?, cep=?, endereco=?, numero=?, "
					+ "complemento=?, bairro=?, cidade=?, estado=? WHERE id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getTelefone());
			stmt.setString(6, obj.getCelular());
			stmt.setString(7, obj.getCep());
			stmt.setString(8, obj.getEndereco());
			stmt.setInt(9, obj.getNumero());
			stmt.setString(10, obj.getComplemento());
			stmt.setString(11, obj.getBairro());
			stmt.setString(12, obj.getCidade());
			stmt.setString(13, obj.getEstado());
			stmt.setInt(14, obj.getId());

			stmt.executeUpdate();

			stmt.close();
				   
			JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!!");
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao editar o cliente!!" + erro);
		}
	}
	
	
	//METODO DE BUSCAR PRODUTOS
	public Clientes BuscarCliente(String nome) {
		try {
			String sql = "SELECT * FROM tb_clientes WHERE nome =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			Clientes obj = new Clientes();
			
			if(rs.next()) {
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone"));
				obj.setCelular(rs.getString("celular"));
				obj.setCep(rs.getString("cep"));
				obj.setEndereco(rs.getString("endereco"));
				obj.setNumero(rs.getInt("numero"));
				obj.setComplemento(rs.getString("complemento"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setEstado(rs.getString("estado"));
			}
			return obj;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao buscar Cliente!!" +  erro);
		}
		return null;
	}
	
	
	//METODO LISTAR PRODUTOS
	public List<Clientes>Listar(){
		List<Clientes> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM tb_clientes";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery();
			//PERCORRERA TODA A LISTA
			while(rs.next()) {
				Clientes obj = new Clientes();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone")); 
				obj.setCelular(rs.getString("celular"));
				obj.setCep(rs.getString("cep"));
				obj.setEndereco(rs.getString("endereco"));
				obj.setNumero(rs.getInt("numero"));
				obj.setComplemento(rs.getString("complemento"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setEstado(rs.getString("estado"));
				
				lista.add(obj);
			}
			return lista; 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO, ao criar a lista: " + e);
		}
		return null;
	}

	
	//METODO FILTRAR PRODUTOS
	public List<Clientes>Filtrar(String nome){
		List<Clientes> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM tb_clientes WHERE nome LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			
			//PERCORRERA TODA A LISTA
			while(rs.next()) {
				Clientes obj = new Clientes();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setTelefone(rs.getString("telefone")); 
				obj.setCelular(rs.getString("celular"));
				obj.setCep(rs.getString("cep"));
				obj.setEndereco(rs.getString("endereco"));
				obj.setNumero(rs.getInt("numero"));
				obj.setComplemento(rs.getString("complemento"));
				obj.setBairro(rs.getString("bairro"));
				obj.setCidade(rs.getString("cidade"));
				obj.setEstado(rs.getString("estado"));
				
				lista.add(obj);
			}
			return lista; 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO, ao filtrar o funcionário: " + e);
		}
		return null;
	}
	
}
