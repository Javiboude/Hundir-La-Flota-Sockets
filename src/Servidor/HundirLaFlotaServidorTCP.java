package Servidor;

import java.io.IOException;

import UndirFlota.JuegoBarcos;

public class HundirLaFlotaServidorTCP {
    public static void main(String[] args) throws IOException {
        String[][] tablero = new String[10][10];
        boolean juegoTerminado = false;

        ServidorTCP canal = new ServidorTCP(5556);
        JuegoBarcos barcos = new JuegoBarcos();
        

        barcos.RellenarTablero(tablero);
        barcos.imprimirTablero(tablero);

        while (!juegoTerminado) {
            System.out.println("Esperando disparo del cliente...");
            String coordenadaCliente = canal.recibirMsg();

            if (coordenadaCliente.equalsIgnoreCase("Fin")) {
                System.out.println("El cliente terminó el juego.");
                juegoTerminado = true;
                canal.enviarMsg("Fin");
                break;
            }

            boolean barcoHundido = barcos.ComprobarCoordenada(coordenadaCliente, tablero);
            barcos.imprimirTablero(tablero);

            if (barcoHundido) {
                System.out.println("El cliente hundió uno de tus barcos.");
                canal.enviarMsg("Fin");
                juegoTerminado = true;
                break;
            } else {
                canal.enviarMsg("Has fallado");
            }

            System.out.println("Es tu turno, dispara:");
            String disparoServidor = barcos.leerCoordenadaConsola();
            canal.enviarMsg(disparoServidor);

            String respuestaCliente = canal.recibirMsg();
            System.out.println("Cliente: " + respuestaCliente);

            if (respuestaCliente.equals("Fin")) {
                System.out.println("El cliente terminó el juego.");
                juegoTerminado = true;
            }
        }

        canal.closeServidorTCP();
    }
}
