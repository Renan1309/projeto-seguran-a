package br.edu.fbv.modelo;

public class Encriptado {
	
	String TituloEncrypted ;
	String VotoEncrypted;
	String HashTitulo;
	String HashVoto;
	public String getTituloEncrypted() {
		return TituloEncrypted;
	}
	public void setTituloEncrypted(String tituloEncrypted) {
		TituloEncrypted = tituloEncrypted;
	}
	public String getVotoEncrypted() {
		return VotoEncrypted;
	}
	public void setVotoEncrypted(String votoEncrypted) {
		VotoEncrypted = votoEncrypted;
	}
	public String getHashTitulo() {
		return HashTitulo;
	}
	public void setHashTitulo(String hashTitulo) {
		HashTitulo = hashTitulo;
	}
	public String getHashVoto() {
		return HashVoto;
	}
	public void setHashVoto(String hashVoto) {
		HashVoto = hashVoto;
	}

}
