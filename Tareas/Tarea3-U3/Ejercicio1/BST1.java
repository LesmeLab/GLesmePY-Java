/*
	Grupo : g60
  Integrantes:
   Alejandro Javier Armoa Bordón      CIC: 6547586   Sección: TQ
   Gustavo Emanuel Lesme Ortega       CIC: 5249373   Sección: TQ
  Tarea 3 - U3(Java) – Ejercicio 3

  Declaración de HONOR: 
    • Yo Gustavo Emanuel Lesme Ortega y Alejandro Javier Armoa Bordón
		• No he/hemos discutido el código fuente de mi/nuestra tarea con ningún otro grupo, solo con el Profesor o el AER.
		• No he/hemos usado código obtenido de otro estudiante o de cualquier otra fuente no autorizada, modificada o no modificada.
		• Cualquier código o documentación utilizada en mi/nuestro programa obtenido de fuentes, tales como libros o notas de curso, han sido claramente indicada en mi/nuestra tarea.
 */

import java.util.Iterator;
/**
 * Implementa un Arbol Binario de Búsqueda (BST) generico.
 *
 * <p>Esta clase permite almacenar elementos comparables en una estructura de árbol,
 * lo que facilita operaciones como inserción, búsqueda y eliminación de manera
 * eficiente. La clase también incluye iteradores y métodos para verificar
 * propiedades del árbol como si está lleno o completo.</p>
 *
 * @param <T> el tipo de datos que se almacenará en el BST. Debe implementar la
 * interfaz {@link Comparable}.
 */
public class BST1 <T extends Comparable<T>> implements Iterable<T> {

	/**
     * Representa un nodo dentro del BST.
     *
     * <p>Cada nodo contiene un dato y referencias a sus hijos izquierdo y derecho,
     * permitiendo la estructura jerárquica del árbol.</p>
     */
    private class NodoBST {
        T dato = null;
        NodoBST izq = null;
        NodoBST der = null;
		
		/**
         * Construye un nuevo nodo con el dato especificado.
         *
         * @param dato el dato a ser almacenado en el nodo.
         */
        public NodoBST(T dato) {
            this.dato = dato;
        }
    }
	
	/**
     * Implementa una cola simple para nodos de BST.
     *
     * <p>Esta clase de utilidad se utiliza para recorridos por niveles del árbol,
     * como en los métodos {@code esCompleto} e {@code imprimirPorNiveles}.</p>
     */
	private class ColaNodoBST {
		private class NodoCola { //implementacion interna de una cola
			NodoBST nodo;
			NodoCola siguiente;

			public NodoCola(NodoBST nodo) {
				this.nodo = nodo;
				this.siguiente = null;
			}
		}
		
		private NodoCola frente = null;
		private NodoCola fondo = null;
		
		/**
         * Agrega un nodo al final de la cola.
         *
         * @param nodo el nodo de BST a encolar.
		 * Costo: O(1)
         */
		public void encolar(NodoBST nodo) {
			NodoCola nuevo = new NodoCola(nodo);
			if (fondo != null) {
				fondo.siguiente = nuevo;
			} else {
				frente = nuevo;
			}
			fondo = nuevo;
		}
		
		/**
         * Remueve y retorna el nodo al frente de la cola.
         *
         * @return el nodo que estaba al frente de la cola.
         * @throws RuntimeException si la cola está vacía.
		 * Costo: O(1)
         */
		public NodoBST desencolar() {
			if (frente == null) throw new RuntimeException("Cola vacía");
			NodoBST resultado = frente.nodo;
			frente = frente.siguiente;
			if (frente == null) fondo = null;
			return resultado;
		}
		
		/**
         * Verifica si la cola está vacía.
         *
         * @return {@code true} si la cola no tiene elementos, {@code false} en caso contrario.
		 * Costo: O(1)
         */
		public boolean estaVacia() {
			return frente == null;
		}
		
		/**
		 * Devuelve el nodo en la posición indicada dentro de la cola sin eliminarlo.
		 * Se recorre la cola desde el frente hasta el índice especificado.
		 *
		 * @param index La posición del nodo que se desea consultar (comenzando desde 0).
		 * @return El nodo en la posición indicada, o {@code null} si el índice está fuera de rango.
		 * Complejidad: O(n), donde n es el índice solicitado.
		 * Costo: O(k)
		 */
		public NodoBST verFrente(int index) {
			NodoCola actual = frente;
			for (int i = 0; i < index && actual != null; i++) {
				actual = actual.siguiente;
			}
			return actual != null ? actual.nodo : null;
		}
	}

	/**
     * Implementa una pila simple para nodos de BST.
     *
     * <p>Esta clase de utilidad es fundamental para el funcionamiento del iterador
     * en orden del árbol.</p>
     */
    private class PilaNodoBST {
        private class NodoPila {
            NodoBST nodo;
            NodoPila siguiente;

            NodoPila(NodoBST nodo, NodoPila siguiente) {
                this.nodo = nodo;
                this.siguiente = siguiente;
            }
        }

        private NodoPila tope = null;
		
		/**
         * Inserta un nodo en el tope de la pila.
         *
         * @param nodo el nodo de BST a apilar.
		 * Costo: O(1)
         */
        public void push(NodoBST nodo) {
            tope = new NodoPila(nodo, tope);
        }
		
		/**
         * Remueve y retorna el nodo del tope de la pila.
         *
         * @return el nodo que estaba en el tope de la pila.
         * @throws RuntimeException si la pila está vacía.
		 * Costo: O(1)
         */
        public NodoBST pop() {
            if (tope == null) {
                throw new RuntimeException("Pila Vacia");
            }
            NodoBST resultado = tope.nodo;
            tope = tope.siguiente;
            return resultado;
        }
		
		/**
         * Verifica si la pila está vacía.
         *
         * @return {@code true} si la pila no tiene elementos, {@code false} en caso contrario.
		 * Costo: O(1)
         */
        public boolean isEmpty() {
            return tope == null;
        }
    }
	
	/**
     * Retorna un iterador para recorrer el árbol en orden (in-order).
     *
     * @return un objeto {@link Iterator} que permite recorrer los elementos del árbol
     * de menor a mayor.
	 * Costo: O(h) (Promedio: O(logn), Peor: O(n))
     */
    @Override
    public Iterator<T> iterator() {
        return new BSTIterator(raiz);
    }
	
	/**
     * Implementa un iterador para recorrer el BST en orden.
     *
     * <p>Utiliza una pila para realizar un recorrido in-order no recursivo,
     * lo que permite iterar sobre los elementos del árbol de forma ascendente.</p>
     */
    private class BSTIterator implements Iterator<T> {
        private PilaNodoBST pila = new PilaNodoBST();
		/**
		* Costo: O(h) (Promedio: O(logn), Peor: O(n))
		*/
        public BSTIterator(NodoBST raiz) {
            empujarIzquierda(raiz);
        }
		/**
		* Costo: O(h subarbol)
		*/
        private void empujarIzquierda(NodoBST nodo) {
            while (nodo != null) {
                pila.push(nodo);
                nodo = nodo.izq;
            }
        }
		
		/**
         * Verifica si quedan elementos por recorrer en el árbol.
         *
         * @return {@code true} si hay más elementos, {@code false} en caso contrario.
		 * Costo: O(1)
         */
        @Override
        public boolean hasNext() {
            return !pila.isEmpty();
        }
		
		/**
         * Retorna el siguiente elemento en el recorrido in-order del árbol.
         *
         * @return el siguiente elemento del árbol.
         * @throws RuntimeException si no hay más elementos para iterar.
		 * Costo: O(1)
         */
        @Override
        public T next() {
            if (!hasNext()) throw new RuntimeException("No hay más elementos");

            NodoBST actual = pila.pop();
            empujarIzquierda(actual.der);
            return actual.dato;
        }
    }
	
	/**
	* Nodo raíz del árbol binario de búsqueda.
	*/
    private NodoBST raiz = null;

	/**
     * Agrega un nuevo dato al árbol binario de búsqueda.
     *
     * <p>Si el dato ya existe en el árbol, la operación es ignorada. El método
     * mantiene la propiedad del BST, insertando el nuevo dato a la izquierda
     * si es menor que el nodo actual, o a la derecha si es mayor.</p>
     *
     * @param dato el dato a ser insertado en el árbol.
	 * Costo: O(h) (Promedio: O(logn), Peor: O(n))
     */
    public void agregar(T dato) {
        raiz = priv_agregar(raiz, dato);
    }

    /**
     * Busca un dato específico en el árbol.
     *
     * <p>La búsqueda se realiza comparando el dato proporcionado con los datos
     * de los nodos, aprovechando la estructura ordenada del BST para una
     * operación eficiente.</p>
     *
     * @param dato el dato a buscar dentro del árbol.
     * @return el dato encontrado en el árbol; {@code null} si no se encuentra.
	 * Costo: O(h) (Promedio: O(logn), Peor: O(n))
     */
    public T buscar(T dato) {
        NodoBST nodo = priv_buscar(raiz, dato);
		if (nodo == null) {
			throw new RuntimeException("El valor " + dato + " no existe en el árbol");
		}
		return nodo.dato;
    }

    /**
     * Imprime los elementos del árbol en un recorrido in-order.
     *
     * <p>Este método muestra en la consola todos los datos del árbol de forma
     * ordenada (ascendente).</p>
	 * Costo: O(n)
     */
    public void imprimir() {
        System.out.println();
        priv_imprimir(raiz);
        System.out.println();
    }

	/**
		* Método auxiliar recursivo para insertar un dato en el árbol.
	 *
	 * <p>Este método compara el dato con el nodo actual y lo inserta
	 * en la posición correspondiente para mantener la propiedad del BST.</p>
	 *
	 * @param n_actual el nodo actual desde el cual se realiza la inserción.
	 * @param dato el dato a insertar.
	 * @return el nodo actualizado después de la inserción.
	 * Costo: O(h) (Promedio: O(logn), Peor: O(n))
	 */
    private NodoBST priv_agregar(NodoBST n_actual, T dato) {
        if (n_actual == null)
            return (new NodoBST(dato));

        int comparacion = dato.compareTo(n_actual.dato);

        if (comparacion < 0) {
            n_actual.izq = priv_agregar(n_actual.izq, dato);
        } else if (comparacion > 0) {
            n_actual.der = priv_agregar(n_actual.der, dato);
        }
        // comparacion=0 se ignora el dato

        return n_actual;
    }

	/**
	 * Método auxiliar para imprimir los elementos del árbol en orden.
	 *
	 * <p>Realiza un recorrido in-order recursivo desde el nodo actual.</p>
	 *
	 * @param n_actual el nodo desde el cual se inicia la impresión.
	 * Costo: O(n)
	 */
    private void priv_imprimir(NodoBST n_actual) {
        if (n_actual != null) {
            priv_imprimir(n_actual.izq);
            System.out.print(n_actual.dato + " ");
            priv_imprimir(n_actual.der);
        }
    }

	/**
	 * Método auxiliar recursivo para buscar un dato en el árbol.
	 *
	 * <p>Compara el dato con el nodo actual y recorre el árbol hacia
	 * la izquierda o derecha según corresponda.</p>
	 *
	 * @param n_actual el nodo actual desde el cual se realiza la búsqueda.
	 * @param dato el dato a buscar.
	 * @return el nodo que contiene el dato, o {@code null} si no se encuentra.
	 * Costo: O(h) (Promedio: O(logn), Peor: O(n))
	 */
    private NodoBST priv_buscar(NodoBST n_actual, T dato) {
        if (n_actual == null) // dato no se encuentra en el arbol
            return null;

        int comparacion = dato.compareTo(n_actual.dato);

        if (comparacion == 0) // dato == n_actual.dato
            return n_actual;
        else if (comparacion < 0) // dato < n_actual.dato, puede estar a la izquierda
            return priv_buscar(n_actual.izq, dato);
        else // dato > n_actual.dato, puede estar a la derecha
            return priv_buscar(n_actual.der, dato);
    }
	
	/**
     * Elimina un valor específico del árbol.
     *
     * <p>Maneja los tres casos de eliminación en un BST: el nodo a eliminar
     * no tiene hijos, tiene un solo hijo, o tiene dos hijos. La estructura
     * del árbol se reajusta para mantener la propiedad de BST.</p>
     *
     * @param valor el valor a ser eliminado del árbol.
	 * Costo: O(h) (Promedio: O(logn), Peor: O(n))
     */
    public void eliminar(T valor) {
        raiz = priv_eliminar(raiz, valor);
    }

	/**
	 * Método auxiliar recursivo para eliminar un nodo del árbol.
	 *
	 * <p>Identifica el nodo a eliminar y ajusta la estructura del árbol
	 * según el caso: sin hijos, con un hijo o con dos hijos.</p>
	 *
	 * @param actual el nodo actual desde el cual se realiza la eliminación.
	 * @param valor el valor a eliminar.
	 * @return el nodo actualizado después de la eliminación.
	 * Costo: O(h) (Promedio: O(logn), Peor: O(n))
	 */
    private NodoBST priv_eliminar(NodoBST actual, T valor) {
        if (actual == null) return null;

        int cmp = valor.compareTo(actual.dato);

        if (cmp < 0) {
            actual.izq = priv_eliminar(actual.izq, valor);
        } else if (cmp > 0) {
            actual.der = priv_eliminar(actual.der, valor);
        } else {
            // Caso 1: sin hijos
            if (actual.izq == null && actual.der == null) {
                return null;
            }
            // Caso 2: un solo hijo
            else if (actual.izq == null) {
                return actual.der;
            } else if (actual.der == null) {
                return actual.izq;
            }
            // Caso 3: dos hijos
            else {
                NodoBST sucesor = encontrarMin(actual.der);
                actual.dato = sucesor.dato;
                actual.der = priv_eliminar(actual.der, sucesor.dato);
            }
        }

        return actual;
    }

	/**
	 * Encuentra el nodo con el valor mínimo en el subárbol dado.
	 *
	 * <p>Recorre hacia la izquierda hasta encontrar el nodo más pequeño.</p>
	 *
	 * @param nodo el nodo raíz del subárbol.
	 * @return el nodo con el valor mínimo.
	 * Costo: O(h) (Promedio: O(logn), Peor: O(n))
	 */
    private NodoBST encontrarMin(NodoBST nodo) {
        while (nodo.izq != null) {
            nodo = nodo.izq;
        }
        return nodo;
    }

    /**
     * Encuentra el sucesor de un valor dado en el árbol.
     *
     * <p>El sucesor es el nodo con el menor valor que es mayor que el valor
     * proporcionado. Este método no implementa correctamente la búsqueda del
     * sucesor in-order estándar, sino que parece buscar el "padre izquierdo"
     * más cercano.</p>
     *
     * @param valor el valor para el cual se busca el sucesor.
     * @return el dato del nodo sucesor, o {@code null} si no existe.
	 * Costo: O(h) (Promedio: O(logn), Peor: O(n))
     */
	public T sucesor (T valor){
		NodoBST actual = raiz;
		NodoBST sucesor = null;
		
		while (actual != null){
			int cmp = valor.compareTo(actual.dato);
			if (cmp<0){
				sucesor = actual;
				actual = actual.izq;
			} else {
				actual = actual.der;
			}
		}
		
		return (sucesor !=null)? sucesor.dato : null;
	}
	
	/**
     * Encuentra el predecesor de un valor dado en el árbol.
     *
     * <p>El predecesor es el nodo con el mayor valor que es menor que el valor
     * proporcionado. Este método no implementa correctamente la búsqueda del
     * predecesor in-order estándar, sino que parece buscar el "padre derecho"
     * más cercano.</p>
     *
     * @param valor el valor para el cual se busca el predecesor.
     * @return el dato del nodo predecesor, o {@code null} si no existe.
	 * Costo: O(h) (Promedio: O(logn), Peor: O(n))
     */
	public T predecesor(T valor){
		NodoBST actual = raiz;
		NodoBST anterior = null;
		
		while (actual != null){
			int cmp = valor.compareTo(actual.dato);
			if (cmp > 0){
				anterior = actual;
				actual = actual.der;
			} else{
				actual = actual.izq;
			}
		}
		
		return (anterior != null) ? anterior.dato : null;
	}
	
	/**
     * Verifica si el árbol binario es completo.
     *
     * <p>Un árbol completo es aquel en el que todos los niveles, excepto
     * posiblemente el último, están completamente llenos, y todos los nodos
     * en el último nivel están lo más a la izquierda posible.</p>
     *
     * @return {@code true} si el árbol es completo, {@code false} en caso contrario.
	 * Costo: O(n)
     */
	public boolean esCompleto() {
		if (raiz == null) return true;

		ColaNodoBST cola = new ColaNodoBST();
		cola.encolar(raiz);
		boolean encontradoNodoIncompleto = false;

		while (!cola.estaVacia()) {
			NodoBST actual = cola.desencolar();

			if (actual.izq != null) {
				if (encontradoNodoIncompleto) return false;
				cola.encolar(actual.izq);
			} else {
				encontradoNodoIncompleto = true;
			}

			if (actual.der != null) {
				if (encontradoNodoIncompleto) return false;
				cola.encolar(actual.der);
			} else {
				encontradoNodoIncompleto = true;
			}
		}

		return true;
	}
	
	/**
     * Verifica si el árbol binario es lleno.
     *
     * <p>Un árbol lleno es un árbol en el que cada nodo tiene cero o dos hijos.</p>
     *
     * @return {@code true} si el árbol es lleno, {@code false} en caso contrario.
	 * Costo: O(n))
     */
	public boolean esLleno() {
		return esLleno(raiz);
	}

	/**
	 * Método auxiliar recursivo para verificar si el árbol es lleno.
	 *
	 * <p>Un árbol es lleno si cada nodo tiene 0 o 2 hijos.</p>
	 *
	 * @param nodo el nodo actual a evaluar.
	 * @return {@code true} si el subárbol es lleno, {@code false} en caso contrario.
	 * Costo: O(n)
	 */
	private boolean esLleno(NodoBST nodo) {
		if (nodo == null) return true;

		if ((nodo.izq == null && nodo.der != null) || (nodo.izq != null && nodo.der == null)) {
			return false;
		}

		return esLleno(nodo.izq) && esLleno(nodo.der);
	}
	
	
	private int altura(NodoBST nodo) {
		if (nodo == null) return 0;
		return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
	}
	
	/**
     * Imprime el árbol por niveles, mostrando su estructura.
     *
     * <p>Este método realiza un recorrido por niveles (BFS) para imprimir los
     * nodos en cada nivel del árbol. Para evitar bucles infinitos en árboles
     * desbalanceados o con muchos nodos nulos, la impresión se limita a una
     * profundidad máxima (en este caso, 5 niveles).</p>
	 * Costo: O(n)
     */
	public void imprimirPorNiveles() {
		if (raiz == null) return;

		ColaNodoBST cola = new ColaNodoBST();
		cola.encolar(raiz);

		int nivel = 0;
		int nodosEnNivel = 1;

		while (!cola.estaVacia()) {
			int siguienteNivel = 0;

			// Imprimir nodos del nivel actual
			for (int i = 0; i < nodosEnNivel; i++) {
				NodoBST actual = cola.desencolar();
				if (actual != null) {
					System.out.print(actual.dato + " ");
					cola.encolar(actual.izq);
					cola.encolar(actual.der);
					siguienteNivel += 2;
				} else {
					System.out.print("  ");
					cola.encolar(null);
					cola.encolar(null);
					siguienteNivel += 2;
				}
			}
			System.out.println();

			// Imprimir ramas del nivel actual
			for (int i = 0; i < nodosEnNivel; i++) {
				NodoBST actual = cola.verFrente(i);
				if (actual != null) {
					System.out.print((actual.izq != null ? "/" : " ") + " ");
					System.out.print((actual.der != null ? "\\" : " ") + " ");
				} else {
					System.out.print("    ");
				}
			}
			System.out.println();

			nodosEnNivel = siguienteNivel;
			nivel++;
			int altura = altura(raiz);
			if (nivel >= altura) break;
		}
	}

	/**
     * Método principal para demostrar el uso de la clase {@code BST1}.
     *
     * <p>Crea un árbol, le agrega elementos, lo imprime, elimina un elemento y
     * realiza una búsqueda.</p>
     *
     * @param args los argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        BST1 <Integer> t = new BST1 <>();
        Integer[] A = { 10, 15, 7, 8, 6, 2, 11, 12, 3 };
        for (int k = 0; k < A.length; k++)
            t.agregar(A[k]);
		System.out.println("Arbol de Ejemplo");
		t.imprimir();
		System.out.println();
		//item a - implementacion con iterador para usarse en el for-each
		System.out.println("Implementacion con iterador para usarse en el for-each");
		System.out.println("Recorrido con for-each:");
		for (Integer valor : t) {
			System.out.print(valor + " ");
		}
		System.out.println();
		
		//item b - eliminar valor del arbol
		System.out.println("Eliminar valor del arbol");
		t.imprimir();
		t.eliminar(A[A.length -1]);
		t.imprimir();
		System.out.println();
		
		//item c - retorna el sucesor de valor o null si no existe 
		System.out.println("Retorna el sucesor de valor o null si no existe");
		System.out.println("Arbol de Ejemplo");
		t.imprimir();
		System.out.println(t.sucesor(2));
		System.out.println();
		
		//item d - Retorna el predecesor de valor o null si no existe
		System.out.println("Retorna el predecesor de valor o null si no existe");
		System.out.println("Arbol de Ejemplo");
		t.imprimir();
		System.out.println(t.predecesor(3));
		System.out.println();
		
		//item e - Retorna true o false si el árbol es completo o no 
		System.out.println("Retorna true o false si el árbol es completo o no");
		System.out.println("Arbol de Ejemplo");
		t.imprimir();
		System.out.println(t.esCompleto());
		System.out.println();
		
		//item f - Retorna true o false si el árbol es lleno o no
		System.out.println("Retorna true o false si el árbol es lleno o no");
		System.out.println("Arbol de Ejemplo");
		t.imprimir();
		System.out.println(t.esLleno());
		System.out.println();
		
		//item g - imprime el árbol por niveles. Una línea por nivel y separados adecuadamente como el ejemplo de abajo
		System.out.println("Retorna true o false si el árbol es lleno o no");
		System.out.println("Arbol de Ejemplo");
		t.imprimir();
		System.out.println();
		t.imprimirPorNiveles();
		System.out.println();
    }
}
