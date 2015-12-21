package pong;

import java.awt.Graphics;

public class Bar {
	int x;
	int y;
	
	public int getPos() { return y;}
	public void setPos(int y) { this.y = y;}
	
	public void drawBar(Graphics g) {
		g.fillRect(x, y, 5, 50);
	}
	
	
	

}
