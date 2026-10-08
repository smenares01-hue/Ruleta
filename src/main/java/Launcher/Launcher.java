package Launcher;

import Controlador.RuletaController;
import Controlador.SessionController;
import Vista.VentanaLogin;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            SessionController session = new SessionController();
            RuletaController ruletaController = new RuletaController(1000);

            VentanaLogin login = new VentanaLogin(session, ruletaController);
            login.mostrarVentana();
        });
    }
}