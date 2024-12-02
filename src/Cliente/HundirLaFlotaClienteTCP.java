package Cliente;

import java.io.IOException;
import java.util.Scanner;

import Servidor.ControladorServidorTPC;
import UndirFlota.JuegoBarcos;
import Vista._01_VistaBarcos;

public class HundirLaFlotaClienteTCP {
	public static void main(String[] args) throws IOException {
		// Conectamos al servidor en localhost y puerto 5556
		ClienteTCP canal = new ClienteTCP("localhost", 5556);

		// Creamos el modelo con la lógica del juego
		JuegoBarcos juegoBarcos = new JuegoBarcos();

		// Creamos la interfaz gráfica para el cliente (Jugador1)
		_01_VistaBarcos miVista = new _01_VistaBarcos("Jugador1");

		// Creamos el controlador del cliente
		ControladorClienteTPC miControlador = new ControladorClienteTPC();

		// Tablero de 10x10
		String[][] tablero = new String[10][10];

		// Variable que indica si el juego ha terminado
		boolean juegoTerminado = false;

		// Escáner para entrada de datos por consola
		Scanner sc = new Scanner(System.in);

		// Establecemos las relaciones entre modelo, vista y controlador
		miControlador.setVista(miVista);
		miControlador.setModelo(juegoBarcos);
		juegoBarcos.setVista(miVista);
		miVista.setModelo(juegoBarcos);
		miVista.setControladorC(miControlador);

		// Mostramos la interfaz gráfica
		miVista.setVisible(true);

		// Rellenamos y mostramos el tablero inicial
		juegoBarcos.RellenarTablero(tablero);
		miVista.imprimirTablero(tablero);

		// Bucle principal del juego
		while (!juegoTerminado) {
			// Turno del jugador para disparar
			String disparoServidor = juegoBarcos.leerCoordenadaConsola("Jugador1");
			canal.enviarMsg(disparoServidor);

			// Si el jugador introduce "Fin", el juego termina
			if (disparoServidor.equalsIgnoreCase("Fin")) {
				System.out.println("Finalizando el juego...");
				juegoTerminado = true;
				break;
			}

			// Comprobamos la respuesta del servidor
			String respuestaServidor = canal.recibirMsg();
			System.out.println("Respuesta del jugador2: " + respuestaServidor);

			if (respuestaServidor.equalsIgnoreCase("Hundido")) {
				System.out.println("Enorabuena, hundiste uno de sus barcos");
				juegoTerminado = true;
				break;
			}

			// Esperamos el disparo del servidor
			System.out.println("Turno del jugador2");
			String coordenadaServidor = canal.recibirMsg();

			// Comprobamos si el disparo del servidor hundió un barco
			boolean barcoHundido = juegoBarcos.ComprobarCoordenada(coordenadaServidor, tablero);
			miVista.imprimirTablero(tablero);

			if (barcoHundido) {
				System.out.println("¡El jufador2 hundió uno de tus barcos!");
				canal.enviarMsg("Hundido");
				juegoTerminado = true;

			} else if (coordenadaServidor.equalsIgnoreCase("Fin")) {
				System.out.println("El Jugador2 ha finalizado el juego.");
				juegoTerminado = true;
				break;
			} else {
				canal.enviarMsg("Has fallado");
			}
		}

		// Cerramos la conexión con el servidor
		canal.closeClienteTCP();
		sc.close();
	}
}
