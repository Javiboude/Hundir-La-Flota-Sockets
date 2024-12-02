package Vista;

import Cliente.ControladorClienteTPC;
import Servidor.ControladorServidorTPC;
import UndirFlota.JuegoBarcos;

public interface Vista {
	
	public void setModelo(JuegoBarcos juegoBarcos);
	public void setControladorC(ControladorClienteTPC miControladorC);
	public void setControladorV(ControladorServidorTPC miControladorV);

}