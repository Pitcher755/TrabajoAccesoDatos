/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package AccesoDatosTest;

import AccesoDatos.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para la clase ConexionBaseDatos. 
 * Esta clase omprueba si funciona la conexión a la base de datos, probando
 * que se realice la conexión y que los errores se manejen correctamente.
 * 
 * @author JFG
 */
public class ConexionBaseDatosTest {

    // Objeto Connection para las pruebas.
    private Connection conexion;

    /**
     * Si la conexión está abierta, la cierra después de cada Test.
     */
    @AfterEach
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                fail("La conexión no se cerró: " + e.getMessage());
            }
        }
    }

    /**
     * Prueba para comprobar que se establece la conexión.
     */
    @Test
    public void testConexion() {
        try {
            conexion = ConexionBaseDatos.getConnection(); // Método getConnection usado en ConexionBaseDatos
            assertNotNull(conexion, "La conexión tiene que ser válida y no nula.");
            assertFalse(conexion.isClosed(), "La conexión tiene que estar activa.");
        } catch (SQLException e) {
            fail("Error al comprobar la conexión: " + e.getMessage());
        }
    }

    /**
     * Test que verifica si una conexión puede cerrarse.
     */
    @Test
    public void testCerrarConexion(){
        try {
            conexion = ConexionBaseDatos.getConnection();
            assertNotNull(conexion, "hay que establecer una conexion antes de cerrarla");
            conexion.close();
            assertTrue(conexion.isClosed(), "La conexión tiene que cerrarse correctamente");
        } catch (SQLException e) {
            fail("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
