import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/luchacriolla";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<Carta> obtenerCartas() {
        List<Carta> cartas = new ArrayList<>();
        try (Connection conexion = conectar();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre, nivel_poder FROM cartas")) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int nivelPoder = rs.getInt("nivel_poder");
                cartas.add(new Carta(nombre, nivelPoder));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartas;
    }
}
