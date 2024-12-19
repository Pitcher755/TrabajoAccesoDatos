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
            NodeList listaNodos = documento.getElementsByTagName("contrato");

            // Crear una instancia del ContratoAD para guardar los datos en la base de datos.
            ContratoAD contratoAD = new ContratoAD();

            // Recorrer todos los elementos "contrato"
            for (int i = 0; i < listaNodos.getLength(); i++) {
                Node nodo = listaNodos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    // Leer el valor de cada etiqueta del contrato <nif>, <empresa>, <descripción>...
                    String nif = obtenerContenidoEtiqueta(elemento, "nif");
                    String empresa = obtenerContenidoEtiqueta(elemento, "empresa");
                    String descripcion = obtenerContenidoEtiqueta(elemento, "descripcion");
                    String tipoContrato = obtenerContenidoEtiqueta(elemento, "tipoContrato");
                    String fecha = obtenerContenidoEtiqueta(elemento, "fecha");
                    String precio = obtenerContenidoEtiqueta(elemento, "precio");

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
     * @param elemento Elemento XML que contiene la etiqueta.
     * @param etiqueta Nombre de la etiqueta a leer.
     * @return Contenido de la etiqueta o una cadena vacía si no existe.
     */
    private String obtenerContenidoEtiqueta(Element elemento, String etiqueta) {
        NodeList listaNodo = elemento.getElementsByTagName(etiqueta);
        if (listaNodo.getLength() > 0) {
            return listaNodo.item(0).getTextContent(); //*****************************************************
        } else {
            return "";
        }
    }
}
