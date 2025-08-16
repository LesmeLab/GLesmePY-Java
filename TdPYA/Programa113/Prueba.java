public class Prueba {
	public static void main ( String [] args ){
		Persona p1 = new Persona();
		p1.responsabilidadesCarga();
		
		Empleado e1 = new Empleado();
		e1.cargaSueldo(8550);
		
		p1.responsabilidadesImprimir();
		e1.imprimirSueldo();
	}
}