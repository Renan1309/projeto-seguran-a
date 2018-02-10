package br.edu.fbv.BancoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.fbv.conexão.ConnectionFactory;

public class MesarioDAO {
	
	private Connection connection;





	public MesarioDAO() {
		new ConnectionFactory();
		this.connection = ConnectionFactory.getConnection();

	}
	
	public boolean loginMesario(String cpf , String senha) {
		boolean existe = false;
		PreparedStatement stm;
		try {                                 //testando o login com o SHA2
			stm = connection.prepareStatement("SELECT * FROM mesario WHERE cpf = ? AND senha = SHA2(?,256) ");
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
	
	public void receberhash  (String codigo ,byte[] hashtexto) {
		String sql = "insert into integridade_txt (codigo ,bytes_hash ) values ( ?,? )";
		
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, codigo);
			stmt.setBytes(2, hashtexto);
			
			stmt.execute();
			stmt.close();
			
				
				
		} catch (SQLException e) {
			
			
			// TODO Auto-generated catch block

		}
}			

}
