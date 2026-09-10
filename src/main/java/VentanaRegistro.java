import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final JFrame frame = new JFrame("Registro de Jugador - Casino Black Cat");
    private final JLabel lblNombre = new JLabel("Nombre Completo:");
    private final JTextField txtNombre = new JTextField(15);
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField(15);
    private final JLabel lblClave = new JLabel("Contraseña:");
    private final JPasswordField txtClave = new JPasswordField(15);
    private final JButton btnGuardar = new JButton("Registrar");
    private final JButton btnVolver = new JButton("Volver al Login");

    public VentanaRegistro() {
        frame.setLayout(new GridLayout(4, 2, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(lblNombre);
        frame.add(txtNombre);
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(btnGuardar);
        frame.add(btnVolver);

        btnGuardar.addActionListener(e -> registrarUsuario());
        btnVolver.addActionListener(e -> volverLogin());

        frame.pack();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());

        if (nombre.isEmpty() || usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        VentanaLogin.USUARIOS.add(new Usuario(usuario, clave, nombre));
        JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

        volverLogin();
    }

    private void volverLogin() {
        frame.dispose();
        VentanaLogin login = new VentanaLogin();
        login.mostrarVentana();
    }
}