package sprites;

import game.Constant;

import java.awt.Graphics;

public class BarRight extends Sprite {

	public BarRight(int x, int y, int dx, int dy) {
		super(x, y, dx, dy);		
	}

	@Override
	public void draw(Graphics g) {
		g.fillRect(x, y, Constant.BAR_WIDTH, Constant.BAR_HEIGHT);
	}

}
