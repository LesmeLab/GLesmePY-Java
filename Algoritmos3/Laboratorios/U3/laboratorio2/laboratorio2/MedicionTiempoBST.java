/*
 * ALGORITMOS Y ESTRUCTURAS DE DATOS III - Seccion TQ/TR - Prof. Cristian Cappo / Prof. Luis Moré
 * Ing. Informatica
 * 2025-2do Periodo
 *
 * Muestra un procedimiento sencillo para medir tiempo
 * de ejecucion de un codigo.
 *
 */
 
record ResumenTiempos (long tmp_add, long tmp_search) {};
  
	
public class MedicionTiempoBST
{
   /*
    *  Cantidad fija de elementos.
    */
	
  

   public static int NELEM = 100000;
	
   public static void main (String argv[])
   {
       
       System.out.printf ("\n\t\tMuestra empiricamente el desempeño de un BST de %,d elementos",NELEM);
       System.out.print ("\n\t\t----------------------------------------------------------------");
       System.out.print ("\n\t\t Agregar(en ms.)	\t Buscar(en ms.)");
	   System.out.print ("\n\t\t -------------------------- \t --------------------------");
	   System.out.print ("\n\t\t Total\t\t Por elem.     \t Total\t\t Por elem.");
	   System.out.print ("\n\t\t -------------------------- \t --------------------------\n");

      
       int A []    =  generar_numeros();
       int nelem   =  A.length;

       ResumenTiempos tiempo = correr_prueba(A); /* Usamos el registro ResumenTiempos para almacenar dos valores */
       
       	   
       System.out.printf ("\t\t %-10d  \t%10.7f",tiempo.tmp_add(), tiempo.tmp_add() / (double) nelem);
       System.out.printf ("\t %-10d  \t%10.7f\n",tiempo.tmp_search(), tiempo.tmp_search() / (double) nelem ) ;
	   
	   
   }

   /*
      Retorna un arreglo estatico de NELEM elementos
   */
   public static int [] generar_numeros()
   {
      int A [] = new int[NELEM];

      for (int k=0; k < A.length; k++)
          A [k] = (int) (Math.random()*(NELEM+1)); /* Genera valores entre 0 y NELEM */

      return A;
   }

   /*
     Genera un BST, inserta los elementos de arreglo
     y retorna un Objeto que contiene el tiempo total de agregar y buscar.

   */
   public static ResumenTiempos correr_prueba (int [] A)
   {
      int n              = A.length;
      long ti, ta, tb;

      BST  t = new BST();
          
      ti = System.currentTimeMillis();
      
	  for (int k=0; k < n; k++) {
          t.agregar (A[k]);
      }
	  
	  ta = System.currentTimeMillis() - ti;
	  
      ti = System.currentTimeMillis();
      for (int k=0; k < n; k++)
          t.buscar (A [k]);
      tb = System.currentTimeMillis() - ti;
	  
	  ResumenTiempos res = new ResumenTiempos(ta,tb);
      
      return res;
   } 
}
