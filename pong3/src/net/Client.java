package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import pong2.GUI;

public class Client implements Runnable {
	ClientState clientState;
	ServerState serverState;
	GUI gui;
	Socket client;
	String serverName;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Client(String serverName, GUI gui){
		this.serverName = serverName;
		this.gui = gui;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Trying to connected to " + serverName);
			client = new Socket(serverName,game.GameConstants.PORT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Just connected to " + client.getRemoteSocketAddress());
		gui.setConnected(true);
		try {
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		while(gui.isConnected()) {
			try {
				serverState = (ServerState)in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 			
		}
	}

	public ServerState getServerState() {
		return serverState;
	}

	public void setServerState(ServerState serverState) {
		this.serverState = serverState;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	public void sendClientState() {
		try {
			out.reset();
			out.writeObject(clientState);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ClientState getClientState() {
		return clientState;
	}

	public void setClientState(ClientState clientState) {
		this.clientState = clientState;
	}
	

}
