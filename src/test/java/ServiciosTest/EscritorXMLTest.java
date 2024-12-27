/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ServiciosTest;

import Servicios.EscritorXML;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import modeloContrato.Contrato;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Test para la clase EscritorXML. Comprueba que el archivo XML que se genera es
 * correcto y cumple los requisitos.
 *
 * @author JFG
 */
public class EscritorXMLTest {

    private static final String RUTA_SALIDA_PRUEBA = "contratoSalidaPrueba.xml";
    private EscritorXML escritorXML;

    /**
     * Configuración antes de cada prueba.
     */
    @BeforeEach
    public void configuracion() {
        escritorXML = new EscritorXML();
    }

    /**
     * Prueba el método generarXML para comprobar que se crea el archivo XML
     * con los datos solicitados.
     */
    @Test
    public void testGenerarXML() {
        // Crear datos de prueba
        List<Contrato> contratos = new ArrayList<>();
        contratos.add(new Contrato("12345678G", "Empresa1", "Descripcion1", "Tipo1", "2024-12-27", "500.00"));
        contratos.add(new Contrato("87654321F", "Empresa2", "Descripcion2", "Tipo2", "2024-12-27", "250.50"));

        // Generar el archivo XML
        escritorXML.generarXML(contratos, RUTA_SALIDA_PRUEBA);

        // Comprobar que el archivo se ha creado
        File archivo = new File(RUTA_SALIDA_PRUEBA);
        assertTrue(archivo.exists(), "El archivo se creó correctamente.");

        // Verificar el contenido del XML
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(archivo);

            // Comprueba la raiz
            Element raiz = documento.getDocumentElement();
            assertEquals("contratos", raiz.getNodeName(), "La raiz no es 'contratos'.");

            // Comprobar el número de contratos en el XML
            NodeList listaContratos = documento.getElementsByTagName("contrato");
            assertEquals(2, listaContratos.getLength(), "El número de contratos no es correcto.");

            // Verificar cada contrato
            for (int i = 0; i < listaContratos.getLength(); i++) {
                Node nodoContrato = listaContratos.item(i);
                assertEquals(Node.ELEMENT_NODE, nodoContrato.getNodeType(), "El nodo no es del tipo ELEMENT.");

                Element contratoElemento = (Element) nodoContrato;

                // Comprobar que están todos los campos menos "tipoContrato"
                assertNotNull(contratoElemento.getElementsByTagName("nif").item(0), "El 'nif' no está en el contrato.");
                assertNotNull(contratoElemento.getElementsByTagName("empresa").item(0), "El 'empresa' no está en el contrato.");
                assertNotNull(contratoElemento.getElementsByTagName("descripcion").item(0), "El 'descripcion' no está en el contrato.");
                assertNotNull(contratoElemento.getElementsByTagName("fecha").item(0), "El 'fecha' no está en el contrato.");
                assertNotNull(contratoElemento.getElementsByTagName("precio").item(0), "El 'precio' no está en el contrato.");
                assertEquals(0, contratoElemento.getElementsByTagName("tipoContrato").getLength(), "El 'tipoContrato' no debe estar en el contrato.");
            }
        } catch (Exception e) {
            fail("Error analizando el archivo XML: " + e.getMessage());
        }
    }
    
    /**
     * Elimina el archivo XML de prueba para poder realizar mas pruebas.
     */
    @AfterEach
    public void limpiar(){
        File archivo = new File(RUTA_SALIDA_PRUEBA);
        if (archivo.exists()){
            assertTrue(archivo.delete(), "No se pudo eliminar el archivo XML de prueba.");
        }
    }
}
