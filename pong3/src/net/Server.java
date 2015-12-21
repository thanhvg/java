package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import pong2.GUI;
import game.GameConstants;

public class Server implements Runnable {
	private ServerSocket serverSocket;
	private GUI gui;
	private Socket server;
	private ObjectInputStream in;
	private ObjectOutputStream out; 
	private ClientState clientState;
	private ServerState serverState;
	
	void openConnection(int port) {
		
	}
	
	public Server(GUI gui) throws IOException {
		serverSocket = new ServerSocket(game.GameConstants.PORT);
		this.gui = gui;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Waiting for client on port " + game.GameConstants.PORT + "...\n");
		
		try {
			server = serverSocket.accept();
			gui.setConnected(true);
			System.out.println("Server just connected to " + server.getRemoteSocketAddress());
			in = new ObjectInputStream(server.getInputStream());
			out = new ObjectOutputStream(server.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Got error after accept \n");
		}
		
		while(gui.isConnected()) {
			try {
				System.out.println("I am before object in");
				clientState = (ClientState)in.readObject();
				System.out.println("I am after object in" + clientState.getY());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getClientY() {
		return clientState.getY();
	}
	
	public void sendServerState() {
		try {
			out.reset();
			out.writeObject(serverState);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public ServerState getServerState() {
		return serverState;
	}

	public void setSeverState(ServerState serverState) {
		this.serverState = serverState;
	}
	
	
	

}
