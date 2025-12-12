package app;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import logica.Busqueda;
// import logica.Ordenamiento;
import modelo.Cancion;

/**
 *
 * @author Alexia BG
 * @author David MD
 * @author Issac SJ
 * @author Raul NR
 */
public class Main {

    public static void main(String[] args) {
        
        // CONFIGURACIÓN DE DATOS
        int CANTIDAD_CANCIONES = 2000000; 
        
        List<Cancion> listaCanciones = new ArrayList<>();
        Map<String, Cancion> mapaCanciones = new HashMap<>();

        System.out.println("   PROYECTO TEMA 6: MOTOR DE BUSQUEDA MUSICAL    ");
        
        // GENERACIÓN DE DATOS (Simulamos la base de datos de Spotify)
        System.out.println("\n> Generando " + CANTIDAD_CANCIONES + " canciones aleatorias...");
        
        long inicioGen = System.currentTimeMillis();
        for (int i = 0; i < CANTIDAD_CANCIONES; i++) {
            // Generamos un ISRC único, ejemplo: "MX-A01-0", "MX-A01-1"...
            String isrc = "MX-A01-" + i;
            String titulo = "Cancion " + i;
            String artista = "Artista Desconocido";
            if (i <= 1000) {
                artista = "Taylor Swift";
            }
            if (i > 1000 && i <= 2000) {
                artista = "Latin Mafia";
            }
            if (i > 2000 && i <= 3000) {
                artista = "Milo J";
            }
            if (i > 3000 && i <= 4000) {
                artista = "Imagine Dragons";
            }
            
            // Creamos el objeto
            Cancion nuevaCancion = new Cancion(isrc, titulo, artista, "Album X", 2024, "Pop");
            
            // Llenamos ambas estructuras
            listaCanciones.add(nuevaCancion); // Para búsqueda Secuencial y Binaria
            mapaCanciones.put(isrc, nuevaCancion); // Para búsqueda Hash
        }
        long finGen = System.currentTimeMillis();
        System.out.println(" -> Datos generados en: " + (finGen - inicioGen) + " ms.");

        // Definimos qué vamos a buscar (El PEOR CASO: El último elemento)
        String dato = JOptionPane.showInputDialog("Introduzca el ultimo numero del isrc");
        String isrcBuscado = "MX-A01-" + dato;
        System.out.println(" -> Objetivo a buscar: " + isrcBuscado);

        
        // PREPARACIÓN PARA BÚSQUEDA BINARIA (ORDENAMIENTO)
        // La búsqueda binaria SOLO funciona si la lista está ordenada.
        System.out.println("\n> Ordenando la lista para la Busqueda Binaria...");
        // System.out.println("    (Usando QuickSort - Esto puede tardar un poco...)");
        
        long inicioSort = System.currentTimeMillis();
        
        // LLAMADA AL ALGORITMO DE ORDENAMIENTO
        // Ordenamiento.quickSort(listaCanciones);
        java.util.Collections.sort(listaCanciones);
        
        long finSort = System.currentTimeMillis();
        System.out.println(" -> Lista ordenada en: " + (finSort - inicioSort) + " ms.");
        // System.out.println(" -> Comparaciones realizadas por QuickSort: " + Ordenamiento.comparaciones);
        
        // BÚSQUEDAS (Medición en Nanosegundos)
        System.out.println("\n           COMPARACION DE VELOCIDAD          ");

        // Búsqueda Secuencial (O(n))
        long t1 = System.nanoTime();
        Cancion resSec = Busqueda.secuencial(listaCanciones, isrcBuscado);
        long t2 = System.nanoTime();
        long tiempoSecuencial = t2 - t1;
        
        System.out.println("1. Busqueda Secuencial: " + tiempoSecuencial + " ns (" + resSec.getTitulo() + ")");

        // Búsqueda Binaria (O(log n))
        long t3 = System.nanoTime();
        Cancion resBin = Busqueda.binaria(listaCanciones, isrcBuscado);
        long t4 = System.nanoTime();
        long tiempoBinaria = t4 - t3;
        
        System.out.println("2. Busqueda Binaria:    " + tiempoBinaria + " ns (" + resBin.getTitulo() + ")");

        // Búsqueda Hash (O(1))
        long t5 = System.nanoTime();
        Cancion resHash = Busqueda.hash(mapaCanciones, isrcBuscado);
        long t6 = System.nanoTime();
        long tiempoHash = t6 - t5;
        
        System.out.println("3. Busqueda Hash:       " + tiempoHash + " ns (" + resHash.getTitulo() + ")");
        
        if (tiempoHash < tiempoSecuencial) {
            long vecesMasRapido = tiempoSecuencial / tiempoHash;
            System.out.println("Hash fue " + vecesMasRapido + " veces mas rapido que la Secuencial.");
        }
        
        System.out.println("\n           CANCION ENCONTRADA          ");
        System.out.println(resHash.toString());
        
        System.out.println("\nComplejidad Algoritmica demostrada:");
        System.out.println("- Secuencial: O(n) - Tuvo que recorrer todo.");
        System.out.println("- Binaria: O(log n) - Dividio la lista en mitades.");
        System.out.println("- Hash: O(1) - Acceso directo instantáneo.");
    }
}
