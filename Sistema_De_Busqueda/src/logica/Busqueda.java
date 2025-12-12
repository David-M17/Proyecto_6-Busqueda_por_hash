package logica;

import modelo.Cancion;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alexia BG
 * @author David MD
 * @author Issac SJ
 * @author Raul NR
 */
public class Busqueda {

    // BÚSQUEDA SECUENCIAL (O(n))
    public static Cancion secuencial(List<Cancion> lista, String isrcBuscado) {
        for (Cancion cancion : lista) {
            if (cancion.getIsrc().equals(isrcBuscado)) {
                return cancion; // Encontrado
            }
        }
        return null; // No encontrado
    }

    // BÚSQUEDA BINARIA (O(log n))
    // ¡IMPORTANTE! La lista debe estar ordenada previamente (usando Ordenamiento.quickSort)
    public static Cancion binaria(List<Cancion> lista, String isrcBuscado) {
        int inicio = 0;
        int fin = lista.size() - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            Cancion cMedio = lista.get(medio);
            
            // Comparamos el ISRC del medio con el buscado
            int resultado = cMedio.getIsrc().compareTo(isrcBuscado);

            if (resultado == 0) {
                return cMedio; // Encontrado
            }
            
            if (resultado < 0) {
                inicio = medio + 1; // Buscar en la mitad derecha
            } else {
                fin = medio - 1; // Buscar en la mitad izquierda
            }
        }
        return null;
    }

    // BÚSQUEDA HASH (O(1) promedio)
    public static Cancion hash(Map<String, Cancion> mapa, String isrcBuscado) {
        // La estructura HashMap hace todo el trabajo difícil internamente
        return mapa.get(isrcBuscado);
    }
}
