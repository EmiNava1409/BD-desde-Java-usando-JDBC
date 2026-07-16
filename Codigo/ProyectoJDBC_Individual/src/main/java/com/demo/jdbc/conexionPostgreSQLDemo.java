/**
 * DEMOSTRACIÓN DE CONEXIÓN A BD DESDE JAVA USANDO JDBC
 * Objetivo: Conectar Java a PostgreSQL con DriverManager, ejecutar consultas SQL con Statement y recorrer los resultados con ResultSet.
 * Simula un sistema hospitalario: lista los pacientes ingresados.
 * Estudiante:María Emilia Navarrete Ávila
 * Fecha: 15/07/2026
 */

package Codigo.ProyectoJDBC_Individual.src.main.java.com.demo.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class conexionPostgreSQLDemo {

    // URL de conexión: protocolo (jdbc:postgresql) + host + puerto + nombre de la base de datos
    private static final String URL = "jdbc:postgresql://localhost:5432/actividad_jdbc";

    // Usuario de la base de datos
    private static final String USUARIO = "emiliaNava";

    // Contraseña de la base de datos (en un proyecto real no debe escribirse la contraseña, por seguridad)
    private static final String CONTRASENA = "root";

    public static void main(String[] args) {

        try (
                // Abre la conexión real con la base de datos PostgreSQL
                Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

                // Crea el objeto que se usará para enviar las sentencias SQL
                Statement statement = conexion.createStatement()

                // Al terminar el bloque try, Java cierra ambos automáticamente, incluso si ocurre un error
        ) {
            System.out.println("Conexión exitosa a PostgreSQL.");
            System.out.println();

            mostrarPacientes(statement);
            mostrarResumenHospitalario(statement);

        } catch (Exception e) {
            // Aquí caen errores como credenciales incorrectas, servidor apagado, o que la tabla "pacientes" no exista
            System.out.println("Error en la conexión o consulta.");
            e.printStackTrace();
        }
    }

    // Consulta todos los pacientes y los imprime en formato de tabla.
    private static void mostrarPacientes(Statement statement) throws Exception {

        String sql = "SELECT id, nombre, edad, diagnostico, habitacion, estado "
                + "FROM pacientes ORDER BY id";

        // executeQuery() ejecuta el SELECT y devuelve un ResultSet
        try (ResultSet resultado = statement.executeQuery(sql)) {

            System.out.println("=== PACIENTES INGRESADOS ===");
            System.out.printf("%-4s %-15s %-5s %-15s %-6s %-10s%n",
                    "ID", "NOMBRE", "EDAD", "DIAGNÓSTICO", "HAB.", "ESTADO");
            System.out.println("---------------------------------------------------------------------");

            // next() mueve el cursor a la siguiente fila; devuelve false cuando ya no hay más
            while (resultado.next()) {

                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                int edad = resultado.getInt("edad");
                String diagnostico = resultado.getString("diagnostico");
                String habitacion = resultado.getString("habitacion");
                String estado = resultado.getString("estado");

                System.out.printf("%-4d %-15s %-5d %-15s %-6s %-10s%n",
                        id, nombre, edad, diagnostico, habitacion, estado);
            }
            System.out.println();
        }
    }

    // Ejecuta una consulta de agregación (COUNT) para mostrar cuántos pacientes hay en total.
    private static void mostrarResumenHospitalario(Statement statement) throws Exception {

        // COUNT(*) cuenta todas las filas de la tabla
        String sql = "SELECT COUNT(*) AS total_pacientes FROM pacientes";

        try (ResultSet resultado = statement.executeQuery(sql)) {

            System.out.println("=== RESUMEN HOSPITALARIO ===");

            // Esta consulta siempre devuelve una sola fila con los totales
            if (resultado.next()) {
                int totalPacientes = resultado.getInt("total_pacientes");

                System.out.println("Total de pacientes ingresados: " + totalPacientes);
            }
        }
    }
}