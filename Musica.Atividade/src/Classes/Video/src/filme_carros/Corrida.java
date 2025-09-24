package filme_carros;

public class Corrida {


	private String  nomeCorrida;
	private String local;
	private int NumeroParticipantes;

public Corrida(String  nomeCorrida, String local, int NumeroParticipantes) {
	this.nomeCorrida = nomeCorrida;
	this. local =  local;
	this.NumeroParticipantes= NumeroParticipantes;
	
}
public String IniciarCorrida() {
	return nomeCorrida;
	
}
public String MostrarColocados() {
	return local;
}
public int EncerrarCorrida(){
	return NumeroParticipantes;
	}
}



