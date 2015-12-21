package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import ball.Ball;
import ball.ClientBall;

public class Client implements Runnable{
	
	Socket client;
	BallPos ballPos;
	boolean connected;
	ObjectInputStream in;
	//Ball ball;
	ClientBall clientBall;
	
	
	
	public ClientBall getClientBall() {
		return clientBall;
	}

	public void setClientBall(ClientBall clientBall) {
		this.clientBall = clientBall;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	public void openConnection() {
		try {
			client = new Socket("localhost",9000);
			in = new ObjectInputStream(client.getInputStream());
			System.out.println("Client connect");
			connected = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		if (connected) {
			try {
				ballPos = (BallPos) in.readObject();
				System.out.println("Client: got new ball");
				clientBall.importBallPos(ballPos);
//				ball.setX(ballPos.getX());
//				ball.setY(ballPos.getY());
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
