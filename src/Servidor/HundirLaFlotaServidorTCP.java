package Servidor;

import java.io.IOException;

import Cliente.ControladorClienteTPC;
import UndirFlota.JuegoBarcos;
import Vista.VistaBarcos;

public class HundirLaFlotaServidorTCP {
	public static void main(String[] args) throws IOException {
		// Inicializamos el servidor en el puerto 5556
		ServidorTCP canal = new ServidorTCP(5556);

		// Creamos el modelo con la lógica del juego
		JuegoBarcos juegoBarcos = new JuegoBarcos();

		// Creamos la interfaz gráfica para el jugador 2
		VistaBarcos miVista = new VistaBarcos("Jugador2");

		// Creamos el controlador del servidor
		ControladorServidorTPC miControlador = new ControladorServidorTPC();

		// Tablero de 10x10
		String[][] tablero = new String[10][10];

		// Variable que indica si el juego ha terminado
		boolean juegoTerminado = false;

		// Establecemos las relaciones entre modelo, vista y controlador
		miControlador.setVista(miVista);
		miControlador.setModelo(juegoBarcos);
		juegoBarcos.setVista(miVista);
		miVista.setModelo(juegoBarcos);
		miVista.setControladorV(miControlador);

		// Mostramos la interfaz gráfica
		miVista.setVisible(true);

		// Rellenamos y mostramos el tablero inicial
		juegoBarcos.RellenarTablero(tablero);
		miVista.imprimirTablero(tablero);

		// Bucle principal del juego
		while (!juegoTerminado) {
			System.out.println("Turno del jugador1");
			String coordenadaCliente = canal.recibirMsg();

			// Si el cliente envía "Fin", el juego termina
			if (coordenadaCliente.equalsIgnoreCase("Fin")) {
				System.out.println("El Jugador1 ha finalizado el juego.");
				juegoTerminado = true;
				break;
			}

			// Comprobamos si el disparo del cliente hundió un barco
			boolean barcoHundido = juegoBarcos.ComprobarCoordenada(coordenadaCliente, tablero);

			// Actualizamos la interfaz gráfica con el estado del tablero
			miVista.imprimirTablero(tablero);

			if (barcoHundido) {
				System.out.println("El Jugador1 ha hundido un barco.");
				canal.enviarMsg("Hundido");
				juegoTerminado = true;
				break;
			} else {
				canal.enviarMsg("Has fallado");
			}

			// Turno del servidor para disparar
			String disparoServidor = juegoBarcos.leerCoordenadaConsola("Jugador2");
			canal.enviarMsg(disparoServidor);

			// Comprobamos la respuesta del cliente
			String respuestaCliente = canal.recibirMsg();
			System.out.println("Respuesta del Jugador1: " + respuestaCliente);

			if (disparoServidor.equalsIgnoreCase("Fin")) {
				System.out.println("Has acabado el juego.");
				juegoTerminado = true;
			} else if (respuestaCliente.equalsIgnoreCase("Hundido")) {
				System.out.println("El Jugador1 ha hundido uno de tus barcos.");
			}
		}

		// Cerramos el servidor
		canal.closeServidorTCP();
	}
}
