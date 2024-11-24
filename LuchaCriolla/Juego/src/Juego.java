import java.util.Collections;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Juego {
    private final Jugador jugador;
    private final Jugador maquina;
    private final VistaGrafica vista;
    private int rondasJugador = 0;
    private int rondasMaquina = 0;
    private int rondasTotales = 5;

    public Juego(VistaGrafica vista) {
        this.vista = vista;
        jugador = new Jugador("Jugador");
        maquina = new Jugador("Máquina");
    }

    public void iniciar() {
        vista.mostrarMensaje("Cargando cartas...");
        List<Carta> todasLasCartas = ConexionBD.obtenerCartas();
        Collections.shuffle(todasLasCartas);  

        for (int i = 0; i < rondasTotales; i++) {
            jugador.agregarCarta(todasLasCartas.get(i));
            maquina.agregarCarta(todasLasCartas.get(i + rondasTotales));
        }

        vista.actualizarCartas(jugador.getCartas(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton fuente = (JButton) e.getSource();
                String textoCarta = fuente.getText();
                Carta cartaSeleccionada = buscarCartaPorTexto(textoCarta);
                jugarRonda(cartaSeleccionada);
            }
        });

        vista.mostrarMensaje("¡Es tu turno!");
        vista.mostrarVentana();
    }

    private void jugarRonda(Carta cartaJugador) {
        if (cartaJugador != null) {
            Carta cartaMaquina = maquina.jugarCarta(0);

            vista.mostrarMensaje("Tú jugaste: " + cartaJugador);
            vista.mostrarMensaje("La máquina jugó: " + cartaMaquina);

            if (cartaJugador.getNivelPoder() > cartaMaquina.getNivelPoder()) {
                vista.mostrarMensaje("¡Ganaste esta ronda!");
                rondasJugador++;
            } else if (cartaJugador.getNivelPoder() < cartaMaquina.getNivelPoder()) {
                vista.mostrarMensaje("¡Perdiste esta ronda!");
                rondasMaquina++;
            } else {
                vista.mostrarMensaje("¡Empate en esta ronda!");
            }

            jugador.getCartas().remove(cartaJugador);
            maquina.getCartas().remove(cartaMaquina);

            vista.limpiarBotones();

            vista.actualizarPuntaje(rondasJugador, rondasMaquina);

            if (jugador.getCartas().isEmpty() && maquina.getCartas().isEmpty()) {
                if (rondasJugador > rondasMaquina) {
                    vista.mostrarGanador("Jugador");
                } else if (rondasJugador < rondasMaquina) {
                    vista.mostrarGanador("Máquina");
                } else {
                    vista.mostrarGanador("Nadie, empate");
                }
            } else {
                vista.mostrarMensaje("Ronda completada. Es tu turno nuevamente.");
                vista.actualizarCartas(jugador.getCartas(), this::handleCartaSeleccionada);
            }
        }
    }

    private void handleCartaSeleccionada(ActionEvent e) {
        JButton fuente = (JButton) e.getSource();
        String textoCarta = fuente.getText();
        Carta cartaSeleccionada = buscarCartaPorTexto(textoCarta);
        jugarRonda(cartaSeleccionada);
    }

    private Carta buscarCartaPorTexto(String texto) {
        for (Carta carta : jugador.getCartas()) {
            if (carta.toString().equals(texto)) {
                return carta;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VistaGrafica vista = new VistaGrafica();
                Juego juego = new Juego(vista);
                juego.iniciar();
            }
        });
    }
}
