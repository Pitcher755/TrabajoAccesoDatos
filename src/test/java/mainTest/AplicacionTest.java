/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mainTest;

import AccesoDatos.ContratoAD;
import Servicios.ProcesadorContrato;
import AccesoDatos.ConexionBaseDatos;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 * Clase de pruebas para la clase principal Aplicacion.
 * 
 * @author JFG
 */
public class AplicacionTest {
    
    private static final String RUTA_ARCHIVO_ENTRADA = "src/test/resources/contratos_adjudicados_test.xml";
    private static final String RUTA_ARCHIVO_SALIDA = "src/test/resources/contratos_adjudicados_salida_test.xml";
    
    // Acceso a la BBDD
    private final ContratoAD contratoAD = new ContratoAD();
    
    /**
     * Limpieza después de cada prueba.
     * Elimina el archivo XML de salida.
     */
    @AfterEach
    public void limpiar(){
        File archivoSalida = new File(RUTA_ARCHIVO_SALIDA);
        if (archivoSalida.exists()){
            archivoSalida.delete();
        }
        
        // Limpiar la BBDD
        limpiarBaseDatos();
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
     * Prueba principal que verifica que se ejecute el método principal.
     */
    @Test
    public void testMain() {
        // Instancia del procesador principal
        ProcesadorContrato procesador = new ProcesadorContrato();
        
        // Ejecutar el proceso
        procesador.proceso(RUTA_ARCHIVO_ENTRADA, RUTA_ARCHIVO_SALIDA);
        
        // Comprueba que el archivo de salida existe
        File archivoSalida = new File(RUTA_ARCHIVO_SALIDA);
        assertTrue(archivoSalida.exists(), "El archivo de salida debe estar creado");
        
        // Comprobar que el archivo de salida no está vacío
        assertTrue(archivoSalida.length() > 0, "El archivo de salida no puede estar vacío");
    }
    
}
