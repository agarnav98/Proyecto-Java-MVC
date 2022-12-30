/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.controlador;

import kilroywashere.modelo.Usuario;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que gestiona la lectura de datos por teclado.
 *
 * @author Alberto García Navarro.
 */
public class GestorTeclado {

    /**
     * Constructor - Private al ser una clase de utilidad, no se debe instanciar.
     */
    private GestorTeclado() {
        throw new IllegalStateException("Clase de utilidad, no puede ser instanciada");
    }

    // MÉTODOS ESTÁTICOS.
    /**
     * Devuelve la cadena introducida por teclado, 
     * y saca por pantalla el mensaje que queramos mostrar al usuario.
     *
     * @param mensaje - Mensaje que se va a mostrar al usuario para que introduzca datos.
     * @return {String}
     */
    public static String leerString(String mensaje) {
        System.out.println(mensaje);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Hash MD5 que se va a usar para encriptar una cadena.
     * 
     * @param cadena - Cadena que se va a encriptar.
     * @return {String} - Cadena encriptada.
     */
    private static String encriptar(String cadena) {
        String hashCadena = "";
        try {
            // Establecemos el método que vamos a usar para encriptar.
            MessageDigest encriptar = MessageDigest.getInstance("MD5");
            // Convertimos la cadena en un array de bytes.
            byte[] arrayBytes = encriptar.digest(cadena.getBytes());
            // Convierte byte array en formato representable.
            BigInteger formato = new BigInteger(1, arrayBytes);
            // Convierte el texto en formato hash
            hashCadena = formato.toString(16);
            while (hashCadena.length() < 32) {
                hashCadena = "0" + hashCadena;
            }       
        } // Para errores al realizar el algoritmo.
        catch (NoSuchAlgorithmException algorithm) {
            GestorErrores.logError("Error al generar el algoritmo", GestorErrores.getStackTrace(algorithm));
        }
        return hashCadena;
    }
    
    /**
     * Devuelve un objeto usuario con su nombre de usuario y la contraseña encriptada,
     * introducido por teclado.
     * 
     * @return {Usuario} - Usuario con el que se hace el login.
     */
    public static Usuario introducirLogin() {
        String nombre = GestorTeclado.leerString("Introduzca su usuario:");
        String password = GestorTeclado.leerString("Introduzca su contraseña:");
        // Devolvemos el usuario con la contraseña ya encriptada.
        return new Usuario(nombre, encriptar(password));
    }

    /**
     * Crea un nuevo usuario con los datos introducidos por teclado,
     * y se valida que cada campo cumple las condiciones.
     * 
     * @return {Usuario} - Usuario que se ha creado.
     */
    public static Usuario introducirNuevoUsuario() {
        String nombre;
        String password;
        String email;
        
        // Se pedirá el nombre de usuario hasta que cumpla la validación.
        do {
            nombre = leerString("Introduzca el nombre de usuario (De 6 a 16 caracteres y sin espacios):");
            if (!Validador.validarUsuario(nombre)) {
                System.out.println("Nombre de usuario no permitido");
            }
        } while (!Validador.validarUsuario(nombre));
        
        // Se pedirá la contraseña hasta que cumpla la validación.
        do {
            password = leerString("Introduzca la contraseña (De 8 a 16 caracteres: Al menos 1 digito, 1 minúscula, 1 mayúscula, 1 carácter especial, sin espacios");
            if (!Validador.validarPassword(password)) {
                System.out.println("Contraseña no válida");
            }
        } while (!Validador.validarPassword(password));

        // Se pedirá el email hasta que cumpla la validación.
        do {
            email = leerString("Introduzca el correo electrónico:");
            if (!Validador.validarEmail(email)) {
                System.out.println("Correo electrónico no válido");
            }
        } while (!Validador.validarEmail(email));

        // Devolvemos el usuario con la contraseña ya encriptada.
        return new Usuario(nombre, encriptar(password), email);
    }
}
