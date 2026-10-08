package Controlador;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private final List usuarios = new ArrayList<>();
    private Usuario usuarioActual;

    public SessionController() {
        usuarios.add(new Usuario("admin", "1234", "Don Donnie"));
    }

    public void registrarUsuario(String u, String p, String n) {
        if (u == null || u.isBlank() || p == null || p.isBlank() || n == null || n.isBlank()) {
            throw new IllegalArgumentException("Todos los campos son requeridos.");
        }
        for (Usuario usr : usuarios) {
            if (usr.getUsername().equalsIgnoreCase(u)) {
                throw new IllegalArgumentException("El usuario ya existe.");
            }
        }
        usuarios.add(new Usuario(u, p, n));
    }

    public boolean iniciarSesion(String u, String p) {
        for (Usuario usr : usuarios) {
            if (usr.validarCredenciales(u, p)) {
                this.usuarioActual = usr;
                return true;
            }
        }
        return false;
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}