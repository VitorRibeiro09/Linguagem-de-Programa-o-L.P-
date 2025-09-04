package Classes;

public class Mar {
	private String cor;
	private Double profundidade;
	private String estado;

public Mar(String cor, Double profundidade, String estado) {
	this.cor = cor;
	this.profundidade = profundidade;
	this.estado = estado;
	
}

public void mudarEstado() {
	System.out.println(" O mar " + cor + " está " + estado + ".");

}

public String getCor() {
return cor;

}

public void setCor(String cor) {
	this.cor = cor;
	
}

public Double getProfundidade() {
	return profundidade;
	
}

public void setProfundidade(Double profundidade) {
	this.profundidade = profundidade;

}

public String getEstado() {
	return estado;
	
}

public void setEstado(String estado) {
	this.estado = estado;
}
}
	



