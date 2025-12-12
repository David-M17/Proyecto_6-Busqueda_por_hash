package modelo;

/**
 *
 * @author Alexia BG
 * @author David MD
 * @author Issac SJ
 * @author Raul NR
 */
import java.util.Objects;

public class Cancion implements Comparable<Cancion> {

    // Atributos definidos en el planteamiento de Alexia
    private String isrc;            // Clave única (Key)
    private String titulo;
    private String artista;
    private String album;
    private int anioLanzamiento;
    private String genero;

    // Constructor
    public Cancion(String isrc, String titulo, String artista, String album, int anioLanzamiento, String genero) {
        this.isrc = isrc;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.anioLanzamiento = anioLanzamiento;
        this.genero = genero;
    }

    // Getters
    public String getIsrc() { 
        return isrc; 
    }
    public String getTitulo() { 
        return titulo; 
    }
    public String getArtista() { 
        return artista; 
    }

    // Implementación de Comparable para poder ordenar la lista
    @Override
    public int compareTo(Cancion otra) {
        return this.isrc.compareTo(otra.isrc);
    }

    // MÉTODOS PARA HASHMAP
    // Aseguran que el HashMap sepa cuándo dos canciones son iguales y maneje colisiones.
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cancion cancion = (Cancion) o;
        return Objects.equals(isrc, cancion.isrc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isrc);
    }

    @Override
    public String toString() {
        return "Cancion{" + "ISRC='" + isrc + '\'' + ", Titulo='" + titulo + '\'' + '}';
    }
}
