package br.edu.fbv.funcoes;

import java.io.BufferedWriter;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

import br.edu.fbv.BancoDAO.CandidatoDAO;
import br.edu.fbv.BancoDAO.InserirDAO;
import br.edu.fbv.BancoDAO.MesarioDAO;
import br.edu.fbv.BancoDAO.administradorDAO;
import br.edu.fbv.function.Funcionalidades;
import br.edu.fbv.modelo.Administrador;
import br.edu.fbv.modelo.Boletim;
import br.edu.fbv.modelo.Candidato;
import br.edu.fbv.modelo.Eleitor;
import br.edu.fbv.modelo.Encriptado;

/**
 * Trabalho execultado na cadeira de Segurança e Auditoria 
 * 
 * @author renan
 *
 */

public class Votacao {

	public static void main(String[] args) throws IOException, SQLException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
		Eleitor eleitor = new Eleitor();
		Candidato candidato = new Candidato();
		Administrador administrador = new Administrador();
		InserirDAO inserirdao = new InserirDAO();
		CandidatoDAO candidatoinse = new CandidatoDAO();
		administradorDAO administradorCHEF = new administradorDAO();
		MesarioDAO mesariodao = new MesarioDAO();
		Funcionalidades funcionalidades = new Funcionalidades();
		AESfuncao aes = new AESfuncao();
		Boletim votosurna = new Boletim();
		Encriptado encriptado = new Encriptado();
		 
		OutputStream os = new FileOutputStream("c:/teste.txt",true);
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		
	
		Locale locale = new Locale("pt","BR");
        GregorianCalendar calendar = new GregorianCalendar(); 
        
        String cpf2;
		String senha;
		String existe;
		cpf2 = JOptionPane.showInputDialog("Digite seu CPF");
		senha = JOptionPane.showInputDialog("Digite sua senha");
		boolean resposta = mesariodao.loginMesario(cpf2, senha);
		
		if (resposta == true) {
			bw.newLine();
		    SimpleDateFormat formatador32 = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
			bw.write("Login realizado por Mesario + -->"+cpf2+"-- "+formatador32.format(calendar.getTime()));
		
	 int  codigo;
		codigo  = Integer.parseInt(JOptionPane.showInputDialog("Inicialização da urna senha!"));
		 
		if (codigo ==200) {
			bw.newLine();
			SimpleDateFormat formatador321 = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
			bw.write("Eleicao iniciada******* -->"+formatador321.format(calendar.getTime()));
			while(codigo == 200 ) {
				
				 
				String titulo ;
				titulo = JOptionPane.showInputDialog("digite seu titulo");
				
				String encerramento = "1111"  ;
				if(titulo.equals(encerramento)) {
					
					codigo = 800;
					JOptionPane.showMessageDialog(null, "votação encerrada no código ");
					bw.newLine();
					 SimpleDateFormat formatador3211 = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
					bw.write("#######--Votacao encerrada--#######-->"+formatador3211.format(calendar.getTime()));
				
				
				}else if(titulo.equals(titulo)) {
					
					boolean avalia = inserirdao.confirmarTitulo(titulo);
					String conf= "não";
					String conferirsevotou = inserirdao.Conferir(titulo);
					if (avalia == true && conferirsevotou.equals(conf)) {
						JOptionPane.showMessageDialog(null, "Titulo Aprovado =)");
						int numerodeeleicao;
						int numerodeputado;
						
						numerodeeleicao = Integer.parseInt(JOptionPane.showInputDialog("Digite  número PRESIDENTE"));
						funcionalidades.buscacandidato(numerodeeleicao);
						

						int confir = JOptionPane.showConfirmDialog(null,
								"Seu candidato esta correto" + JOptionPane.YES_NO_OPTION);
						if (confir == 0) {
							
							numerodeputado = Integer.parseInt(JOptionPane.showInputDialog("Digite  número DEPUTADO"));
							funcionalidades.buscacandidato(numerodeputado);
							int confirdepu = JOptionPane.showConfirmDialog(null,
									"Seu candidato esta correto" + JOptionPane.YES_NO_OPTION);
							if (confirdepu == 0) {
							
							String senhaAES = JOptionPane.showInputDialog("Digete uma senha de 16 Dígitos");
							JOptionPane.showMessageDialog(null, "voto computado =)");
							
							
							candidatoinse.InserirVoto(numerodeeleicao);
							
							candidatoinse.InserirVoto(numerodeputado);
							
							String numeroDoVoto = String.valueOf(numerodeeleicao);
							String numeroDeputa = String.valueOf(numerodeputado);
							//inserirdao.computarvotoparaoauditor(titulo, numeroDoVoto,senhaAES);
							aes.geraition(senhaAES);
							String tencript =aes.encrypt(titulo);
							encriptado.setTituloEncrypted(tencript);
							String vencript = aes.encrypt(numeroDoVoto);
							encriptado.setVotoEncrypted(vencript);
							String hasht=aes.gerarhash(tencript);
							encriptado.setHashTitulo(hasht);
							String hashv =aes.gerarhash(vencript);
							encriptado.setHashVoto(hashv);
							  inserirdao.AdicionaEncriptados(encriptado);
							String tencriptDepu =aes.encrypt(titulo);
							encriptado.setTituloEncrypted(tencriptDepu);
							String vencriptDepu = aes.encrypt(numeroDeputa);
							encriptado.setVotoEncrypted(vencriptDepu);
							String hashtDepu=aes.gerarhash(tencriptDepu);
							encriptado.setHashTitulo(hashtDepu);
							String hashvDepu =aes.gerarhash(vencriptDepu);
							encriptado.setHashVoto(hashvDepu);
								inserirdao.AdicionaEncriptados(encriptado);
								inserirdao.Javotou(titulo);
							//JOptionPane.showMessageDialog(null,tencript+"*--*"+vencript+"*HASH-->"+hasht);
								
								bw.newLine();
								SimpleDateFormat formatador07 = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
								bw.write("Voto Computado Para Presidente-->"+formatador07.format(calendar.getTime()));
								bw.newLine();
								bw.write("Voto Computado Para Deputado Federal-->"+formatador07.format(calendar.getTime()));
							
							
							
						} else {
							JOptionPane.showMessageDialog(null, "recomece a votação");
							
							bw.newLine();
							 SimpleDateFormat formatador320 = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
							bw.write("Erro candidato não encontrado "+formatador320.format(calendar.getTime()));
							

						}

					} else {
						JOptionPane.showMessageDialog(null, "Titulo não encontrado");
						
						bw.newLine();
						 SimpleDateFormat formatador2 = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
						bw.write("Título não autenticado "+formatador2.format(calendar.getTime()));
						
						
					}
					
					
				}else {
					
					JOptionPane.showMessageDialog(null, "Titulo não aprovado");
					bw.newLine();
					 SimpleDateFormat formatador2 = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
					bw.write("Título não autenticado "+formatador2.format(calendar.getTime()));
				}
			}
				}   
			
			JOptionPane.showMessageDialog(null, "Emitir Boletim");
			bw.newLine();
			SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
			bw.write("Emitir Boletim"+formatador.format(calendar.getTime()));
		
			

			List<Candidato> candidatos = candidatoinse.getList();
			for (Candidato Candidato : candidatos) {
				String leitura1 = "Nome: " + Candidato.getNome();

				String leitura2 = "Partido: " + Candidato.getPartido();
				String leitura3 = "Número de votos: " + Candidato.getNumerodevotos();
				JOptionPane.showMessageDialog(null,
						"Boletim de Urna " + "\n" + "Eleições 2017" + "\n" + "Votos computados por candidatos"
								+ "\n" + leitura1 + "---- " + leitura2 + "----" + leitura3);
		
		
		}	
			JOptionPane.showMessageDialog(null, "Guarda HASH NO BANCO");
			byte [] data = Files.readAllBytes(Paths.get("c:/teste.txt"));
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashsalvo = md.digest(data);
			String cpf_loca = JOptionPane.showInputDialog("Digite o seu CPF , Código para localizar o hash");
			mesariodao.receberhash(cpf_loca,hashsalvo);
			JOptionPane.showMessageDialog(null, "Hash salvo");
			
			
			
			JOptionPane.showMessageDialog(null, "***---VOTAÇÃO-ENCERRADA---***");
	}
		else {
			JOptionPane.showMessageDialog(null, "Mesario não autenticado !");
			bw.newLine();
			SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
			bw.write("Aceso negado"+formatador.format(calendar.getTime()));
			
		}
}else {
				JOptionPane.showMessageDialog(null, "Não autenticado");
				bw.newLine();
				SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
				bw.write("Aceso negado não autenticado"+formatador.format(calendar.getTime()));
}bw.close();

	}
}


