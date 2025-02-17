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


public class FornecedoresDao {
private Connection conn;
	
	//CONSTRUTOR
	public FornecedoresDao () {
		this.conn = new ConexaoBanco().getConnection();
	}
	
	
	//METODO DE SALVAR FORNECEDOR
	public void Salvar(Fornecedores obj) {
		try {
			
			String sql = "INSERT INTO tb_fornecedores (nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCnpj());
			stmt.setString(3, obj.getEmail());
			stmt.setString(4, obj.getTelefone());
			stmt.setString(5, obj.getCelular());
			stmt.setString(6, obj.getCep());
			stmt.setString(7, obj.getEndereco());
			stmt.setInt(8, obj.getNumero());
			stmt.setString(9, obj.getComplemento());
			stmt.setString(10, obj.getBairro());
			stmt.setString(11, obj.getCidade());
			stmt.setString(12, obj.getEstado());

			stmt.executeUpdate();
			stmt.close();
			   
			JOptionPane.showMessageDialog(null, "Fornecedor salvo com sucesso! ");
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao salvar o fornecedor! " + erro);
		}
	}

	
	//METODO EXCLUIR FORNECEDOR
	public void Excluir(Fornecedores obj) {
		try {
			String sql = "DELETE FROM tb_fornecedores WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, obj.getId());
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso! ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao excluir o fornecedor! " + e);
		}
	}
	
	
	//METODO EDITAR FORNECEDOR
	public void Editar(Fornecedores obj) {
		try {
			String sql = "UPDATE tb_fornecedores SET nome=?, cnpj=?, email=?, telefone=?, celular=?, cep=?, endereco=?, numero=?, "
					+ "complemento=?, bairro=?, cidade=?, estado=? WHERE id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCnpj());
			stmt.setString(3, obj.getEmail());
			stmt.setString(4, obj.getTelefone());
			stmt.setString(5, obj.getCelular());
			stmt.setString(6, obj.getCep());
			stmt.setString(7, obj.getEndereco());
			stmt.setInt(8, obj.getNumero());
			stmt.setString(9, obj.getComplemento());
			stmt.setString(10, obj.getBairro());
			stmt.setString(11, obj.getCidade());
			stmt.setString(12, obj.getEstado());
			stmt.setInt(13, obj.getId());

			stmt.executeUpdate();
			stmt.close();
				   
			JOptionPane.showMessageDialog(null, "Fornecedor editado com sucesso! ");
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao editar o fornecedor! " + erro);
		}
	}
	
	
	//METODO DE BUSCAR FORNECEDOR
	public Fornecedores BuscarFornecedores(String nome) {
	//RETORNA UM OBJETO DO TIPO 'FORNECEDOR'
		try {
			String sql = "SELECT * FROM tb_fornecedores WHERE nome =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			//'RS' RESULT SET
			ResultSet rs = stmt.executeQuery();
			//INSTANCIANDO MODELO DE CLIENTES
			Fornecedores obj = new Fornecedores();
			//'NEXT' PERCORRE A TABELA NO BANCO DE DADOS 
			if(rs.next()) {
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setCnpj(rs.getString("cnpj"));
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
			//RETORNA OBJETO DO TIPO CLIENTE
			return obj;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao buscar fornecedor! " +  erro);
		}
		//PEDE QUE RETORNE ALGO, MAS NÃO PRECISO, POR ISSO RETORNA NULL
		return null;
	}
	
	
	//METODO LISTAR FORNECEDOR
	public List<Fornecedores>Listar(){
		List<Fornecedores> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM tb_fornecedores";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery();
			//PERCORRERA TODA A LISTA
			while(rs.next()) {
				Fornecedores obj = new Fornecedores();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setCnpj(rs.getString("cnpj"));
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
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao criar a lista: " + e);
		}
		return null;
	}

	
	//METODO FILTRAR FORNECEDOR
	public List<Fornecedores>Filtrar(String nome){
		List<Fornecedores> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM tb_fornecedores WHERE nome LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			
			//PERCORRERA TODA A LISTA
			while(rs.next()) {
				Fornecedores obj = new Fornecedores();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setCnpj(rs.getString("cnpj"));
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
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao filtrar o fornecedor: " + e);
		}
		return null;
	}

}
