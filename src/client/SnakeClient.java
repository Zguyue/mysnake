package client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.util.LinkedList;

import Util.ImageUtil;
import constant.CONSTANT;
import core.Food;
import core.MyFrame;
import core.MySnake;


public class SnakeClient extends MyFrame{
	Point head=new Point(CONSTANT.window_width/2,CONSTANT.window_height/2);
	MySnake mySnake = new MySnake(head);//蛇
	Food food = new Food();//食物
	Image background=ImageUtil.getImage("img/background.png");
	Image fail=ImageUtil.getImage("img/fail.png");
	int width=background.getWidth(null);
	int heigth=background.getHeight(null);
	public void startGame() {
		super.loadFrame();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				mySnake.keyPressed(e);//委托给mysnake
			}
		});
	}
	//绘制界面
	@Override
	public void paint(Graphics g) {
		g.drawImage(background,0,0,null);
		if(mySnake.isLive()) {
			mySnake.draw(g);
//			mySnake.draw(g);
			if(food.isLive()) {
				food.draw(g);
				food.isBound();
				food.isEaten(mySnake);
			}else {
				food=new Food();
			}
		}else {
			g.drawImage(fail,300,200,null);
		}
	}
	public static void main(String args[]) {
		SnakeClient myclient=new SnakeClient();
		myclient.startGame();
	}
}
