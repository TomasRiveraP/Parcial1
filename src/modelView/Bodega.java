package modelView;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.FormularioBod;
import view.FormularioTra;

public class Bodega {
    ///conexion con sql
    private static java.sql.Connection con;
    public static String driver  = "com.mysql.cj.jdbc.Driver";
    public static String user = "root";
    public static String pass = "";
    public static String url = "jdbc:mysql://localhost:3306/test";
    ///Usuario y contraseña para login de trabajador
    public static final String USUARIO_VALIDO = "admin";
    public static final String CONTRASENA_VALIDA = "password";
    
    static FormularioBod Bodega = new FormularioBod();
    public static Bodeg inventario = new Bodeg();
    static FormularioTra Trab = new FormularioTra();
    
    public static void vTrabajador() {
        Trab.setVisible(true);
    }
    
    public static void cerrarT() {
        Trab.setVisible(false);
    } 
    public static void cerrar() {
        Bodega.setVisible(false);
    } 
   
    public static void main(String[] args) {
        
        Bodega.setVisible(true);
        
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
                  
    }
}

