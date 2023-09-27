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
/*class Productos {
    private int id;
    private String nombre;

    public Productos(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}

class Prod {
    private int id;
    private String nombre;
    private double precio;

    public Prod(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}

class Pedido {
    final String tabla = "PEDIDOS";
    private Map<Integer, Prod> prods = new HashMap<>();
    private double costoTotal = 0;

    public void agregarProductos(Prod prod) {
        prods.put(prod.getId(), prod);
        costoTotal += prod.getPrecio();
    }

    public Map<Integer, Prod> obtenerProductos() {
        return prods;
    }

    public double obtenerCostoTotal() {
        return costoTotal;
    }
}

class Login {
    private static final String USUARIO_VALIDO = "admin";
    private static final String CONTRASENA_VALIDA = "password";

    public boolean autenticar(String usuario, String contrasena) {
        return USUARIO_VALIDO.equals(usuario) && CONTRASENA_VALIDA.equals(contrasena);
    }
}

public class Bodega {
    public static String driver  = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Map<Integer, Productos> lProductos = new HashMap<>();
    private Map<Integer, Pedido> pedidos = new HashMap<>();
    private int proximoNumeroPedido = 1;

    public static void main(String[] args) {
        Bodega bodega = new Bodega();
        Login login = new Login();

        // Iniciar sesión
        Scanner scanner = new Scanner(System.in);
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        if (login.autenticar(usuario, contrasena)) {
            System.out.println("Inicio de sesión exitoso.");
            bodega.iniciar();
        } else {
            System.out.println("Inicio de sesión fallido. Usuario o contraseña incorrectos.");
        }
        scanner.close();
    }

    private void iniciar() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            cargarMenusDesdeBD(conn);
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenuPrincipal();
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            if (opcion == 1) {
                mostrarMenusDisponibles();
            } else if (opcion == 2) {
                realizarPedido();
            } else if (opcion == 3) {
                System.out.println("Gracias por utilizar el Simulador de Restaurante.");
                break;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }

        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        System.out.println("Simulador de Restaurante");
        System.out.println("1. Ver Menús Disponibles");
        System.out.println("2. Realizar Pedido");
        System.out.println("3. Salir");
    }

    private void mostrarMenusDisponibles() {
        System.out.println("Menús Disponibles:");
        for (Productos producto : lProductos.values()) {
            System.out.println(producto.getId() + ". " + producto.getNombre());
        }
    }

    private void cargarMenusDesdeBD(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM menus");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Productos producto = new Productos(id, nombre);
                lProductos.put(id,producto);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error al cargar los menús desde la base de datos: " + e.getMessage());
        }
    }

    private void realizarPedido() {
        Scanner scanner = new Scanner(System.in);
        mostrarMenusDisponibles();
        System.out.print("Seleccione un menú: ");
        int seleccionMenu = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        Productos menuSeleccionado = lProductos.get(seleccionMenu);

        Map<Integer, Prod> productosBodega = cargarProductosDesdeBD(menuSeleccionado.getId());
        Pedido pedido = new Pedido();

        while (true) {
            System.out.println("Platos disponibles en el menú '" + menuSeleccionado.getNombre() + "':");
            for (Prod prod : productosBodega.values()) {
                System.out.println(prod.getId() + ". " + prod.getNombre() + " - $" + prod.getPrecio());
            }

            System.out.print("Seleccione un plato (0 para terminar): ");
            int seleccionProducto = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            if (seleccionProducto == 0) {
                break;
            }

            Prod prodSeleccionado = productosBodega.get(seleccionProducto);
            pedido.agregarProductos(prodSeleccionado);
            System.out.println("Plato '" + prodSeleccionado.getNombre() + "' agregado al pedido.");
        }

        pedidos.put(proximoNumeroPedido, pedido);
        System.out.println("Pedido #" + proximoNumeroPedido + " registrado. Costo total: $" + pedido.obtenerCostoTotal());
        proximoNumeroPedido++;
    }

    private Map<Integer, Prod> cargarProductosDesdeBD(int menuId) {
        Map<Integer, Prod> prod = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM platos WHERE menu_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                Prod plato = new Prod(id, nombre, precio);
                prod.put(id, plato);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("Error al cargar los platos desde la base de datos: " + e.getMessage());
        }

        return prod;
    }
}*/
