/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ServiciosTest;

import AccesoDatos.ContratoAD;
import Servicios.ProcesadorContrato;
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
 * Clase de prueba para la clase ProcesadorContrato.
 *
 * @author JFG
 */
public class ProcesadorContratoTest {

    private ProcesadorContrato procesadorContrato;
    private ContratoAD contratoAD;

    private static final String ARCHIVO_ENTRADA = "src/test/resources/entradaPrueba.xml";
    private static final String ARCHIVO_SALIDA = "src/test/resources/salidaPrueba.xml";

    /**
     * Configuración anterior a cada prueba.
     */
    @BeforeEach
    void configuracion() {
        contratoAD = new ContratoAD();
        procesadorContrato = new ProcesadorContrato();

        // Limpiar la base de datos antes de cada prueba.
        limpiarBaseDatos();
    }

    /**
     * Limpiar después de cada prueba. Elimina los archivos y dela la base de
     * datos vacía.
     */
    @AfterEach
    void limpiar() {
        // Limpiar la base de datos despues de cada prueba
        limpiarBaseDatos();

        // Eliminar arcchivo de salida
        eliminarArchivo(ARCHIVO_SALIDA);
    }

    /**
     * Prueba del método "proceso. Comprueba que el archivo de entrada se
     * procesa correctamente y crea el archivo de lasida, además de guardar los
     * datos en la BBDD.
     */
    @Test
    public void testProceso() {
        procesadorContrato.proceso(ARCHIVO_ENTRADA, ARCHIVO_SALIDA);

        // Comprobar que los contratos se han guardado en la base de datos
        List<Contrato> contratos = contratoAD.encontrarTodo();
        assertEquals(2, contratos.size(), "El número de contratos en la base de datos no es correcto.");

        // Comprobar que se ha creado el archivo de salida
        File archivoSalida = new File(ARCHIVO_SALIDA);
        assertTrue(archivoSalida.exists(), "El archivo de salida no se creó correctamente.");
    }

    /**
     * Limpia la base de datos eliminando todos los registros.
     */
    public void limpiarBaseDatos() {
        Connection conexion = null;
        PreparedStatement sentenciaSQL = null;

        try {
            // Conectar a la base de datos
            conexion = ConexionBaseDatos.getConnection();

            // Sentencia SQL para eliminar todos los registros de la tabla
            String consulta = "TRUNCATE TABLE contratos";
            sentenciaSQL = conexion.prepareStatement(consulta);

            // Ejecutar la sentencia SQL
            sentenciaSQL.executeUpdate();
        } catch (SQLException e) {
            fail("Error al limpiar la base de datos: " + e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (sentenciaSQL != null) {
                    sentenciaSQL.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos en limpiarBaseDatos: " + e.getMessage());
            }
        }
    }

    /**
     * Elimina un archivo si existe.
     *
     * @param rutaArchivo ruta del archivo a eliminar.
     */
    private void eliminarArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            archivo.delete();
        }
    }
}
