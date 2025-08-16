import java.util.Scanner;
//Clase Padre
public class Operacion {
	protected Scanner teclado;
	protected int valor1;
	protected int valor2;
	protected int resultado;
	
	public Operacion (){
		teclado = new Scanner ( System.in );
	}
	
	public void cargar1 () {
		System.out.print("Ingrese el primer valor : ");
		valor1 = teclado.nextInt();
	}
	
	public void cargar2 () {
		System.out.print("Ingrese el segundo valor : ");
		valor2 = teclado.nextInt();
	}
	
	public void mostrarResultado () {
		System.out.println(resultado);
	}
}