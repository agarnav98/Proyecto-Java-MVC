/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.modelo;

/**
 * Clase para instanciar una pista que será un tipo de Tesoro (hereda).
 *
 * @author Alberto García Navarro.
 * @see Tesoro
 */
public class Pista extends Tesoro {

    // ATRIBUTOS.
    /**
     * {String} - Nombre de la pista.
     */
    private String nombrePista;

    /**
     * {String} - Mensaje con la pista.
     */
    private String mensajePista;

    // CONSTRUCTORES.
    /**
     * Constructor por defecto.
     */
    public Pista() {
        super();
        nombrePista = "";
        mensajePista = "";
    }

    /**
     * Constructor por parámetros.
     *
     * @param nombre
     * @param latitud
     * @param longitud
     * @param descripcion
     * @param tipo
     * @param nombrePista
     * @param pista
     */
    public Pista(String nombre, double latitud, double longitud, String descripcion, String tipo, String nombrePista, String pista) {
        super(nombre, latitud, longitud, descripcion, tipo);
        this.nombrePista = nombrePista;
        this.mensajePista = pista;
    }

    // GETTERS/SETTERS.
    /**
     * Devuelve el nombre de la pista.
     * 
     * @return {String} - Nombre de la pista.
     */     
    public String getNombrePista() {
        return nombrePista;
    }
    
    /**
     * Establece el nombre de la pista.
     *
     * @param nombrePista
     */
    public void setNombrePista(String nombrePista) {
        this.nombrePista = nombrePista;
    }

    /**
     * Devuelve el mensaje con la pista.
     * 
     * @return {String} - Mensaje con la pista.
     */     
    public String getMensajePista() {
        return mensajePista;
    }

    /**
     * Establece el mensaje de la pista.
     *
     * @param mensajePista
     */    
    public void setMensajePista(String mensajePista) {
        this.mensajePista = mensajePista;
    }
    
    //MÉTODOS.
    /**
     * Implementa la acción al descubrir una pista,
     * al encontrarla mostrará el mensaje de la pista.
     * 
     * @return {boolean} - True si ha encontrado la pista, false si no.
     */
    @Override
    public boolean descubrirTesoro() {
        System.out.println(toString());
        return true;
    }
    
    /**
     * Formato cadena para sacar los datos de la pista.
     * 
     * @return {String} - Cadena con los datos de la pista.
     */
    @Override
    public String toString() {
        return nombrePista + ": " + mensajePista;
    }   
}
