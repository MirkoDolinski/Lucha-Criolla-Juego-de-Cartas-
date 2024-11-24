import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaGrafica {

    private JFrame frame;
    private JPanel panelCartas;
    private JTextArea mensajeArea;
    private JButton[] botonesCartas;
    private int numeroCartas;
    private JPanel panelPuntaje;
    private JLabel labelPuntaje;
    @SuppressWarnings("unused")
    private int puntajeJugador = 0;
    @SuppressWarnings("unused")
    private int puntajeMaquina = 0;

    public VistaGrafica() {
        frame = new JFrame("Lucha Criolla");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelCartas = new JPanel();
        panelCartas.setLayout(new GridLayout(2, 5));  
        botonesCartas = new JButton[5];  

        for (int i = 0; i < botonesCartas.length; i++) {
            botonesCartas[i] = new JButton("Carta " + (i + 1));
            botonesCartas[i].setFont(new Font("Arial", Font.PLAIN, 14));
            botonesCartas[i].setBackground(Color.CYAN);  
            botonesCartas[i].setForeground(Color.BLACK);  
            botonesCartas[i].setFocusPainted(false);  
            botonesCartas[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));  
            botonesCartas[i].setPreferredSize(new Dimension(100, 150));  
            botonesCartas[i].setOpaque(true);
            botonesCartas[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    JButton source = (JButton) evt.getSource();
                    source.setBackground(Color.LIGHT_GRAY);  
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    JButton source = (JButton) evt.getSource();
                    source.setBackground(Color.CYAN);  
                }
            });
            panelCartas.add(botonesCartas[i]);
        }

        mensajeArea = new JTextArea(10, 30);
        mensajeArea.setEditable(false);  
        mensajeArea.setLineWrap(true);  
        mensajeArea.setWrapStyleWord(true);
        mensajeArea.setFont(new Font("Arial", Font.BOLD, 16));
        mensajeArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(mensajeArea);
        frame.add(scrollPane, BorderLayout.SOUTH);  

       
        panelPuntaje = new JPanel();
        panelPuntaje.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelPuntaje = new JLabel("Puntaje: Jugador - 0 | Máquina - 0");
        labelPuntaje.setFont(new Font("Arial", Font.BOLD, 18));
        labelPuntaje.setForeground(Color.DARK_GRAY);
        panelPuntaje.add(labelPuntaje);
        frame.add(panelPuntaje, BorderLayout.NORTH);  

        frame.add(panelCartas, BorderLayout.CENTER);
        frame.setSize(600, 400);  
        frame.setLocationRelativeTo(null);  
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    public void mostrarMensaje(String mensaje) {
        mensajeArea.append(mensaje + "\n"); 
    }

    public void actualizarCartas(List<Carta> cartas, ActionListener listener) {
        limpiarBotones();  
        numeroCartas = cartas.size();  

        for (int i = 0; i < numeroCartas; i++) {
            botonesCartas[i].setText(cartas.get(i).toString());
            botonesCartas[i].setVisible(true);  
            botonesCartas[i].addActionListener(listener);
        }

        for (int i = numeroCartas; i < botonesCartas.length; i++) {
            botonesCartas[i].setVisible(false);  
        }
    }

    public void limpiarBotones() {
        for (JButton boton : botonesCartas) {
            for (ActionListener listener : boton.getActionListeners()) {
                boton.removeActionListener(listener);
            }
        }
    }

    public void actualizarPuntaje(int puntajeJugador, int puntajeMaquina) {
        this.puntajeJugador = puntajeJugador;
        this.puntajeMaquina = puntajeMaquina;
        labelPuntaje.setText("Puntaje: Jugador - " + puntajeJugador + " | Máquina - " + puntajeMaquina);
    }

    // Mostrar el ganador
    public void mostrarGanador(String ganador) {
        JOptionPane.showMessageDialog(frame, "¡" + ganador + " ha ganado el juego!");
    }
}
