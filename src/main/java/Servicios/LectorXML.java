/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import AccesoDatos.ContratoAD;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import modeloContrato.Contrato;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servicio para leer y procesar un archivo XML, guardando sus datos en una base
 * de datos.
 *
 * @author JFG
 */
public class LectorXML {

    /**
     * Procesa un arvhivo XML y guarda sus datos en la base de datos.
     *
     * @param rutaArchivo Ruta del archivo XML de origen.
     */
    public void procesarXML(String rutaArchivo) {
        try {
            // Cargar el archivo XML
            File archivo = new File(rutaArchivo);
            DocumentBuilderFactory fabricaDocumentos = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabricaDocumentos.newDocumentBuilder();
            Document documento = constructor.parse(archivo);

            // Nomralización del documento
            documento.getDocumentElement().normalize();

            // Obtener todos los elementos "contrato" del XML
            NodeList filas = documento.getElementsByTagName("Row");

            // Crear una instancia del ContratoAD para guardar los datos en la base de datos.
            ContratoAD contratoAD = new ContratoAD();

            // Recorrer todas las filas
            for (int i = 1; i < filas.getLength(); i++) { // i = 1 para saltar la fila de los encabezados
                Node fila = filas.item(i);

                if (fila.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoFila = (Element) fila;

                    // Coger todas las celdas dentro de la fila
                    NodeList celdas = elementoFila.getElementsByTagName("Cell");
                    
                    // Leer los valores
                    String nif = obtenerValorCelda(celdas, 0); 
                    String empresa = obtenerValorCelda(celdas, 1);
                    String descripcion = obtenerValorCelda(celdas, 2);
                    String tipoContrato = obtenerValorCelda(celdas, 7);
                    String fecha = obtenerValorCelda(celdas, 4);
                    String precio = obtenerValorCelda(celdas, 5);

                    // Crear un objeto Contrato con los datos leidos
                    Contrato contrato = new Contrato(nif, empresa, descripcion, tipoContrato, fecha, precio);

                    // Guardar el contrato a la base de datos
                    contratoAD.insertarContrato(contrato);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el contenido de una etiqueta dentro de un elemento XML.
     *
     * @param celdas Lista de nodos de celdas en la fila.
     * @param indice Índice de la celda a leer
     * @return Contenido del nodo Data dentro de una celda, o una cadena vacía si no existe.
     */
    private String obtenerValorCelda(NodeList celdas, int indice) {
        if (indice < celdas.getLength()){
            Node celda = celdas.item(indice);
            if (celda.getNodeType() == Node.ELEMENT_NODE){
                Element elementoCelda = (Element) celda;
                NodeList datos = elementoCelda.getElementsByTagName("Data");
                if (datos.getLength() > 0){
                    return datos.item(0).getTextContent(); //+++++++++++*************++++++++++++++***********
                }
            }
        } 
        return ""; // Si no existe el nodo, devuelve cadena vacía
    }
}
