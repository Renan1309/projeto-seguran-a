package br.edu.fbv.BancoDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fbv.conexão.ConnectionFactory;
import br.edu.fbv.modelo.Candidato;
import br.edu.fbv.modelo.Eleitor;
import br.edu.fbv.modelo.Encriptado;

public class InserirDAO {
	private Connection connection;
	//Eleitor eleitor = new Eleitor();

	public InserirDAO() {
		new ConnectionFactory();
		this.connection = ConnectionFactory.getConnection();
	}

	public void Adiciona(Eleitor Eleitor) {
						///SE LEMBRA QUE EU MUDEI A TABELA ELEITOR PARA ELEITORES PARA TESTAR A DUPLICAÇÃO
		String sql = "insert into eleitores" + " (nome , titulodeeleitor)" + " values (?,?)";
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, Eleitor.getNome());
			stmt.setString(2, Eleitor.getTitulodeeleitor());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			return ;
			
			// TODO Auto-generated catch block

		}

	}

	public List<Eleitor> getList() {

		try {
			List<Eleitor> eleitores = new ArrayList<Eleitor>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from eleitores");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Eleitor eleitor = new Eleitor();
				eleitor.setNome(rs.getString("nome"));
				eleitor.setTitulodeeleitor(rs.getString("titulodeeleitor"));
				

				eleitores.add(eleitor);

			}

			rs.close();
			stmt.close();

			return eleitores;

		} catch (SQLException e) {
			 throw new RuntimeException(e);
		}

	}
	
	public void computarvotoparaoauditor(String titulo , String voto , String senhaAES) {
		//"insert into Encriptados (titulocomputado, votoinserido) values (AES_ENCRYPT( ?, ?),(AES_ENCRYPT( ?, ?)))"
		String sql = "insert into Encriptados (tituloENC, votocom) values (AES_ENCRYPT( ?, ?),(AES_ENCRYPT( ?, ?)))";
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, titulo);
			stmt.setString(2, senhaAES);
			stmt.setString(3,voto);
			stmt.setString(4, senhaAES);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public boolean confirmarTitulo(String titulo) {
		boolean existe = false;
		PreparedStatement stm;
		try {                                
			stm = connection.prepareStatement("SELECT * FROM eleitores WHERE titulodeeleitor = ?  ");
			stm.setString(1, titulo);
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
	public void AdicionaEncriptados(Encriptado Encriptado) {
		///SE LEMBRA QUE EU MUDEI A TABELA ELEITOR PARA ELEITORES PARA TESTAR A DUPLICAÇÃO
		String sql = "insert into votosencriptado" + " ( tencript ,vencript, thash, vhash)" + " values (?,?,?,?)";
		try {

		   PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, Encriptado.getTituloEncrypted());
				stmt.setString(2, Encriptado.getVotoEncrypted());
				stmt.setString(3, Encriptado.getHashTitulo());
				stmt.setString(4, Encriptado.getHashVoto());
				stmt.execute();
				stmt.close();
				} catch (SQLException e) {
				return ;

// TODO Auto-generated catch block

}
}
	
	public String Conferir (String titulo) {
		
		String confirmacao = null ;
		PreparedStatement stm;
		try {                                
			stm = connection.prepareStatement("select*from eleitores where titulodeeleitor= ?  ");
			stm.setString(1, titulo); 
			ResultSet rs = stm.executeQuery();
			if(rs.next()){
				
				 confirmacao = rs.getString("votou");
				 }
				return confirmacao;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return confirmacao;
		
		
		
	}public void Javotou (String titulo) throws SQLException {
		
		String sql = "UPDATE eleitores SET votou = 'sim'  WHERE titulodeeleitor = ? ";
		
		try {

			   PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1, titulo);
					
					stmt.execute();
					stmt.close();
		}
		
	  catch (SQLException e) {
		e.printStackTrace();
	}
		
	
}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/**
		String sql = " SELECT *FROM eleitor where nome = ? and titulodeeleitor = ?";
	//"update contatos set nome=?, email=?, endereco=?," +
//  "dataNascimento=? where id=?";
	
	 try {
         PreparedStatement stmt = connection.prepareStatement(sql);
         stmt.setString(1, eleitor.getNome());
         stmt.setString(2, eleitor.getTitulodeeleitor());
         
         stmt.execute();
         stmt.close();
        return eleitor;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }

		
}
*/

