public class ListaGenerica {
	class Nodo {
		int info;
		Nodo sig;
	}
	
	private Nodo raiz;
	
	public boolean vacia (){
		if ( raiz == null ){
			return true;
		} else {
			return false;
		}
	}
	
	public int cantidad (){
		int cant = 0;
		Nodo recorrido = raiz;
		while ( recorrido != null ){
			cant++;
			recorrido = recorrido.sig;
		}
		return cant;
	}
	
	public void insertar ( int pos, int x ){
		if ( pos <= cantidad()+1 ){
			Nodo nuevo = new Nodo();
			nuevo.info = x;
			if ( pos == 1 ){
				nuevo.sig = raiz;
				raiz = nuevo;
			} else {
				if ( pos == cantidad() + 1 ){
					Nodo recorrido = raiz;
					while ( recorrido.sig != null ){
						recorrido = recorrido.sig;
					}
					recorrido.sig = nuevo;
				} else {
					Nodo recorrido = raiz;
					for ( int f=1; f<=pos-2 ; f++){
						recorrido = recorrido.sig;
					}
					Nodo siguiente = recorrido.sig;
					recorrido.sig = nuevo;
					nuevo.sig = siguiente;
				}
			}
		}
	}
	
	public void extraer( int pos ){
		if (pos > cantidad()){
			return Integer.MAX_VALUE;
		} else {
			if ( pos == 1 ){
				int informacion = raiz.info;
				raiz = raiz.sig;
				return informacion;
			} else {
				Nodo recorrido = raiz;
				for ( int f=1 ; f<pos ; f++){
					recorrido = recorrido.sig;
				}
				Nodo proximo = recorrido.sig;
				int informacion = proximo.info;
				reco.sig = prox.sig;
				return informacion;
			}
		}
	}
	
	public void borrar ( int pos ){
		if ( pos >= cantidad() ){
			return ;
		} else {
			if ( pos==1 ){
				raiz = raiz.sig;
			} else {
				Nodo recorrido = raiz;
				for ( int f=1 ; f<pos ; f++){
					recorrido = recorrido.sig;
				}
				Nodo proximo = recorrido.sig;
				reco.sig = prox.sig;
			}
		}
	}
	
	public void intercambiar ( int pos1 , int pos2 ){
		if ( pos1 <= cantidad() && pos2 <= cantidad() ){
			Nodo recorrido1 = raiz;
			for (int f=1; f < pos1 ; f++){
				recorrido1 = recorrido1.sig;
			}
			Nodo recorrido2 = raiz;
			for (int f=1; f < pos1 ; f++){
				recorrido2 = recorrido2.sig;
			}
			int aux = recorrido1.info;
			recorrido1.info = aux;
		}
	}
	
	public int mayor (){
		if ( vacia() ){
			return Integer.MAX_VALUE;
		} else {
			int mayor = raiz.info;
			Nodo recorrido = raiz.sig;
			while (recorrido != null ){
				if ( recorrido.info > mayor ){
					mayor = recorrido.info;
				}
				recorrido = recorrido.sig;
			}
			return mayor;
		}
	}
	
	public int posMayor (){
		if ( vacia() ){
			return Integer.MAX_VALUE;
		} else {
			int mayor = raiz.info;
			Nodo recorrido = raiz.sig;
			int pos = 1;
			int c =1;
			while (recorrido != null ){
				c++;
				if ( recorrido.info > mayor ){
					mayor = recorrido.info;
					pos =0;
				}
				recorrido = recorrido.sig;
			}
			return pos;
		}
	}
	
	public boolean ordenada (){
		if ( cantidad()>1) {
			Nodo recorrido1 = raiz;
			Nodo recorrido2 = raiz.sig;
			while ( recorrido2 != null ){
				if ( recorrido2.info < recorrido1.info ){
					return false;
				}
				recorrido1 = recorrido1.sig;
				recorrido2 = recorrido2.sig;
			}
		}
		return true;
	}
	
	public boolean existe ( int info ){ // existe info en L
		Nodo recorrido = raiz;
		while ( recorrido ! = null ){
			if ( recorrido.info == info ){
				return true;
			}
			recorrido = recorrido.sig;
		}
		return false;
	}
	
	public void imprimir () {
		Nodo recorrido = raiz;
		while ( recorrido != null ){
			System.out.print(recorrido.info + " - ");
		}
		System.out.println();
	}
	
	public static void main ( String [] args){
		ListaGenerica L1 = new ListaGenerica();
	}
}