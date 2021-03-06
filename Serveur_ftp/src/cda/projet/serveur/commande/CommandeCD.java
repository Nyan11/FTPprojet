package cda.projet.serveur.commande;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import cda.projet.serveur.WorkerClient;

public class CommandeCD extends Commande {

	public CommandeCD(PrintStream ps, String commandeStr, WorkerClient client) {
		super(ps, commandeStr, client);
	}

	public void execute() {
		File dir = new File(client.getPos(), commandeArgs[0]);
		ps.println("1 " + dir.getAbsolutePath());
		try {
			if(dir.exists()) {
				if(dir.isDirectory()) {
					if(dir.getCanonicalPath().startsWith(client.getDir().getAbsolutePath())) {
						dir.compareTo(dir);
						client.setPos(dir.getCanonicalFile());
						ps.println("0 ok");
					}
					else {
						ps.println("2 access refused");
					}
				}
				else {
					ps.println("2 file is not a directory");
				}
			}
			else {
				ps.println("2 directory don't exist");
			}
		} catch (IOException e) {
			ps.println("2 " + "error occured, please log back");
			e.printStackTrace();
		}
	}
}
