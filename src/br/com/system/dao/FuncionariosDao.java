package br.com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.system.jdbc.ConexaoBanco;
import br.com.system.model.Funcionarios;
import br.com.system.view.AreaDeTrabalho;

public class FuncionariosDao {
	private Connection conn;
	
	//CONSTRUTOR
	public FuncionariosDao () {
		this.conn = new ConexaoBanco().getConnection();
	}
	
	
	//METODO DE SALVAR FUNCIONARIO
	public void Salvar(Funcionarios obj) {
		
		//VERIFICA A QUANTIDADE DE CARACTERES NA SENHA
		if(obj.getSenha().length() < 8) {
			JOptionPane.showMessageDialog(null, "A senha deve conter no minimo 8 caracteres");
			return;
		}
		
		try {
			
			String sql = "INSERT INTO tb_Funcionarios (nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());
			stmt.setString(5, obj.getSenha());
			stmt.setString(6, obj.getCargo());
			stmt.setString(7, obj.getNivel_acesso());
			stmt.setString(8, obj.getTelefone());
			stmt.setString(9, obj.getCelular());
			stmt.setString(10, obj.getCep());
			stmt.setString(11, obj.getEndereco());
			stmt.setInt(12, obj.getNumero());
			stmt.setString(13, obj.getComplemento());
			stmt.setString(14, obj.getBairro());
			stmt.setString(15, obj.getCidade());
			stmt.setString(16, obj.getEstado());

			stmt.executeUpdate();
			stmt.close();
			   
			JOptionPane.showMessageDialog(null, "Funcionário salvo com sucesso! ");
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao salvar o Funcionário! " + erro);
		}
	}

	
	//METODO EXCLUIR FUNCIONARIO
	public void Excluir(Funcionarios obj) {
		try {
			String sql = "DELETE FROM tb_funcionarios WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, obj.getId());
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Funcionário excluido com sucesso !");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO, ao excluir o funcionário!" + e);
		}
	}
	
	
	//METODO EDITAR FUNCIONARIO
	public void Editar(Funcionarios obj) {
		try {
			String sql = "UPDATE tb_funcionarios SET nome=?, rg=?, cpf=?, email=?, senha=?, cargo=?, nivel_acesso=?, telefone=?,"
					+ "celular=?, cep=?, endereco=?, numero=?,complemento=?, bairro=?, cidade=?, estado=? WHERE id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getRg());
			stmt.setString(3, obj.getCpf());
			stmt.setString(4, obj.getEmail());
						
			stmt.setString(5, obj.getSenha());
			stmt.setString(6, obj.getCargo());
			stmt.setString(7, obj.getNivel_acesso());
			
			stmt.setString(8, obj.getTelefone());
			stmt.setString(9, obj.getCelular());
			stmt.setString(10, obj.getCep());
			stmt.setString(11, obj.getEndereco());
			stmt.setInt(12, obj.getNumero());
			stmt.setString(13, obj.getComplemento());
			stmt.setString(14, obj.getBairro());
			stmt.setString(15, obj.getCidade());
			stmt.setString(16, obj.getEstado());
			stmt.setInt(17, obj.getId());

			stmt.executeUpdate();
			stmt.close();
			   
			JOptionPane.showMessageDialog(null, "Funcionário editado com sucesso!!");
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao editar o funcionário!!" + erro);
		}
	}
	
	
	//METODO DE BUSCAR FUNCIONARIO
	public Funcionarios BuscarFuncionario(String nome) {
	//RETORNA UM OBJETO DO TIPO 'FUNCIONARIO'
		try {
			String sql = "SELECT * FROM tb_funcionarios WHERE nome =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			//'RS' RESULT SET
			ResultSet rs = stmt.executeQuery();
			//INSTANCIANDO MODELO DE CLIENTES
			Funcionarios obj = new Funcionarios();
			//'NEXT' PERCORRE A TABELA NO BANCO DE DADOS 
			if(rs.next()) {
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setSenha(rs.getString("senha"));
				obj.setCargo(rs.getString("cargo"));
				obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao buscar funcionário!!" +  erro);
		}
		return null;
	}
	
	
	//METODO LISTAR FUNCIONARIO
	public List<Funcionarios>Listar(){
		List<Funcionarios> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM tb_funcionarios";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery();
			
			//PERCORRERA TODA A LISTA
			while(rs.next()) {
				Funcionarios obj = new Funcionarios();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				obj.setSenha(rs.getString("senha"));
				obj.setCargo(rs.getString("cargo"));
				obj.setNivel_acesso(rs.getString("nivel_acesso"));
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

	
	//METODO FILTRAR FUNCIONARIO
	public List<Funcionarios>Filtrar(String nome){
		List<Funcionarios> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM tb_Funcionarios WHERE nome LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			
			//PERCORRERA TODA A LISTA
			while(rs.next()) {
				Funcionarios obj = new Funcionarios();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setRg(rs.getString("rg"));
				obj.setCpf(rs.getString("cpf"));
				obj.setEmail(rs.getString("email"));
				
				obj.setSenha(rs.getString("senha"));
				obj.setCargo(rs.getString("cargo"));
				obj.setNivel_acesso(rs.getString("nivel_acesso"));
				
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
			JOptionPane.showMessageDialog(null, "ERRO, ao filtrar a funcionarios: " + e);
		}
		return null;
	}

	
	//METODO EFETUAR LOGIN DO FUNCIONARIOS
	public void efetuarLogin(String email, String senha) {
		try {
			String slq = "SELECT * FROM tb_funcionarios WHERE email=? AND senha=?";
			PreparedStatement stmt = conn.prepareStatement(slq);
			
			stmt.setString(1, email);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Seja bem Vindo ao sistema! ");
				AreaDeTrabalho at = new AreaDeTrabalho();
				at.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Dados Invalidos! ");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: Error" + e);
		}
	}
}
