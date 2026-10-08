package Modelo;

import java.util.Random;

public class Ruleta {
    private int saldo;
    private final Random rng = new Random();
    private final int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    public Ruleta(int saldoInicial) {
        this.saldo = Math.max(0, saldoInicial);
    }

    public Ruleta() {
        this(0);
    }

    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) return true;
        }
        return false;
    }

    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        if (numero == 0) return false;

        return switch (tipo) {
            case ROJO -> esRojo(numero);
            case NEGRO -> !esRojo(numero);
            case PAR -> numero % 2 == 0;
            case IMPAR -> numero % 2 != 0;
        };
    }

    public void procesarApuesta(int monto, boolean acierto) {
        if (acierto) {
            saldo += monto;
        } else {
            saldo -= monto;
        }
    }

    public void depositar(int monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero.");
        }
        this.saldo += monto;
    }

    public int getSaldo() {
        return saldo;
    }
}