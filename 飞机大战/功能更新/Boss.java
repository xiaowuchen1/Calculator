package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Plane.Config;
/**
 * 
 * Boss类
 * @author HE
 *
 */
public class Boss
{
	public int x,y,w,h;
	
	public int type;
	
	public int count = 1000;
	
	public int countAll = count;
	
	private ArrayList<Image> imageList = new ArrayList<>();
	
	
	public Boss(int x,int y,int w,int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.type = 0;
		
		imageList.add(new ImageIcon(Config.enemy7).getImage());
		imageList.add(new ImageIcon(Config.enemy12).getImage());
		imageList.add(new ImageIcon(Config.enemy13).getImage());
		imageList.add(new ImageIcon(Config.enemy14).getImage());
		imageList.add(new ImageIcon(Config.enemy17).getImage());
	}
	
	//水平移动方向
	private int a = 0;
	
	public void draw(Graphics g)
	{
		g.drawImage(imageList.get(type),x,y,w,h, null);
		g.setColor(Color.white);
		g.drawRect(x, y + h + 10, w, 10);
		g.setColor(Color.red);
		g.fillRect(x, y + h + 10, w * count/countAll , 10);
		g.setColor(Color.white);
		g.drawString(String.valueOf(count), x + w/2 - 15, y + h + 20);
	}
	
	//Boss移动
	public void move()
	{
		if(y < 50)
		{
			y++;
		}
		if(a == 0)
		{
			x++;
		}
		else if(a == 1)
		{
			x--;
		}
		if(x == 0)
		{
			a = 0;
		}
		if(x == 480 - w)
		{
			a = 1;
		}
		
	}
	
	//检测碰撞
	public boolean isGetRect(int rectX,int rectY,int rectW,int rectH)
	{
		Rectangle rect = new Rectangle(x, y, w, h);
			
		if(rect.intersects(new Rectangle(rectX, rectY, rectW, rectH)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void changeBoss()
	{
		type++;
		if(type == 5)
		{
			type = 0;
		}
		w = imageList.get(type).getWidth(null);
		h = imageList.get(type).getHeight(null);
		
	}
}
