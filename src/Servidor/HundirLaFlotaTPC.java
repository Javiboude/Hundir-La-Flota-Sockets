package Servidor;

import java.io.*;
import java.net.*;

class ServidorTCP {
	private Socket socketCliente;
	private ServerSocket socketServidor;
	private BufferedReader entrada;
	private PrintWriter salida;

	public ServidorTCP(int puerto) {
		this.socketCliente = null;
		this.socketServidor = null;
		this.entrada = null;
		this.salida = null;
		try {
			socketServidor = new ServerSocket(puerto);
			System.out.println("Esperando conexión...");
			socketCliente = socketServidor.accept();
			System.out.println("Conexión acceptada: " + socketCliente);
			entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
		} catch (IOException e) {
			System.out.println("No puede escuchar en el puerto: " + puerto);
			System.exit(-1);
		}
	}

	public void closeServidorTCP() {
		try {
			salida.close();
			entrada.close();
			socketCliente.close();
			socketServidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-> Servidor Terminado");
	}

	public String recibirMsg() {
		String linea = "";
		try {
			linea = entrada.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linea;
	}

	public void enviarMsg(String linea) {
		salida.println(linea);
	}

}