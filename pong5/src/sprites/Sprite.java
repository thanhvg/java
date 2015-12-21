package sprites;

import java.awt.Graphics;

public abstract class Sprite  {
	int x, y;
	int dx, dy; //speed 

	//x, y getters setters
	public int getX() { 
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	//dx, dx getters setters
	public int getdX() { 
		return dx;
	}

	public int getdY() {
		return dy;
	}

	public void setdX(int dx) {
		this.dx = dx;
	}

	public void setdY(int dy) {
		this.dy = dy;
	}

	public void init(int x, int y, int dx, int dy) {
		this.x = x   ;
		this.y = y   ;
		this.dx = dx ;
		this.dy = dy ;
	}

	public Sprite(int x, int y, int dx, int dy)  {
		init (x,y,dx,dy);
	}

	public abstract void draw(Graphics g);
	//public abstract void move();

}
