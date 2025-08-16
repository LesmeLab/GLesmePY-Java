import java.util.Scanner;

public class Persona {
	protected Scanner teclado;
	protected String nombre;
	protected int edad;
	
	public Persona () {
		teclado = new Scanner ( System.in );
	}
	
	public void responsabilidadesCarga () {
		System.out.print("Nombre -> ");
		nombre = teclado.nextLine();
		System.out.print("Edad -> ");
		edad = teclado.nextInt();
	}
	
	public void responsabilidadesImprimir () {
		System.out.println("-----------------------------------------");
		System.out.println("Nombre -> " + nombre);
		System.out.println("Edad -> " + edad);
	}
	
}