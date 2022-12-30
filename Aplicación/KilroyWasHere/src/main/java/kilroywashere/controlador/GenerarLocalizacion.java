/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.controlador;

import kilroywashere.modelo.Coordenada;
import java.util.Random;

/**
 * Clase con las coordenadas de los tesoros para simular la función del gps.
 *
 * @author Alberto García Navarro.
 */
public class GenerarLocalizacion {

    /**
     * Constructor - Private al ser una clase de utilidad, no se debe instanciar.
     */
    private GenerarLocalizacion() {
        throw new IllegalStateException("Clase de utilidad, no puede ser instanciada");
    }
    
    // CONSTANTES ESTÁTICAS.
    /**
     * {Random} - Para utilizar números aleatorios.
     */
    private static final Random RANDOM = new Random();
    
    // MÉTODOS ESTÁTICOS.
    /**
     * Array con localizaciones que corresponden a tesoros y otras que no,
     * para simular el juego.
     * 
     * @return {Coordenada} - La coordenada aleatoria.
     */
    public static Coordenada generarCoordenada() {
        // Generamos 6 coordenadas, para simular la aplicación gps.
        Coordenada coordenada1 = new Coordenada(37.386009216308594, -5.993149757385254);
        Coordenada coordenada2 = new Coordenada(36.377293725745176, -5.993149757385254);
        Coordenada coordenada3 = new Coordenada(37.377293725745176, -5.986935917656098);
        Coordenada coordenada4 = new Coordenada(37.386009216308594, -6.993149757385254);
        Coordenada coordenada5 = new Coordenada(37.39348727403332, -5.99175545046858);
        Coordenada coordenada6 = new Coordenada(37.3860092163, -5.9931497573854);

        Coordenada[] coordenadas = {coordenada1, coordenada2, coordenada3, coordenada4, coordenada5, coordenada6};
        
        // Devolvemos la coordenada aleatoria.
        return coordenadas[RANDOM.nextInt(6)];
    }
}
