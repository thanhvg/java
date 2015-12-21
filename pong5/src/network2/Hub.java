package network2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import controllers.*;
import game.*;
import sprites.*;

public class Hub {
	private State state;
	public State getState() {
		return state;
	}
	List<StateListener> stateListenerList = new ArrayList<>();
	
	Fsm fsm;
	Ball ball;
	BarLeft barLeft;
	BarRight barRight;
	BallControl2 ballControl;
	LeftBarControl2 leftBarControl;
	RightBarControl2 rightBarControl;
	//Thread BallMove; // update ball pos every certain time
	Thread Refresh; // periodically update ball pos, send ball pos, player pos and state to clients
	
	ConnectionToPlayer cP1; // constructor(msgFromP1, socket-connection)
	ConnectionToPlayer cP2;
	BlockingQueue<Object> msgsFromP1;
	BlockingQueue<Object> msgsFromP2;
	
	// Hub should run the seversocket, get the connection to player, and pass it to ConnectionToPlayer constructor, 
	// closes the socket when two players have connected, this will be put in Hub constructor 
	
	Thread getMgsFromP1; // get msg from msgFromP1, update state
	Thread getMgsFromP2;
	Thread realizeState; // 
	
	ServerSocket serverSocket;
	Socket socket1, socket2;
	
	private int connectCount = 0;
	
	public void addListener(StateListener sl) {
		stateListenerList.add(sl);
		
	}
	
	public Sprite getBarLeft() {
		return barLeft; 
	}
	
	public void eventCenter(Event e) {		
		State s = fsm.whatsNext(e, state);
		if (s == null) {
			System.out.println("eventCenter unknow event " + e + " for state " + state );
			return;
		}
		setState(s);
		System.out.println("Event center state "+ s + " event " + e);
		for(StateListener sl : stateListenerList) {
			sl.stateChange(e);
			
		}
		
	}
	public void setState(State s) {
		this.state = s;		
	}
	public Sprite getBarRight() {
		return barRight;
	}
	
	public Hub() {
		ball = new Ball(5,(Constant.BOX_HEIGHT/2), 7, 3);
		barLeft = new BarLeft(0,(Constant.BOX_HEIGHT/2 - Constant.BAR_HEIGHT/2),0,6);
		barRight = new BarRight(Constant.BOX_WIDTH-Constant.BAR_WIDTH,(Constant.BOX_HEIGHT/2 - Constant.BAR_HEIGHT/2),0,6);
		ballControl = new BallControl2(ball, this);
		leftBarControl = new LeftBarControl2(barLeft, this);
		rightBarControl = new RightBarControl2(barRight, this);
		fsm = new Fsm();
		setState(State.LEFT_TO_SERVE);
		// cP1 = new ConnectionProxy();
		
		
		try {
			serverSocket = new ServerSocket(2000);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		

		new Thread() {
			public void run() {
				//				boolean isPlayer2Connected = false;
				//				while (!isPlayer2Connected) {
				try {
					socket1 = serverSocket.accept(); 
					msgsFromP1 = new LinkedBlockingQueue<Object>();
					cP1 = new ConnectionToPlayer(msgsFromP1, socket1);	
					// System.out.println("Huh");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				new GetMsgFromP1().start();
				System.out.println("player 1 connected");
				connectCount = 1;
				try {
					socket2 = serverSocket.accept();
					//					isPlayer2Connected = true;
					msgsFromP2 = new LinkedBlockingQueue<Object>();
					cP2 = new ConnectionToPlayer(msgsFromP2, socket2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				new GetMsgFromP2().start();
				System.out.println("player 2 connected");
				connectCount = 2;

			}

		}.start();
		
		new Thread() {
			public void run () {
				while (true) {
					try {
						sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ServerToClientMsg sMsgs = new ServerToClientMsg(ball.getX(), ball.getY(), barLeft.getY(), barRight.getY());
					if (connectCount ==1) {
						cP1.send(sMsgs);
					}

					if (connectCount == 2) {
						cP1.send(sMsgs);
						cP2.send(sMsgs);
					}
						
					ballControl.move();
					leftBarControl.move();
					rightBarControl.move();
					// System.out.println("State " + state.toString());

				}
				
			}
		}.start();
		
	}
	
	class GetMsgFromP1 extends Thread {
		Object o = null;
		public void run() {
			while (true) {
				try {
					o = msgsFromP1.take();
					if (o instanceof ClientToServerMsg) {
						udpateInputP1((ClientToServerMsg) o);
						// System.out.println("Get something");
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	class GetMsgFromP2 extends Thread {
		Object o = null;
		public void run() {
			while (true) {
				try {
					o = msgsFromP2.take();
					if (o instanceof ClientToServerMsg) {
						udpateInputP2((ClientToServerMsg) o);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}

	public void udpateInputP1(ClientToServerMsg cMsg) {
		if (cMsg.isPressed) {
			leftBarControl.keyPressedHandle(cMsg.keyCode);
		} else
			leftBarControl.keyReleasedHandle(cMsg.keyCode);
	}
	
	public void udpateInputP2(ClientToServerMsg cMsg) {
		if (cMsg.isPressed) {
			rightBarControl.keyPressedHandle(cMsg.keyCode);
		} else
			rightBarControl.keyReleasedHandle(cMsg.keyCode);
	}

}
