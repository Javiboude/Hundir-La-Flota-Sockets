package Vista;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaBarcos extends JFrame {

	public void imprimirTablero(String[][] tablero) {
		setTitle("Hundir la flota");
		setBounds(100, 100, 874, 644);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 860, 607);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		getContentPane().setLayout(new GridLayout(10, 10)); // 10x10 tablero
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JButton boton = new JButton(tablero[i][j]);
				boton.setFont(new Font("Arial", Font.PLAIN, 24));
				boton.setFocusPainted(false);
				boton.setEnabled(false); // Deshabilitar interacción con los botones
				getContentPane().add(boton);
			}
		}
	}
}
