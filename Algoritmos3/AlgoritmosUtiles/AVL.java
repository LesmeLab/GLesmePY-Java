public class AVL {
	private class Nodo {
		public int hijosIzq = 0;
		public int hijosDer = 0;
		public int factorEQ = 0;
		public Nodo izq = null;
		public Nodo padre = null;
		public Nodo der = null;
		public int dato;
		
		public Nodo(int dato){
			this.dato = dato;
		}
	}
	
	private int totalIzq = 0;
	private Nodo raiz = null;
	private int totalDer = 0;
	
	public boolean estaVacio(){
		if (raiz == null){
			return true;
		}
		return false;
	}
	
	public void preOrden (Nodo reco){
		if (reco != null){
			System.out.printf(" %d ",reco.dato);
			preOrden(reco.izq);
			preOrden(reco.der);
		}
	}
	
	public void inOrden (Nodo reco){
		if (reco!=null){
			inOrden(reco.izq);
			System.out.printf(" %d FE=%d %n", reco.dato,reco.factorEQ);
			inOrden(reco.der);
		}
	}
	
	public void postOrden(Nodo reco){
		if (reco!=null){
			postOrden(reco.izq);
			postOrden(reco.der);
			System.out.printf("%d%n",reco.dato);
		}
	}
	
	public void imprimirPostOrden(){
		if (!estaVacio()){
			postOrden(raiz);
		}
	}
	
	public void imprimirPreOrden(){
		if (!estaVacio()){
			preOrden(raiz);
		}
	}
	
	public void imprimirInOrden(){
		if (!estaVacio()){
			inOrden(raiz);
		}
	}
	
	public Nodo buscarNodo(int x){
		Nodo aux = null;
		if (!estaVacio()){
			aux = raiz;
			while (aux !=null && aux.dato!=x){
				if (x<aux.dato){
					aux=aux.izq;
				} else {
					aux = aux.der;
				}
			}
		}
		return aux;
	}
	
	public Nodo hijoNoNullDelNodo (Nodo reco){
		if (reco.izq != null){
			return reco.izq;
		} else {
			return reco.der;
		}
	}
	
	public int alturaNodo (Nodo reco){
		if (reco!=null){
			int hi = alturaNodo(reco.izq);
			int hd = alturaNodo(reco.der);
			if (hi>hd){
				return h1+1;
			} else {
				return hd+1;
			}
		} else {
			return 0;
		}
	}
	
	public int factorEquilibrio(Nodo reco){
		if (reco != null){
			return alturaNodo(reco.der)-alturaNodo(reco.izq);
		}
		return 0;
	}
	
	public void rotacionIzquierda(Nodo reco){
		Nodo aux, p, q;
		aux = reco.der;
		q = aux.izq;
		p = reco.padre;
		
		reco.der = q;
		aux.izq = reco;
		
		if (q!=null){
			q.padre = n;
		}
		reco.padre=aux;
		aux.padre=p;
		if (p==null){
			raiz = aux;
		} else {
			if (aux.dato > p.dato){
				p.der = aux;
			} else {
				p.izq = aux;
			}
		}
		aux.factorEQ=factorEquilibrio(aux);
		reco.factorEQ = factorEquilibrio(reco);
	}
	
	public void rotacionDerecha (Nodo reco){
		Nodo aux, p ,q;
		aux = reco.izq;
		q = aux.der;
		p = reco.padre;
		
		reco.izq = q;
		aux.der = reco;
		
		if (q!=null){
			q.padre=reco;
		}
		reco.padre=aux;
		aux.padre=p;
		if (p==null){
			raiz = null;
		} else {
			if (aux.dato > p.dato){
				p.der = aux;
			} else {
				p.izq = aux;
			}
		}
		aux.factorEQ=factorEquilibrio(aux);
		reco.factorEQ=factorEquilibrio(reco);
	}
	
	public void rotacionDobleIzquierda(Nodo reco){
		rotacionDerecha(reco.izq);
		rotacionIzquierda(reco);
	}
	
	public void rotacionDobleDerecha(Nodo reco){
		rotacionIzquierda(reco.izq);
		rotacionDerecha(reco);
	}
	
	public void balancear(Nodo reco){
		Nodo anterior = null;
		if (p!=null){
			anterior=reco.padre;
			reco.factorEQ=factorEquilibrio(reco);
			if (reco.factorEQ >1){
				if(factorEquilibrio(reco.der) < 0){
					rotacionDobleIzquierda(reco);
				} else {
					rotacionIzquierda(reco);
				}
			} else if (reco.factorEQ < -1){
				if(factorEquilibrio(reco.izq)>0){
					rotacionDobleDerecha(reco);
				} else {
					rotacionDerecha(reco);
				}
			}
			balancear(anterior);
		}
	}
	
	public void insertarNodo (Nodo reco){
		Nodo aux,ant;
		if (estaVacio()){
			raiz = reco;
		} else {
			aux =raiz;
			while (aux!=null){
				ant=aux;
				if (reco.dato > aux.dato){
					aux=aux.der;
				} else {
					aux=aux.izq;
				}
			}
			reco.padre = ant;
			if (reco.dato > ant.dato){
				ant.der = n;
			} else {
				ant.izq = reco;
			}
			balancear(ant);
		}
	}
	
	public void eliminarNodo(int valor){
		Nodo aux = buscarNodo(valor);
		Nodo aux2;
		Nodo anterior;
		if (aux!=null){
			anterior=aux.padre;
			if (aux.izq == null && aux.der == null){
				if (anterior!=null){
					if(aux.dato < anterior.dato){
						anterior.izq = null;
					} else {
						anterior.der = null;
					}
				} else {
					raiz=null;
				}
			} else if(aux.der==null || aux.izq==null){
				if (anterior!=null){
					if (aux.dato < anterior.dato){
						anterior.izq=hijoNoNullDelNodo(aux);
						anterior.izq.padre=anterior;
					} else {
						anterior.der = hijoNoNullDelNodo(aux);
						anterior.der.padre=anterior;
					}
				} else {
					raiz = hijoNoNullDelNodo(aux);
				}
			} else {
				aux2 =aux.izq;
				while (aux2.der!=null){
					aux2=aux2.der;
				}
				anterior=aux2.padre;
				if (aux2.dato < anterior.dato){
					anterior.izq = aux2.izq;
				} else {
					anterior.der=aux2.izq;
				}
				aux.dato = aux2.dato;
			}
			balancear(anterior);
		}
	}
}