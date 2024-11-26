package Cliente;

import java.io.IOException;
import java.util.Scanner;

public class HundirLaFlotaTPC {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String linea;
		ClienteTCP canal = new ClienteTCP("localhost", 5555);
		System.out.println("Comience a escribir ('Adiós') para terminar");
		do {
			linea = sc.nextLine();
			canal.enviarMsg(linea);
			System.out.println("Servidor: " + canal.recibirMsg());
		} while (!linea.equals("Adiós"));
		canal.closeClienteTCP();
	}
}