package bodega;

import java.sql.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;



public class Bodega {
    ///conexion con sql
    private static java.sql.Connection con;
    public static String driver  = "com.mysql.cj.jdbc.Driver";
    public static String user = "root";
    public static String pass = "";
    public static String url = "jdbc:mysql://localhost:3306/test";
    ///Usuario y contraseña para login de trabajador
    private static final String USUARIO_VALIDO = "admin";
    private static final String CONTRASENA_VALIDA = "password";
   
    public static void main(String[] args) {
        
        Bodeg bodeg = new Bodeg();
        con = null;
        try {
            
            con =  DriverManager.getConnection(url, user, pass);
            if (con != null) {
                System.out.println("Conexion exitosa");
                }           
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error "+e);
            }
        ///Crear inventario
            bodeg.agregarProducto(new Producto(1,"Hamburguesa", 8.99, 10));
            bodeg.agregarProducto(new Producto(2,"Perro", 8.99, 10));
            bodeg.agregarProducto(new Producto(3,"Empanada", 8.99, 10));
            bodeg.agregarProducto(new Producto(4,"Pizza", 8.99, 10));
            bodeg.agregarProducto(new Producto(5,"Arepa", 8.99, 10));
            bodeg.agregarProducto(new Producto(6,"Chorizo", 8.99, 10));
            bodeg.agregarProducto(new Producto(7,"Salchipapa", 8.99, 10));
            bodeg.agregarProducto(new Producto(8,"Gaseosa", 8.99, 10));
            
            Scanner scanner = new Scanner(System.in);
            
            Trabajador trabajador = new Trabajador(USUARIO_VALIDO, CONTRASENA_VALIDA);
            ///Menu principal
            while (true) {
                System.out.println("\nMenu de Opciones:");
                System.out.println("1. Verificar los productos en bodega");
                System.out.println("2. Ingresar como trabajador");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opcion: ");

                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        // Consultar y mostrar productos en la base de datos
                        bodeg.mostrarProductos();
                        break;
                    case 2:
                        System.out.print("Ingrese su nombre de usuario: ");
                        String usuario = scanner.next();
                        System.out.print("Ingrese su contrasena: ");
                        String contrasena = scanner.next();

                        // Autenticar al trabajador
                        if (trabajador.autenticar(usuario, contrasena)) {
                            System.out.println("Bienvenido, " + usuario + "!");

                            // Operaciones que puede realizar el trabajador después de autenticarse
                            // Menu del trabajador
                            while (true) {
                                System.out.println("\nOperaciones disponibles para trabajador:");
                                System.out.println("1. Agregar stock a un producto");
                                System.out.println("2. Ver Inventario Actual");
                                System.out.println("3. Vender productos");
                                System.out.println("4. Salir como trabajador");
                                System.out.print("Seleccione una operacion: ");

                                int operacion = scanner.nextInt();

                                switch (operacion) {
                                    case 1:
                                        // Operación para agregar stock a un producto
                                        System.out.print("Ingrese el ID del producto al que desea agregar stock: ");
                                        int productoIdAgregarStock = scanner.nextInt();
                                        System.out.print("Ingrese la cantidad que desea agregar: ");
                                        int cantidadAgregar = scanner.nextInt();
                                        bodeg.actualizarProducto(productoIdAgregarStock, cantidadAgregar);
                                        break;
                                    case 2:
                                        System.out.println("Mostrando el inventario actualizado");
                                        bodeg.mostrarProductos();
                                        break;
                                    case 3:
                                        System.out.print("Ingrese el ID del producto que desea vender: ");
                                        int productoIdVender = scanner.nextInt();
                                        System.out.print("Ingrese la cantidad que desea vender: ");
                                        int cantidadVender = scanner.nextInt();

                                        Producto productoVender = bodeg.obtenerProducto(productoIdVender);
                                        if (productoVender != null) {
                                            if (cantidadVender <= productoVender.getCantidad()) {
                                                productoVender.vender(cantidadVender);
                                                System.out.println("Venta realizada con exito.");
                                            } else {
                                                System.out.println("No hay suficiente stock disponible.");
                                            }
                                        } else {
                                            System.out.println("Producto no encontrado en la bodega.");
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Saliendo del modo trabajador.");
                                        break;
                                    default:
                                        System.out.println("Operacion no valida. Por favor, seleccione una operacion valida.");
                                }

                                if (operacion == 3) {
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Credenciales incorrectas. Acceso denegado.");
                        }
                        break;
                    case 3:
                        System.out.println("Gracias por usar el simulador de bodega. ¡Hasta luego!");
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                }
            }
    }

}

