package Quadra;

public class QuadraEsportiva {
	
	private String  tipoesporte;
	private int numerojogador;
	private int placar;

public QuadraEsportiva(String  tipoesporte, int numerojogador, int placar) {
	this.tipoesporte= tipoesporte;
	this.numerojogador = numerojogador;
	this. placar=  placar;
	
}
public String IniciarJogo() {
	return tipoesporte;
	
}
public int EncerrarJogo() {
	return numerojogador;
}
public int MostrarPlacar(){
	return placar;
		}
	}
