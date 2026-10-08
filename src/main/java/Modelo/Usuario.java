package Modelo;
public class Usuario {
    private String username;
    private String password;
    private String nombre;

    // Constructor con parámetros[cite: 28]
    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    // Constructor sin parámetros (Usuario invitado por defecto)[cite: 28]
    public Usuario() {
        this("invitado", "1234", "Invitado");
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public String getUsername() { return username; }

    public String getNombre() { return nombre; }

    // Setter validado para el nombre[cite: 28]
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getPassword() { return password; }
}