//NO FUNCIONAL

public class ListaSimplementeEnlazada {
	private class Nodo {
		public int elemento;
		public Nodo siguiente = null;
		
		public Nodo (int elemento){
			this.elemento = elemento;
		}
	}
	
	private Nodo cabeza = null;
	private int longitud = 0;
	
	public ListaSimplementeEnlazada (){
		cabeza=null;
	}
	
	public void insertarInicio (int x){
		Nodo temporal = new Nodo (x);
		if (cabeza == null){
			cabeza = temporal;
		} else {
			temporal.siguiente = cabeza;
			cabeza=temporal;
		}
		longitud++;
	}
	
	public Nodo index (int x){
		Nodo temporal = cabeza;
		if (x<0 || x>=longitud){
			return null;
		}
		for (int contador = 0; contador<x; contador++){
			temporal=temporal.siguiente;
		}
		return temporal;
	}
	
	public void insertar (int n, int x){
		Nodo temporal = new Nodo (x);
		if (n == 0){
			insertarInicio(x);
		} else {
			Nodo actual = index(n-1);
			temporal.siguiente = actual.siguiente;
			actual.siguiente = temporal;
			longitud++;
		}
	}
	
	public void eliminarInicio (){
		if (cabeza!=null){
			Nodo temporal = cabeza;
			cabeza = temporal.siguiente;
			temporal.siguiente = null;
			longitud--;
		}
	}
	
	public void eliminarIndex (int n){
		if (n>=0 && n<longitud){
			Nodo actual = index(n-1);
			if (n == 0){
				eliminarInicio();
			} else if (n == longitud-1){
				actual.siguiente = null;
				longitud--;
			} else {
				Nodo temporal = actual.siguiente;
				actual.siguiente = temporal.siguiente;
				temporal.siguiente = null;
				longitud--;
			}	
		}
	}
	
	public void imprimir (){
		Nodo actual = cabeza;
		int x = 0;
		System.out.print("[ ");
		while (x<longitud && actual!=null){
			System.out.print(actual.elemento);
			if (x<longitud-1){
				System.out.print(" , ");
			}
			x++;
			actual=actual.siguiente;
		}
		System.out.print(" ]");
		System.out.println();
	}
	
	public int len (){
		return longitud;
	}
	
	public static void main (String [] args){
		ListaSimplementeEnlazada listaUno = new ListaSimplementeEnlazada ();
		for (int x = 0; x<10; x++){
			listaUno.insertar(0,x);
		}
		listaUno.imprimir();
		listaUno.eliminarIndex(1);
		listaUno.imprimir();
		int as = listaUno.len();
		listaUno.eliminarIndex(as);
		listaUno.imprimir();
	}
}