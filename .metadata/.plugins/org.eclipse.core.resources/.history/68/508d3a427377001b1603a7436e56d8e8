package cda.projet.serveur;

import java.io.IOException;
import java.io.PrintStream;

public class CommandePWD extends Commande {
	
	public CommandePWD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		String s;
		try {
			s = CommandExecutor.userPos.getCanonicalPath().toString();
			ps.println("0 " + s);
		} catch (IOException e) {
			ps.println("2 " + "error occured, please log back");
			e.printStackTrace();
		}
	}

}
