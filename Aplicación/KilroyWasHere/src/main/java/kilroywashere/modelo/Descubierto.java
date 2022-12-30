/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.modelo;

/**
 * Clase para instanciar un tesoro que ha sido descubierto.
 *
 * @author Alberto García Navarro.
 */
public class Descubierto {

    // ATRIBUTOS.
    /**
     * {String} - Nombre del tesoro que ha sido descubierto.
     */
    private String nombreTesoro;
    
    /**
     * {String} - Descripción del tesoro que ha sido descubierto.
     */ 
    private String descripcion;
    
    /**
     * {String} - Firma del usuario que ha descubierto el tesoro.
     */
    private String firma;
    
    // CONSTRUCTORES.
    /**
     * Constructor por defecto.
     */
    public Descubierto() {
        this.nombreTesoro = "";
        this.descripcion = "";
        this.firma = "";
    }
    
    /**
     * Constructor por parámetros.
     *
     * @param nombreTesoro
     * @param descripcion
     * @param firma
     */
    public Descubierto(String nombreTesoro, String descripcion, String firma) {
        this.nombreTesoro = nombreTesoro;
        this.descripcion = descripcion;
        this.firma = firma;
    }

    // GETTERS/SETTERS.
    /**
     * Devuelve el nombre del tesoro.
     * 
     * @return {String} - Nombre del tesoro.
     */
    public String getNombreTesoro() {
        return nombreTesoro;
    }

    /**
     * Establece el nombre del tesoro.
     *
     * @param nombreTesoro
     */
    public void setNombreTesoro(String nombreTesoro) {
        this.nombreTesoro = nombreTesoro;
    }

    /**
     * Devuelve la descripción del tesoro.
     *
     * @return {String} - Descripción del tesoro.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del tesoro.
     *
     * @param descripcion
     */    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    

    /**
     * Devuelve la firma del usuario.
     *
     * @return {String} - Firma del usuario.
     */    
    public String getFirma() {
        return firma;
    }

    /**
     * Establece la firma del usuario.
     *
     * @param firma
     */
    public void setFirma(String firma) {
        this.firma = firma;
    }

    //MÉTODOS.
    /**
     * Formato cadena para sacar los datos del tesoro descubierto.
     * 
     * @return {String} - Cadena con los datos del tesoro descubierto.
     */
    @Override
    public String toString() {
        return nombreTesoro + ": " + descripcion + " -- Firma: " + firma;
    }
}
