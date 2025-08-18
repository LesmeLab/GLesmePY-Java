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
  TAD Lista genérica implementada con arreglo dinámico.
 */
public class Lista<T> {
    private T[] datos;
    private int tamaño;
    private static final int CAPACIDAD_INICIAL = 10;

    @SuppressWarnings("unchecked")
    public Lista() {
        datos = (T[]) new Object[CAPACIDAD_INICIAL];
        tamaño = 0;
    }

    /*
     Agrega un elemento al final de la lista.
     */
    public void agregar(T valor) {
        asegurarCapacidad();
        datos[tamaño++] = valor;
    }

    /*
      Retorna el elemento en la posición dada.
      Excepción si el índice es inválido.
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño)
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        return datos[indice];
    }

    /*
      return cantidad de elementos en la lista.
     */
    public int tamaño() {
        return tamaño;
    }

    @SuppressWarnings("unchecked")
    private void asegurarCapacidad() {
        if (tamaño == datos.length) {
            T[] copia = (T[]) new Object[datos.length * 2];
            for (int i = 0; i < datos.length; i++) {
                copia[i] = datos[i];
            }
            datos = copia;
        }
    }
}