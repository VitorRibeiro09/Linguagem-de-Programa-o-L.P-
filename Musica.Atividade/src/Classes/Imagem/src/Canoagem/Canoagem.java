package Canoagem;

public class Canoagem {

		private String  barco;
		private Double velocidade;
		private Double distancia;

	public Canoagem(String  barco, Double velocidade, Double distancia) {
		this. barco = barco;
		this.velocidade = velocidade;
		this.distancia= distancia;
		
	}
	public String nomebarco() {
		return barco;
		
	}
	public Double velocidade() {
		return velocidade;
	}
	public Double distancia(){
		return distancia;
		}
	}

