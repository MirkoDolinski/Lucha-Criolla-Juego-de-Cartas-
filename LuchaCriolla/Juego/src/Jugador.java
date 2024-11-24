import java.util.ArrayList;
import java.util.Collections;
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

    public List<Carta> getMejoresCartas() {
        List<Carta> cartasOrdenadas = new ArrayList<>(cartas);
        Collections.sort(cartasOrdenadas, (a, b) -> b.getNivelPoder() - a.getNivelPoder());
        return cartasOrdenadas.subList(0, Math.min(3, cartasOrdenadas.size()));
    }
}
