package Vista;

import javax.swing.*;

import Cliente.ControladorClienteTPC;
import Servidor.ControladorServidorTPC;
import UndirFlota.JuegoBarcos;

import java.awt.*;

public class VistaBarcos extends JFrame {

	// Damos a conocer las clases entre ellas
	private ControladorClienteTPC miControladorC;
	private JuegoBarcos juegoBarcos;
	private ControladorServidorTPC miControladorV;

	public void setModelo(JuegoBarcos juegoBarcos) {
		this.juegoBarcos = juegoBarcos;
	}

	public void setControladorC(ControladorClienteTPC miControladorC) {
		this.miControladorC = miControladorC;
	}

	public void setControladorV(ControladorServidorTPC miControladorV) {
		this.miControladorV = miControladorV;
	}

	// Panel donde mostramos el tablero
	private JPanel panel;

	// Get content pane
	public VistaBarcos(String jugador) {
		setTitle("Hundir la Flota " + jugador);
		setBounds(100, 100, 874, 644);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		// Configuración del panel del tablero.
		panel = new JPanel();
		panel.setLayout(new GridLayout(10, 10)); // Crear una cuadrícula de 10x10.
		getContentPane().add(panel, BorderLayout.CENTER); // Agregar al centro de la vista.
	}

	public void imprimirTablero(String[][] tablero) {
		// Limpiar el panel por si se quiere actualizar.
		panel.removeAll();

		// Iteramos sobre las filas
		for (int i = 0; i < tablero.length; i++) {
			// Iteramos sobre las colimnas
			for (int j = 0; j < tablero[i].length; j++) {
				// Creamos un JLabel para cada celda
				JLabel celda = new JLabel(tablero[i][j], SwingConstants.CENTER);
				// Borde para distinguir las celdas.
				celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				celda.setOpaque(true);

				// Configurar color de fondo según el contenido
				switch (tablero[i][j]) {
				case "🌊":
					celda.setBackground(Color.CYAN);
					break;
				case "🚤":
					celda.setBackground(Color.GRAY);
					break;
				case "💥":
					celda.setBackground(Color.RED);
					break;
				default:
					celda.setBackground(Color.WHITE);
					break;
				}
				//Agregamos el panel
				panel.add(celda);
			}
		}

		// Refrescamos la ventana
		panel.revalidate();
		panel.repaint();
	}
}
