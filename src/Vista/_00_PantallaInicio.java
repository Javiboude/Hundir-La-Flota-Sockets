package Vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import Cliente.ControladorClienteTPC;
import Servidor.ControladorServidorTPC;
import UndirFlota.JuegoBarcos;

public class _00_PantallaInicio extends JFrame implements Vista {

	// Damos a conocer las clases entre ellas
	private JuegoBarcos juegoBarcos;
	private ControladorClienteTPC miControladorC;
	private ControladorServidorTPC miControladorV;

	private JLabel lblTitulo;
	private JProgressBar progressBar;
	private JLabel lblImagen;
	
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

	public _00_PantallaInicio() {

		setResizable(false);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 918, 604);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lblTitulo = new JLabel("Undir La Flota");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 55));
		lblTitulo.setBounds(272, 19, 360, 107);
		getContentPane().add(lblTitulo);
		setLocationRelativeTo(null);

		progressBar = new JProgressBar();
		progressBar.setToolTipText("");
		progressBar.setBackground(new Color(230, 230, 230));
		progressBar.setForeground(new Color(255, 255, 255));
		progressBar.setBounds(228, 501, 448, 28);

		getContentPane().add(progressBar);

		lblImagen = new JLabel("Imagen");
		lblImagen.setIcon(new ImageIcon(_00_PantallaInicio.class.getResource("/assets/HundirLaFlota.avif")));
		lblImagen.setBounds(228, 147, 448, 311);
		getContentPane().add(lblImagen);

	}
}
