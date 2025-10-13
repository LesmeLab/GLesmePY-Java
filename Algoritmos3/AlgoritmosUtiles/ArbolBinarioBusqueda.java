//NO FUNCIONAL

public class ArbolBinarioBusqueda {
	private class Nodo{
		public int elemento;
		public Nodo izq = null;
		public Nodo der = null;
		
		public Nodo (int elemento){
			this.elemento = elemento;
		}
	}
	
	private Nodo raiz = null;
	
	public void insertar (int x){
		Nodo temporal = new Nodo (x);
		if (raiz == null){
			raiz = temporal;
		} else {
			Nodo reco = raiz;
			Nodo anterior=null;
			while (reco!=null){
				anterior=reco;
				if (x<reco.elemento){
					reco=reco.izq;
				} else {
					reco=reco.der;
				}
			}
			if (x<anterior.elemento){
				anterior.izq = temporal;
			} else {
				anterior.der = temporal;
			}
		}
	}
	
	public void eliminar (int x){
		if (raiz != null){
			Nodo reco = raiz;
			Nodo anterior = null;
			private int bandera=0; //0 por default, 1 si es igual
			while ((reco != null) && (reco.elemento == x){
				anterior = reco;
				if (x<reco.elemento){
					reco=reco.izq;
				} else {
					reco=reco.der;
				} 
			}
			if (reco!=null){ //Si reco es null, no esta x
				//Caso1->Hoja
				if (reco.izq == null && reco.der = null){
					if (x<anterior.elemento){
						anterior.izq = null;
					} else {
						anterior.der = null;
					}
				}
				//Caso2->1Hijo
				if ((reco.izq = null && reco.der !=null)){ 
					if (x == anterior.izq.elemento){
						anterior.izq = reco.der;
					} else if (x == anterior.der.elemento){
						anterior.der = reco.der;
					}
					reco.der = null;
					reco.izq = null;
				}
				
				if (reco.izq != null && reco.der == null){
					if (x == anterior.izq.elemento){
						anterior.izq = reco.izq;
					} else if (x == anterior.der.elemento){
						anterior.der = reco.izq;
					}
				}
				//Caso3->2Hijos, elevando al max del sub arbol izq
				if (reco.izq != null && reco.der != null){
					
				}
			}
			
		}
	}
}