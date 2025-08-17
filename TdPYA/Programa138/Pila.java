public class Pila {
	
	class Nodo {
		int info;
		Nodo sig;
	}
	
	private Nodo raiz; //automatico en null
	
	/* public Pila () {
		raiz = null;
	} */ //No necesario
	
	public void insertar (int x) {
		Nodo nuevo = new Nodo();
		nuevo.info = x;
		if (raiz == null){
			raiz = nuevo;
			//nuevo.sig = null           // no necesario
		}
		else {
			nuevo.sig = raiz;
			raiz = nuevo;
		}
	}
	
	public void imprimir () {
		Nodo reco = raiz;
		while (reco != null){
			System.out.print(reco.info + "-");
			reco = reco.sig;
		}
		System.out.println();
	}
	
	public int extraer () {
		if ( raiz == null ){
			return Integer.MAX_VALUE;
		} else {
			int informacion = raiz.info;
			raiz = raiz.sig;
			return informacion;
		}
	}
	
	public int cantidad (){
		int cant = 0;
		Nodo reco = raiz;
		while ( reco!=null ){
			reco = reco.sig;
			cant++;
		}
		return cant;
	}
	
	public boolean vacia () {
		if ( raiz==null ){
			return true;
		} else {
			return false;
		}
	}
	
	public int retornar (){
		if ( raiz == null ){
			return Integer.MAX_VALUE;
		} else {
			return raiz.info;
		}
	}
	
	public static void main (String [] args ) {
		Pila pila1 = new Pila();
		pila1.insertar(5);
		pila1.insertar(40);
		pila1.insertar(32);
		pila1.imprimir();
		System.out.println("Extraemos el primer nodo " + pila1.extraer() );
		System.out.println("Cantidad de Nodos " + pila1.cantidad() );
		pila1.imprimir();
		pila1.extraer();
		pila1.extraer();
		if (pila1.vacia()){
			System.out.println("Esta vacia");
		} else {
			System.out.println("No esta vacia");
		}
	}
}