/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modeloContrato.Contrato;

/**
 * Objeto de Acceso a Datos para realizar operaciones con la base de datos. Con
 * métodos para isertar contratos y recuperarlos desde la tabla "contratos".
 *
 * @author JFG
 */
public class ContratoAD {

    /**
     * Constructor por defecto no necesita inicializar.
     */
    public ContratoAD() {
        // Constructor por defecto
    }

    /**
     * Inserta un contrato en la base de datos. Este método prepara y ejecuta
     * una consulta SQL para guardar los datos de un contrato.
     *
     * @param contrato Objeto de la clase Contrato que se va a insertar en la
     * base de datos.
     */
    public void insertarContrato(Contrato contrato) {
        String consulta = "INSERT INTO contratos (nif, empresa, descripcion, tipoContrato, fecha, precio) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conexion = null;
        PreparedStatement sentenciaSQL = null;
        try {
            conexion = ConexionBaseDatos.getConnection();
            sentenciaSQL = conexion.prepareStatement(consulta);

            // Establecer los valores en la consulta
            sentenciaSQL.setString(1, contrato.getNif());
            sentenciaSQL.setString(2, contrato.getEmpresa());
            sentenciaSQL.setString(3, contrato.getDescripcion());
            sentenciaSQL.setString(4, contrato.getTipoContrato());
            sentenciaSQL.setString(5, contrato.getFecha());
            sentenciaSQL.setString(6, contrato.getPrecio());

            // Ejecutar la inserción
            sentenciaSQL.executeUpdate();
            System.out.println("Contrato insertado correctamente: " + contrato.getNif());

        } catch (SQLException e) {
            System.err.println("Error al insertar contrato: " + e.getMessage());
        } finally {
            try {
                if (sentenciaSQL != null) {
                    sentenciaSQL.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la sentencia SQL: " + e.getMessage());
            }
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Recupera todos los contratos de la base de datos. Ejecuta una consula SQL
     * para recuperar los registros de la tabla "contratos."
     *
     * @return Una lista de obtetos de la clase Contrato, cada uno representa un
     * registro de la tabla.
     */
    public List<Contrato> encontrarTodo() {
        String consulta = "SELECT nif, empresa, descripcion, tipoContrato, fecha, precio FROM contratos";
        List<Contrato> contratos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement sentenciaSQL = null;
        ResultSet resultado = null;
        try {
            conexion = ConexionBaseDatos.getConnection();
            sentenciaSQL = conexion.prepareStatement(consulta);
            resultado = sentenciaSQL.executeQuery();

            // Recorre cada fila del ResultSet y construye un objeto Contrato.
            while (resultado.next()) {
                Contrato contrato = new Contrato(
                        resultado.getString("nif"),
                        resultado.getString("empresa"),
                        resultado.getString("descripcion"),
                        resultado.getString("tipoContrato"),
                        resultado.getString("fecha"),
                        resultado.getString("precio"));

                contratos.add(contrato);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener contratos: " + e.getMessage());
        } finally {
            cerrarRecursos(conexion, sentenciaSQL, resultado);
        }
        return contratos; // Devuelve la lista de contratos.
    }

    /**
     * Cierra los recursos utilizados en las operaciones con la base de datos.
     *
     * @param conexion Objeto de la clase Connection a cerrar.
     * @param sentenciaSQL Objeto de la clase PreparedStatement a cerrar.
     * @param resultado Objeto Resuslset a cerrar.
     */
    public void cerrarRecursos(Connection conexion, PreparedStatement sentenciaSQL, ResultSet resultado) {
        try {
            if (resultado != null) {
                resultado.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar ResultSet: " + e.getMessage());
        }
        try {
            if (sentenciaSQL != null) {
                sentenciaSQL.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la sentencia SQL: " + e.getMessage());
        }
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
