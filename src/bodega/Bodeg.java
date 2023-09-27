
package bodega;

import java.util.HashMap;
import java.util.Map;


public class Bodeg {
    private static Map<Integer, Producto> inventario = new HashMap<>();   
   
    public void agregarProducto(Producto producto) {
        inventario.put(producto.getId(), producto);
    }

    public Producto obtenerProducto(int id) {
        return inventario.get(id);
    }

    public void actualizarProducto(int id, int cantidad) {
        Producto producto = inventario.get(id);
        if (producto != null) {
            producto.agregarStock(cantidad);
            inventario.put(id, producto);
            System.out.println("Stock Actualizado");
        } else {
            System.out.println("Producto no encontrado en la bodega.");
        }
    }
        public void mostrarProductos() {
            
            System.out.println("Menu Disponible:");
            
            for (Map.Entry<Integer, Producto> entry : inventario.entrySet()) {
                Producto valor = entry.getValue();
                System.out.println(valor);
        }
    }
}
