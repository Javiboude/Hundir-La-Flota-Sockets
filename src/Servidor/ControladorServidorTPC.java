package Servidor;

import UndirFlota.JuegoBarcos;
import Vista._01_VistaBarcos;

public class ControladorServidorTPC {
	
	//Controlador servidor
	private _01_VistaBarcos miVista;
    private JuegoBarcos juegoBarcos;
    
    public ControladorServidorTPC() {
        super();
    }

    public void setModelo(JuegoBarcos juegoBarcos) {
        this.juegoBarcos = juegoBarcos;
    }
    
    public void setVista(_01_VistaBarcos miVista) {
        this.miVista = miVista;
    }
}
