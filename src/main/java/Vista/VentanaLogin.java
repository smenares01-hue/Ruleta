package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin {
    private final SessionController session;
    private final RuletaController ruletaController;

    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField(15);
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField(15);
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registrarse");

    public VentanaLogin(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;

        frame.setLayout(new GridLayout(4, 2, 10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(btnIngresar);
        frame.add(btnRegistrar);

        btnIngresar.addActionListener(e -> intentarLogin());
        btnRegistrar.addActionListener(e -> abrirRegistro());

        frame.pack();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void intentarLogin() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtClave.getPassword());

        if (session.iniciarSesion(user, pass)) {
            JOptionPane.showMessageDialog(frame, "¡Bienvenido/a " + session.getNombreUsuario() + "!", "Acceso Concedido", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            new VentanaMenu(session, ruletaController).mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirRegistro() {
        frame.dispose();
        new VentanaRegistro(session, ruletaController).mostrarVentana();
    }
}
