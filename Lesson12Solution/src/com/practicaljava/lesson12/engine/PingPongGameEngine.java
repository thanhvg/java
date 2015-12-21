package com.practicaljava.lesson12.engine;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;
import com.practicaljava.lesson12.common.GameConstants;
import com.practicaljava.lesson12.common.CurrentScore;
import com.practicaljava.lesson12.ui.PingPongGreenTable;

/**
 * This class is a mouse and keyboard listener. It calculates ball and racket
 * movements, changes their coordinates.
 */
public class PingPongGameEngine implements Runnable, MouseMotionListener,
KeyListener, ActionListener {

	private PingPongGreenTable table;// reference to table
	private int kidRacket_Y = GameConstants.KID_RACKET_Y_START;
	private int computerRacket_Y = GameConstants.COMPUTER_RACKET_Y_START;
	private int kidScore;
	private int computerScore;
	private int ballX; // ball's X position
	private int ballY; // ball's Y position
	private boolean movingLeft = true;
	private boolean ballServed = false;
	private boolean newGameFlag = false;

	// Value in pixels of the vertical ball movement
	private int verticalSlide;
	Thread worker;

	// Array to store top 10 scores
	private ArrayList<CurrentScore> top10 = new ArrayList<CurrentScore>();
	
	public ArrayList<CurrentScore> getScoresArray ( ) {
		return top10;
	}

	// Constructor. Stores a reference to the table
	public PingPongGameEngine(PingPongGreenTable greenTable) {
		table = greenTable;
		worker = new Thread(this);
		worker.start();
	}

	// Methods required by MouseMotionListener
	// interface (some of them are empty, but must be
	// included in the class anyway)

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {

		int mouse_Y = e.getY();

		// If a mouse is above the kid's racket
		// and the racket did not go over the table top
		// move it up, otherwise move it down
		if (mouse_Y < kidRacket_Y && kidRacket_Y > GameConstants.TABLE_TOP) {
			kidRacket_Y -= table.getRacket_increment();
		} else if (kidRacket_Y < GameConstants.TABLE_BOTTOM) {
			kidRacket_Y += table.getRacket_increment();
		}

		// Set the new position of the racket on the table
		table.setKidRacket_Y(kidRacket_Y);
	}

	// Methods required by KeyListener interface
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();

		if ('n' == key || 'N' == key) {
			startNewGame();
		} else if ('q' == key || 'Q' == key) {
			endGame();
		} else if ('s' == key || 'S' == key) {
			kidServe();
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {
	}

	// Start a new Game
	public void startNewGame() {
		computerScore = 0;
		kidScore = 0;
		table.setMessageText("Score Computer: 0  Kid: 0");
		newGameFlag = true;
		kidServe();

	}

	// End the game
	public void endGame() {
		writeScores (top10, GameConstants.SCORES_FILE);
		System.exit(0);
	}

	// Method run() is required by Runnable interface
	public void run() {

		boolean canBounce = false;
		while (true) {

			System.out.println("ballServed is" + ballServed);

			if (ballServed) { // if ball is moving

				System.out.println("In run() 1");

				// Step 1. Is ball moving to the left?
				if (movingLeft && ballX > GameConstants.BALL_MIN_X) {

					canBounce = (ballY >= computerRacket_Y
							&& ballY < (computerRacket_Y + GameConstants.RACKET_LENGTH) ? true
									: false);

					ballX -= GameConstants.BALL_INCREMENT;

					// Add up or down slide to any left/right ball
					// movement

					if (ballY <= GameConstants.TABLE_TOP) {
						verticalSlide = -1;

					} else if (ballY >= (GameConstants.TABLE_BOTTOM + 20)) {
						verticalSlide = 1;
					}

					ballY -= verticalSlide;

					table.setBallPosition(ballX, ballY);
					// Can bounce?
					if (ballX <= GameConstants.COMPUTER_RACKET_X && canBounce) {
						movingLeft = false;
					}
				}

				System.out.println("In run() 2");

				// Step 2. Is ball moving to the right?
				if (!movingLeft && ballX <= GameConstants.BALL_MAX_X) {
					canBounce = (ballY >= kidRacket_Y
							&& ballY < (kidRacket_Y + GameConstants.RACKET_LENGTH) ? true
									: false);

					ballX += GameConstants.BALL_INCREMENT;

					if (ballY <= GameConstants.TABLE_TOP) {
						verticalSlide = -1;

					} else if (ballY >= (GameConstants.TABLE_BOTTOM + 20)) {
						verticalSlide = 1;
					}

					ballY -= verticalSlide;

					table.setBallPosition(ballX, ballY);
					// Can bounce?
					if (ballX >= (GameConstants.KID_RACKET_X - 10) && canBounce) {
						if (ballY + GameConstants.BALL_CENTER > kidRacket_Y
								+ GameConstants.RACKET_CENTER + 5) {
							verticalSlide = -5;
						} else if (ballY + GameConstants.BALL_CENTER < kidRacket_Y
								+ GameConstants.RACKET_CENTER - 5) {
							verticalSlide = 5;
						} else
							verticalSlide = 0;

						movingLeft = true;
					}
				}

				// Step 3. Move computer's racket up or down
				// to block the ball

				System.out.println("In run() 3");

				if (computerRacket_Y < ballY
						&& computerRacket_Y < GameConstants.TABLE_BOTTOM) {
					computerRacket_Y += GameConstants.RACKET_INCREMENT;
				} else if (computerRacket_Y > GameConstants.TABLE_TOP) {
					computerRacket_Y -= GameConstants.RACKET_INCREMENT;
				}
				table.setComputerRacket_Y(computerRacket_Y);

				System.out.println("In run() 4");

				// Step 4. Sleep a little
				try {
					System.out.println("Sleeping for "
							+ table.getSleep_time());
					Thread.sleep(table.getSleep_time());
					System.out.println("Woke up");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Step 5. Update the score if the ball is in the
				// green area but is not moving
				if (isBallOnTheTable()) {
					if (ballX > GameConstants.BALL_MAX_X) {
						computerScore++;
						displayScore();
					} else if (ballX < GameConstants.BALL_MIN_X) {
						kidScore++;
						displayScore();
					}
				}

			} // End if ballServed
		} // End while
	}// End run()

	// Serve from the current position of the kid's racket
	private void kidServe() {

		if (newGameFlag) {

			System.out.println("In kidServe() 1");
			ballServed = true;
			ballX = GameConstants.KID_RACKET_X - 10;
			ballY = kidRacket_Y;

			System.out.println("ballServed is " + ballServed);

			if (ballY > GameConstants.TABLE_HEIGHT / 2) {
				verticalSlide = -1;
			} else {
				verticalSlide = 1;
			}

			table.setBallPosition(ballX, ballY);
			table.setKidRacket_Y(kidRacket_Y);

		}

	}

	private void displayScore() {
		System.out.println("In displayScore() ");
		ballServed = false;

		if (computerScore == table.getWining_score() && newGameFlag) {

			table.setMessageText("Computer won! " + computerScore + ":"
					+ kidScore);
			CurrentScore currScore = new CurrentScore("Computer", kidScore,
					computerScore);
			table.populateScoreList(addScore(top10, currScore));
			newGameFlag = false;

		} else if (kidScore == table.getWining_score() && newGameFlag) {

			table.setMessageText("Kid won! " + kidScore + ":" + computerScore);
			CurrentScore currScore = new CurrentScore("Kid", kidScore,
					computerScore);
			table.populateScoreList(addScore(top10, currScore));
			newGameFlag = false;

		} else {
			table.setMessageText("Computer: " + computerScore + " Kid: "
					+ kidScore);
		}
	}

	// add, sort and return strings array of scores
	private String[] addScore(ArrayList<CurrentScore> top10, CurrentScore currScore) {

		CurrentScore score;
		String top10Strings[] = new String[10];
		boolean done = false;

		if (top10.size() == 0) { // add first record
			top10.add(currScore);
		} else
			for (int i = 0; i < top10.size(); i++) { // sort records
				score = top10.get(i);
				if (Math.abs(currScore.getKidScore() - currScore.getComputerScore()) >= Math
						.abs(score.getKidScore() - score.getComputerScore())) {
					top10.add(i, currScore);
					done = true;
					break;
				}
			}
		
		if (!done) {
			top10.add(currScore);
		}

		if (top10.size() > 10) { // store only best 10 scores
			top10.remove(10);
		}

		for (int i = 0; i < top10.size(); i++) { // populate the string list
			// with scores
			score = top10.get(i);
			top10Strings[i] = score.toString();
		}

		return top10Strings;
	}

	// write scores to a file
	private void writeScores (ArrayList<CurrentScore> top10, String fName) {

		DataOutputStream dataOut = null;

		try {
			dataOut = new DataOutputStream (new BufferedOutputStream (new FileOutputStream(fName)));
			for (int i=0; i < top10.size(); i++) {
				dataOut.writeInt(top10.get(i).getWinner()=="Kid"?0:1);
				dataOut.writeInt(top10.get(i).getKidScore());
				dataOut.writeInt(top10.get(i).getComputerScore());
			}
		} catch (IOException e) {
			System.out.println ("Could not write file:" + e.toString());
		} finally {
			if (dataOut !=null ){
				try {
					dataOut.flush();
					dataOut.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	// check if ball did not cross the top or bottom
	// borders of the table
	private boolean isBallOnTheTable() {
		if (ballY >= GameConstants.BALL_MIN_Y
				&& ballY <= GameConstants.BALL_MAX_Y) {
			return true;
		} else {
			return false;
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "New") {
			startNewGame();
		} else if (e.getActionCommand() == "Serve") {
			kidServe();
		} else if (e.getActionCommand() == "Quit") {
			endGame();
		}

	}
}