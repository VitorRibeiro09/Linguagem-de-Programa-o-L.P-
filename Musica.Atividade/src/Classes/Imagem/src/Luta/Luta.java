package Luta;

public class Luta {
		private String nome_lutador;
		private Double placar;
		private Double tipo;

	public Luta(String nome_lutador, Double placar, Double tipo) {
		this.nome_lutador =nome_lutador;
		this.placar = placar;
		this.tipo= tipo;
		
	}
	public String nome() {
		return nome_lutador;
		
	}
	public Double tipogolpe() {
		return tipo;
	}
	public Double pontuacao(){
		return placar;
	}
}