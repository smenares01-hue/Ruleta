package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final SessionController session;
    private final RuletaController ruletaController;

    private final JFrame frame = new JFrame("Registro de Jugador - Casino Black Cat");
    private final JLabel lblNombre = new JLabel("Nombre Completo:");
    private final JTextField txtNombre = new JTextField(15);
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField(15);
    private final JLabel lblClave = new JLabel("Contraseña:");
    private final JPasswordField txtClave = new JPasswordField(15);
    private final JButton btnGuardar = new JButton("Registrar");
    private final JButton btnVolver = new JButton("Volver al Login");

    public VentanaRegistro(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;

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
        try {
            String nombre = txtNombre.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String clave = new String(txtClave.getPassword());

            session.registrarUsuario(usuario, clave, nombre);
            JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            volverLogin();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void volverLogin() {
        frame.dispose();
        new VentanaLogin(session, ruletaController).mostrarVentana();
    }
}