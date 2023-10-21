
package modelView;


public class Producto {
    final String tabla = "estudiante";
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
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

    public int getCantidad() {
        return cantidad;
    }

    public void agregarStock(int cantidad) {
        this.cantidad += cantidad;
    }

    public void vender(int cantidad) {
        if (cantidad <= this.cantidad) {
            this.cantidad -= cantidad;
        } else {
            System.out.println("No hay suficiente stock disponible.");
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + "\nPrecio: $" + precio + "\nCantidad en Stock: " + cantidad + "\n";
    }
}
