package modelView;

import java.util.HashMap;
import java.util.Map;

public class Bodeg {
    private static Map<Integer, Producto> inventario = new HashMap<>();   
    ///static FormularioBod inven = new FormularioBod();
   
    public Bodeg (){
        inventario = new HashMap<>();
    }
    
    public void agregarProducto(Producto producto) {
        inventario.put(producto.getId(), producto);
    }

    public static Producto obtenerProducto(int id) {
        return inventario.get(id);
    }

    public Map<Integer, Producto> actualizarProducto(int id, int cantidad) {
        Producto producto = inventario.get(id);
        if (producto != null) {
            producto.agregarStock(cantidad);
            inventario.put(id, producto);
        } else {
            
        }
        return inventario;
    }
    public Map<Integer, Producto> mostrarProductos() {
        return inventario;
    }
    public Map<Integer, Producto> venderProducto(int id, int cantidad) {
        Producto productoVender = obtenerProducto(id);
        if (productoVender != null) {
            if (cantidad <= productoVender.getCantidad()) {
                productoVender.vender(cantidad);
                System.out.println("Venta realizada con exito.");
            } else {
                System.out.println("No hay suficiente stock disponible.");
                }
            } else {
                System.out.println("Producto no encontrado en la bodega.");
            }
        return inventario;
    }
}
