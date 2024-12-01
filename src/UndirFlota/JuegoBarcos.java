package UndirFlota;

import java.util.Scanner;


public class JuegoBarcos{

	public static void RellenarTablero(String[][] tablero) {
		// Se llena el tablero con agua
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = "🌊";
			}
		}

		// Se colocan 10 barcos en posiciones aleatorias
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

	// Método que comprueba si una coordenada contiene un barco
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

	public void imprimirTablero(String[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]);
			}
			System.out.println();
		}
	}
	
	
	
	public String leerCoordenadaConsola() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce las coordenadas de disparo (ejemplo: 5 3):");
		return sc.nextLine();
	}
}
