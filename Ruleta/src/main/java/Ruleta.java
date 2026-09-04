import java.util.Random;
import java.util.Scanner;

     public class Ruleta {

    public static final int MAX_HISTORIAL = 100;
    public static final int CANTIDAD_NUMEROS = 37; // 0 a 36
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;

    public static Random rng = new Random();

    public static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14,
            16, 18, 19, 21, 23, 25,
            27, 30, 32, 34, 36
    };

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        } while (opcion != 3);
    }

    public static void mostrarMenu() {
        System.out.println("\n--- CASINO BLACK CAT - RULETA ---");
        System.out.println("1. Iniciar ronda");
        System.out.println("2. Ver estadísticas");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static int leerOpcion(Scanner in) {
        if (in.hasNextInt()) {
            int op = in.nextInt();
            in.nextLine(); // Limpiar búfer
            return op;
        }
        in.nextLine();
        return -1;
    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1:
                iniciarRonda(in);
                break;
            case 2:
                mostrarEstadisticas();
                break;
            case 3:
                System.out.println("¡Gracias por jugar en Casino Black Cat!");
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    public static void iniciarRonda(Scanner in) {
        if (historialSize >= MAX_HISTORIAL) {
            System.out.println("El historial está lleno. No se pueden registrar más rondas.");
            return;
        }

        char tipo = leerTipoApuesta(in);

        System.out.print("Ingrese el monto a apostar ($): ");
        int monto = in.nextInt();
        in.nextLine(); // Limpiar búfer

        int numeroObtenido = girarRuleta();
        boolean gano = evaluarResultado(numeroObtenido, tipo);

        registrarResultado(numeroObtenido, monto, gano);
        mostrarResultado(numeroObtenido, tipo, monto, gano);
    }

    public static char leerTipoApuesta(Scanner in) {
        char tipo = ' ';
        boolean valido = false;
        while (!valido) {
            System.out.print("Seleccione tipo de apuesta (R = Rojo, N = Negro, P = Par, I = Impar): ");
            String entrada = in.nextLine().trim().toUpperCase();
            if (entrada.length() > 0) {
                tipo = entrada.charAt(0);
                if (tipo == 'R' || tipo == 'N' || tipo == 'P' || tipo == 'I') {
                    valido = true;
                } else {
                    System.out.println("Tipo inválido. Ingrese R, N, P o I.");
                }
            }
        }
        return tipo;
    }

    public static int girarRuleta() {
        return rng.nextInt(CANTIDAD_NUMEROS);
    }

    public static boolean evaluarResultado(int numero, char tipo) {
        if (numero == 0) {
            return false; // El 0 no es ni par/impar ni rojo/negro en esta lógica básica
        }

        switch (tipo) {
            case 'R':
                return esRojo(numero);
            case 'N':
                return !esRojo(numero);
            case 'P':
                return numero % 2 == 0;
            case 'I':
                return numero % 2 != 0;
            default:
                return false;
        }
    }

    public static boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) {
                return true;
            }
        }
        return false;
    }

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        System.out.println("\n--- RESULTADO DE LA RONDA ---");
        System.out.println("Número obtenido: " + numero + (numero == 0 ? " (Verde)" : (esRojo(numero) ? " (Rojo)" : " (Negro)")));
        System.out.println("Tu apuesta: " + tipo + " por $" + monto);
        if (acierto) {
            System.out.println("¡FELICIDADES! Has ganado la ronda.");
        } else {
            System.out.println("Lo sentimos, has perdido la apuesta.");
        }
    }

    public static void mostrarEstadisticas() {
        if (historialSize == 0) {
            System.out.println("\nAún no se han jugado rondas.");
            return;
        }

        int montoTotalApostado = 0;
        int totalAciertos = 0;
        int ganancias = 0;

        for (int i = 0; i < historialSize; i++) {
            montoTotalApostado += historialApuestas[i];
            if (historialAciertos[i]) {
                totalAciertos++;
                ganancias += historialApuestas[i]; // Gana el doble del monto (recupera + gana)
            } else {
                ganancias -= historialApuestas[i]; // Pierde el monto apostado
            }
        }

        double porcentajeAciertos = ((double) totalAciertos / historialSize) * 100.0;

        System.out.println("\n--- ESTADÍSTICAS GENERALES ---");
        System.out.println("Rondas jugadas: " + historialSize);
        System.out.println("Monto total apostado: $" + montoTotalApostado);
        System.out.println("Cantidad total de aciertos: " + totalAciertos);
        System.out.printf("Porcentaje de aciertos: %.2f%%\n", porcentajeAciertos);
        System.out.println("Ganancia/Pérdida neta: $" + ganancias);
    }
}