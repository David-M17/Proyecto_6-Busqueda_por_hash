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
    public Cancion() {
    }
    
    public Cancion(String isrc, String titulo, String artista, String album, int anioLanzamiento, String genero) {
        this.isrc = isrc;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.anioLanzamiento = anioLanzamiento;
        this.genero = genero;
    }

    // Getters & Setters

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
        return "Cancion{" + "ISRC='" + isrc + '\'' + ", Titulo='" + titulo + '\'' + ", Artista:'" + artista + '}';
    }
}
