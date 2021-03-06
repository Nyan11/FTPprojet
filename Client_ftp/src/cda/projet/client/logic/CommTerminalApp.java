package cda.projet.client.logic;

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

	public void start() {
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
	public void help(String message) throws IOException {
		System.out.println("Commande inconnue");
	}

	@Override
	public void end(String message) throws IOException {
		ps.println("bye");
		System.out.println("Communication terminée");
	}

	@Override
	public void upload(String message) throws IOException {
		File file;
		String recv;
		int port;

		file = new File(message.split(" ")[1]);
		if(!file.exists() || !file.isFile()) {
			System.out.println("Le fichier n'existe pas");
			return;
		}

		ps.println("stor " + file.getName());
		recv = recvMessage();

		if(recv.charAt(0) == '0') {
			port = Integer.parseInt(recv.split(" ")[4]);
			new Thread(new Uploader(file, this.host, port)).start();
		}
	}

	@Override
	public void download(String message) throws IOException {
		File file;
		String recv;
		int port;
		
		file = new File(message.split(" ")[1]);
		if(file.exists() && file.isFile()) {
			System.out.println("Le fichier existe deja");
			return;
		}

		ps.println(message);
		recv = recvMessage();

		if(recv.charAt(0) == '0') {
			port = Integer.parseInt(recv.split(" ")[4]);
			new Thread(new Downloader(LOCAL_PATH, message.split(" ")[1], this.host, port)).start();
		}
	}

	@Override
	public void cd(String message) throws IOException {
		ps.println(message);
		recvMessage();
	}

	@Override
	public void pwd(String message) throws IOException {
		ps.println(message);
		recvMessage();
	}

	@Override
	public void ls(String message) throws IOException {
		ps.println(message);
		recvMessage();
	}

	@Override
	public void pass(String message) throws IOException{
		ps.println(message);
		recvMessage();
	}

	@Override
	public void user(String message) throws IOException {
		ps.println(message);
		recvMessage();
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
