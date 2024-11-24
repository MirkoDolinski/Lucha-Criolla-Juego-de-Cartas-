import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/luchacriolla?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<Carta> obtenerCartas() {
        List<Carta> cartas = new ArrayList<>();
        try (Connection conexion = conectar();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre, nivel_poder, tipo, descripcion, efecto FROM cartas")) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int nivelPoder = rs.getInt("nivel_poder");
                String tipo = rs.getString("tipo");
                String descripcion = rs.getString("descripcion");
                String efecto = rs.getString("efecto");
                cartas.add(new Carta(nombre, nivelPoder, tipo, descripcion, efecto));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartas;
    }

    public static void registrarPartida(String jugador, String resultado) {
        try (Connection conexion = conectar()) {
            String query = "INSERT INTO historial_partidas (jugador, resultado) VALUES (?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, jugador);
                stmt.setString(2, resultado);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
