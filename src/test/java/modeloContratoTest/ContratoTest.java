/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modeloContratoTest;

import modeloContrato.Contrato;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Clase de pruebas para la case modelo Contrato. Comprueba el constructor y los
 * métodos getters y setters.
 *
 * @author JFG
 */
public class ContratoTest {

    private Contrato contrato;

    /**
     * Configuración antes de cada prueba. Crea una instancia de la clase
     * Contrato con datos de prueba
     */
    @BeforeEach
    public void configuracion() {
        contrato = new Contrato("12345678J", "Empresa SA", "Descripción de contrato", "tipo de contrato", "2024-12-27", "666.00");
    }

    /**
     * Prueba para comprobar que el constructor asigna los valores iniciales.
     */
    @Test
    public void testConstructor() {
        assertEquals("12345678J", contrato.getNif(), "El NIF debe ser '12345678J'");
        assertEquals("Empresa SA", contrato.getEmpresa(), "La empresa debe ser 'Empresa SA'");
        assertEquals("Descripción de contrato", contrato.getDescripcion(), "La descripción debe ser 'Descripción de contrato");
        assertEquals("tipo de contrato", contrato.getTipoContrato(), "El tipo de contrato debe ser 'tipo de contrato'");
        assertEquals("2024-12-27", contrato.getFecha(), "La fecha debe ser '2024-12-27'");
        assertEquals("666.00", contrato.getPrecio(), "El Precio debe ser '666.00'");
    }

    /**
     * Prueba para comprobar los métodos setters. Se dan otros valores y se
     * comprueba que se actualizan.
     */
    @Test
    public void testSetters() {
        contrato.setNif("87654321J");
        contrato.setEmpresa("Empresa Dos SL");
        contrato.setDescripcion("Nueva descripción");
        contrato.setTipoContrato("Medio");
        contrato.setFecha("2024-12-28");
        contrato.setPrecio("999.00");

        assertEquals("87654321J", contrato.getNif(), "El NIF debe ser '87654321J'");
        assertEquals("Empresa Dos SL", contrato.getEmpresa(), "La empresa debe ser 'Empresa Dos SL'");
        assertEquals("Nueva descripción", contrato.getDescripcion(), "La descripción debe ser 'Nueva descripción");
        assertEquals("Medio", contrato.getTipoContrato(), "El tipo de contrato debe ser 'Medio'");
        assertEquals("2024-12-28", contrato.getFecha(), "La fecha debe ser '2024-12-28'");
        assertEquals("999.00", contrato.getPrecio(), "El Precio debe ser '999.00'");
    }

    /**
     * Prueba para compropar los métodos getters sin modificar los valores
     * iniciales. Comprueba que los valores devueltos sean los mismos que los
     * del constructor.
     */
    @Test
    public void testGetters() {
        assertEquals("12345678J", contrato.getNif(), "El NIF debe ser '12345678J'");
        assertEquals("Empresa SA", contrato.getEmpresa(), "La empresa debe ser 'Empresa SA'");
        assertEquals("Descripción de contrato", contrato.getDescripcion(), "La descripción debe ser 'Descripción de contrato");
        assertEquals("tipo de contrato", contrato.getTipoContrato(), "El tipo de contrato debe ser 'tipo de contrato'");
        assertEquals("2024-12-27", contrato.getFecha(), "La fecha debe ser '2024-12-27'");
        assertEquals("666.00", contrato.getPrecio(), "El Precio debe ser '666.00'");
    }
}
