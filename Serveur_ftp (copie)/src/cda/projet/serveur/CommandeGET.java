package cda.projet.serveur;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandeGET extends Commande {
	
	public static int port = 6969;
	
	public CommandeGET(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		File file = new File(CommandExecutor.userPos, commandeArgs[0]);
		if(!file.exists() || !file.isFile()) {
			ps.println("2 le fichier n'existe pas");
			return;
		}
		
		ServerSocket serveurDownloadFile;
		Socket socketFile;
		BufferedOutputStream bos;
		FileInputStream fis;
		BufferedInputStream bis;
		byte[] barray = new byte[(int) file.length()];
		try {
			serveurDownloadFile = new ServerSocket(port);
			ps.println("1 Le fichier " + commandeArgs[0] + " existe et prêt pour l’envoi");
			ps.println("0 Sur le port " + port);
			port++;
			socketFile = serveurDownloadFile.accept();
			
			
			
			
			bos = new BufferedOutputStream(socketFile.getOutputStream());
			
			fis = new FileInputStream(file);
			
			bis = new BufferedInputStream(fis);
			
			bis.read(barray, 0, barray.length);
			
			bos.write(barray);
			bos.flush();
			bos.close();
			socketFile.close();
			serveurDownloadFile.close();
		} catch (IOException e) {
			ps.println("2 une erreur a eu lieu");
			e.printStackTrace();
		}
	}
}
