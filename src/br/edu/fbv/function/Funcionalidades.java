package br.edu.fbv.function;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.fbv.BancoDAO.CandidatoDAO;
import br.edu.fbv.BancoDAO.InserirDAO;
import br.edu.fbv.BancoDAO.administradorDAO;
import br.edu.fbv.modelo.Administrador;
import br.edu.fbv.modelo.Candidato;
import br.edu.fbv.modelo.Eleitor;

public class Funcionalidades {
	

	
	public static void listarcandidatos () {
		CandidatoDAO candidatoinse = new CandidatoDAO();
	
		List<Candidato> candidatos = candidatoinse.getList();
		for(Candidato Candidato : candidatos) {
			System.out.println("Nome: " + Candidato.getNome());

			System.out.println("T�tulo: " + Candidato.getPartido());
			System.out.println("N�mero de votos: " + Candidato.getNumerodevotos());
	}
	}
	public void buscacandidato (int numerodeeleicao) {
		CandidatoDAO candidatoinse = new CandidatoDAO();
		try {

			//int numerodeeleicao = 0;

			// Valida��o do c�digo digitado
			while (true) {
				
				if (numerodeeleicao < 0)
					System.out.println("C�digo inv�lido.");
				else
					break;
			}
			Candidato candidatos = candidatoinse.retornarCandidato(numerodeeleicao);
			JOptionPane.showMessageDialog(null,"Nome: " + candidatos.getNome() + "\n" + "N�mero: " + candidatos.getNumerodeeleicao());

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

 public void metodovota��o() throws SQLException {
	 Eleitor eleitor = new Eleitor();
		Candidato candidato = new Candidato();
		Administrador administrador = new Administrador();
		InserirDAO inserirdao = new InserirDAO();
		CandidatoDAO candidatoinse = new CandidatoDAO();
		administradorDAO administradorCHEF = new administradorDAO();
		Funcionalidades funcionalidades = new Funcionalidades();
	 String ti = JOptionPane.showInputDialog("informe o titulo");
		boolean avalia = inserirdao.confirmarTitulo(ti);
		if (avalia == true) {
			JOptionPane.showMessageDialog(null, "Titulo Aprovado =)");
			int numerodeeleicao;
			numerodeeleicao = Integer.parseInt(JOptionPane.showInputDialog("Digite  n�mero do seu  candidato"));
			funcionalidades.buscacandidato(numerodeeleicao);
		
		
	int confir	=JOptionPane.showConfirmDialog(null, "Seu candidato esta correto"+ 
		               JOptionPane.YES_NO_OPTION);
	 if (confir ==0) {
				  JOptionPane.showMessageDialog(null, "voto computado =)");

					candidatoinse.InserirVoto(numerodeeleicao);
			 }
	 else {
		 JOptionPane.showMessageDialog(null, "recomece a vota��o");
	 }
			
			
			
			
			
			//String titulocadas = ti ;
			//JOptionPane.showMessageDialog(null, titulocadas);
			
			 
		}else {
			JOptionPane.showMessageDialog(null, "Titulo n�o encontrado");
		}
	}
		
 }

