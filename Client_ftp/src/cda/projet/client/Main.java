/*
 * TP JAVA RIP
 * Min Client FTP
 * */
package cda.projet.client;

import java.io.IOException;
import java.net.UnknownHostException;


import cda.projet.client.logic.CommTerminalApp;

public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Le Client FTP");
		CommTerminalApp app = new CommTerminalApp("localhost", 2121);
		app.start();
	}
}
