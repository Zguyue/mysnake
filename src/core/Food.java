package core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Queue;
import java.util.Random;

import org.w3c.dom.Node;

import Util.ImageUtil;
import constant.CONSTANT;

public class Food {
	
	boolean live;
	int x;
	int y;
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}


	Image image;
	
	public Food(){
		Random random=new Random();

		x=random.nextInt(CONSTANT.window_width)/5;
		y=random.nextInt(CONSTANT.window_height)/5;
		x*=5;
		y*=5;
		
		image=ImageUtil.getImage("img/food1.png");
		live=true;
	}
	
	public void isBound() {
		int band=50;
		if(x<(0+band)||x>(CONSTANT.window_width-band)||y<(0+band)||y>(CONSTANT.window_height-band)) {
			live=false;
		}
	}
	public void isEaten(MySnake snake){
		Queue<Point> body=snake.getBody();
		if(snake.getHead().x==x&&snake.getHead().y==y) {
			
			Point newnode=new Point(0,0);
			body.offer(newnode);
			snake.setBody(body);
			//int len=snake.getBody().size();
			live=false;
		}
		for(Point node:body) {
			if(node.x==x&&node.y==y) {
				live=false;
			}
		}
	}


	//绘制食物方法
	public void draw(Graphics g){
		g.drawImage(image, x, y, null);
	}
}
