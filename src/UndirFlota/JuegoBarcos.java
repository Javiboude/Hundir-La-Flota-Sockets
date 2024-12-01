package UndirFlota;

import java.util.Scanner;

import Cliente.ControladorClienteTPC;
import Vista.VistaBarcos;

public class JuegoBarcos {

	// Damos a conocer las clases entre ellas
	private VistaBarcos miVista;
	private ControladorClienteTPC miControlador;

	public JuegoBarcos() {
		super();
	}

	public void setModelo(ControladorClienteTPC miControlador) {
		this.miControlador = miControlador;
	}

	public void setVista(VistaBarcos miVista) {
		this.miVista = miVista;
	}

	// Llenamos las filas del tablero con agua
	public static void RellenarTablero(String[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = "🌊";
			}
		}

		//Colocamos 10 barcos en posiciones aleatorias
		for (int i = 0; i < 10; i++) {
			int numX = (int) (Math.random() * 10) + 1;
			int numY = (int) (Math.random() * 10) + 1;
			if (tablero[numX - 1][numY - 1].equals("🌊")) {
				tablero[numX - 1][numY - 1] = "🚤";
			} else {
				i--;
			}
		}
	}

	//Comprobamos si la coordenada coincide com un barco
	public static boolean ComprobarCoordenada(String coordenada, String[][] tablero) {
		int x = Integer.parseInt(coordenada.split(" ")[0]);
		int y = Integer.parseInt(coordenada.split(" ")[1]);
		x--;
		y--;

		if (tablero[x][y].equals("🚤")) {
			tablero[x][y] = "💥";
			return true;
		} else {

			return false;
		}
	}
	
	//Leemos la coordenada entroducida
	public String leerCoordenadaConsola(String jugador) {
		Scanner sc = new Scanner(System.in);
		System.out.println(jugador + " introduce las coordenadas de disparo o 'Fin' para terminar:");
		return sc.nextLine();
	}

}
