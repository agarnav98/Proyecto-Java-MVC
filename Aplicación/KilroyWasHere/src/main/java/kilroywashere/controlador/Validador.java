/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.controlador;

import java.util.regex.Pattern;

/**
 * Clase que gestiona la validación de datos.
 *
 * @author Alberto García Navarro.
 */
public class Validador {
    
    /**
     * Constructor - Private al ser una clase de utilidad, no se debe instanciar.
     */
    private Validador() {
        throw new IllegalStateException("Clase de utilidad, no puede ser instanciada");
    }    
    
    // CONSTANTES ESTÁTICAS.
    /**
     * {String} - Regex para validar el usuario.
     * 
     * De 6 a 16 caracteres y no permite espacios.
     */
    private static final String USER_REGEX = "^(?=\\S+$).{6,16}$";
    
    /**
     * {String} - Regex de validación de OWASP para validar la contraseña.
     * 
     * De 8 a 16 caracteres y debe contener:
     * Al menos un dígito.
     * Al menos una letra en minúscula.
     * Al menos una letra en mayúscula.
     * Al menos un caracter especial.
     * No permite espacios.
     */
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!~<>,;:_=?*+#.”&§%°()\\|\\[\\]\\-\\$\\^\\@\\/])(?=\\S+$).{8,16}$";
    
    /**
     * {String} - Regex para validar el email.
     */    
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    /**
     * {Pattern} - Patrón que se va a usar para validar el usuario.
     */
    private static final Pattern PATRON_USER = Pattern.compile(USER_REGEX);
    
    /**
     * {Pattern} - Patrón que se va a usar para validar la contraseña.
     */
    private static final Pattern PATRON_PASSWORD = Pattern.compile(PASSWORD_REGEX);
    
    /**
     * {Pattern} - Patrón que se va a usar para validar el email.
     */
    private static final Pattern PATRON_EMAIL = Pattern.compile(EMAIL_REGEX);
    
    // MÉTODOS ESTÁTICOS.
    /**
     * Valida si el nombre del usuario cumple el patrón.
     * 
     * @param usuario - Nombre del usuario que se va a validar.
     * @return {boolean} - True si es correcto, false si no.
     */
    public static boolean validarUsuario(String usuario){
        return PATRON_USER.matcher(usuario).matches();
    }
    
    /**
     * Valida si la contraseña del usuario cumple el patrón.
     * 
     * @param password - Contraseña que se va a validar.
     * @return {boolean} - True si es correcto, false si no.
     */
    public static boolean validarPassword(String password){
        return PATRON_PASSWORD.matcher(password).matches();
    }
    
    /**
     * Valida si el email cumple el patrón.
     * 
     * @param email - Email que se va a validar.
     * @return {boolean} - True si es correcto, false si no.
     */
    public static boolean validarEmail(String email){  
        return PATRON_EMAIL.matcher(email).matches();
    }    
}
