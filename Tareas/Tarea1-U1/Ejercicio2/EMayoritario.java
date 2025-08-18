/*
  Grupo : g60
  Integrantes:
   Alejandro Javier Armoa Bordón      CIC: 6547586   Sección: TQ
   Gustavo Emanuel Lesme Ortega       CIC: 5249373   Sección: TQ
  Tarea 1 - U1(Java) – Ejercicio 2

  Declaración de HONOR: 
    • Yo Gustavo Emanuel Lesme Ortega y Alejandro Javier Armoa Bordón
		• No he/hemos discutido el código fuente de mi/nuestra tarea con ningún otro grupo, solo con el Profesor o el AER.
		• No he/hemos usado código obtenido de otro estudiante o de cualquier otra fuente no autorizada, modificada o no modificada.
		• Cualquier código o documentación utilizada en mi/nuestro programa obtenido de fuentes, tales como libros o notas de curso, han sido claramente indicada en mi/nuestra tarea.
 */

import java.util.Random;

public class EMayoritario {
	// esta es la version del metodo encontrarMayoritario de O(n^2), que es notablemente mas lento,
	//se adjunta la tabla de esa version vs la tabla de la version mejorada por el algoritmo de Boyer Moore al pie de la clase
	/*public static <E> E encontrarMayoritario ( E [] T ){
		int repetido;
		E elementoMayoritario = null;
		for ( int x = 0 ; x< T.length ; x++){
			repetido = 0;
			for ( int y = x+1 ; y<T.length ; y++ ){
				if ( T[x] != null && T[x].equals(T[y]) ){
					repetido++;
					elementoMayoritario = T[x];
				}
				if ( repetido > (T.length/2) ){
					return elementoMayoritario;
				}
			}
		}
		return null;
	}*/ 
	
	
	
	
	//Aqui se usa el Algoritmo Boyer-Moore Majority Vote
	//Idea obteida del libro Data Structures and Algorithms in Java,Goodrich,Tamassia, pag.756
	public static <E> E encontrarMayoritario ( E [] T ){ //E es el tipo de dato generico y T el arreglo
		if ( T == null || T.length == 0 ) //Verifica que el Arreglo T no este inicializado o vacio
			return null;
		//Un elemento es mayoritario si aparece mas de n/2 veces
		E elementoMayoritario = null;
		int contador = 0;
		//Se busca un posible candidato a elementoMayoritario
		for ( E elemento : T ){ //recorrido for-each
			if ( contador == 0 ){
				elementoMayoritario = elemento;
				contador = 1;
			} else if ( ( elementoMayoritario != null && elemento != null )&& elementoMayoritario.equals(elemento) ) {
				contador++;
			}else {
				contador--;
			}
		} //Al final quedara un elementoMayoritario posible, luego se procede con la
		
		//Verificacion, de si aparece mas de n/2
		contador = 0;
		for ( E elemento : T ){
			if ( elementoMayoritario != null && elementoMayoritario.equals(elemento) ){
				contador++;
			}
		}
		if ( contador > T.length/2 )
			return elementoMayoritario; //si hay elementoMayoritario
		return null; // no hay elementoMayoritario
	}
	
	
	public static void main (String [] args ){
		Random random = new Random();
		System.out.printf("%7s %20s%n----------------------------------------%n%n", "N","Tiempo (ms)");
		for (int n = 100000 ; n<=1000000 ; n=n+100000){
			Integer[] A = new Integer[n];
			//Generacion de Numeros Aleatorios
			for (int x = 0 ; x<n ; x++){
				A[x] = 100000 + random.nextInt(1000000 - 100000 +1);
			}
			
			try {
				double tiempoInicio = System.nanoTime();
				EMayoritario.encontrarMayoritario(A);
				double tiempoFinal = System.nanoTime();
			
				double duracionMs = (tiempoFinal - tiempoInicio)/1000000; //(Diferencia de nanosegundos) / 1000000 para conversion a ms
				System.out.printf("%10d %,15f %n", n, duracionMs);
			} catch ( Exception e ){
				System.err.println("Error al ejecutar el algoritmo: " + e.getMessage());
			}
		}
	}
	
	
}
//Versus de Algoritmos
/*Algoritmo O(n^2)							Algoritmo Boyer–Moore Majority Vote O(n)
      N          Tiempo (ms)			      N          Tiempo (ms)
--------------------------------------     --------------------------------------

    100000    2.913,606700		 			100000        4,516300
    200000   11.355,702200					200000        4,754200
    300000   27.152,806400					300000        0,948000
    400000   56.460,849800					400000        1,098300
    500000  108.924,699700					500000        1,346300
    600000  163.098,729200					600000        1,528600
	700000  243.807,403200					700000        2,508900
	800000  374.944,642300					800000        2,770100
	900000  473.645,356200					900000        2,360300
   1000000  685.095,210500				   1000000        2,699600
En conclusion O(n^2) se vuelve impractico velozmente.
Boyer-Moore mantiene tiempos bajos incluso con N de gran tamaño
*/