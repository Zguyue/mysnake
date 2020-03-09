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
	//�ߵ��˶����� 0�ϣ�1�ң�2��3��
	int direction;
	//��ͷ
	Point head;
	//�ߵ�����
	Queue<Point> body;
	//�ߵ�״̬
	boolean live;
	//�����ʼ����
	int len;
	public MySnake(Point head){
		this.head=head;
		init();
		//LinkedList<Point> body=new LinkedList<>();
		live=true;
	}
	
	private void init() {
		//��ʼ���ڵ��С
		size=5;
		//��ʼ����ͷλ��
		head.x=CONSTANT.window_width/2;
		head.y=CONSTANT.window_height/2;
		//��ʼ�������Ⱥͽڵ�
		len=3;
		body=new LinkedList<>();
		for(int i=1;i<len+1;i++) {
			body.add(new Point(head.x,head.y+i*size));
		}
		
	}
	public void draw(Graphics g) {
		//�ж��Ƿ���ײ�߽������ͷ��������
		if(isBound()||isEaten()) {
			live=false;
		}
		//������ͷ
		g.drawImage(ImageUtil.getImage("img/snake_head1.png"),head.x,head.y,null);
		//��������
		for(Point node:body) {
			g.drawImage(ImageUtil.getImage("img/snake_body1.png"),node.x,node.y,null);
		}
		//�ƶ�����
		move();
	}
	private boolean isBound() {
		// �ж��Ƿ������߽�
		if(head.x<0||head.x>CONSTANT.window_width||head.y<0||head.y>CONSTANT.window_height) {
			return true;
		}
		return false;
	}
	private Boolean isEaten() {
		//�ж���ͷ�Ƿ���������
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
			if (direction!=2) {// �������ʼ����ķ������ƶ�
				direction=0;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (direction!=0) {// �������ʼ����ķ������ƶ�
				direction=2;
			}
			break;
		case KeyEvent.VK_LEFT:
			if (direction!=1) {// �������ʼ����ķ������ƶ�
				direction=3;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (direction!=3) {// �������ʼ����ķ������ƶ�
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
