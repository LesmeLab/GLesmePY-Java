public class Cliente{
	private String nombre;
	private int monto;
	
	public Cliente ( String nombre ){
		this.nombre = nombre;
	}
	
	public void depositar ( int monto ){
		this.monto = monto;
	}
	
	public void extraer ( int monto ){
		this.monto = this.monto - monto;
	}
	
	public int retornarMonto (){
		return monto;
	}
	
	public void imprimir (){
		System.out.println("Cliente -> " + nombre);
		System.out.println("Deposito -> " + monto);
	}
}

