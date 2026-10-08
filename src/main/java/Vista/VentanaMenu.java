package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final SessionController session;
    private final RuletaController ruletaController;
    private final JFrame frame = new JFrame("RULETA - Casino Black Cat");
    private final JLabel lblSaldo = new JLabel();

    public VentanaMenu(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel panelIzquierdo = new JPanel(new GridLayout(5, 1, 5, 5));
        JButton btnInicio = new JButton("Inicio");
        JButton btnJugar = new JButton("Jugar");
        JButton btnPerfil = new JButton("Perfil");
        JButton btnHistorial = new JButton("Historial");
        JButton btnSalir = new JButton("Salir");

        panelIzquierdo.add(btnInicio);
        panelIzquierdo.add(btnJugar);
        panelIzquierdo.add(btnPerfil);
        panelIzquierdo.add(btnHistorial);
        panelIzquierdo.add(btnSalir);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        JLabel lblBienvenida = new JLabel("Bienvenido/a " + session.getNombreUsuario() + " al menú principal");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 14));

        JTextArea txtInfo = new JTextArea(
                "A la izquierda tienes:\n" +
                        "• Jugar: abre la ventana de juego.\n" +
                        "• Perfil: gestiona tu información y saldo.\n" +
                        "• Historial: consulta el registro de tus jugadas.\n" +
                        "• Salir: cierra sesión y vuelve al login."
        );
        txtInfo.setEditable(false);
        txtInfo.setOpaque(false);

        panelCentral.add(lblBienvenida);
        panelCentral.add(Box.createVerticalStrut(10));
        panelCentral.add(txtInfo);

        refrescarSaldo();

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInferior.add(new JLabel("Usuario activo: " + session.getUsuarioActual().getUsername() + " | "));
        panelInferior.add(lblSaldo);

        frame.add(panelIzquierdo, BorderLayout.WEST);
        frame.add(panelCentral, BorderLayout.CENTER);
        frame.add(panelInferior, BorderLayout.SOUTH);

        btnJugar.addActionListener(e -> abrirJuego());
        btnPerfil.addActionListener(e -> abrirPerfil());
        btnSalir.addActionListener(e -> cerrarSesion());
        btnHistorial.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "Funcionalidad de historial disponible en futuras versiones.", "Información", JOptionPane.INFORMATION_MESSAGE)
        );

        frame.setSize(550, 320);
    }

    public void refrescarSaldo() {
        lblSaldo.setText("Saldo actual: $" + ruletaController.getSaldo());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirJuego() {
        frame.dispose();
        new VentanaRuleta(session, ruletaController).mostrarVentana();
    }

    private void abrirPerfil() {
        frame.dispose();
        new VentanaPerfil(session, ruletaController).mostrarVentana();
    }

    private void cerrarSesion() {
        session.cerrarSesion();
        frame.dispose();
        new VentanaLogin(session, ruletaController).mostrarVentana();
    }
}