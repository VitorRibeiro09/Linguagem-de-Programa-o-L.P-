package filme_carros;

public class Carro {

	private String  modelo;
	private String personagem;
	private Double velocidadeMaxima;

public Carro(String  modelo, String personagem, Double velocidadeMaxima) {
	this.modelo = modelo;
	this. personagem =  personagem;
	this.velocidadeMaxima= velocidadeMaxima;
	
}
public String acelerar() {
	return modelo;
	
}
public String falar() {
	return personagem;
}
public Double frear(){
	return velocidadeMaxima;
	}
}



