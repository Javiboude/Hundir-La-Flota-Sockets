package Cliente;

import java.io.IOException;
import java.util.Scanner;

import UndirFlota.JuegoBarcos;

public class HundirLaFlotaClienteTCP {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String[][] tablero = new String[10][10];
        boolean juegoTerminado = false;

        ClienteTCP canal = new ClienteTCP("localhost", 5556);
        JuegoBarcos barcos = new JuegoBarcos();

        barcos.RellenarTablero(tablero);
        barcos.imprimirTablero(tablero);

        while (!juegoTerminado) {
            System.out.println("Es tu turno, escribe las coordenadas (ejemplo: 5 3) o 'Fin' para terminar:");
            String linea = sc.nextLine();
            canal.enviarMsg(linea);

            if (linea.equalsIgnoreCase("Fin")) {
                System.out.println("Terminando el juego...");
                juegoTerminado = true;
                break;
            }

            String respuestaServidor = canal.recibirMsg();
            System.out.println("Servidor: " + respuestaServidor);

            if (respuestaServidor.equals("Fin")) {
                System.out.println("¡El servidor ha terminado el juego!");
                juegoTerminado = true;
                break;
            }

            System.out.println("Esperando el disparo del servidor...");
            String coordenadaServidor = canal.recibirMsg();
            System.out.println("Servidor dispara a " + coordenadaServidor);

            boolean barcoHundido = barcos.ComprobarCoordenada(coordenadaServidor, tablero);
            barcos.imprimirTablero(tablero);

            if (barcoHundido) {
                System.out.println("El servidor hundió uno de tus barcos.");
                canal.enviarMsg("Fin");
                juegoTerminado = true;
            } else {
                canal.enviarMsg("Has fallado");
            }
        }

        canal.closeClienteTCP();
        sc.close();
    }
}