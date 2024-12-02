package Vista;

import javax.swing.*;

import Cliente.ControladorClienteTPC;
import Servidor.ControladorServidorTPC;
import UndirFlota.JuegoBarcos;

import java.awt.*;

public class _01_VistaBarcos extends JFrame implements Vista {

	// Damos a conocer las clases entre ellas
	private JuegoBarcos juegoBarcos;
	private ControladorClienteTPC miControladorC;
	private ControladorServidorTPC miControladorV;

	// Panel donde mostramos el tablero
	private JPanel panelTablero;
	private JLabel celda;

	@Override
	public void setModelo(JuegoBarcos juegoBarcos) {
		// TODO Auto-generated method stub
		this.juegoBarcos = juegoBarcos;
	}

	@Override
	public void setControladorC(ControladorClienteTPC miControladorC) {
		// TODO Auto-generated method stub
		this.miControladorC = miControladorC;
	}

	@Override
	public void setControladorV(ControladorServidorTPC miControladorV) {
		// TODO Auto-generated method stub
		this.miControladorV = miControladorV;
	}

	// Get content pane
	public _01_VistaBarcos(String jugador) {
		setTitle("Hundir la Flota " + jugador);
		setBounds(100, 100, 874, 644);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		// Configuración del panel del tablero.
		panelTablero = new JPanel();
		panelTablero.setLayout(new GridLayout(10, 10)); // Crear una cuadrícula de 10x10.
		getContentPane().add(panelTablero, BorderLayout.CENTER); // Agregar al centro de la vista.
	}

	public void imprimirTablero(String[][] tablero) {
		// Limpiar el panel por si se quiere actualizar.
		panelTablero.removeAll();

		// Iteramos sobre las filas
		for (int i = 0; i < tablero.length; i++) {
			// Iteramos sobre las colimnas
			for (int j = 0; j < tablero[i].length; j++) {
				// Creamos un JLabel para cada celda
				 celda = new JLabel(tablero[i][j], SwingConstants.CENTER);
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
				// Agregamos el panel
				panelTablero.add(celda);
			}
		}

		// Refrescamos la ventana
		panelTablero.revalidate();
		panelTablero.repaint();
	}
}
