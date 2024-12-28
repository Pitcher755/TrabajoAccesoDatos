/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import Servicios.ProcesadorContrato;
import java.io.File;

/**
 * Clase principal que sirve como punto de entrada para la aplicación.
 * Ejecuta el procesamiento de datos completo.
 * 
 * @author JFG
 */
public class Aplicacion {

    /**
     * Constructor por defecto sin inicialización.
     */
    public Aplicacion() {
        // Constructor por defecto
    }
    
    /**
     * Método principal que inicia el programa.
     * 
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Define las rutas de los archivos XML de entrada y salida.
        String rutaArchivoEntrada = "contratos-adjudicados-sep-24.xml"; 
        String rutaArchivoSalida = "contratosAdjudicados_salida.xml";
        
        // Comprobar si el archivo de entrada existe
        File archivoEntrada = new File(rutaArchivoEntrada);
        if (!archivoEntrada.exists()){
            System.err.println("El archivo de entrada no existe: " + rutaArchivoEntrada);
            return;
        }
        
        // Inicializa el procesador principal
        ProcesadorContrato procesador = new ProcesadorContrato();
        
        // Ejecuta el flujo completo de procesamiento.
        procesador.proceso(rutaArchivoEntrada, rutaArchivoSalida);
    }    
}
