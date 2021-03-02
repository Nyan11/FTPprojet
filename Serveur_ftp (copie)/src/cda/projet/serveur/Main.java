/*
 * TP JAVA RIP
 * Min Serveur FTP
 * */
package cda.projet.serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) throws IOException{
		System.out.println("Le Serveur FTP");
		ServerSocket serveurFTP = null;
		Socket socket = null;
		BufferedReader br = null;
		PrintStream ps = null;
		
		serveurFTP = new ServerSocket(2121);

		while(true) {
			try {
				connectClient(serveurFTP, socket, br, ps);
			} catch (NullPointerException e) {
				System.out.println("Erreur du client");
				CommandExecutor.pwOk = false;
				CommandExecutor.userOk = false;
			}
		}
	}

	public static void connectClient(ServerSocket serveurFTP, Socket socket, BufferedReader br, PrintStream ps) throws IOException, NullPointerException {
		System.out.println("Attente d'un client");

		socket = serveurFTP.accept();

		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		ps = new PrintStream(socket.getOutputStream());

		ps.println("1 Bienvenue ! ");
		ps.println("1 Serveur FTP Personnel.");
		ps.println("0 Authentification : ");

		String commande = "";

		// Attente de reception de commandes et leur execution
		while(!(commande=br.readLine()).equals("bye")) {
			System.out.println(">> "+commande);
			CommandExecutor.executeCommande(ps, commande);
		}
		ps.close();
		br.close();
		socket.close();
		serveurFTP.close();
	}

}
