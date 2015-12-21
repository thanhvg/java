package pong4;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GUI extends JPanel {
	private Ball ball;
	private Bar1 bar1; // user controlled bar
	private Bar2 bar2;
	private Timer timer;
	private EventCenter eventCenter; // this will gather all the events and call the classes that handle them
	
	
}
