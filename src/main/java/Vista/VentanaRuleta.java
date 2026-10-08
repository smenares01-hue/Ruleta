package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.Resultado;
import Modelo.TipoApuesta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Casino Black Cat - Juego de Ruleta");
    private final SessionController session;
    private final RuletaController ruletaController;

    private final JComboBox cboTipoApuesta = new JComboBox<>(TipoApuesta.values());
    private final JTextField txtMonto = new JTextField("100", 8);
    private final JButton btnGirar = new JButton("Girar");
    private final JButton btnVolver = new JButton("Volver al Menú");
    private final JLabel lblSaldo = new JLabel();
    private final JLabel lblResultado = new JLabel("Realice su apuesta y presione Girar.");

    public VentanaRuleta(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel panelControles = new JPanel(new FlowLayout());
        panelControles.add(new JLabel("Tipo de apuesta:"));
        panelControles.add(cboTipoApuesta);
        panelControles.add(new JLabel("Monto ($):"));
        panelControles.add(txtMonto);
        panelControles.add(btnGirar);
        panelControles.add(lblSaldo);

        JPanel panelResultado = new JPanel(new FlowLayout());
        lblResultado.setFont(new Font("Arial", Font.BOLD, 12));
        panelResultado.add(lblResultado);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.add(btnVolver);

        frame.add(panelControles, BorderLayout.NORTH);
        frame.add(panelResultado, BorderLayout.CENTER);
        frame.add(panelInferior, BorderLayout.SOUTH);

        refrescarSaldo();

        btnGirar.addActionListener(e -> ejecutarGiro());
        btnVolver.addActionListener(e -> volverAlMenu());

        frame.pack();
        frame.setSize(620, 200);
    }

    private void refrescarSaldo() {
        lblSaldo.setText("Saldo: $" + ruletaController.getSaldo());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void ejecutarGiro() {
        try {
            int monto = Integer.parseInt(txtMonto.getText().trim());
            TipoApuesta tipo = (TipoApuesta) cboTipoApuesta.getSelectedItem();

            Resultado res = ruletaController.jugarRonda(monto, tipo);

            String estado = res.isGano() ? "¡GANASTE!" : "PERDISTE";
            lblResultado.setText("Número: " + res.getNumero() + " (" + res.getColor() + ") | Apuesta: " + res.getTipoApuesta() + " | " + estado);
            refrescarSaldo();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, ingrese un monto numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error de Apuesta", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void volverAlMenu() {
        frame.dispose();
        new VentanaMenu(session, ruletaController).mostrarVentana();
    }
}