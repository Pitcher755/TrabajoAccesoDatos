/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloContrato;

/**
 * Sirve como modelo para representar un contrato adjudicado.
 * Incluye información como NIF, empresa, descripción, tipo de contrato, fecha y precio.
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
    
    // Constructor
    public Contrato(String nif, String empresa, String descripcion, String tipoContrato, String fecha, String precio) {
        this.nif = nif;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.tipoContrato = tipoContrato;
        this.fecha = fecha;
        this.precio = precio;
    }

    // Getters
    public String getNif() {
        return nif;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPrecio() {
        return precio;
    }

    // Setters
    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }       
}
