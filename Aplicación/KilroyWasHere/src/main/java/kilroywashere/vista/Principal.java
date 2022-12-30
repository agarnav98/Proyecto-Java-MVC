/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package kilroywashere.vista;

import kilroywashere.controlador.*;
import kilroywashere.modelo.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Clase que ejecutará el programa principal y mostrará los datos al usuario por consola.
 *
 * @author Alberto García Navarro.
 */
public class Principal {

    /**
     * Línea separadora para mostrar por consola.
     */
    private static void separador() {
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Main con el programa principal que se muestra por consola.
     * 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        try {
            // Variables.
            Usuario usuario = new Usuario();
            Tesoro tesoro;
            boolean salir = false;
            boolean login = false;

            // Conexión a la base de datos.
            GestorBBDD.conectarBBDD();

            // Hacemos login de usuario, pedirá datos hasta que introduzcamos salir.
            System.out.println("Bienvenido a Kilroy was here");
            separador();
            
            while (!salir) {
                System.out.print("Seleccione una opción:"
                        + "\n\t1) Iniciar sesión"
                        + "\n\t2) Crear cuenta"
                        + "\n\t3) Salir de la aplicación");
                String opcion = GestorTeclado.leerString("");
                separador();
                
                switch (opcion) {
                    // Hace el login del usuario.
                    case "1" -> {
                        usuario = GestorTeclado.introducirLogin();
                        if (GestorBBDD.login(usuario)) {
                            salir = true;
                            login = true;
                        // Si no existe en la base de datos, mostramos el mensaje.
                        } else {
                            System.out.println("Credenciales no válidas");
                        }
                    }
                    // Crea un nuevo usuario.
                    case "2" -> {
                        usuario = GestorTeclado.introducirNuevoUsuario();
                        if (GestorBBDD.crearUsuario(usuario)) {
                            salir = true;
                            login = true;
                        }
                    }
                    // Sale de la aplicación.
                    case "3" ->
                        salir = true;
                    // Si el usuario introduce otra opción no contemplada.
                    default ->
                        System.out.println("Opción no válida");
                }
                separador();
            }

            // Si consigue hacer login, se muestra el segundo menú.
            if (login) {
                System.out.println("Login completado con éxito");
                salir = false;
                separador();

                // Interaccionamos con el menú hasta que introduzcamos la opción de salir.
                while (!salir) {
                    System.out.print("Elija qué quiere hacer:"
                            + "\n\t1) Buscar tesoro en mi zona"
                            + "\n\t2) Consultar tesoros descubiertos"
                            + "\n\t3) Modificar mensaje con la firma personal"
                            + "\n\t4) Salir de la aplicación");
                    String opcion = GestorTeclado.leerString("");
                    separador();
                    
                    switch (opcion) {
                        // Buscará si hay tesoros cercas
                        case "1" -> {
                            // Actualizamos la lista de tesoros descubieros del usuario
                            GestorBBDD.listarDescubiertos(usuario);
                            // Sacamos una coordenada aleatoria y vemos si existe el tesoro.
                            tesoro = GestorBBDD.buscarTesoro(GenerarLocalizacion.generarCoordenada());

                            // Si existe el tesoro, comprobará si ya lo ha descubierto.
                            if (tesoro != null) {
                                System.out.println("Está cerca de un tesoro con la siguiente descripcion: " + tesoro.getDescripcion());

                                // Si ya ha descubierto el tesoro, ofrece la opción de actualizar la firma.
                                if (usuario.verificarDescubiertos(tesoro)) {
                                    System.out.println("El tesoro ya ha sido descubierto: " + tesoro.getNombre());
                                    String modificar = GestorTeclado.leerString("Pulse 1) si desea actualizar su firma");
                                    
                                    // Actualiza la firma
                                    if (modificar.equals("1")) {
                                        GestorBBDD.actualizarFirma(GestorFichero.leerMensaje(), tesoro, usuario);
                                        System.out.println("Firma actualizada");
                                    }
                                // Si no ha descubierto el tesoro, realiza la acción de descubrir.
                                // Dependiendo del tipo de tesoro hará una cosa u otra.
                                } else {
                                    if (tesoro.descubrirTesoro()) {
                                        System.out.println("¡Enhorabuena! Ha encontrado el tesoro: " + tesoro.getNombre());
                                        
                                        // Una vez se haya obtenido el tesoro, se firma.
                                        if (GestorBBDD.firmar(tesoro, usuario, GestorFichero.leerMensaje())) {
                                            System.out.println("Se ha guardado su firma personal");
                                          // Si falla la firma, se muestra el error.
                                        } else {
                                            System.out.println("Error al firmar");
                                        }
                                    }
                                }
                                
                            // Si no hay tesoros en esa localización, muestra el mensaje.
                            } else {
                                System.out.println("No hay tesoros cerca");
                            }
                        }
                        // Muestra los tesoros descubiertos por el usuario.
                        case "2" -> {
                            // Si tiene tesoros, los muestra.
                            if (GestorBBDD.listarDescubiertos(usuario)) {
                                System.out.println("Tesoros descubiertos:");
                                usuario.mostrarTesoros();
                              // Si no tiene tesoros, muestra este mensaje.
                            } else {
                                System.out.println("Todavía no se ha descubierto algún tesoro");
                            }

                        }
                        // Permite modificar la firma del usuario guardado en un fichero.
                        case "3" -> {
                            System.out.println("Mensaje actual: " + GestorFichero.leerMensaje());
                            String modificar = GestorTeclado.leerString("Pulse 1) si desea modificarlo");

                            // Si desea modificarlo, modifica el fichero.
                            if (modificar.equals("1")) {
                                GestorFichero.escribirMensaje(GestorTeclado.leerString("Establezca el mensaje con su firma personal:"));
                            }
                        }
                        // Sale de la aplicación.
                        case "4" ->
                            salir = true;
                        // Si el usuario introduce otra opción no contemplada.
                        default ->
                            System.out.println("Opción no válida");
                    }
                    separador();
                }
            }
        } // Captura de excepciones críticas, que cerrarán el programa si hay problema.
        catch (IOException io) {
            GestorErrores.logError("Error al abrir el fichero", GestorErrores.getStackTrace(io));
        } catch (ClassNotFoundException notFound) {
            GestorErrores.logError("Controlador jdbc MySql no descargado", GestorErrores.getStackTrace(notFound));
        } catch (SQLException sql) {
            GestorErrores.logError("Error en la conexión a la base de datos", GestorErrores.getStackTrace(sql));
          // Finalmente borramos el fichero .tmp de errores y cerramos la conexión con la Base de datos.
        } finally {
            GestorFichero.borrarFicheroTemporal();
            GestorBBDD.cerrarConexion();
        }
    }
}
