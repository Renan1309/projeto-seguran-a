package br.edu.fbv.modelo;

import java.util.List;

public class Candidato {
		private String nome ;
		private String partido;
		private int numerodevotos;
		private int numerodeeleicao;
		private String cpf ;
		public int getNumerodeeleicao() {
			return numerodeeleicao;
		}
		public void setNumerodeeleicao(int numerodeeleicao) {
			this.numerodeeleicao = numerodeeleicao;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getPartido() {
			return partido;
		}
		public void setPartido(String partido) {
			this.partido = partido;
		}
		public int getNumerodevotos() {
			return numerodevotos;
		}
		public void setNumerodevotos(int numerodevotos) {
			this.numerodevotos = numerodevotos;
		}
		
}
