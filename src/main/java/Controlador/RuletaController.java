package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;

public class RuletaController {
    private final Ruleta ruleta;

    public RuletaController(int saldoInicial) {
        this.ruleta = new Ruleta(saldoInicial);
    }

    public Resultado jugarRonda(int monto, TipoApuesta tipo) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero.");
        }
        if (monto > ruleta.getSaldo()) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar esta apuesta.");
        }

        int numero = ruleta.girarRuleta();
        boolean gano = ruleta.evaluarResultado(numero, tipo);
        ruleta.procesarApuesta(monto, gano);

        String color = (numero == 0) ? "Verde" : (ruleta.esRojo(numero) ? "Rojo" : "Negro");
        return new Resultado(numero, color, tipo, gano, monto);
    }

    public void recargarSaldo(int monto) {
        ruleta.depositar(monto);
    }

    public int getSaldo() {
        return ruleta.getSaldo();
    }
}