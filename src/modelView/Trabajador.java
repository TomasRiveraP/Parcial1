
package modelView;

public class Trabajador {
    private String nombreUsuario;
    private String contrasena;

    public Trabajador(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public boolean autenticar(String nombreUsuario, String contrasena) {
        return this.nombreUsuario.equals(nombreUsuario) && this.contrasena.equals(contrasena);
    }
}
