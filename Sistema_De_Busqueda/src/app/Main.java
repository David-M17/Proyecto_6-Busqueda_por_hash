package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import logica.Busqueda;
import logica.Ordenamiento;
import modelo.Cancion;

/**
 *
 * @author Alexia BG
 * @author David MD
 * @author Issac SJ
 * @author Raul NR
 */
public class Main {

    // BANCO DE DATOS MEZCLADO (INGLES / ESPAÑOL - SIN ACENTOS)
    private static final String[] SUJETOS = {
        "El Amor", "The Night", "El Sol", "My Heart", "El Silencio", 
        "The Life", "Un Sueno", "The Rhythm", "Mi Corazon", "The Party",
        "Your Eyes", "La Lluvia", "Sweet Home", "El Destino", "Dark Side"
    };
    
    private static final String[] VERBOS = {
        "Perdido", "Found", "Stolen", "Eterno", "Forbidden", 
        "Blue", "Far Away", "Ardiente", "Wild", "Magico",
        "Broken", "Olvidado", "Forever", "Loco", "In the Sky"
    };
    
    private static final String[] ARTISTAS = {
        "Bad Bunny", "Taylor Swift", "Luis Miguel", "Queen", "Shakira", 
        "The Beatles", "Harry Styles", "Karol G", "Metallica", "Bruno Mars",
        "Adele", "Daddy Yankee", "Coldplay", "Rihanna", "Peso Pluma"
    };
    
    private static final String[] GENEROS = {"Pop", "Rock", "Reggaeton", "Jazz", "Electronica", "Salsa", "Hip Hop", "Indie"};

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // CANTIDAD DE DATOS
        int CANTIDAD_CANCIONES = 2000000; 
        
        List<Cancion> listaCanciones = new ArrayList<>();
        Map<String, Cancion> mapaCanciones = new HashMap<>();

        System.out.println("==================================================");
        System.out.println("   PROYECTO TEMA 6: MOTOR DE BUSQUEDA MUSICAL");
        System.out.println("==================================================");
        
        // 1. GENERACIÓN DE DATOS
        System.out.println("\n[1] Cargando " + CANTIDAD_CANCIONES + " canciones en memoria...");
        
        long inicioGen = System.currentTimeMillis();
        
        for (int i = 0; i < CANTIDAD_CANCIONES; i++) {
            // Datos aleatorios
            String sujeto = SUJETOS[random.nextInt(SUJETOS.length)];
            String verbo = VERBOS[random.nextInt(VERBOS.length)];
            String artista = ARTISTAS[random.nextInt(ARTISTAS.length)];
            String genero = GENEROS[random.nextInt(GENEROS.length)];
            int anio = 1980 + random.nextInt(46); 

            String titulo = sujeto + " " + verbo; 
            
            // LA CLAVE: El ISRC se basa en el contador 'i'
            String isrc = String.format("MX-BK1-%07d", i); 
            
            Cancion nuevaCancion = new Cancion(isrc, titulo, artista, "Album " + anio, anio, genero);
            
            listaCanciones.add(nuevaCancion);
            mapaCanciones.put(isrc, nuevaCancion);
        }
        
        // Desordenamos la lista
        Collections.shuffle(listaCanciones);

        long finGen = System.currentTimeMillis();
        System.out.println(" -> Datos generados en: " + (finGen - inicioGen) + " ms.");

        // 2. ORDENAMIENTO (Necesario para Binaria)
        System.out.println("\n[2] Ordenando base de datos (QuickSort)...");
        System.out.println("    (Esto puede tardar unos segundos...)");
        
        long inicioSort = System.currentTimeMillis();
        try {
            Ordenamiento.quickSort(listaCanciones); 
        } catch (StackOverflowError e) {
            System.out.println("ERROR: Memoria insuficiente en la pila (-Xss).");
            return;
        }
        long finSort = System.currentTimeMillis();
        System.out.println(" -> Lista ordenada en: " + (finSort - inicioSort) + " ms.");

        // 3. MENÚ INTERACTIVO SIMPLIFICADO
        String respuesta = "s";
        
        while (respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("si")) {
            
            System.out.println("\n==================================================");
            System.out.print(" Ingresa el ID de la cancion (Numero entre 0 y " + (CANTIDAD_CANCIONES-1) + "): ");
            
            // 1. EL USUARIO SOLO METE NÚMEROS
            int idUsuario;
            try {
                idUsuario = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("¡Error! Debes ingresar un numero entero.");
                scanner.nextLine(); // Limpiar buffer
                continue;
            }

            // 2. EL PROGRAMA CONVIERTE EL NÚMERO A ISRC INTERNAMENTE
            String isrcBuscado = String.format("MX-BK1-%07d", idUsuario);
            System.out.println(" -> Buscando clave interna: " + isrcBuscado);
            
            System.out.println("\n--- COMPARATIVA DE TIEMPOS (Nanosegundos) ---");

            // BÚSQUEDA SECUENCIAL
            long t1 = System.nanoTime();
            Busqueda.secuencial(listaCanciones, isrcBuscado);
            long t2 = System.nanoTime();
            System.out.println("1. Secuencial: " + (t2 - t1) + " ns");

            // BÚSQUEDA BINARIA
            long t3 = System.nanoTime();
            Busqueda.binaria(listaCanciones, isrcBuscado);
            long t4 = System.nanoTime();
            System.out.println("2. Binaria:    " + (t4 - t3) + " ns");

            // BÚSQUEDA HASH
            long t5 = System.nanoTime();
            Cancion resultado = Busqueda.hash(mapaCanciones, isrcBuscado);
            long t6 = System.nanoTime();
            System.out.println("3. Hash Map:   " + (t6 - t5) + " ns");

            // 3. MOSTRAR DATOS DE LA CANCIÓN SI SE ENCONTRÓ
            if (resultado != null) {
                System.out.println("\n---------------------------------");
                System.out.println(" ♫  CANCION ENCONTRADA  ♫");
                System.out.println("---------------------------------");
                System.out.println(" Titulo:   " + resultado.getTitulo());
                System.out.println(" Artista:  " + resultado.getArtista());
                System.out.println(" Album:    " + resultado.getAlbum());
                System.out.println(" Genero:   " + resultado.getGenero());
                System.out.println(" Año:      " + resultado.getAnioLanzamiento());
                System.out.println(" ISRC:     " + resultado.getIsrc());
                System.out.println("---------------------------------");
            } else {
                System.out.println("\n[!] La cancion con ID " + idUsuario + " no existe.");
            }
            
            // PREGUNTA
            System.out.print("\n¿Buscar otra? (si/no): ");
            respuesta = scanner.next();
        }
        
        System.out.println("Programa finalizado.");
    }
}

