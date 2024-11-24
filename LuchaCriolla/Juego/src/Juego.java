import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private Jugador usuario;
    private Jugador maquina;

    public Juego() {
        usuario = new Jugador("Usuario");
        maquina = new Jugador("Máquina");
    }

    public void iniciar() {
        System.out.println("Cargando cartas...");
        List<Carta> todasLasCartas = ConexionBD.obtenerCartas();
        Collections.shuffle(todasLasCartas);

        for (int i = 0; i < 4; i++) {
            usuario.agregarCarta(todasLasCartas.get(i));
            maquina.agregarCarta(todasLasCartas.get(i + 4));
        }

        System.out.println("¡El juego comienza!");
        jugarRondas();
    }

    private void jugarRondas() {
        Scanner scanner = new Scanner(System.in);

        while (usuario.tieneCartas() && maquina.tieneCartas()) {
            System.out.println("\nTus cartas:");
            for (int i = 0; i < usuario.getCartas().size(); i++) {
                System.out.println(i + ": " + usuario.getCartas().get(i));
            }

            System.out.print("Elige una carta (número): ");
            int eleccionUsuario = scanner.nextInt();
            Carta cartaUsuario = usuario.jugarCarta(eleccionUsuario);

            Carta cartaMaquina = maquina.jugarCarta(0); 
            System.out.println("Máquina juega: " + cartaMaquina);

            if (cartaUsuario.getNivelPoder() > cartaMaquina.getNivelPoder()) {
                System.out.println("¡Ganaste esta ronda!");
            } else {
                System.out.println("¡Perdiste esta ronda!");
            }
        }

        if (usuario.tieneCartas()) {
            System.out.println("¡Ganaste el juego!");
        } else {
            System.out.println("Perdiste el juego. ¡Intenta de nuevo!");
        }

        scanner.close();
    }
}
