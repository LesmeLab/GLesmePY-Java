public class Prueba {
	public static void main ( String [] args ){
		Suma suma1 = new Suma();
		suma1.cargar1();
		suma1.cargar2();
		suma1.operar();
		System.out.print("El resultado de la suma es " );
		suma1.mostrarResultado();
	}
}