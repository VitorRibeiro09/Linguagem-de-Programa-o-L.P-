package mediacalculo;
import java.util.Scanner;
public class CalculadorPolinomio {

	    // Função para calcular o valor do polinômio em x
	    public static double calcularPolinomio(double[] coeficientes, double x) {
	        double resultado = 0.0;
	        int grau = coeficientes.length;

	        // Calculando o valor do polinômio
	        for (int i = 0; i < grau; i++) {
	            resultado += coeficientes[i] * Math.pow(x, grau - i - 1); // x^(grau - i - 1)
	        }

	        return resultado;
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Pedir o grau do polinômio
	        System.out.print("Informe o grau do polinômio (máximo): ");
	        int grau = scanner.nextInt();

	        // Criar o array para armazenar os coeficientes
	        double[] coeficientes = new double[grau + 1];  // Grau + 1 coeficientes

	        // Ler os coeficientes
	        System.out.println("Informe os coeficientes do polinômio, do maior para o menor grau:");
	        for (int i = 0; i <= grau; i++) {
	            System.out.print("Coeficiente de x^" + (grau - i) + ": ");
	            coeficientes[i] = scanner.nextDouble();
	        }

	        // Pedir o valor de x
	        System.out.print("Informe o valor de x para calcular P(x): ");
	        double x = scanner.nextDouble();

	        // Calcular o valor do polinômio em x
	        double resultado = calcularPolinomio(coeficientes, x);

	        // Exibir o resultado
	        System.out.println("O valor do polinômio em x = " + x + " é: " + resultado);

	        scanner.close();
	    }
	}


