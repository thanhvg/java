package ball;

import javax.swing.JFrame;

public class ballTest {
	public static void main(String args[]) {
		Ball aball = new Ball();
		JFrame aframe = new JFrame();
		aframe.add(aball);
		aframe.setSize(600,400);
		aframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aframe.setTitle("Moving ball");
		aframe.setVisible(true);				
	}

}
