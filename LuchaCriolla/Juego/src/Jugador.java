import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Carta> cartas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cartas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public Carta jugarCarta(int indice) {
        return cartas.remove(indice);
    }

    public boolean tieneCartas() {
        return !cartas.isEmpty();
    }
}
