package pong2;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Bar2 extends Sprite implements GameConstants {
	GUI gui;
	int    speed;
	public void draw(Graphics g) {
		g.fillRect(x, y, BAR_WIDTH,BAR_HEIGHT);
	}

	public Bar2(int x, int y, int dx, int dy) {
		super(x,  y, dx, dy);
	}

	public Bar2(int x, int y, int dx, int dy, GUI gui) {
		super(x,  y, dx, dy);
		this.gui = gui;
		this.speed = dy;
	}

	public void move() {
		y += speed;

		if (y < 0) {
			y = 0;
		}
		if (y > (BOX_HEIGHT-BAR_HEIGHT)) {
			y = (BOX_HEIGHT-BAR_HEIGHT);
		}
	}
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			speed = -dy;
		}

		if (key == KeyEvent.VK_DOWN) {
			speed = dy;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();


		if (key == KeyEvent.VK_UP) {
			speed = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			speed = 0;
		}
	}
}
