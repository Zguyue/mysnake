package core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

import Util.ImageUtil;
import constant.CONSTANT;

public class MySnake {
	int size;
	//蛇的运动方向 0上，1右，2左，3下
	int direction;
	//蛇头
	Point head;
	//蛇的身体
	Queue<Point> body;
	//蛇的状态
	boolean live;
	//蛇身初始长度
	int len;
	public MySnake(Point head){
		this.head=head;
		init();
		//LinkedList<Point> body=new LinkedList<>();
		live=true;
	}
	
	private void init() {
		//初始化节点大小
		size=5;
		//初始化蛇头位置
		head.x=CONSTANT.window_width/2;
		head.y=CONSTANT.window_height/2;
		//初始化蛇身长度和节点
		len=3;
		body=new LinkedList<>();
		for(int i=1;i<len+1;i++) {
			body.add(new Point(head.x,head.y+i*size));
		}
		
	}
	public void draw(Graphics g) {
		//判断是否碰撞边界或者蛇头碰到蛇身
		if(isBound()||isEaten()) {
			live=false;
		}
		//画出蛇头
		g.drawImage(ImageUtil.getImage("img/snake_head1.png"),head.x,head.y,null);
		//画出蛇身
		for(Point node:body) {
			g.drawImage(ImageUtil.getImage("img/snake_body1.png"),node.x,node.y,null);
		}
		//移动蛇身
		move();
	}
	private boolean isBound() {
		// 判断是否碰到边界
		if(head.x<0||head.x>CONSTANT.window_width||head.y<0||head.y>CONSTANT.window_height) {
			return true;
		}
		return false;
	}
	private Boolean isEaten() {
		//判断蛇头是否碰到蛇身
		for(Point node:body) {
			if(node.equals(head)) {
				return true;
			}
		}
		return false;
	}

	public void move() {
		Point prev=new Point(head.x,head.y);
		Point tmp=new Point(0,0);
		if(direction==0) {
			head.y-=size;
			for(Point node:body) {
				tmp.x=node.x;
				tmp.y=node.y;
				
				node.x=prev.x;
				node.y=prev.y;
				
				prev.x=tmp.x;
				prev.y=tmp.y;
			}
		}else if(direction==1) {
			head.x+=size;
			for(Point node:body) {
				tmp.x=node.x;
				tmp.y=node.y;
				
				node.x=prev.x;
				node.y=prev.y;
				
				prev.x=tmp.x;
				prev.y=tmp.y;
			}
		}else if(direction==2) {
			head.y+=size;
			for(Point node:body) {
				tmp.x=node.x;
				tmp.y=node.y;
				
				node.x=prev.x;
				node.y=prev.y;
				
				prev.x=tmp.x;
				prev.y=tmp.y;
			}
		}else {
			head.x-=size;
			for(Point node:body) {
				tmp.x=node.x;
				tmp.y=node.y;
				
				node.x=prev.x;
				node.y=prev.y;
				
				prev.x=tmp.x;
				prev.y=tmp.y;
			}
		}
	}
	


	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (direction!=2) {// 不能向初始方向的反方向移动
				direction=0;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (direction!=0) {// 不能向初始方向的反方向移动
				direction=2;
			}
			break;
		case KeyEvent.VK_LEFT:
			if (direction!=1) {// 不能向初始方向的反方向移动
				direction=3;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (direction!=3) {// 不能向初始方向的反方向移动
				direction=1;
			}
			break;
		}
	}
	
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public Point getHead() {
		return head;
	}
	public void setHead(Point head) {
		this.head = head;
	}

	public Queue<Point> getBody() {
		return body;
	}

	public void setBody(Queue<Point> body) {
		this.body = body;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
}
