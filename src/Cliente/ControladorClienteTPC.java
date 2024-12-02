package Cliente;


import UndirFlota.JuegoBarcos;
import Vista._01_VistaBarcos;

public class ControladorClienteTPC {
	
	//Controlador del cliente
	private _01_VistaBarcos miVista;
    private JuegoBarcos juegoBarcos;
    
    public ControladorClienteTPC() {
        super();
    }

    public void setModelo(JuegoBarcos juegoBarcos) {
        this.juegoBarcos = juegoBarcos;
    }
    
    public void setVista(_01_VistaBarcos miVista) {
        this.miVista = miVista;
    }
}
