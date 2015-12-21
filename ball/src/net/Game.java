package net;

import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import ball.Ball;
import ball.ClientBall;
import ball.ServerBall;

public class Game extends JFrame implements ActionListener {
	private MenuBar menuBar = new MenuBar();
	private Menu file = new Menu(); 
	private MenuItem close = new MenuItem(); 
	private MenuItem newGame = new MenuItem();
	private MenuItem newMultiPlayer = new MenuItem();
	private MenuItem quitCurrentGame = new MenuItem();
	private MenuItem server = new MenuItem();
	private MenuItem client = new MenuItem();
	private Ball ball;
	//private ServerBall serverBall;
	//private ClientBall clientBall;
	public final String HOST = "localhost";
	
	public Game() {
		setSize(600,400);
		setTitle("Ping Pong Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
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
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == close) {
			this.dispose();
			return;
		}
		
		if (e.getSource() == newGame) {
			//getContentPane().remove(gui);
			//gui = new GUI();
			
			
			//gui.setFocusable(true);
			//create new instance without properly remove the old one 
			//the new instance wont recieve input events
			//there are also references to the old install: setFocusable and keyboardListener
			//may need to use the init function just reset the instance parameters not create a new one
			//getContentPane().add(gui);

			ball = new Ball();
			getContentPane().add(ball);
			setVisible(true);
			//gui.setFocusable(true);
			return;
		}
		
		if (e.getSource() == newMultiPlayer) {
			
			return;
		}
		
		if (e.getSource() == server) {
			
			ball = new ServerBall();
			getContentPane().add(ball);
			setVisible(true);
			return;
			
			
			
		}
		
		if (e.getSource() == client) {
//			System.out.println("hi");	
//					
			ball = new ClientBall();
			getContentPane().add(ball);
			setVisible(true);
			return;				
		}
	}


	public static void main(String args[]) {
		Game app = new Game();
		app.setVisible(true);
	}

}
