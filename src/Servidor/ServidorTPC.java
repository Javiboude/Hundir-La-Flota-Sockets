package Servidor;

import java.io.IOException;

public class ServidorTPC {
	public static void main(String[] args) throws IOException {
		ServidorTCP canal = new ServidorTCP(5555);
		String linea;
		do {
			linea = canal.recibirMsg();
			System.out.println("Cliente: " + linea);
			canal.enviarMsg("Eco - " + linea);
		} while (!linea.equals("Adiós"));
		canal.closeServidorTCP();
	}
}