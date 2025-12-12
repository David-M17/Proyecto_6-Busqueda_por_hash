package logica;
import modelo.Cancion;
import java.util.List;

/**
 *
 * @author Alexia BG
 * @author David MD
 * @author Issac SJ
 * @author Raul NR
 */

/**
 * Clase dedicada exclusivamente a los algoritmos de ordenamiento.
 */
public class Ordenamiento {

    // Variable estática para contar las comparaciones (útil para el reporte)
    public static long comparaciones = 0;

    /**
     * Método público para llamar al QuickSort.
     * @param lista La lista de canciones a ordenar por ISRC.
     */
    public static void quickSort(List<Cancion> lista) {
        comparaciones = 0; // Reiniciamos el contador
        if (lista != null && !lista.isEmpty()) {
            quickSortRecursivo(lista, 0, lista.size() - 1);
        }
        System.out.println("QuickSort finalizado. Comparaciones realizadas: " + comparaciones);
    }

    // Método recursivo privado (la lógica interna)
    private static void quickSortRecursivo(List<Cancion> lista, int l, int h) {
        if (l < h) {
            int pivoteIndex = separar(lista, l, h);
            
            // Ordenamos sublista izquierda y derecha
            quickSortRecursivo(lista, l, pivoteIndex - 1);
            quickSortRecursivo(lista, pivoteIndex + 1, h);
        }
    }

    // Método para dividir la lista (Partition)
    private static int separar(List<Cancion> lista, int l, int h) {
        Cancion pivote = lista.get(h); // Tomamos el último elemento como pivote
        int i = l - 1;

        for (int j = l; j < h; j++) {
            comparaciones++; // Contamos la comparación
            
            // Usamos compareTo porque estamos comparando Objetos (Strings del ISRC), no números
            // Si lista[j] <= pivote
            if (lista.get(j).compareTo(pivote) <= 0) {
                i++;
                intercambiar(lista, i, j);
            }
        }
        intercambiar(lista, i + 1, h);
        return i + 1;
    }

    // Método auxiliar para intercambiar elementos en la lista
    private static void intercambiar(List<Cancion> lista, int i, int j) {
        Cancion temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }
}