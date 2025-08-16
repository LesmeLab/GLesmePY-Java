public class Empleado extends Persona {
	private float sueldo;
	public void cargaSueldo( float sueldo ){
		this.sueldo = sueldo;
	}
	
	public void imprimirSueldo () {
		System.out.println("Sueldo -> " + sueldo);
	}
}