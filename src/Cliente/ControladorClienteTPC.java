package Cliente;


import UndirFlota.JuegoBarcos;
import Vista.VistaBarcos;

public class ControladorClienteTPC {
	
	//Controlador del cliente
	private VistaBarcos miVista;
    private JuegoBarcos juegoBarcos;
    
    public ControladorClienteTPC() {
        super();
    }

    public void setModelo(JuegoBarcos juegoBarcos) {
        this.juegoBarcos = juegoBarcos;
    }
    
    public void setVista(VistaBarcos miVista) {
        this.miVista = miVista;
    }
}
