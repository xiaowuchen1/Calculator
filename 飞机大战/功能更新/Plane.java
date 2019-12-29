package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import Plane.Config;

public class Plane
{
	// 属性
	public int x, y, w, h;// 位置+大小
	//生命值
	public int hp;
	// 图片
	public Image bg;// 飞机的图片

	public Plane(int x, int y, int w, int h, Image bg)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.bg = bg;
		this.hp = 100;
	}

	/**
	 * 画飞机
	 * 
	 * @param g
	 */
	public void draw(Graphics g)
	{
		g.drawImage(bg, x - w/2, y - h/2, w, h, null);
		g.setColor(Color.white);
		g.drawRect(Config.window_width - 220, 15, 200, 15);
		g.setColor(Color.RED);
		g.fillRect(Config.window_width - 220, 15, 200*hp/100, 15);
		g.setColor(Color.white);
		g.setFont(new Font("",Font.BOLD,15));
		g.drawString("生命值", Config.window_width - 280, 29);
		g.drawString(String.valueOf(hp),Config.window_width - 130, 29);
	}

	/**
	 * 移动
	 * 
	 * @param x
	 * @param y
	 */
	public void move(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	//检测碰撞
	public boolean isGetRect(int rectX,int rectY,int rectW,int rectH)
	{
		Rectangle rect = new Rectangle(x - w/2, y - h/2, w, h);
			
		if(rect.intersects(new Rectangle(rectX, rectY, rectW, rectH)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
