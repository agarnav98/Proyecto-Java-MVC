/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.modelo;

/**
 * Clase para instanciar una reliquia que será un tipo de Tesoro (hereda).
 * Es el tipo de Tesoro básico.
 *
 * @author Alberto García Navarro.
 * @see Tesoro
 */
public class Reliquia extends Tesoro {
    
    // CONSTRUCTORES.
    /**
     * Constructor por defecto.
     */
    public Reliquia() {
        super();
    }
    
    /**
     * Constructor por parámetros.
     *
     * @param nombre
     * @param latitud
     * @param longitud
     * @param descripcion
     * @param tipo
     */
    public Reliquia(String nombre, double latitud, double longitud, String descripcion, String tipo) {
        super(nombre, latitud, longitud, descripcion, tipo);
    }
    
    //MÉTODOS.
    /**
     * Comprobará que existe la reliquia, que es la instancia básica de tesoro.
     * 
     * @return {boolean} - True si ha encontrado la reliquia, false si no.
     */
    @Override
    public boolean descubrirTesoro() {
        return true;
    }
}
