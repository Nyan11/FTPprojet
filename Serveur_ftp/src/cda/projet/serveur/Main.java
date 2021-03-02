/*
 * TP JAVA RIP
 * Min Serveur FTP
 * */
package cda.projet.serveur;


public class Main {

	public static void main(String[] args) {
		System.out.println("Le Serveur FTP");
		LauncherServerFTP server = new LauncherServerFTP(2121);
		server.start();
	}
		
}
