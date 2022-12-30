/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kilroywashere.controlador;

import kilroywashere.modelo.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión con la base de datos.
 *
 * @author Alberto García Navarro.
 */
public class GestorBBDD {

    /**
     * Constructor - Private al ser una clase de utilidad, no se debe instanciar.
     */
    private GestorBBDD() {
        throw new IllegalStateException("Clase de utilidad, no puede ser instanciada");
    }

    // CONSTANTES ESTÁTICAS.
    /**
     * {Connection} - Gestiona la conexión de las consultas.
     */
    private static Connection conexion;
    
    /**
     * {PreparedStatement} - Establece las consultas preparadas.
     */
    private static PreparedStatement comando;
    
    /**
     * {ResultSet} - Recoge los registros obtenidos de las consultas.
     */
    private static ResultSet registro;
    
    /**
     * {String} - Driver jdbc para la conexión con base de datos MYSQL.
     */    
    private static final String DRIVER_MYSQL = "com.mysql.cj.jdbc.Driver";  
    
    /**
     * {String} - Ruta del servidor de la base de datos.
     */    
    private static final String SERVIDOR = "jdbc:mysql://localhost:3306/dwes_manana_kilroywashere";
    
    /**
     * {String} - Usuario para conectar a la base de datos.
     */
    private static final String USUARIO = "dwes_manana";
    
    /**
     * {String} - Clave para conectar a la base de datos.
     */    
    private static final String CLAVE = "dwes_2223";

    // MÉTODOS ESTÁTICOS.
    /**
     * Realiza la conexión con la base de datos.
     * 
     * @throws ClassNotFoundException - Si no encuentra el controlador jdbc.
     * @throws SQLException 
     */
    public static void conectarBBDD() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_MYSQL);
        conexion = DriverManager.getConnection(SERVIDOR, USUARIO, CLAVE);
    }

    /**
     * Finaliza la conexión, cerrando el flujo de datos.
     */   
    public static void cerrarConexion() {
        try {
            conexion.close();
            comando.close();
            registro.close();
        } catch (SQLException sql) {
            GestorErrores.logError("Error al cerrar la conexión con la base de datos", GestorErrores.getStackTrace(sql));
        }
    }

    /**
     * Verifica que existe el usuario en la base de datos e inicia sesión.
     * 
     * @param usuario - Usuario que va a iniciar sesión.
     * @return {boolean} - True si logra iniciar sesión, false si no.
     */
    public static boolean login(Usuario usuario) {
        boolean logeado = false;
        
        try {
            String select = "SELECT * FROM usuarios "
                    + "WHERE nombre LIKE ?"
                    + "AND contraseña LIKE ?";
            
            comando = conexion.prepareStatement(select);
            comando.setString(1, usuario.getNombre());
            comando.setString(2, usuario.getClave());           
            registro = comando.executeQuery();
            
            // Si encuentra resultado, devolvemos true.
            if (registro.next()) {
                logeado = true;
                // Guardamos también su email.
                usuario.setEmail(registro.getString("email"));
            }   
        } catch (SQLException sql) {
            GestorErrores.logError("Error al iniciar sesión", GestorErrores.getStackTrace(sql));
        }       
        return logeado;
    }

    /**
     * Inserta el usuario creado en la base de datos.
     * 
     * @param usuario - Usuario que se crea la cuenta.
     * @return {boolean} - True si logra crear la cuenta, false si no.
     */
    public static boolean crearUsuario(Usuario usuario){
        boolean creado = false;
        
        try {
            String insert = "INSERT INTO usuarios VALUES(?, ?, ?)";
            
            comando = conexion.prepareStatement(insert);
            comando.setString(1, usuario.getNombre());
            comando.setString(2, usuario.getClave());
            comando.setString(3, usuario.getEmail());
            
            // Si no hay problemas al ejecutar, devolvemos true.
            if (!comando.execute()) {
                creado = true;
            }
        } catch (SQLException sql) {
            GestorErrores.logError("Error al crear el usuario", GestorErrores.getStackTrace(sql));
        }
        return creado;
    }

    /**
     * Comprueba si existe un tesoro con las coordenadas pasadas,
     * devuelve el tesoro instanciado según el tipo que corresponda (Polimorfismo)
     * 
     * @param coordenada - Coordenada que pasamos para comprobar si hay tesoros cerca.
     * @return {Tesoro} - Tesoro instanciado según su tipo.
     */
    public static Tesoro buscarTesoro(Coordenada coordenada) {
        Tesoro tesoro = null;
        String tipo;
        String nombreTesoro;
        double latitud;
        double longitud;
        String descripcion;
        String nombrePista = "";
        String pista = "";
        String nombrePieza = "";
        int piezas = 0;

        try {
            String select = "SELECT * FROM tesoros "
                    + "WHERE latitud = ?"
                    + "AND longitud = ?";
            
            comando = conexion.prepareStatement(select);
            comando.setDouble(1, coordenada.getLatitud());
            comando.setDouble(2, coordenada.getLongitud());
            registro = comando.executeQuery();
            
            // Si obtiene un registro, guardamos los datos en variables.
            if (registro.next()) {
                nombreTesoro = registro.getString("nombre");
                latitud = registro.getDouble("latitud");
                longitud = registro.getDouble("longitud");
                descripcion = registro.getString("descripcion");
                tipo = registro.getString("tipo");

                // Según el tipo de tesoro, instanciaremos con polimorfismo un objeto u otro.
                switch (tipo) {
                    case "reliquia" -> {
                        tesoro = new Reliquia(nombreTesoro, latitud, longitud, descripcion, tipo);
                    }

                    case "pista" -> {
                        select = "SELECT * FROM pistas "
                                + "WHERE nombre_tesoro = ?";
                        
                        comando = conexion.prepareStatement(select);
                        comando.setString(1, nombreTesoro);
                        registro = comando.executeQuery();
                        
                        // Obtenemos la pista asociada al tesoro.
                        if (registro.next()) {
                            nombrePista = registro.getString("nombre");
                            pista = registro.getString("pista");
                        }
                        tesoro = new Pista(nombreTesoro, latitud, longitud, descripcion, tipo, nombrePista, pista);
                    }

                    case "pieza" -> {
                        select = "SELECT * FROM piezas "
                                + "WHERE nombre_tesoro = ?";
                        
                        comando = conexion.prepareStatement(select);
                        comando.setString(1, nombreTesoro);
                        registro = comando.executeQuery();
                        
                        // Obtenemos las piezas asociadas al tesoro.
                        if (registro.next()) {
                            nombrePieza = registro.getString("nombre");
                            piezas = registro.getInt("piezas");
                        }
                        tesoro = new Pieza(nombreTesoro, latitud, longitud, descripcion, tipo, nombrePieza, piezas);
                    }
                }
            }
        } catch (SQLException sql) {
            GestorErrores.logError("Error al realizar la búsqueda", GestorErrores.getStackTrace(sql));
        }
        return tesoro;
    }

    /**
     * Inserta la firma del usuario al descubrir un tesoro.
     * 
     * @param tesoro - Tesoro que se ha descubierto.
     * @param usuario - Usuario que ha descubierto el tesoro.
     * @param mensaje - Mensaje que se va a guardar en la base de datos.
     * @return {boolean} - True si logra guardar la firma, false si no.
     */
    public static boolean firmar(Tesoro tesoro, Usuario usuario, String mensaje) {
        boolean firmado = false;
        
        try {
            String insert = "INSERT INTO descubiertos VALUES(?, ?, ?)";
            
            comando = conexion.prepareStatement(insert);
            comando.setString(1, tesoro.getNombre());
            comando.setString(2, usuario.getNombre());
            comando.setString(3, mensaje);
            
            // Si no hay problemas al ejecutar, devolvemos true.
            if (!comando.execute()) {
                firmado = true;
            }
        } catch (SQLException sql) {
            GestorErrores.logError("Error al enviar la firma", GestorErrores.getStackTrace(sql));
        }
        return firmado;
    }

    /**
     * Actualiza la firma de un tesoro descubierto en la base de datos.
     * 
     * @param mensaje - Mensaje con la nueva firma.
     * @param tesoro - Tesoro que va a ser actualizado.
     * @param usuario - Usuario que va a actualizar su firma.
     * @return {boolean} - True si logra actualizar la firma, false si no.
     */
    public static boolean actualizarFirma(String mensaje, Tesoro tesoro, Usuario usuario) {
        boolean actualizado = false;
        
        try {
            String update = "UPDATE descubiertos "
                    + "SET mensaje = ?"
                    + "WHERE nombre_tesoro = ?"
                    + "AND nombre_usuario = ?";
            
            comando = conexion.prepareStatement(update);
            comando.setString(1, mensaje);
            comando.setString(2, tesoro.getNombre());
            comando.setString(3, usuario.getNombre());
            
            // Si no hay problemas al ejecutar, devolvemos true.
            if (!comando.execute()) {
                actualizado = true;
            }
        } catch (SQLException sql) {
            GestorErrores.logError("Error al hacer actualizar su firma", GestorErrores.getStackTrace(sql));
        }
        return actualizado;
    }
    
    /**
     * Obtiene todos los tesoros descubiertos de un usuario.
     * 
     * @param usuario - Usuario del que se va a consultar sus tesoros.
     * @return {boolean} - True si el usario tiene tesoros, false si no.
     */
    public static boolean listarDescubiertos(Usuario usuario) {
        List<Descubierto> descubiertos = new ArrayList<>();
        String nombre;
        String descripcion;
        String mensaje;
        boolean existeRegistro = false;
        
        try {
            String select = "SELECT * FROM tesoros, descubiertos "
                    + "WHERE nombre LIKE nombre_tesoro "
                    + "AND nombre_usuario LIKE ?";
            
            comando = conexion.prepareStatement(select);
            comando.setString(1, usuario.getNombre());
            registro = comando.executeQuery();
            
            // Creamos un objeto por cada tesoro descubierto.
            while (registro.next()) {             
                nombre = registro.getString("nombre");
                descripcion = registro.getString("descripcion");
                mensaje = registro.getString("mensaje");
                Descubierto descubierto = new Descubierto(nombre, descripcion, mensaje);
                // Añadimos el tesoro descubierto al ArrayList.
                descubiertos.add(descubierto);
                existeRegistro = true;
            }
            // Establecemos la lista de tesoros descubiertos del usuario.
            usuario.setDescubiertos(descubiertos);
        } catch (SQLException sql) {
            GestorErrores.logError("Error al comprobar los tesoros del usuario", GestorErrores.getStackTrace(sql));
        }
        return existeRegistro;
    }
}
