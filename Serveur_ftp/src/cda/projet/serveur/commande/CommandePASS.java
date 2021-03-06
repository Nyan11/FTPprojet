package cda.projet.serveur.commande;

import java.io.PrintStream;

import cda.projet.serveur.WorkerClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CommandePASS extends Commande {

    public CommandePASS(PrintStream ps, String commandeStr, WorkerClient client) {
		super(ps, commandeStr, client);
	}

    public void execute() {
        if(client.isUserOk()) {
            try {
                File passFile = new File(client.getDir(), ".pass");
                BufferedReader br = new BufferedReader(new FileReader(passFile));
                if(commandeArgs[0].equals(br.readLine())) {
                	client.logPw();
                    ps.println("1 Commande pass OK");
                    ps.println("0 Vous êtes bien connecté sur notre serveur");
                }
                else {
                    ps.println("2 Le mot de passe est faux");
                }
                br.close();
            }
            catch(FileNotFoundException fnfe) {
                System.err.println(fnfe);
                ps.println("2 No pass file. Contact admin");
            }
            catch(IOException ioe) {
                System.err.println(ioe);
                ps.println("2 Somethong went wrong");
            }
        }
        else {
            ps.println("2 User inconnu");
        }
    }
}
