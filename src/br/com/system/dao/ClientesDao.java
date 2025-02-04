//Anot
//O metodo do construtor recebe o mesmo nome da classe.

//'public void Salvar(){}' metodo de salvar, que passa 'Clientes' como parametro

package br.com.system.dao;

import br.com.system.jdbc.ConexaoBanco; //Daqui esta vindo o 'ConexaoBanco()' e 'getConnection()'.
import br.com.system.model.Clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class ClientesDao {
	private Connection conn;
	
	//CONSTRUTOR
	public ClientesDao () {
		this.conn = new ConexaoBanco().getConnection();
	}
	
	//METODO DE SALVAR CLIENTE
	public void Salvar(Clientes obj) {
		try {
			//1° passo(criar o sql)
			String sql = "insert into tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			//2°passo(preparar a conexão SQL)
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

			//3°passo(executar o SQL com o objeto encapsulado)
			stmt.executeUpdate();

			//4°passo(fechar a conexão)
			stmt.close();
			   
			JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!!");
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao salvar o cliente!!" + erro);
		}
	}

	
	//METODO EXCLUIR CLIENTE
	
	
	//METODO DE PESQUISAR CLIENTES

	
	//METODO EXCLUIR CLIENTE
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
	
	
	public Clientes BuscarCliente(String nome) {
	//RETORNA UM OBJETO DO TIPO 'CLIENTES'
		try {
			String sql = "SELECT * FROM tb_clientes WHERE nome =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			//'RS' RESULT SET
			ResultSet rs = stmt.executeQuery();
			//INSTANCIANDO MODELO DE CLIENTES
			Clientes obj = new Clientes();
			//'NEXT' PERCORRE A TABELA NO BANCO DE DADOS 
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
			//RETORNA OBJETO DO TIPO CLIENTE
			return obj;
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO: Erro ao buscar Cliente!!" +  erro);
		}
		//PEDE QUE RETORNE ALGO, MAS NÃO PRECISO, POR ISSO RETORNA NULL
		return null;
	}
	
	
	//METODO LISTAR CLIENTE
	public List<Clientes>listar(){
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

	
	//METODO FILTRAR CLIENTE
	public List<Clientes>filtrar(String nome){
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
			JOptionPane.showMessageDialog(null, "ERRO, ao criar a lista: " + e);
		}
		return null;
	}


	//METODO EDITAR CLIENTE
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
}
