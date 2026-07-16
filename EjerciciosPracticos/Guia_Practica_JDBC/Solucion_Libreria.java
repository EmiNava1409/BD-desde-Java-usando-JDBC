package EjerciciosPracticos.Guia_Practica_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Solucion_Libreria {

    // URL de conexión hacia la base de datos PostgreSQL.
    private static final String URL = "jdbc:postgresql://localhost:5432/inventario_libreria";

    // Usuario registrado en PostgreSQL.
    private static final String USUARIO = "TU_USUARIO";

    // Contraseña del usuario de PostgreSQL.
    private static final String CONTRASENA = "TU_CONTRASENA";


    public static void main(String[] args) {

        /*
         * Try-with-resources:
         * Permite cerrar automáticamente la conexión (Connection)
         * y el objeto Statement al finalizar la ejecución, evitando fugas de recursos.
         */
        try (Connection con = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             Statement st = con.createStatement()) {


            // Mensaje inicial del sistema.
            System.out.println("SISTEMA DE INVENTARIO - BOOK LITERARIO");

            // Confirmación de que la conexión JDBC fue realizada correctamente.
            System.out.println("Conexión establecida correctamente con PostgreSQL.\n");

            /*
             * Se llama al método listarLibros().
             * para recorrer los registros obtenidos. Este metodo utiliza ResultSet
             */
            int totalLibros = listarLibros(st);

            /*
             * Se llama al método calcularPrecioPromedio().
             * para obtener información adicional del inventario
             */
            calcularPrecioPromedio(st);

            // Muestra un resumen final de la información consultada.
            System.out.println("RESUMEN");
            System.out.println("Total de libros registrados: " + totalLibros);
            System.out.println("Consulta ejecutada correctamente mediante JDBC.");

        } catch (SQLException e) {

            /*
             * Captura cualquier error relacionado con la conexión,
             */
            System.out.println("Error al conectar con la base de datos.");
            System.out.println("Detalle del error: " + e.getMessage());
        }
    }


    /*
     * Método encargado de consultar todos los libros almacenados
     * en la tabla libros.
     *
     * Utiliza:
     * - Statement para enviar la consulta SQL.
     * - ResultSet para recorrer los datos obtenidos.
     *
     * Retorna la cantidad total de registros encontrados.
     */
    private static int listarLibros(Statement st) throws SQLException {


        // Consulta SQL para obtener los datos de los libros.
        String sql = "SELECT id, titulo, autor, precio FROM libros";

        // Variable utilizada para contar los libros encontrados.
        int contador = 0;


        /*
         * executeQuery() ejecuta la consulta SQL y devuelve un objeto ResultSet con los resultados.
         */
        try (ResultSet rs = st.executeQuery(sql)) {

            System.out.println("LISTADO DE LIBROS");

            /*
             * next() permite avanzar registro por registro dentro
             * del ResultSet mientras existan datos disponibles.
             */
            while (rs.next()) {

                // Incrementa el contador por cada libro encontrado.
                contador++;

                /*
                 * Obtiene los valores de cada columna de la tabla
                 * y los muestra en pantalla.
                 */
                System.out.printf(
                        "ID: %-2d | %-30s | %-25s | $%.2f%n",
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getDouble("precio")
                );
            }
        }
        // Devuelve la cantidad total de libros encontrados.
        return contador;
    }

    /*
     * Método encargado de calcular el precio promedio
     * de todos los libros registrados.
     *
     * Utiliza una consulta SQL con la función AVG().
     */
    private static void calcularPrecioPromedio(Statement st) throws SQLException {

        // Consulta SQL para obtener el promedio de precios.
        String sql = "SELECT AVG(precio) AS promedio FROM libros";

        /*
         * Ejecuta la consulta y almacena el resultado
         * dentro de un objeto ResultSet.
         */
        try (ResultSet rs = st.executeQuery(sql)) {

            // Verifica si existe un resultado disponible.
            if (rs.next()) {

                System.out.println("\nRESUMEN FINANCIERO");

                // Obtiene el valor promedio calculado por PostgreSQL.
                System.out.printf(
                        "Precio promedio: $%.2f%n",
                        rs.getDouble("promedio")
                );
            }
        }
    }
}