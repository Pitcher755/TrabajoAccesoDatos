/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import configuracion.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modeloContrato.Contrato;

/**
 * Objeto de Acceso a Datos para realizar operaciones con la base de datos.
 *
 * @author JFG
 */
public class ContratoAD {

    /**
     * Inserta un contrato en la base de datos.
     *
     * @param contrato Objeto contrato que se va a insertar en la base de datos.
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
                if (sentenciaSQL != null){
                    sentenciaSQL.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la sentencia SQL: " + e.getMessage());
            }
            try {
                if (conexion != null){
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Recupera todos los contratos de la base de datos
     *
     * @return List<Contrato> - Lista de objetos de la clase Contrato.
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
        return contratos; // Devuelve la lista de contratos.
    }
}
