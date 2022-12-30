/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.modelo;

import java.util.List;
import java.util.ArrayList;

/**
 * Clase para instanciar un nuevo usuario.
 *
 * @author Alberto García Navarro.
 */
public class Usuario {

    // ATRIBUTOS.
    /**
     * {String} - Nombre del usuario.
     */
    private String nombre;
    
    /**
     * {String} - Contraseña del usuario.
     */    
    private String clave;
    
    /**
     * {String} - Email del usuario.
     */    
    private String email;
    
    /**
     * {List} - Lista con los tesoros descubiertos por el usuario.
     */    
    private List<Descubierto> descubiertos;

    // CONSTRUCTORES.
    /**
     * Constructor por defecto
     */
    public Usuario() {
        this.nombre = "";
        this.clave = "";
        this.email = "";
        this.descubiertos = new ArrayList<>();
    }

    /**
     * Constructor por parámetros introduciendo nombre y clave
     *
     * @param nombre
     * @param clave
     */
    public Usuario(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
        this.email = "";
        this.descubiertos = new ArrayList<>();
    }

    /**
     * Constructor por parámetros introduciendo nombre, clave y email
     *
     * @param nombre
     * @param clave
     * @param email
     */
    public Usuario(String nombre, String clave, String email) {
        this(nombre, clave);
        this.email = email;   
    }
    
    /**
     * Constructor por parámetros introduciendo nombre, clave y la lista de descubiertos.
     *
     * @param nombre
     * @param clave
     * @param descubiertos
     */
    public Usuario(String nombre, String clave, List<Descubierto> descubiertos) {
        this(nombre, clave);
        this.descubiertos = new ArrayList<>(descubiertos);
    }

    /**
     * Constructor por parámetros con todos los datos.
     *
     * @param nombre
     * @param clave
     * @param email
     * @param descubiertos
     */
    public Usuario(String nombre, String clave, String email, List<Descubierto> descubiertos) {
        this(nombre, clave, email);
        this.descubiertos = new ArrayList<>(descubiertos);
    }

    // GETTERS/SETTERS.
    /**
     * Devuelve el nombre del usuario.
     * 
     * @return {String} - Nombre del usuario.
     */ 
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la contraseña del usuario.
     * 
     * @return {String} - Contraseña del usuario.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Devuelve el email del usuario.
     * 
     * @return {String} - Email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve la lista de tesoros descubiertos del usuario.
     * 
     * @return {List} - Lista de tesoros descubiertos.
     */    
    public List<Descubierto> getDescubierto() {
        return descubiertos;
    }

    /**
     * Establece la lista de tesoros descubiertos del usuario.
     *
     * @param descubiertos
     */
    public void setDescubiertos(List<Descubierto> descubiertos) {
        this.descubiertos = descubiertos;
    }

    //MÉTODOS.
    /**
     * Muestra por pantalla los tesoros descubiertos del usuario.
     */    
    public void mostrarTesoros() {
        descubiertos.forEach(tesoro -> System.out.println("\t" + tesoro.toString()));
    }

    /**
     * Comprueba si un tesoro ha sido descubierto por el usuario.
     * 
     * @param tesoro - Tesoro que se quiere comprobar.
     * @return {boolean} - True si coincide el tesoro, false si no.
     */
    public boolean verificarDescubiertos(Tesoro tesoro) {
        boolean encontrado = false;
        
        // Bucle que devuelve true si coincide el tesoro con algunos de la lista.
        for (Descubierto descubierto : descubiertos){
            if(descubierto.getNombreTesoro().equals(tesoro.getNombre())){
                encontrado = true;
            }
        }
        return encontrado;
    }
}
