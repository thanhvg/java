package network2;

import game.Animator;
import game.Constant;
import game.KeyBoardHandler;
import game.KeyBoardHandlerProxy;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.JFrame;

import sprites.Ball;
import sprites.BarLeft;
import sprites.BarRight;

public class Player extends JFrame implements ActionListener {
	private static int port = 2000;
	Animator ani;
	BarLeft barLeft;
	BarRight barRight;
	Ball ball;
//	Timer timer;
	//Controllable controller = new ControllerProxy();
	KeyBoardHandler keyboardHandler = new KeyBoardHandlerProxy();
	Thread worker;
	
	Hub hub;
	ConnectionToHub connectionToHub;
	BlockingQueue<Object> outMsgs;
	BlockingQueue<Object> inMsgs;
	
	private MenuBar menuBar = new MenuBar();
	private Menu file = new Menu(); 
	private MenuItem close = new MenuItem(); 
	private MenuItem newGame = new MenuItem();
	// private MenuItem newMultiPlayer = new MenuItem();
	// private MenuItem quitCurrentGame = new MenuItem();
	private MenuItem server = new MenuItem();
	private MenuItem client = new MenuItem();
		
	public Player() {
		ball = new Ball(5,(Constant.BOX_HEIGHT/2), 7, 3);
		barLeft = new BarLeft(0,(Constant.BOX_HEIGHT/2 - Constant.BAR_HEIGHT/2),0,6);
		barRight = new BarRight(Constant.BOX_WIDTH-Constant.BAR_WIDTH,(Constant.BOX_HEIGHT/2 - Constant.BAR_HEIGHT/2),0,6);
		ani = new Animator(ball, barLeft, barRight);
		// timer = new Timer(300, this);
		setSize(Constant.BOX_WIDTH +3,Constant.BOX_HEIGHT+10);
		setTitle("Ping Pong Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(ani);
		this.addKeyListener(new TAdapter());
		setMenuBar(menuBar);
		menuBar.add(file);
		file.setLabel("File");
		
		newGame.setLabel("New single player game");
		newGame.addActionListener(this);
		file.add(newGame);
		
//		newMultiPlayer.setLabel("Play on LAN vs other");
//		newMultiPlayer.addActionListener(this);
//		file.add(newMultiPlayer);
		
		server.setLabel("Server game");
		server.addActionListener(this);
		file.add(server);
		
		client.setLabel("Client game");
		client.addActionListener(this);
		file.add(client);
//		
//		quitCurrentGame.setLabel("Quit this game");
//		quitCurrentGame.addActionListener(this);
//		file.add(quitCurrentGame);
		
		close.setLabel("Close");
		close.addActionListener(this);
		file.add(close);
		
		
		
		worker = new Thread() {
			public void run() {
				while(true){
					ani.repaint();
					// controller.act();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		
		//gameSingle = new GameSingle(ball, barLeft,barRight);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player player = new Player();
		player.setVisible(true);
		player.worker.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
//		if (e.getSource() == timer) {
//			ani.repaint();
//			return;								
//		}
		
		
		if (e.getSource() == close) {
			this.dispose();
			return;
		}
		
		if (e.getSource() == newGame) {
//			GameSingle gs = new GameSingle(ball, barLeft,barRight);
//			controller = new GameSingleWrapper(gs);
//			controller.addListener(this);
//			keyboardHandler = (KeyBoardHandler) controller;
//			GameSingle.setState(State.LEFT_TO_SERVE);
//			// GameSingle.eventCenter(Event.BALL_SERVED);
			return;
		}
		
//		if (e.getSource() == newMultiPlayer) {
//			
//			return;
//		}
		
		if (e.getSource() == server) {	
			outMsgs = new LinkedBlockingDeque<>();
			inMsgs = new LinkedBlockingDeque<>();
			
			new Thread(){
				public void run() {
					hub = new Hub();
					// System.out.println("in Player after Hub constructor");
					connectionToHub = new ConnectionToHub(Player.this, "localhost", port );
					// System.out.println("in Player after ConnectionToHub constructor");
					new NetInputProcessing().start();
					keyboardHandler = new NetInputTransmissionToServer();
					// System.out.println("keyboard handler");
				}				
			}.start();
			
			System.out.println("Server option selected");
			return;	
			
		}
		
		if (e.getSource() == client) {
			outMsgs = new LinkedBlockingDeque<>();
			inMsgs = new LinkedBlockingDeque<>();
			System.out.println("Client mode selected");			
			connectionToHub = new ConnectionToHub(Player.this,"localhost", port);
			new NetInputProcessing().start();
			keyboardHandler = new NetInputTransmissionToServer();
			
			return;
		
		}
	}
	
	void updateCoordinate(ServerToClientMsg sMsg) {
		ball.setX(sMsg.ballPosX);
		ball.setY(sMsg.ballPoxY);
		barLeft.setY(sMsg.barLeftPos);
		barRight.setY(sMsg.barRightPos);
		
	}
	
	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
		
			//System.out.println("Key released event");	
			keyboardHandler.keyReleasedHandle(e);

		}

		public void keyPressed(KeyEvent e) {
			//System.out.println("Key pressed event");
			keyboardHandler.keyPressedHandle(e);
			
		
		}
	} // end of adapter

//	@Override
//	public void stateChange(Event e) {
//		// TODO Auto-generated method stub
//		worker.interrupt();
//	}
	
	class NetInputProcessing extends Thread {
		public void run() {
			Object o = null;
			
			while (true) {
				try {
					o = inMsgs.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (o instanceof ServerToClientMsg) {
					updateCoordinate((ServerToClientMsg) o);
					// System.out.println("player got a msg");
				} 
			}
		}
	}
	
	class NetInputTransmissionToServer implements KeyBoardHandler {
		
		private ClientToServerMsg cMsg;
		private void send() {
			outMsgs.add(cMsg);
		}
		

		@Override
		public void keyPressedHandle(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE ||
				e.getKeyCode() == KeyEvent.VK_UP 	||
				e.getKeyCode() == KeyEvent.VK_DOWN ) {
				cMsg = new ClientToServerMsg(e.getKeyCode(), true);
				send();
				// System.out.println("Did i get any input");
			}
			
		}

		@Override
		public void keyReleasedHandle(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE ||
				e.getKeyCode() == KeyEvent.VK_UP 	||
				e.getKeyCode() == KeyEvent.VK_DOWN ) {
				cMsg = new ClientToServerMsg(e.getKeyCode(), false);
				send();
				}			
		}
		
	}


}
