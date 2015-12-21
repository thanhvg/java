package pong2;

import javax.swing.JFrame;

public class Game implements GameConstants {
    public static void main(String args[]) {
	GUI gui = new GUI();
	JFrame f = new JFrame();
	f.setSize(BOX_WIDTH,BOX_HEIGHT+30);
	f.add(gui);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setTitle("Moving ball");
	
	f.setVisible(true);	
	//f.pack();
    }
}
