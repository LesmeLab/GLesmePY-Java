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

import java.util.Scanner;

/*
  A – back
  S – forward
  U – visitar nueva URL
  L – listar historial de visitas (apunta al actual)
  Q – salir
 */
public class Minibrow {
    private Pila<String> pilaAtras;        // historial para comando “A”
    private Pila<String> pilaAdelante;     // historial para comando “S”
    private Lista<String> historialVisitas;// todas las URL que fueron páginas actuales
    private String urlActual;              // página actual

    /**
      Recibe la URL inicial como único parámetro.
     */
    public static void main(String[] args) {
        Minibrow navegador = new Minibrow();
        navegador.iniciar(args);
    }

    /*
      Inicializa estructuras y lee comandos.
     */
    public void iniciar(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java Minibrow <URL_inicial>");
            return;
        }
        pilaAtras        = new Pila<>();
        pilaAdelante     = new Pila<>();
        historialVisitas = new Lista<>();
        urlActual        = args[0];
        historialVisitas.agregar(urlActual);

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.print("Ingrese comando (A, S, U, L, Q) (URL actual = "
                             + urlActual + "): ");
            String linea = scanner.nextLine().trim();
            if (linea.isEmpty()) 
                continue;

            char comando = Character.toUpperCase(linea.charAt(0));
            try {
                switch (comando) {
                    case 'U':
                        manejarVisita(linea);
                        System.out.println(urlActual);
                        break;
                    case 'A':
                        retroceder();
                        System.out.println(urlActual);
                        break;
                    case 'S':
                        avanzar();
                        System.out.println(urlActual);
                        break;
                    case 'L':
                        listarHistorial();
                        break;
                    case 'Q':
                        continuar = false;
                        break;
                    default:
                        throw new ComandoInvalidoException("Comando inválido");
                }
            } catch (ComandoInvalidoException ex) {
                System.out.println(ex.getMessage());
            }
        }
        scanner.close();
    }

    /*
      Comando U visitar <URL>.
      Excepción si no incluye URL
     */
    private void manejarVisita(String linea) throws ComandoInvalidoException {
        String[] partes = linea.split("\\s+", 2);
        if (partes.length < 2 || partes[1].isEmpty()) {
            throw new ComandoInvalidoException("URL no especificada");
        }
        pilaAtras.push(urlActual);
        urlActual = partes[1];
        historialVisitas.agregar(urlActual);
        pilaAdelante = new Pila<>();
    }

    /* 
      Comando A Anterior. Si no hay annterior: IGNORADO.
     */
    private void retroceder() {
        if (pilaAtras.isEmpty()) {
            System.out.println("IGNORADO");
        } else {
            pilaAdelante.push(urlActual);
            urlActual = pilaAtras.pop();
            historialVisitas.agregar(urlActual);
        }
    }

    /*
      Comando S Siguiente. Si no hay adelante: IGNORADO.
     */
    private void avanzar() {
        if (pilaAdelante.isEmpty()) {
            System.out.println("IGNORADO");
        } else {
            pilaAtras.push(urlActual);
            urlActual = pilaAdelante.pop();
            historialVisitas.agregar(urlActual);
        }
    }

    private void listarHistorial() {
        // 1. Mostrar siguientes
        Lista<String> siguientes = pilaAdelante.obtenerElementos();
        for (int i = siguientes.tamaño() - 1; i >= 0; i--) {
            System.out.println(siguientes.obtener(i));
        }

        // 2. Mostrar actual
        System.out.println(urlActual + " <--");

        // 3. Mostrar anteriores
        Lista<String> anteriores = pilaAtras.obtenerElementos();
        for (int i = anteriores.tamaño() - 1; i >= 0; i--) {
            System.out.println(anteriores.obtener(i));
        }
    }


    /*
      Excepción para atrapar los comandos inválidos o mal formados.
     */
    private static class ComandoInvalidoException extends Exception {
        public ComandoInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
}