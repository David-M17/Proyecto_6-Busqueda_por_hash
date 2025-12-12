package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import logica.Busqueda;
import modelo.Cancion;

/**
 *
 * @author Alexia BG
 * @author David MD
 * @author Issac SJ
 * @author Raul NR
 */
public class Main {

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
        
        int CANTIDAD_CANCIONES = 2000000; 
        
        // Usamos ArrayList para Secuencial/Binaria y HashMap para Hash
        List<Cancion> listaCanciones = new ArrayList<>();
        Map<String, Cancion> mapaCanciones = new HashMap<>();

        System.out.println("==================================================");
        System.out.println("   PROYECTO TEMA 6: MOTOR DE BUSQUEDA MUSICAL");
        System.out.println("==================================================");
        
        // 1. GENERACIÓN DE DATOS 
        System.out.println("\n[1] Generando " + CANTIDAD_CANCIONES + " canciones...");
        
        long inicioGen = System.currentTimeMillis();
        
        for (int i = 0; i < CANTIDAD_CANCIONES; i++) {
            // Datos aleatorios solo para "decoración"
            String sujeto = SUJETOS[random.nextInt(SUJETOS.length)];
            String verbo = VERBOS[random.nextInt(VERBOS.length)];
            String artista = ARTISTAS[random.nextInt(ARTISTAS.length)];
            String genero = GENEROS[random.nextInt(GENEROS.length)];
            int anio = 1980 + random.nextInt(46); 

            String titulo = sujeto + " " + verbo; 
            
            // LA CLAVE IMPORTANTE:
            // Generamos los ISRC en orden secuencial (0, 1, 2...)
            // Al agregarlos así a la lista, la lista queda AUTOMÁTICAMENTE ORDENADA.
            String isrc = String.format("MX-BK1-%07d", i); 
            
            Cancion nuevaCancion = new Cancion(isrc, titulo, artista, "Album " + anio, anio, genero);
            
            listaCanciones.add(nuevaCancion);
            mapaCanciones.put(isrc, nuevaCancion);
        }
        
        long finGen = System.currentTimeMillis();
        System.out.println(" -> Datos generados y listos en: " + (finGen - inicioGen) + " ms.");
        System.out.println(" -> (La lista ya esta ordenada por ISRC, omitimos QuickSort).");

        // 2. MENÚ INTERACTIVO
        String respuesta = "s";
        
        while (respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("si")) {
            
            System.out.println("\n==================================================");
            System.out.print(" Ingresa el ID de la cancion (Numero entre 0 y " + (CANTIDAD_CANCIONES-1) + "): ");
            
            int idUsuario;
            try {
                idUsuario = scanner.nextInt();
                // Validación simple para que no pongan números negativos o gigantes
                if (idUsuario < 0 || idUsuario >= CANTIDAD_CANCIONES) {
                    System.out.println("¡Numero fuera de rango!");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("¡Error! Debes ingresar un numero entero.");
                scanner.nextLine(); // Limpiar el error del scanner
                continue;
            }

            // Convertimos el número simple al ISRC complejo
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

            // MOSTRAR DATOS
            if (resultado != null) {
                System.out.println("\n---------------------------------");
                System.out.println(" ♫  DETALLES DE LA CANCION  ♫");
                System.out.println("---------------------------------");
                System.out.println(" Titulo:   " + resultado.getTitulo());
                System.out.println(" Artista:  " + resultado.getArtista());
                System.out.println(" Album:    " + resultado.getAlbum());
                System.out.println(" Genero:   " + resultado.getGenero());
                System.out.println(" Año:      " + resultado.getAnioLanzamiento());
                System.out.println(" ISRC:     " + resultado.getIsrc());
                System.out.println("---------------------------------");
            } else {
                System.out.println("\n[!] Error extraño: No se encontró la canción.");
            }
            
            System.out.print("\n¿Buscar otra? (si/no): ");
            respuesta = scanner.next();
            respuesta = respuesta.toLowerCase();
        }
        
        System.out.println("Programa finalizado.");
        scanner.close();
    }
}

