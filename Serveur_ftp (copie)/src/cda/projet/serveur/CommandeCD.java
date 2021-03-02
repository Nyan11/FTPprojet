package cda.projet.serveur;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class CommandeCD extends Commande {

	public CommandeCD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		File dir = new File(CommandExecutor.userPos, commandeArgs[0]);
		ps.println("1 " + dir.getAbsolutePath());
		try {
			if(dir.exists()) {
				if(dir.isDirectory()) {
					if(dir.getCanonicalPath().startsWith(CommandExecutor.userDir.getAbsolutePath())) {
						dir.compareTo(dir);
						CommandExecutor.userPos = dir.getCanonicalFile();
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
