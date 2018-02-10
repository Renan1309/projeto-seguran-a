package br.edu.fbv.BancoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.fbv.conexão.ConnectionFactory;
import br.edu.fbv.modelo.Administrador;
import br.edu.fbv.modelo.Boletim;
import br.edu.fbv.modelo.Candidato;

public class administradorDAO {
	private Connection connection;
	
	//Administrador administrador = new  Administrador();
	
	public administradorDAO() {
		new ConnectionFactory();
		this.connection = ConnectionFactory.getConnection();

	}
	public boolean login(String cpf , String senha) {
		boolean existe = false;
		PreparedStatement stm;
		try {                                 //testando o login com o SHA2
			stm = connection.prepareStatement("SELECT * FROM administrador WHERE cpf = ? AND senha = SHA2(?,256) ");
			stm.setString(1, cpf);
			stm.setString(2, senha);
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				existe = true;
				
				
				}
				
				return existe;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;
		
		
		
	}
	/**
	
	public void adiciona (Administrador administrador) {
		String sql = "insert into testeAES (cpf,nome,senha) values ( ? , ? , AES_ENCRYPT( ?, 'renan'))";
		//String sql = "insert into testeAES (cpf , nome , senha)" + " values (?,?,?)";
		//String sql =" insert into administrador (cpf, nome , senha ) values (?, ?, ?)";
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, administrador.getCpf());
			stmt.setString(2, administrador.getNome());
			stmt.setString(3, administrador.getSenha());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			return ;
			
			// TODO Auto-generated catch block

		}
	}**/
	
	public List<Boletim> getList() {

		try {
			List<Boletim> boletins = new ArrayList<Boletim>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from encriptados");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Boletim boletim= new Boletim();
				// Eleitor eleitor = new Eleitor();

				boletim.setTitulo(rs.getString("titulocomputado"));
				boletim.setVotocomputado(rs.getInt("votoinserido"));
			}

			rs.close();
			stmt.close();

			return boletins;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
		
		
		
			

	}

	
	
	
	
	

