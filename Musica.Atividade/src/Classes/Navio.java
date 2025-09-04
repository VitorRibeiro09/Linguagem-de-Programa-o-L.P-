package Classes;

public class Navio {
private String nome;
private Double tonelagem;
private String destino;

public Navio(String nome, Double tonelagem, String destino) {
this.nome = nome;
this.tonelagem = tonelagem;
this.destino = destino;

}

public void navegar() {
	System.out.println(" O navio " + nome + " está navegando " + destino + ".");
	
}

public String getNome() {
return nome;

}

public void setNome(String nome) {
	this.nome = nome;
	
}

public Double getTonelagem() {
	return tonelagem;
	
}

public void setTonelagem(Double tonelagem){
	this.tonelagem = tonelagem;
	
}

public String getDestino() {
	return destino;
	
}

public void setDestino(String destino) {
	this.destino = destino;

}


}


