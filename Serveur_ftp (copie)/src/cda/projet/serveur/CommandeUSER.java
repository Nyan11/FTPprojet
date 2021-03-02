package cda.projet.serveur;

import java.io.PrintStream;
import java.io.File;
import java.util.Arrays;

public class CommandeUSER extends Commande {


	public CommandeUSER(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		for(String pathname : CommandExecutor.USERS_DIR.list()) {
            ps.println("1 " + pathname);
        }
		if(Arrays.asList(CommandExecutor.USERS_DIR.list()).contains(commandeArgs[0].toLowerCase())) {
			CommandExecutor.userOk = true;
			CommandExecutor.userDir = new File(CommandExecutor.USERS_DIR, commandeArgs[0].toLowerCase() + "/");
			CommandExecutor.userPos = new File(CommandExecutor.userDir, "");
			ps.println("0 Commande user OK");
		}
		else {
			ps.println("2 Le user " + commandeArgs[0] + " n'existe pas");
		}
	}
}