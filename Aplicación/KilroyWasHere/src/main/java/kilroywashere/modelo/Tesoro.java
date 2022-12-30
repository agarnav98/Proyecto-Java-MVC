/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.modelo;

/**
 * Clase para instanciar un tesoro, será una clase Padre Abstracta.
 *
 * @author Alberto García Navarro.
 */
public abstract class Tesoro {

    // ATRIBUTOS.
    /**
     * {String} - Nombre del tesoro.
     */
    private String nombre;
    
    /**
     * {Coordenada} - Coordenada de localización del tesoro.
     */
    private Coordenada coordenada;
    
    /**
     * {String} - Descripción del tesoro.
     */
    private String descripcion;
    
    /**
     * {String} - Tipo de tesoro, que se relaciona con una Clase Hijo.
     */
    private String tipo;
    
    // CONSTRUCTORES.
    /**
     * Constructor por defecto - Protected para que sea heredable pero no se pueda instanciar.
     */
    protected Tesoro() {
        this.nombre = "";
        this.coordenada = new Coordenada();
        this.descripcion = "";
        this.tipo = "";
    }

    /**
     * Constructor por parámetros - Protected para que sea heredable pero no se pueda instanciar.
     *
     * @param nombre
     * @param longitud
     * @param latitud
     * @param descripcion
     * @param tipo
     */
    protected Tesoro(String nombre, double latitud, double longitud, String descripcion, String tipo) {
        this.nombre = nombre;
        this.coordenada = new Coordenada(longitud, latitud);
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    // GETTERS/SETTERS.
    /**
     * Devuelve el nombre del tesoro.
     * 
     * @return {String} - Nombre del tesoro.
     */  
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del tesoro.
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la coordenada donde se encuentra el tesoro.
     * 
     * @return {Coordenada} - Nombre del tesoro.
     */  
    public Coordenada getCoordenada() {
        return coordenada;
    }

    /**
     * Establece la coordenada donde se encuentra el tesoro.
     *
     * @param coordenada
     */    
    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
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
     * Devuelve el tipo de tesoro.
     * 
     * @return {String} - Tipo de tesoro.
     */     
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de tesoro.
     *
     * @param tipo
     */    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //MÉTODOS.
    /**
     * Método abstracto que implementará la acción al descubrir el tesoro en las clases hijas.
     * 
     * @return {boolean}
     */    
    public abstract boolean descubrirTesoro();
}
