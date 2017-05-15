package gameClass;

import java.awt.Graphics;

public class GameObject {
	private int x;
	private int y;
	protected GameObject(int x, int y){
		this.x = x;
		this.y = y;
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	void draw(Graphics g){
		g.fillRect(x, y, 100, 100);
	}
}
