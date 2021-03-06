package cda.projet.serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

import cda.projet.serveur.commande.CommandExecutor;

public class WorkerClient implements Runnable {
	
	public static final File USERS_DIR = new File("users");
	protected File userDir      = null;
	protected File userPos      = null;
	protected boolean userOk    = false;
	protected boolean pwOk      = false;
	
	private Socket clientSocket;

	public WorkerClient(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public void run() {
		BufferedReader br = null;
		PrintStream ps = null;
		String commande = "";
		try {
			try {
				br = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
				ps = new PrintStream(this.clientSocket.getOutputStream());

				ps.println("1 Bienvenue ! ");
				ps.println("1 Serveur FTP Personnel.");
				ps.println("0 Authentification : ");

				while(!(commande=br.readLine()).equals("bye")) {
					System.out.println(">> "+commande);
					CommandExecutor.executeCommande(ps, commande, this);
				}

				ps.close();
				br.close();
				System.out.println("Deconnexion du client");
			} catch (NullPointerException e) {
				System.out.println("Deconnexion du client (Null Pointer Exception)");
				ps.close();
				br.close();
				this.clientSocket.close();
			} catch (SocketException e) {
				System.out.println("Deconnexion du client (Socket Exception");
				ps.close();
				br.close();
				this.clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isConnected() {
		return this.userOk && this.pwOk;
	}
	
	public void setDir(File dir) {
		this.userDir = dir;
	}
	public File getDir() {
		return this.userDir;
	}
	public void setPos(File dir) {
		this.userPos = dir;
	}
	public File getPos() {
		return this.userPos;
	}
	public void logUser() {
		this.userOk = true;
		this.pwOk = false;
	}
	public void logPw() {
		this.pwOk = true;
	}
	public boolean isUserOk() {
		return this.userOk;
	}
}
