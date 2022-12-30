/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.modelo;

/**
 * Clase para instanciar una pieza que será un tipo de Tesoro (hereda).
 *
 * @author Alberto García Navarro.
 * @see Tesoro
 */
public class Pieza extends Tesoro {

    // ATRIBUTOS.
    /**
     * {String} - Nombre de la pieza.
     */
    private String nombrePieza;
    
    /**
     * {int} - Número de piezas que se debe conseguir para obtener el tesoro.
     */    
    private int piezas;
    
    /**
     * {int} - Número de piezas conseguidas por el usuario.
     */    
    private static int contadorPiezas = 0;

    // CONSTRUCTORES.
    /**
     * Constructor por defecto.
     */
    public Pieza() {
        super();
        nombrePieza = "";
        piezas = 0;
    }

    /**
     * Constructor por parámetros.
     * 
     * @param nombre
     * @param latitud
     * @param longitud
     * @param descripcion
     * @param tipo
     * @param nombrePieza
     * @param piezas 
     */
    public Pieza(String nombre, double latitud, double longitud, String descripcion, String tipo, String nombrePieza, int piezas) {
        super(nombre, latitud, longitud, descripcion, tipo);
        this.nombrePieza = nombrePieza;
        this.piezas = piezas;
    }

    // GETTERS/SETTERS.
    /**
     * Devuelve el nombre de la pieza.
     * 
     * @return {String} - Nombre de la pieza.
     */    
    public String getNombrePieza() {
        return nombrePieza;
    }
    
    /**
     * Establece el nombre de la pieza.
     *
     * @param nombrePieza
     */
    public void setNombrePieza(String nombrePieza) {
        this.nombrePieza = nombrePieza;
    }
    
    /**
     * Devuelve el número de piezas necesarias.
     * 
     * @return {int} - Número de piezas.
     */  
    public int getPiezas() {
        return piezas;
    }
    
    /**
     * Establece el número de piezas necesarias.
     *
     * @param piezas
     */
    public void setPiezas(int piezas) {
        this.piezas = piezas;
    }

    /**
     * Devuelve cuantas piezas se han conseguido.
     * 
     * @return {int} - Número de piezas conseguidas por el usuario.
     */  
    public static int getContadorPiezas() {
        return contadorPiezas;
    }
    
    /**
     * Establece el número de piezas conseguidas por el usuario.
     *
     * @param contadorPiezas
     */
    public static void setContadorPiezas(int contadorPiezas) {
        Pieza.contadorPiezas = contadorPiezas;
    }
    
    //MÉTODOS.
    /**
     * Implementa la acción al descubrir una pieza,
     * al encontrarla sumará 1 al contador.
     * 
     * @return {boolean} - True si ha conseguido todas las piezas, false si no.
     */
    @Override
    public boolean descubrirTesoro() {
        boolean descubierto = false;
        System.out.println(toString());
        
        // Si no coincide el número de piezas con el contador, sumamos 1 al contador.
        if (piezas != contadorPiezas) {
            contadorPiezas++;
            System.out.println("Pieza guardada, le quedan: " + (piezas - contadorPiezas));
        }
        
        // Si coincide le número de piezas con el contador, reseteamos y devolvemos true.
        if (getPiezas() == contadorPiezas) {
            contadorPiezas = 0;
            descubierto = true;
        }
        return descubierto;
    }
    
    /**
     * Formato cadena para sacar los datos de la pieza.
     * 
     * @return {String} - Cadena con los datos de la pieza.
     */
    @Override
    public String toString() {
        return nombrePieza + ": " + piezas + " piezas";
    }    
}
