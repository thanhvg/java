package net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ball.Ball;
import ball.ServerBall;

public class Server implements Runnable {
	ServerSocket serverSocket;
	Socket server;
	//BallPos ballPos;
	boolean connected;
	private ObjectOutputStream out;
	ServerBall serverBall;
	
	
	
	public ServerBall getServerBall() {
		return serverBall;
	}

	public void setServerBall(ServerBall serverBall) {
		this.serverBall = serverBall;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public void openConnection() {
		try {
			serverSocket = new ServerSocket(9000);
			server = serverSocket.accept();
			out = new ObjectOutputStream(server.getOutputStream());
			connected = true;			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		if (connected) {
			System.out.println("Server thread running");
			//ballPos = new BallPos(ball.getX(), ball.getY());
			try {
				BallPos ballPos = serverBall.exportBallPos();				
				out.reset(); // very important file:///home/thanh/Desktop/javanotes7-web-site/c11/s1.html
				out.writeObject(ballPos);
				// out.writeObject(serverBall.exportBallPos());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
