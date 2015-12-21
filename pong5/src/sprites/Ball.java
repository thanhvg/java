package sprites;

import game.Constant;

import java.awt.Graphics;

public class Ball extends Sprite {

	public Ball(int x, int y, int dx, int dy) {
		super(x, y, dx, dy);
	}

	@Override
	public void draw(Graphics g) {
		g.fillOval(x,y,Constant.BALL_SIZE,Constant.BALL_SIZE);
		
	}

	

}
