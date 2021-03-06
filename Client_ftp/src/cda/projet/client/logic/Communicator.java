package cda.projet.client.logic;

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
	
	public void sendMessage(String message) throws IOException {
		if(message.equals("bye")) {
			end(message);
		} else if(message.startsWith("user ")) {
			user(message);
		} else if(message.startsWith("pass ")) {
			pass(message);
		} else if(message.equals("ls")) {
			ls(message);
		} else if(message.equals("pwd")) {
			pwd(message);
		} else if(message.startsWith("cd ")) {
			cd(message);
		} else if(message.startsWith("get ")) {
			download(message);
		} else if(message.startsWith("stor ")) {
			upload(message);
		} else {
			help(message);
		}
	}
	
	public abstract void help(String message) throws IOException;
	
	public abstract void end(String message) throws IOException;

	public abstract void upload(String message) throws IOException;
	
	public abstract void download(String message) throws IOException;
	
	public abstract void cd(String message) throws IOException;
	
	public abstract void pwd(String message) throws IOException;
	
	public abstract void ls(String message) throws IOException;
	
	public abstract void pass(String message) throws IOException;
	
	public abstract void user(String message) throws IOException;
	
}
