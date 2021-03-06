package cda.ftp.client.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class Communicator {
	
	protected static Socket socket;
	protected static BufferedReader br;
	protected static PrintStream ps;
	
	public Communicator(String host, int port) throws UnknownHostException, IOException {
		Communicator.socket = new Socket(host, port);
		Communicator.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Communicator.ps = new PrintStream(socket.getOutputStream());
	}
	
	public void closeAll() throws IOException {
		Communicator.socket.close();
		Communicator.br.close();
		Communicator.ps.close();
	}
	
	public boolean sendMessage(String message) throws IOException {
		if(message.equals("bye")) {
			return end(message);
		} else if(message.startsWith("user ")) {
			return user(message);
		} else if(message.startsWith("pass ")) {
			return pass(message);
		} else if(message.equals("ls")) {
			return ls(message);
		} else if(message.equals("pwd")) {
			return pwd(message);
		} else if(message.startsWith("cd ")) {
			return cd(message);
		} else if(message.startsWith("get ")) {
			return download(message);
		} else if(message.startsWith("stor ")) {
			return upload(message);
		} else {
			return help(message);
		}
	}
	
	public abstract boolean help(String message) throws IOException;
	
	public abstract boolean end(String message) throws IOException;

	public abstract boolean upload(String message) throws IOException;
	
	public abstract boolean download(String message) throws IOException;
	
	public abstract boolean cd(String message) throws IOException;
	
	public abstract boolean pwd(String message) throws IOException;
	
	public abstract boolean ls(String message) throws IOException;
	
	public abstract boolean pass(String message) throws IOException;
	
	public abstract boolean user(String message) throws IOException;
	
}
