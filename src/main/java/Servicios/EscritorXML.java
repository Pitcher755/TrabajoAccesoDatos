/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modeloContrato.Contrato;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Servicio para generar un archivo XML de salida con la lista de contratos.
 * Excluye el campo "tipoContrato" en el archivo generado.
 *
 * @author JFG
 */
public class EscritorXML {
    
    /**
     * Genera un archivo XML a partir de la lista de contratos del XML de entrada.
     * 
     * @param contratos Lista de contratos que se incluirá en el XML.
     * @param rutaArchivo Ruta donde se creará el archivo XML.
     */

    public void generarXML(List<Contrato> contratos, String rutaArchivo) {
        try {
            // Configuración del constructor de documentos XML
            DocumentBuilderFactory fabricaDocumentos = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabricaDocumentos.newDocumentBuilder();
            Document documento = constructor.newDocument();

            // Creación del elemento raíz "contratos"
            Element raiz = documento.createElement("contratos");
            documento.appendChild(raiz);

            // Recorrer la lista de contratos para crear el XML
            for (Contrato contrato : contratos) {
                Element contratoElemento = documento.createElement("contrato");

                // Agregar los elementos del contrato, sin incluir "tipoContrato"
                Element nif = documento.createElement("nif");
                nif.appendChild(documento.createTextNode(contrato.getNif()));
                contratoElemento.appendChild(nif);

                Element empresa = documento.createElement("empresa");
                empresa.appendChild(documento.createTextNode(contrato.getEmpresa()));
                contratoElemento.appendChild(empresa);

                Element descripcion = documento.createElement("descripcion");
                descripcion.appendChild(documento.createTextNode(contrato.getDescripcion()));
                contratoElemento.appendChild(descripcion);

                Element fecha = documento.createElement("fecha");
                fecha.appendChild(documento.createTextNode(contrato.getFecha()));
                contratoElemento.appendChild(fecha);

                Element precio = documento.createElement("precio");
                precio.appendChild(documento.createTextNode(String.valueOf(contrato.getPrecio())));
                contratoElemento.appendChild(precio);

                raiz.appendChild(contratoElemento);
            }

            // Configuración del transformador que crea el archivo XML
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformador = transFactory.newTransformer();
            DOMSource fuente = new DOMSource(documento);
            StreamResult resultado = new StreamResult(new File(rutaArchivo));

            // Transformar el DOM en un XML
            transformador.transform(fuente, resultado);
            System.out.println("Archivo XML de salida creado: " + rutaArchivo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
