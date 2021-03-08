package cda.ftp.client.logic;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CommTerminalApp extends Communicator {

	public static final File LOCAL_PATH = new File("local");
	private String host;

	public CommTerminalApp(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
		this.host = host;
	}

	public void launch() {
		Scanner sc;
		String message = "";
		sc = new Scanner(System.in);
		try {
			recvMessage();
			while(!message.equals("bye")) {
				message = sc.nextLine();
				sendMessage(message);
			}
			this.closeAll();
			sc.close();
		} catch (IOException e) {
			System.out.println("Le serveur est arreter");
		} catch (NullPointerException e) {
			System.out.println("Le serveur a un probleme");
		}
	}

	@Override
	public boolean help(String message) throws IOException {
		System.out.println("Commande inconnue");
		return true;
	}

	@Override
	public boolean end(String message) throws IOException {
		ps.println("bye");
		System.out.println("Communication terminée");
		return true;
	}

	@Override
	public boolean upload(String message) throws IOException {
		File file;
		String recv;
		int port;

		file = new File(message.split(" ")[1]);
		if(!file.exists() || !file.isFile()) {
			System.out.println("Le fichier n'existe pas");
			return false;
		}

		ps.println("stor " + file.getName());
		recv = recvMessage();

		if(recv.charAt(0) == '0') {
			port = Integer.parseInt(recv.split(" ")[4]);
			new Thread(new Uploader(file, this.host, port)).start();
		}
		return true;
	}

	@Override
	public boolean download(String message) throws IOException {
		File file;
		String recv;
		int port;
		
		file = new File(message.split(" ")[1]);
		if(file.exists() && file.isFile()) {
			System.out.println("Le fichier existe deja");
			return false;
		}

		ps.println(message);
		recv = recvMessage();

		if(recv.charAt(0) == '0') {
			port = Integer.parseInt(recv.split(" ")[4]);
			new Thread(new Downloader(LOCAL_PATH, message.split(" ")[1], this.host, port, 0)).start();
		}
		return true;
	}

	@Override
	public boolean cd(String message) throws IOException {
		ps.println(message);
		if(recvMessage().startsWith("2 ")) {
			return false;
		}
		else {
			return true;	
		}
	}

	@Override
	public boolean pwd(String message) throws IOException {
		ps.println(message);
		recvMessage();
		return true;
	}

	@Override
	public boolean ls(String message) throws IOException {
		ps.println(message);
		recvMessage();
		return true;
	}

	@Override
	public boolean pass(String message) throws IOException{
		ps.println(message);
		recvMessage();
		return true;
	}

	@Override
	public boolean user(String message) throws IOException {
		ps.println(message);
		recvMessage();
		return true;
	}

	public static String recvMessage() throws IOException {
		String value = "";
		do {
			value = br.readLine();
			System.out.println(">> " + value);
		} while(value.charAt(0) == '1');
		return value;
	}

}
