package filme_carros;

public class Pista {


	private String  TipoPista;
	private String localizacao;
	private Double comprimentoKm;

public Pista(String  TipoPista, String localizacao, Double comprimentoKm) {
	this.TipoPista = TipoPista;
	this.localizacao = localizacao;
	this. comprimentoKm = comprimentoKm;
	
}
public String MostrarDtelhes() {
	return TipoPista;
	
}
public String VerificarCondicoes() {
	return localizacao;
}
public Double ReceberCorrida(){
	return  comprimentoKm;
	}
}




