package com.practicaljava.lesson12.ui;



import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.practicaljava.lesson12.common.GameConstants;
import com.practicaljava.lesson12.engine.PingPongGameEngine;
import com.practicaljava.lesson12.common.CurrentScore;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static java.awt.event.InputEvent.ALT_DOWN_MASK;

/**
 *  This class paints the green ping pong table,
 *  ball, rackets and displays the score
 */
public class PingPongGreenTable extends JPanel {

	private JLabel label;
	private PingPongGameEngine gameEngine;

	private int computerRacket_Y = GameConstants.COMPUTER_RACKET_Y_START;
	private int kidRacket_Y = GameConstants.KID_RACKET_Y_START;
	private int ballX = GameConstants.BALL_START_X;
	private int ballY = GameConstants.BALL_START_Y;

	private static JMenu scoresMenu;
	
	private int wining_score;
	private int racket_increment;
	private int sleep_time; 
	
	public int getWining_score() {
		return wining_score;
	}

	public int getRacket_increment() {
		return racket_increment;
	}

	public int getSleep_time() {
		return sleep_time;
	}
	

	private Dimension preferredSize = new Dimension(GameConstants.TABLE_WIDTH,GameConstants.TABLE_HEIGHT);

	// This method sets the size of the frame.
	// It's called by JVM
	public Dimension getPreferredSize() {
		return preferredSize;
	}

	// Constructor. Creates a listener for mouse events 
	PingPongGreenTable(){

		PingPongGameEngine gameEngine = new PingPongGameEngine(this);
		this.gameEngine = gameEngine;
		// Listen to mouse movements to move the rackets
		addMouseMotionListener(gameEngine);
		//Listen to the keyboard events 
		addKeyListener(gameEngine);
	}
	// Add a panel with a JLabel to the frame
	void addPaneltoFrame(Container container) {
		container.setLayout(new BoxLayout(container,  BoxLayout.Y_AXIS));
		container.add(this);
		label = new JLabel ("Press N for a new game, S to serve or Q to quit");
		container.add(label);
	}

	// repaint the window. This method is called by JVM 
	// when it needs to refresh the screen or when a 
	// method repaint() is called from PingPointGameEngine 
	public void paintComponent(Graphics g) {

		super.paintComponent(g);  
		g.setColor(Color.GREEN);
		// paint the table green
		g.fillRect(0,0,GameConstants.TABLE_WIDTH,GameConstants.TABLE_HEIGHT); 

		g.setColor(Color.yellow);
		// paint the right racket
		g.fillRect(GameConstants.KID_RACKET_X, kidRacket_Y, GameConstants.RACKET_WIDTH, GameConstants.RACKET_LENGTH); 
		g.setColor(Color.blue);
		// paint the left racket
		g.fillRect(GameConstants.COMPUTER_RACKET_X, computerRacket_Y, GameConstants.RACKET_WIDTH,GameConstants.RACKET_LENGTH); 
		// paint the ball
		g.setColor(Color.red);
		g.fillOval(ballX,ballY,10,10);     	
		//draw the white lines
		g.setColor(Color.white);
		g.drawRect(10,10,300,200);
		g.drawLine(160,10,160,210);
		// Set the focus to the table, so the  key
		// listenerwill send commands to  the table
		requestFocus();
	}

	// Set the current position of the kid's racket
	public void setKidRacket_Y(int yCoordinate){
		this.kidRacket_Y = yCoordinate;
		repaint();
	}
	// Return current posiition of the kid's racket
	public int getKidRacket_Y(){
		return kidRacket_Y;
	}

	// Set the current position of the computer's racket
	public void setComputerRacket_Y(int yCoordinate){
		this.computerRacket_Y = yCoordinate;
		repaint();
	}

	// Set the game's message
	public void setMessageText(String text){
		label.setText(text);
		//repaint();
	}

	// Set the ball position
	public void setBallPosition(int xPos, int yPos){
		ballX=xPos;
		ballY=yPos;
		repaint();
	}

	// Game's menu
	private JMenuBar gameMenu() {

		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_G);
		JMenu scoresMenu = new JMenu("Top10");
		scoresMenu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(gameMenu);
		menuBar.add(scoresMenu);
		PingPongGreenTable.scoresMenu = scoresMenu;

		JMenuItem newGameMenu = new JMenuItem("New", KeyEvent.VK_N);
		JMenuItem serveMenu = new JMenuItem("Serve", KeyEvent.VK_S);
		JMenuItem quitMenu = new JMenuItem("Quit", KeyEvent.VK_Q);
		newGameMenu.setAccelerator(KeyStroke.getKeyStroke('N', ALT_DOWN_MASK));
		serveMenu.setAccelerator(KeyStroke.getKeyStroke('S', ALT_DOWN_MASK));
		quitMenu.setAccelerator(KeyStroke.getKeyStroke('Q', ALT_DOWN_MASK));

		gameMenu.add(newGameMenu);
		gameMenu.add(serveMenu);
		gameMenu.add (quitMenu);

		newGameMenu.addActionListener(gameEngine);
		serveMenu.addActionListener(gameEngine);
		quitMenu.addActionListener(gameEngine);

		return menuBar;
	}

	public void populateScoreList (String [] scoreList) {

		scoresMenu.removeAll();

		for (String record : scoreList) {
			JMenuItem scoreRecord = new JMenuItem (record);
			scoreRecord.setEnabled(false);
			scoresMenu.add(scoreRecord);
		}
	}

	//read scores from file
	private String[] readScores (String fName) {

		DataInputStream dataIn = null;
		CurrentScore sc = null;
		String top10Strings[] = new String[10];
		ArrayList<CurrentScore> scoresArray = gameEngine.getScoresArray();

		try {
			dataIn = new DataInputStream ( new BufferedInputStream(new FileInputStream (fName)));
			for (int i = 0; i < 10; i++) {
				sc = new CurrentScore (dataIn.readInt()==0?"Kid":"Computer", dataIn.readInt(), dataIn.readInt());
				scoresArray.add(sc);
				top10Strings [i] = sc.toString();
			}
		} catch (FileNotFoundException fnf) {
			System.out.println ("File not found:" + fnf.toString());
		} catch (EOFException eof) {
			System.out.println ("End of file:" + eof.toString());
		} catch (IOException e) {
			System.out.println ("Could not read file:" + e.toString());
		} finally {
			if (dataIn != null) {
				try {
					dataIn.close();
				} catch (Exception e1) {
					e1.printStackTrace(); 
				}
			}
		}

		return top10Strings;
	}

	// load properties
	private void loadProperties (String fName) {

		Properties prop = new Properties();
		FileReader in = null;

		try {
			in = new FileReader (fName);
			prop.load(in);
			this.wining_score = Integer.parseInt(prop.getProperty("WINNING_SCORE"));
			this.racket_increment = Integer.parseInt(prop.getProperty("RACKET_INCREMENT"));
			this.sleep_time = Integer.parseInt(prop.getProperty("SLEEP_TIME"));
			
		} catch (FileNotFoundException fnf) {
			System.out.println ("File not found:" + fnf.toString());
		} catch (IOException ioe) {
			System.out.println ("Could not read file:" + ioe.toString());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e1) {
					e1.printStackTrace(); 
				}
			}
		}
	}

	public static void main(String[] args) {

		// Create an instance of the frame
		JFrame f = new JFrame("Ping Pong Green Table");

		// Ensure that the window can be closed 
		// by pressing a little cross in the corner
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		PingPongGreenTable table = new PingPongGreenTable();
		table.addPaneltoFrame(f.getContentPane());

		f.setJMenuBar(table.gameMenu());
		table.populateScoreList(table.readScores(GameConstants.SCORES_FILE));
		table.loadProperties(GameConstants.PROPERTIES_FILE);

		// Set the frame's size and make it visible
		f.setBounds(0,0,GameConstants.TABLE_WIDTH+5, GameConstants.TABLE_HEIGHT+60);
		f.setVisible(true);
	}
}

