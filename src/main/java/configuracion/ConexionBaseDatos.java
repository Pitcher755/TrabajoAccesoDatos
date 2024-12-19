/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos.
 * 
 * @author JFG
 */
public class ConexionBaseDatos {
    // URL para conectarse a la base de datos.
    private static final String URL = "jdbc:mysql://localhost:3308/contratos";
    // Usuario de la base de datos.
    private static final String USUARIO = "root";
    // Contraseña del usuario de la base de datos.
    private static final String CONTRASEÑA = "diciembre2024";
    
    /**
     * Establece la conexión con la base de datos.
     * 
     * @return Objeto Connection activo o null si ocurre un error.
     */
    public static Connection getConnection(){
        Connection conexion = null;
        try {
            // Intenta conectar con la base de datos utilizando la URL, usuario y contraseña.
            conexion = DriverManager.getConnection(URL,USUARIO, CONTRASEÑA);
            
        } catch (SQLException e) {
            // Muestra un mensaje de error si falla la conexión.
            System.err.println("No ha sido posible la conexión a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}
