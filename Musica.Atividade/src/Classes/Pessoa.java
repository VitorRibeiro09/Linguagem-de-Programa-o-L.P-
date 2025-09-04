package Classes;

public class Pessoa {
	private String nome;
	private String emoção;
	private String localização;

public Pessoa(String nome, String localização,String emoção) {
	this.nome = nome;
	this.emoção = emoção;
	this.localização = localização;

}

public void pessoaEmoção() {
	System.out.println(" O " + nome + " está " + emoção + ".");

}

public String getNome() {
return nome;

}

public void setNome(String nome) {
	this.nome = nome;
	
}

public String getEmoção() {
	return emoção;
	
}

public void setEmoção(String emoção) {
	this.emoção = emoção;

}

public String getLocalização() {
	return localização;
	
}

public void setLocalização(String localização) {
	this.localização = localização;
	}
}
	
