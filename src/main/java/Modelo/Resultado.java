package Modelo;

public class Resultado {
    private final int numero;
    private final String color;
    private final TipoApuesta tipoApuesta;
    private final boolean gano;
    private final int monto;

    public Resultado(int numero, String color, TipoApuesta tipoApuesta, boolean gano, int monto) {
        this.numero = numero;
        this.color = color;
        this.tipoApuesta = tipoApuesta;
        this.gano = gano;
        this.monto = monto;
    }

    public int getNumero() { return numero; }
    public String getColor() { return color; }
    public TipoApuesta getTipoApuesta() { return tipoApuesta; }
    public boolean isGano() { return gano; }
    public int getMonto() { return monto; }
}