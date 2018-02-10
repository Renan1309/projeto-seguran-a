package br.edu.fbv.modelo;

public class Eleitor {
	private String nome ;
	private String titulodeeleitor ;
	private String votou;
	public String getVotou() {
		return votou;
	}
	public void setVotou(String votou) {
		this.votou = votou;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTitulodeeleitor() {
		return titulodeeleitor;
	}
	public void setTitulodeeleitor(String titulodeeleitor) {
		this.titulodeeleitor = titulodeeleitor;
	}

}
