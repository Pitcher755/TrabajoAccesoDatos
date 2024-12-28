/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ServiciosTest;

import AccesoDatos.ContratoAD;
import Servicios.LectorXML;
import AccesoDatos.ConexionBaseDatos;
import java.io.File;
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
 * Clase de prueba para la clase LectorXML. Prueba el procesamiento del archivo
 * XML y la inserción en la base de datos.
 *
 * @author JFG
 */
public class LectorXMLTest {

    private LectorXML lectorXML;
    private ContratoAD contratoAD;
    private static final String RUTA_ARCHIVO_PRUEBA = "src/test/resources/contratos_prueba.xml";

    /**
     * Configuración para las pruebas. Inicializa los objetos que se necesitan y
     * limpia la base de datos.
     */
    @BeforeEach
    public void configuracion() {
        lectorXML = new LectorXML();
        contratoAD = new ContratoAD();
        limpiarBaseDatos();
    }

    /**
     * Limpia la baase de datos antes y despues de cada Test.
     */
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
     * Prueba para verirficar que el XML se puede procesar bien y se guardan sus
     * datos en la base de datos.
     */
    @Test
    public void testProcesarXML() {
        // Verificar que el archivo de prueba existe
        File archivoPrueba = new File(RUTA_ARCHIVO_PRUEBA);
        assertTrue(archivoPrueba.exists(), "El archivo de prueba debe existir.");

        // Procesar el archivo XML
        lectorXML.procesarXML(RUTA_ARCHIVO_PRUEBA);

        // Comprobar los datos insertados en la base de datos
        List<Contrato> contratos = contratoAD.encontrarTodo();
        assertEquals(2, contratos.size(), "Debe haber 2 ontratos en la base de datos");

        Contrato contrato1 = contratos.get(0);
        assertEquals("B41138447", contrato1.getNif(), "El NIF del primer contrato debe coincidir.");
        assertEquals("FERRETERÍA INDUSTRIAL ROMA SL", contrato1.getEmpresa(), "La empresa del primer contrato tiene que coincidir.");
        assertEquals("MATERIAL FERRETERÍA", contrato1.getDescripcion(), "La descripcion del primer contrato tiene que coincidir.");
    }

    /**
     * Limpia la base de datos despues de casa prueba.
     */
    @AfterEach
    public void limpiar() {
        limpiarBaseDatos();
    }
}
