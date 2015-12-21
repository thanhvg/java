package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Hub {
	// run a timer move the ball
	// get bar positions, 
	// produces the events and states
	// send out bar pos event and state to players
	StateNet stateNet;
	ConnectionToPlayer1 cP1;
	BlockingQueue<Object> msgsFromP1;
	BlockingQueue<Object> msgsFromP2;
	
	private class Sync extends Thread {
		// piece together messages to create a state of the whole game
		private volatile boolean isBothPlayersReady;
		
		public Sync() {
			
		}
		
		public void run() {
			while(isBothPlayersReady) {
				try {
					Object o1 = msgsFromP1.take();
					Object o2 = msgsFromP2.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	private class WhatsNext {
		// give me the event i'll tell you the state
	}
	
	private class ConnectionToPlayer1 { // Handles communication with one client.		
		// what this class provides:
		// connection to player 1, socket, input stream, output stream
		// method sends message to player 1
		// method gets message from player 1
		private BlockingQueue<Object> incomingMessages;
		private LinkedBlockingQueue<Object> outgoingMessages;
		private Socket connection;
		private ServerSocket server;
		private ObjectInputStream in;
		private ObjectOutputStream out;
		private volatile boolean closed;  // Set to true when connection is closing normally.
		private Thread sendThread; // Handles setup, then handles outgoing messages.
		private volatile Thread recieveThread; // Created only after connection is open.
		
		public ConnectionToPlayer1(BlockingQueue<Object> queue) throws IOException {
			incomingMessages = queue ;
			outgoingMessages = new LinkedBlockingQueue<Object>();
			server = new ServerSocket(2000);
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
					connection = server.accept(); // from this should be in a thread
					in = (ObjectInputStream) connection.getInputStream();
					out = (ObjectOutputStream) connection.getOutputStream();
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
			// put it on the incommingqueue
			public void run() {
				while (!closed) {
					try {
						Object o = in.readObject();
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
	

}
