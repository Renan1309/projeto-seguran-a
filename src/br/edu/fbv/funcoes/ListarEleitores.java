package br.edu.fbv.funcoes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ListarEleitores {
	
	private Connection con = null;
	
	private Statement statement = null ;


private ResultSet resultset = null;


	
	
	public void listarcontatos () {
		try {
			String query =" SELECT *FROM eleitor ";
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.con.createStatement();
			while (this.resultset.next() ) {
				//JOptionPane.showConfirmDialog(null, "ID "+ this.resultset.getString("id")+ "- Nome: " +this.resultset.getString("nome") +"- Título de eleitor : " +this.resultset.getString("nome"));
				System.out.println("ID "+ this.resultset.getString("id")+ "- Nome: " +this.resultset.getString("nome") +"- Título de eleitor : " +this.resultset.getString("titulodeeleitor"));
			}
		}catch (Exception e) {
			System.out.println("Erro "+e.getMessage());
		}
}
	}
