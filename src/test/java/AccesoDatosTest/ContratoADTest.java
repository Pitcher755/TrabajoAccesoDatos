/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package AccesoDatosTest;

import AccesoDatos.ContratoAD;
import configuracion.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modeloContrato.Contrato;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Clase de pruebas para la clase ContratoAD. Prueba la interacción con la base
 * de datos.
 *
 * @author JFG
 */
public class ContratoADTest {

    // Objeto de la clase ContratoAD y de la clse Connection
    private ContratoAD contratoAD;
    private Connection conexion;

    @BeforeEach
    public void configuracion() {
        contratoAD = new ContratoAD();
        conexion = ConexionBaseDatos.getConnection();
        assertNotNull(conexion, "La conexión a la base de datos tiene que estar disponible para las pruebas");
    }

    /**
     * Limpia la tabla contratos. Este método elimina todos los registros antes
     * de cada prueba.
     */
    @AfterEach
    public void limpiarBaseDatos() {
        Connection conexion = null;
        PreparedStatement sentenciaSQL = null;

        try {
            conexion = ConexionBaseDatos.getConnection();
            String consulta = "TRUNCATE TABLE contratos";
            sentenciaSQL = conexion.prepareStatement(consulta);
            sentenciaSQL.executeUpdate();
        } catch (SQLException e) {
            fail("Error al limpiar la base de datos: " + e.getMessage());
        } finally {
            try {
                if (sentenciaSQL != null) {
                    sentenciaSQL.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                fail("Error al cerrar recursos en limpiarBaseDatos: " + e.getMessage());
            }
        }
    }

    /**
     * Prueba para ver que un contrato se inserta correctamente en la base de
     * datos.
     */
    @Test
    public void testInsertarContrato() {
        Contrato contrato = new Contrato("123456789F", "Empresa S.L.", "Descripción cualquiera", "Cualquiera", "2024-12-24", "1000");
        contratoAD.insertarContrato(contrato);

        List<Contrato> contratos = contratoAD.encontrarTodo();
        assertEquals(1, contratos.size(), "Debe haber solo un contrato en la base de datos.");
        assertEquals("123456789F", contratos.get(0).getNif(), "El NIF del contrato debe coincidir.");
    }

    /**
     * Prueba para verificar que se recuperan todos los contratos de la base de
     * datos.
     */
    @Test
    public void testEncontrarTodo() {
        Contrato contrato1 = new Contrato("123456789F", "Empresa SL", "descripcion 1", "Cualquiera", "2024-12-24", "1000");
        Contrato contrato2 = new Contrato("987654321F", "OtraEmpresa SL", "descripcion 1", "Cualquiera 2", "2024-12-24", "2000");

        contratoAD.insertarContrato(contrato1);
        contratoAD.insertarContrato(contrato2);

        List<Contrato> contratos = contratoAD.encontrarTodo();
        assertEquals(2, contratos.size(), "Debe haber dos contratos en la base de datos.");
    }

    @AfterEach
    public void limpiar() {
        limpiarBaseDatos();
    }
}
