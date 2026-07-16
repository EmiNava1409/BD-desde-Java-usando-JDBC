/*
 * DESAFÍO DE PERSISTENCIA: GESTIÓN DE INVENTARIO INDUSTRIAL
 * ---------------------------------------------------------
 * INSTRUCCIONES PARA EL EQUIPO:
 * 1. Asegúrense de tener el driver JDBC de PostgreSQL configurado en su proyecto.
 * 2. Implementen la conexión, la ejecución de la consulta y el manejo de errores.
 * 3. El objetivo es lograr una extracción de datos robusta y un manejo de 
 *    errores profesional mediante SQLState y ErrorCode.
 * 
 * ¡Buena suerte, ingenieros!
 */


package Desafio_Individual;
import java.sql.*;

public class DesafioInventario {

    private static final String URL = "jdbc:postgresql://localhost:5432/TU_DB";
    private static final String USER = "TU_USUARIO";
    private static final String PASS = "TU_PASSWORD";

    // TODO: 1. Implementar método getConexion() utilizando DriverManager.getConnection()
    private static Connection getConexion() throws SQLException {
        // Ellos deben escribir aquí la lógica de retorno de la conexión
        return null; 
    }

    public static void ejecutarConsulta() {
        String sql = "SELECT id_componente, stock_actual, umbral_minimo FROM inventario";

        // El 'try-with-resources' asegura el cierre de la conexión y el statement.
        try (Connection conn = getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // TODO: 1. Declarar e inicializar el ResultSet usando stmt.executeQuery()
            // TODO: 2. Implementar bucle 'while' para recorrer el ResultSet
            // TODO: 3. Mostrar por consola los valores usando rs.getInt(...)

        } catch (SQLException e) {
            // TODO: 4. Implementar Log profesional mostrando e.getMessage(), e.getSQLState() y e.getErrorCode()
        }
    }

    public static void main(String[] args) {
        ejecutarConsulta();
    }
}