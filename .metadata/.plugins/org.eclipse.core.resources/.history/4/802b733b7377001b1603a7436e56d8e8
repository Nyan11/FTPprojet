package cda.projet.serveur;

import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CommandePASS extends Commande {

    public CommandePASS(PrintStream ps, String commandeStr) {
        super(ps, commandeStr);
    }

    public void execute() {
        if(CommandExecutor.userOk == true) {
            try {
                File passFile = new File(CommandExecutor.userDir, ".pass");
                BufferedReader br = new BufferedReader(new FileReader(passFile));
                if(commandeArgs[0].equals(br.readLine())) {
                    CommandExecutor.pwOk = true;
                    ps.println("1 Commande pass OK");
                    ps.println("0 Vous êtes bien connecté sur notre serveur");
                }
                else {
                    ps.println("2 Le mot de passe est faux");
                }
            }
            catch(FileNotFoundException fnfe) {
                System.err.println(fnfe);
                ps.println("2 Somethong went wrong");
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
