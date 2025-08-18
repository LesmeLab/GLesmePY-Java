/*
  g60
  Integrantes:
   Alejandro Javier Armoa Bordón      CIC: 6547586   Sección: TQ
   Gustavo Emanuel Lesme Ortega       CIC: 5249373   Sección: TQ
  Tarea 1 – Ejercicio 1

  Declaración de honor 
    • Yo (o nosotros) Fulano (y Sultano):
    • No he/hemos discutido el código fuente de mi/nuestra tarea con ningún otro grupo, solo con el Profesor o el AER.
    • No he/hemos usado código obtenido de otro estudiante o de cualquier otra fuente no autorizada, modificada o no modificada.
    • Cualquier código o documentación utilizada en mi/nuestro programa obtenido de fuentes, tales como libros o notas de curso, han sido claramente indicada en mi/nuestra tarea.
 */

package g60.minibrow;

    /*
     TAD Pila genérica implementada sobre arreglo dinámico.
     Soporta push, pop, isEmpty y size.
    */
public class Pila<T> {
    private T[] elementos;
    private int tope;
    private static final int CAPACIDAD_INICIAL = 10;

    @SuppressWarnings("unchecked")
    public Pila() {
        elementos = (T[]) new Object[CAPACIDAD_INICIAL];
        tope = 0;
    }

    /*
      Agrega un valor al tope de la pila.
     */
    public void push(T valor) {
        asegurarCapacidad();
        elementos[tope++] = valor;
    }

    /*
      Saca y retorna el elemento del tope.
      Excepción si la pila está vacía.
     */
    public T pop() {
        if (isEmpty())
            throw new IllegalStateException("Pila vacía");
        T valor = elementos[--tope];
        elementos[tope] = null;
        return valor;
    }


    /*
      return true si no tiene elementos.
     */
    public boolean isEmpty() {
        return tope == 0;
    }

    /*
      return cantidad de elementos en la pila.
     */
    public int size() {
        return tope;
    }

    @SuppressWarnings("unchecked")
    private void asegurarCapacidad() {
        if (tope == elementos.length) {
            T[] copia = (T[]) new Object[elementos.length * 2];
            for (int i = 0; i < elementos.length; i++) {
                copia[i] = elementos[i];
            }
            elementos = copia;
        }
    }

    /*
      Devuelve una lista con los elementos en orden en que se agregó, sin modificar la pila original.
     */
    public Lista<T> obtenerElementos() {
        Lista<T> copia = new Lista<>();
        for (int i = 0; i < tope; i++) {
            copia.agregar(elementos[i]);
        }
        return copia;
    }
}

