public class Carta {
    private String nombre;
    private int nivelPoder;

    public Carta(String nombre, int nivelPoder) {
        this.nombre = nombre;
        this.nivelPoder = nivelPoder;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivelPoder() {
        return nivelPoder;
    }

    @Override
    public String toString() {
        return nombre + " (Poder: " + nivelPoder + ")";
    }
}
