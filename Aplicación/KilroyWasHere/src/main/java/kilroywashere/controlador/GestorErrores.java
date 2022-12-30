/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.controlador;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * Clase que gestiona las excepciones, generando un archivo .tmp.
 *
 * @author Alberto García Navarro.
 */
public class GestorErrores {

    /**
     * Constructor - Private al ser una clase de utilidad, no se debe instanciar.
     */
    private GestorErrores() {
        throw new IllegalStateException("Clase de utilidad, no puede ser instanciada");
    }

    // CONSTANTES ESTÁTICAS.
    /**
     * {Logger} - Registra el log de errores,
     * son registros anónimos para evitar que actualicen este controlador.
     */
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    // MÉTODOS ESTÁTICOS.
    /**
     * Recoge la excepción para mostrarla por pantalla
     * y guardar un archivo .tmp que mostrará información detallada del error
     * mientras esté en ejecución el programa.
     * 
     * @param mensaje - Mensaje de la excepeción que se va a mostrar y guardar.
     * @param trazaError - Traza detallada de la excepción que solo aparecerá en el .tmp.
     */   
    public static void logError(String mensaje, String trazaError) {
        // Establecemos un formato de fecha que aparecerá en el .tmp.
        DateFormat formatoFecha = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
        String fecha = formatoFecha.format(new Date());
        // Guardamos la información en el fichero, junto a su traza.
        GestorFichero.escribirError(fecha, mensaje, trazaError);
        // Mostramos el error por consola para el usuario.
        LOGGER.severe(mensaje);
    }

    /**
     * Convierte la traza de la excepción en una cadena String.
     * 
     * @param excepcion - Excepción que se va a convertir en String.
     * @return {String} - Cadena con la traza de la excepción.
     */
    public static String getStackTrace(Exception excepcion) {
        // Para construir la cadena.
        StringWriter sWriter = new StringWriter();
        // Para imprimir la cadena.
        PrintWriter pWriter = new PrintWriter(sWriter);
        // Imprime la traza y la recoge pWriter.
        excepcion.printStackTrace(pWriter);
        // Escribe la cadena recogida y lo convierte a String.
        return sWriter.toString();
    }
}
