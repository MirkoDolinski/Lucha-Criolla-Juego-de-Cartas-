public class Carta {
    private String nombre;
    private int nivelPoder;
    private String tipo;  
    private String descripcion;  
    private String efecto;  

    public Carta(String nombre, int nivelPoder, String tipo, String descripcion, String efecto) {
        this.nombre = nombre;
        this.nivelPoder = nivelPoder;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.efecto = efecto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivelPoder() {
        return nivelPoder;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEfecto() {
        return efecto;
    }

    @Override
    public String toString() {
        return nombre + " (Poder: " + nivelPoder + ", Tipo: " + tipo + ")";
    }
}
