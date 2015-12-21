package network2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import network.Disconnect;

public class ConnectionToPlayer {
	// what this class provides:
	// connection to player 1, socket, input stream, output stream
	// method sends message to player 1
	// method gets message from player 1
	private BlockingQueue<Object> incomingMessages;
	private LinkedBlockingQueue<Object> outgoingMessages;
	private Socket connection;
	// private ServerSocket server;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private volatile boolean closed;  // Set to true when connection is closing normally.
	private Thread sendThread; // Handles setup, then handles outgoing messages.
	private volatile Thread recieveThread; // Created only after connection is open.
	

	public ConnectionToPlayer(BlockingQueue<Object> queue, Socket connection) throws IOException {
		incomingMessages = queue ;
		outgoingMessages = new LinkedBlockingQueue<Object>();
		this.connection = connection;
		sendThread = new SendThread();
		sendThread.start();			
	}

	public void send(Object o) {
		outgoingMessages.add(o);
	}

	private class SendThread extends Thread {
		// this read connects to player 1 
		// sets up the input and output streams and 
		// gets message from the player 1 and put it on incomingMessages
		public void run(){
			try {				
				out = new ObjectOutputStream(connection.getOutputStream());
				in = new ObjectInputStream(connection.getInputStream());
				out.flush();
				
				closed = false;
				recieveThread = new RecieveThread();
				recieveThread.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (!closed) {
				try {
					Object o = outgoingMessages.take();
					if (o instanceof Disconnect) {
						closed = true;
					}
					out.writeObject(o);;
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private class RecieveThread extends Thread {
		// this does 
		// gets msg from stream,
		// put it on the incoming queue
		public void run() {
			while (!closed) {
				try {
					Object o = in.readObject();
					// System.out.println("In ConnectionToPlayer RecieveThread" + o.toString());
					
					if (o instanceof Disconnect) {
						closed = true;
					}
					incomingMessages.add(o);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}


