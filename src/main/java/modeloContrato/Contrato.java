/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloContrato;

/**
 * Sirve como modelo para representar un contrato adjudicado. Incluye
 * información como NIF, empresa, descripción, tipo de contrato, fecha y precio.
 * Se usa en varias partes del programa para procesar, guardar y manipular los
 * datos relacionados con contratos
 *
 * @author JFG
 */
public class Contrato {

    private String nif; // NIF de la empresa con la que se realiza el contrato.
    private String empresa; // Nombre de la empresa
    private String descripcion; // Descripción del contrato.
    private String tipoContrato; // Tipo del contrato (excluido en el XML de salida).
    private String fecha; // Fecha del contrato.
    private String precio; // Importe.

    /**
     * Constructor para inicializar un objeto de la clase Contrato.
     *
     * @param nif NIF del adjudicatario.
     * @param empresa Nombre de la empresa adjudicataria.
     * @param descripcion Descripción del contrato.
     * @param tipoContrato Tipo de contrato.
     * @param fecha Fecha en la que se firmó el contrato en formato
     * "yyyy-MM-dd".
     * @param precio Importe del contrato.
     */
    public Contrato(String nif, String empresa, String descripcion, String tipoContrato, String fecha, String precio) {
        this.nif = nif;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.tipoContrato = tipoContrato;
        this.fecha = fecha;
        this.precio = precio;
    }

    //Getters
    /**
     * Obtiene el Nif del adjudicatario.
     *
     * @return NIF del adjudicatario
     */
    public String getNif() {
        return nif;
    }

    /**
     * Obtiene el nombre de la empresa.
     *
     * @return Nombre de la empresa.
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Obtiene la descripción del contrato.
     *
     * @return Descripción del contrato.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el tipo de contrato.
     *
     * @return Tipo de contrato.
     */
    public String getTipoContrato() {
        return tipoContrato;
    }

    /**
     * Obtiene la fecha de adjudicación.
     *
     * @return Fecha de adjudicación.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Obtiene el importe del contrato.
     *
     * @return Importe del contrato.
     */
    public String getPrecio() {
        return precio;
    }

    // Setters
    /**
     * Establece el NIF del adjudicatario.
     *
     * @param nif NIF del adjudicatario.
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Establece el nombre de la empresa.
     *
     * @param empresa Empresa adjudicataria.
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Establece la descripción del contrato.
     *
     * @param descripcion Descripcion del contrato.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Establece el tipo de contrato.
     *
     * @param tipoContrato Tipo de contrato.
     */
    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    /**
     * Establece la fecha del contrato.
     *
     * @param fecha Fecha del contrato.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Establece el importe del contrato.
     *
     * @param precio Importe del contrato.
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
