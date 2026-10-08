package Controlador;

import Modelo.Resultado;
import java.util.ArrayList;
import java.util.List;

public class ResultadoController {
    private final List historial = new ArrayList<>();

    public void registrarResultado(Resultado resultado) {
        historial.add(resultado);
    }

    public List getHistorial() {
        return new ArrayList<>(historial);
    }
}