/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.modelo;

/**
 * Clase para instanciar las coordenadas de una localización.
 *
 * @author Alberto García Navarro.
 */
public class Coordenada {

    // ATRIBUTOS.
    /**
     * {double} - Latitud de una coordenada.
     */
    private double latitud;
    
    /**
     * {double} - Longitud de una coordenada.
     */
    private double longitud;

    // CONSTRUCTORES.
    /**
     * Constructor por defecto.
     */
    public Coordenada() {
        this.latitud = 0;
        this.longitud = 0;
    }

    /**
     * Constructor por parámetros.
     * @param latitud
     * @param longitud
     */
    public Coordenada(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // GETTERS/SETTERS.
    /**
     * Devuelve la latitud.
     * 
     * @return {double} - Latitud.
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Establece la latitud.
     * 
     * @param latitud 
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    
    /**
     * Devuelve la longitud.
     * 
     * @return {double} - Longitud.
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Establece la longitud.
     * 
     * @param longitud 
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
