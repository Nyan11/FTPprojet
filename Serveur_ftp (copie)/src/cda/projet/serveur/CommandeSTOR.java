package cda.projet.serveur;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandeSTOR extends Commande {
	
	public static int port = 4000;
	
	public CommandeSTOR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		File file = new File(CommandExecutor.userPos, commandeArgs[0]);
		if(file.exists() && file.isFile()) {
			ps.println("2 le fichier existe");
			return;
		}
		
		ServerSocket serveurDownloadFile;
		Socket socketFile;

		byte[] aByte = new byte[1];
		int bytesRead;
		try {
			serveurDownloadFile = new ServerSocket(port);
			ps.println("1 Le fichier " + commandeArgs[0] + " est prêt pour la reception");
			ps.println("0 Sur le port " + port);
			port++;
			socketFile = serveurDownloadFile.accept();
			
			InputStream is = socketFile.getInputStream();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			bytesRead = is.read(aByte, 0, aByte.length);

            do {
                    baos.write(aByte);
                    bytesRead = is.read(aByte);
            } while (bytesRead != -1);

            bos.write(baos.toByteArray());
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