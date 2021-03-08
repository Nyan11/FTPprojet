package cda.ftp.client.logic;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import cda.ftp.client.logic.services.DownloaderService;
import cda.ftp.client.logic.services.UploaderService;
import javafx.util.Pair;

public class CommFTP extends Communicator {

	public CommFTP(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
	}

	@Override
	public boolean help(String message) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean end(String message) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean uploadBis(String message, File file, String hostName) throws IOException {
		String recv;
		int port;

		if(!file.exists() || !file.isFile()) {
			System.out.println("Le fichier n'existe pas");
			return false;
		}

		ps.println("stor " + file.getName());
		recv = recvMessage();

		if(recv.charAt(0) == '0') {
			port = Integer.parseInt(recv.split(" ")[4]);
			UploaderService uls = new UploaderService(file, hostName, port);
			uls.start();
			FTPinterface.createStatusInfo(false, file.getName(), uls.progressProperty());
		}
		return true;
	}

	public boolean downloadBis(String message, File file, String hostName, long size) throws IOException {
		String recv;
		int port;
		
		if(file.exists() && file.isFile()) {
			System.out.println("Le fichier existe deja");
			return false;
		}

		ps.println(message);
		recv = recvMessage();

		if(recv.charAt(0) == '0') {
			port = Integer.parseInt(recv.split(" ")[4]);
			DownloaderService dls = new DownloaderService(file, message.split(" ")[1], hostName, port, size);
			dls.start();
			FTPinterface.createStatusInfo(true, FTPinterface.currentFile, dls.progressProperty());
		}
		return true;
	}

	@Override
	public boolean cd(String message) throws IOException {
		String recv = "";
		ps.println(message);
		recv = recvMessage();
		if(recv.startsWith("0")) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean pwd(String message) throws IOException {
		String recv = "";
		ps.println(message);
		recv = recvMessage();
		if(recv.startsWith("0")) {
			FTPinterface.pwdValue = recv.split(" ")[1];
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean ls(String message) throws IOException {
		ps.println(message);
		recvLs();
		return true;
	}

	@Override
	public boolean pass(String message) throws IOException {
		String recv = "";
		ps.println(message);
		recv = recvMessage();
		return recv.startsWith("0");
	}

	@Override
	public boolean user(String message) throws IOException {
		String recv = "";
		recv = recvMessage();
		ps.println(message);
		recv = recvMessage();
		return recv.startsWith("0");
	}

	public static String recvMessage() throws IOException {
		String value = "";
		do {
			value = br.readLine();
			System.out.println(">> " + value);
		} while(value.charAt(0) == '1');
		return value;
	}
	
	public static String recvLs() throws IOException {
		String value = "";
		Set<Pair<String, Pair<Boolean, Long>>> lsValue = new HashSet<Pair<String, Pair<Boolean, Long>>>();
		Pair<Boolean, Long> type;
		String[] names;
		String fullName = "";
		boolean dir = false;
		do {
			value = br.readLine();
			System.out.println(">> " + value);
			if(value.startsWith("1")) {
				names = value.split(" ");
				dir = names[names.length - 1].equals("dir");
				fullName = value.substring(2, value.length() - names[names.length - 1].length() - 1);
				for(int i = 2; i < names.length - 1; i++) {
					fullName += " " + names[i];
				}
				if(dir) {
					type = new Pair<Boolean, Long>(true, 0l);
				}
				else {
					type = new Pair<Boolean, Long>(false, Long.valueOf(names[names.length - 1]));
				}
				lsValue.add(new Pair<String, Pair<Boolean, Long>>(fullName, type));
				
			}
			
		} while(value.charAt(0) == '1');
		FTPinterface.lsValue = lsValue;
		return value;
	}

	@Override
	public boolean download(String message) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean upload(String message) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
}
