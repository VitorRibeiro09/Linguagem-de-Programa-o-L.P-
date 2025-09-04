package Classes;

public class Main {
	public static void main(String[] args) {
		Navio n1 = new Navio ("Titanic", 52310.0, "Nova York");
		n1.navegar();
		n1.setDestino("Londres");
		n1.navegar();
		
		
		
		Mar m1 = new Mar("Vermelho", 25.03, "agitado");
		m1.mudarEstado();
		m1.setEstado("Verdin");
		m1.mudarEstado();
		
		Pessoa p1 = new Pessoa("Vitor", "Caçapava", "apaixonado");
		p1.pessoaEmoção();
		p1.setEmoção("Louco de paixão");
		p1.pessoaEmoção();
	}
}	
