package network2;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import game.KeyBoardHandler;
import sprites.Ball;
import sprites.BarLeft;
import sprites.BarRight;

public class ConnectionToHub  {
	 String host ;
	 int port = 2000;
	
	Ball ball;
	BarLeft barLeft;
	BarRight barRight;
	
	Socket toHubSocket;
	KeyEvent ek;
	Thread send;
	Thread receive;
	BlockingQueue<Object> inMsgs, outMsgs;
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public ConnectionToHub(Player player, String host, int port) {
		this.ball = player.ball;
		this.barLeft = player.barLeft;
		this.barRight = player.barRight;
		this.outMsgs = player.outMsgs;
		this.inMsgs = player.inMsgs;
		this.host = host;
		this.port = port;
		
		try {
			toHubSocket = new Socket(host,port);
			// System.out.println("in ConnectionToHub after toHubSocket");
			out = new ObjectOutputStream (toHubSocket.getOutputStream());
			in = new ObjectInputStream(toHubSocket.getInputStream());	
			out.flush();
			// System.out.println("in ConnectionToHub after Stream getting");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		// TODO send thread
		send = new SendThread();
		receive = new ReceiveThread();
		send.start();
		receive.start();
		// TODO receive thread
	}
	
//	public void send(Object o) {
//		outMsgs.add(o);
//	}

	
	
	private class SendThread extends Thread {
		public void run() {
			while (true) {
				try {
					Object o = outMsgs.take();
					// System.out.println("In ConnectionToHub SendThread" + o.toString());
					// out.writeObject("in coming");
					out.writeObject(o);
				} catch (InterruptedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
			}
			
		}
		
	}
	
	private class ReceiveThread extends Thread {
		public void run() {
			while (true) {
				try {
					Object o = in.readObject();
					inMsgs.add(o);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
