/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.controlador;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase que gestiona el acceso y la modificación de ficheros.
 *
 * @author Alberto García Navarro.
 */
public class GestorFichero {

    /**
     * Constructor - Private al ser una clase de utilidad, no se debe instanciar.
     */
    private GestorFichero() {
        throw new IllegalStateException("Clase de utilidad, no puede ser instanciada");
    }

    // CONSTANTES ESTÁTICAS.
    /**
     * {String} - Fichero que contiene la firma del usuario.
     */
    private static final String FICHERO_MENSAJE = "mensaje.txt";
    
    /**
     * {String} - Fichero .tmp que guarda el log de excepciones.
     */    
    private static final String FICHERO_ERROR = "log.tmp";
    
    /**
     * {String} - Ruta relativa donde se guarda el fichero con el mensaje.
     */    
    private static final String RUTA_FICHERO_MENSAJE = System.getProperty("user.dir") + "\\ficheros\\" + FICHERO_MENSAJE;
    
    /**
     * {String} - Ruta relativa donde se guarda el fichero con los errores.
     */    
    private static final String RUTA_FICHERO_ERROR = System.getProperty("user.dir") + "\\ficheros\\" + FICHERO_ERROR;

    // MÉTODOS ESTÁTICOS.
    /**
     * Guarda un mensaje por defecto en el fichero si no se pasa parámetro.
     * 
     * @throws IOException 
     */
    public static void escribirMensaje() throws IOException {
        // Un try entre paréntesis cerrará siemple el flujo de datos.
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter(RUTA_FICHERO_MENSAJE));) {
            // Mensaje por defecto.
            escribir.write("Kilroy was here");
        }
    }

    /**
     * Guarda el mensaje pasado por parámetro en el fichero (Sobrecarga método).
     * 
     * @param mensaje - Mensaje que se va a guardar en el fichero.
     * @throws IOException 
     */
    public static void escribirMensaje(String mensaje) throws IOException {
        // Un try entre paréntesis cerrará siemple el flujo de datos.
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter(RUTA_FICHERO_MENSAJE));) {
            // Escribe el mensaje en el fichero.
            escribir.write(mensaje);
        }
    }
 
    /**
     * Devuelve el texto del fichero mensaje en formato String.
     * 
     * @return {String} - Cadena que contiene el fichero.
     * @throws IOException 
     */
    public static String leerMensaje() throws IOException {
        // El mensaje que devolverá por defecto.
        String mensaje = "Kilroy was here";
        
        // Un try entre paréntesis cerrará siemple el flujo de datos.
        try (BufferedReader leer = new BufferedReader(new FileReader(RUTA_FICHERO_MENSAJE))) {
            // Lee el mensaje del fichero.
            mensaje = leer.readLine();
        } catch (FileNotFoundException ex) {
            // Si no existe el archivo, se genera uno nuevo.
            GestorFichero.escribirMensaje();
        }
        return mensaje;
    }
    
    /**
     * Escribe los errores en el fichero.
     * 
     * @param fecha - Fecha y hora cuando se origina el error.
     * @param error - Mensaje con el error.
     * @param traza - Traza descriptiva del error que se ha generado.
     */
    public static void escribirError(String fecha, String error, String traza) {
        // Un try entre paréntesis cerrará siemple el flujo de datos.
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter(RUTA_FICHERO_ERROR, true));) {
            // Escribe el error en el fichero según el formato dado.
            escribir.write(fecha + "\n" + error + "\n" + traza + "\n");
        } catch (IOException ioex) {
            GestorErrores.logError("No se pudo guardar el error", GestorErrores.getStackTrace(ioex));
        }
    }

    /**
     * Borra el fichero .tmp de errores al finalizar el programa.
     * 
     * @throws IOException 
     */
    public static void borrarFicheroTemporal() throws IOException {
        // Obtenemos la ruta del fichero en Path y lo borramos si existe.
        Files.deleteIfExists(Paths.get(RUTA_FICHERO_ERROR));
    }
}
