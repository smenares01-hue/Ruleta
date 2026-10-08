package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaPerfil {
    private final SessionController session;
    private final RuletaController ruletaController;
    private final JFrame frame = new JFrame("Perfil de Usuario - Casino Black Cat");

    private final JTextField txtNombre = new JTextField(15);
    private final JLabel lblUsuarioVal = new JLabel();
    private final JLabel lblSaldoVal = new JLabel();

    public VentanaPerfil(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        Usuario usr = session.getUsuarioActual();
        lblUsuarioVal.setText(usr.getUsername());
        txtNombre.setText(usr.getNombre());
        refrescarSaldo();

        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelDatos.add(new JLabel("Usuario (Username):"));
        panelDatos.add(lblUsuarioVal);

        panelDatos.add(new JLabel("Nombre Completo:"));
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Saldo Actual:"));
        panelDatos.add(lblSaldoVal);

        JButton btnGuardarNombre = new JButton("Actualizar Nombre");
        JButton btnRecargar = new JButton("Recargar Saldo");

        panelDatos.add(btnGuardarNombre);
        panelDatos.add(btnRecargar);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnVolver = new JButton("Volver al Menú");
        panelInferior.add(btnVolver);

        frame.add(panelDatos, BorderLayout.CENTER);
        frame.add(panelInferior, BorderLayout.SOUTH);

        btnGuardarNombre.addActionListener(e -> actualizarNombre());
        btnRecargar.addActionListener(e -> recargarSaldo());
        btnVolver.addActionListener(e -> volverMenu());

        frame.pack();
    }

    private void refrescarSaldo() {
        lblSaldoVal.setText("$" + ruletaController.getSaldo());
    }

    private void actualizarNombre() {
        try {
            session.getUsuarioActual().setNombre(txtNombre.getText().trim());
            JOptionPane.showMessageDialog(frame, "Nombre actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recargarSaldo() {
        String input = JOptionPane.showInputDialog(frame, "Ingrese el monto a recargar ($):", "Recargar Saldo", JOptionPane.QUESTION_MESSAGE);
        if (input != null && !input.isBlank()) {
            try {
                int monto = Integer.parseInt(input.trim());
                ruletaController.recargarSaldo(monto);
                refrescarSaldo();
                JOptionPane.showMessageDialog(frame, "Recarga realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Por favor, ingrese un monto entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void volverMenu() {
        frame.dispose();
        new VentanaMenu(session, ruletaController).mostrarVentana();
    }
}