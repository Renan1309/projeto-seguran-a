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

			System.out.println("Título: " + Candidato.getPartido());
			System.out.println("Número de votos: " + Candidato.getNumerodevotos());
	}
	}
	public void buscacandidato (int numerodeeleicao) {
		CandidatoDAO candidatoinse = new CandidatoDAO();
		try {

			//int numerodeeleicao = 0;

			// Validação do código digitado
			while (true) {
				
				if (numerodeeleicao < 0)
					System.out.println("Código inválido.");
				else
					break;
			}
			Candidato candidatos = candidatoinse.retornarCandidato(numerodeeleicao);
			JOptionPane.showMessageDialog(null,"Nome: " + candidatos.getNome() + "\n" + "Número: " + candidatos.getNumerodeeleicao());

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

 public void metodovotação() throws SQLException {
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
			numerodeeleicao = Integer.parseInt(JOptionPane.showInputDialog("Digite  número do seu  candidato"));
			funcionalidades.buscacandidato(numerodeeleicao);
		
		
	int confir	=JOptionPane.showConfirmDialog(null, "Seu candidato esta correto"+ 
		               JOptionPane.YES_NO_OPTION);
	 if (confir ==0) {
				  JOptionPane.showMessageDialog(null, "voto computado =)");

					candidatoinse.InserirVoto(numerodeeleicao);
			 }
	 else {
		 JOptionPane.showMessageDialog(null, "recomece a votação");
	 }
			
			
			
			
			
			//String titulocadas = ti ;
			//JOptionPane.showMessageDialog(null, titulocadas);
			
			 
		}else {
			JOptionPane.showMessageDialog(null, "Titulo não encontrado");
		}
	}
		
 }

