package game;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.*;

import sprites.Ball;
import sprites.BarLeft;
import sprites.BarRight;

public class Game extends JFrame implements ActionListener, StateListener {
	Animator ani;
	BarLeft barLeft;
	BarRight barRight;
	Ball ball;
//	Timer timer;
	Controllable controller = new ControllerProxy();
	KeyBoardHandler keyboardHandler = new KeyBoardHandlerProxy();
	Thread worker;
	
	private MenuBar menuBar = new MenuBar();
	private Menu file = new Menu(); 
	private MenuItem close = new MenuItem(); 
	private MenuItem newGame = new MenuItem();
	private MenuItem newMultiPlayer = new MenuItem();
	private MenuItem quitCurrentGame = new MenuItem();
	private MenuItem server = new MenuItem();
	private MenuItem client = new MenuItem();
		
	public Game() {
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
		
		newMultiPlayer.setLabel("Play on LAN vs other");
		newMultiPlayer.addActionListener(this);
		file.add(newMultiPlayer);
		
		server.setLabel("Server game");
		server.addActionListener(this);
		file.add(server);
		
		client.setLabel("Client game");
		client.addActionListener(this);
		file.add(client);
		
		quitCurrentGame.setLabel("Quit this game");
		quitCurrentGame.addActionListener(this);
		file.add(quitCurrentGame);
		
		close.setLabel("Close");
		close.addActionListener(this);
		file.add(close);
		
		
		
		worker = new Thread() {
			public void run() {
				while(true){
					ani.repaint();
					controller.act();
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
		Game game = new Game();
		game.setVisible(true);
		game.worker.start();

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
			hub gs = new hub(ball, barLeft,barRight);
			controller = new GameSingleWrapper(gs);
			controller.addListener(this);
			keyboardHandler = (KeyBoardHandler) controller;
			hub.setState(State.LEFT_TO_SERVE);
			// GameSingle.eventCenter(Event.BALL_SERVED);
			return;
		}
		
		if (e.getSource() == newMultiPlayer) {
			
			return;
		}
		
		if (e.getSource() == server) {	
			return;	
			
		}
		
		if (e.getSource() == client) {
			System.out.println("Client mode selected");				
			return;
		
		}
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

	@Override
	public void stateChange(Event e) {
		// TODO Auto-generated method stub
		worker.interrupt();
	}

}


