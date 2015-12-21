package pong;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameTest {
	public static void main(String args[]) throws InterruptedException {
		Game2 aGame = new Game2();
		JFrame f = new JFrame();
		f.add(aGame);
		f.setSize(605,435);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Moving ball");
		aGame.init();
		f.setVisible(true);	
	}

}
