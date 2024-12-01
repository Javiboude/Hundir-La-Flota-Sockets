package Servidor;

import UndirFlota.JuegoBarcos;
import Vista.VistaBarcos;

public class ControladorServidorTPC {
	
	//Controlador servidor
	private VistaBarcos miVista;
    private JuegoBarcos juegoBarcos;
    
    public ControladorServidorTPC() {
        super();
    }

    public void setModelo(JuegoBarcos juegoBarcos) {
        this.juegoBarcos = juegoBarcos;
    }
    
    public void setVista(VistaBarcos miVista) {
        this.miVista = miVista;
    }
}
