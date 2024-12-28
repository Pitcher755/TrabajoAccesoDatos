/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import AccesoDatos.ContratoAD;
import java.util.List;
import modeloContrato.Contrato;

/**
 * Servicio principal que coordina el procesamiento completo de los contratos.
 *
 * Esta clase tiene las funciones de lectura de datos desde un XML,
 * almacenamiento en la base de datos y creación de un nuevo archivo XML con los
 * datos procesados. Es el centro de la aplicación donde se gestionan todos los
 * servicios.
 *
 * @author JFG
 */
public class ProcesadorContrato {

    private final LectorXML servicioLector;
    private final EscritorXML servicioEscritor;
    private final ContratoAD contratoAD;

    /**
     * Constructor para inicializar los servicios necesarios. Crea instancias de
     * las clases LectorXML, EscritorXML y ContratoAD.
     */
    public ProcesadorContrato() {
        this.servicioLector = new LectorXML();
        this.servicioEscritor = new EscritorXML();
        this.contratoAD = new ContratoAD();
    }

    /**
     * Método que realiza todo el flujo del programa.
     *
     * Este método: Lee un archivo XML y extrae los datos usando LectorXML.
     * Guarda los datos en la base de datos usando ContratoAD. Crea un archivo
     * XML de salida sin el campo "tipoContrato" usando EscritorXML.
     *
     * @param rutaAcrchivoEntrada Ruta del archivo XML de entrada.
     * @param rutaArchivoSalida Ruta del archivo XML de salida.
     * @throws IllegalArgumentException Si alguna ruta es inválida.
     */
    public void proceso(String rutaAcrchivoEntrada, String rutaArchivoSalida) {
        System.out.println("Iniciando el procesamiento...");

        // Leer el XML y guardar los datos en la base de datos.
        System.out.println("Leyendo el archivo XML y almacenando los datos...");
        servicioLector.procesarXML(rutaAcrchivoEntrada);

        // Recuperar los contratos desde la base de datos.
        System.out.println("Recuperando datos desde la base de datos...");
        List<Contrato> contratos = contratoAD.encontrarTodo();

        // Generar un archivo XML de salida sin incluir "tipoContrato".
        System.out.println("Generando el archivo XML de salida...");
        servicioEscritor.generarXML(contratos, rutaArchivoSalida);

        System.out.println("Procesamiento completado.");
    }
}
