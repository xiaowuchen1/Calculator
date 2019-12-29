package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import Plane.Config;

public class Property
{
	
	public int x,y,w,h;
	
	public ArrayList<Image> image = new ArrayList<>();
	
	public int type;
	
	/**
	 * 
	 * 构造方法
	 * 
	 */
	public Property(int type)
	{
		image.add(new ImageIcon(Config.blood).getImage());
		image.add(new ImageIcon(Config.bomb5).getImage());
		
		this.type = type;
		
		this.w = image.get(type).getWidth(null);
		this.h = image.get(type).getHeight(null);
		
		this.x = (Config.window_width - this.w)/2;
		
		this.y = -50;
	}
	
	
	public void draw(Graphics g)
	{
		g.drawImage(image.get(type),x,y,w,h, null);
	}
	
	int a = new Random().nextInt(2);
	public void move()
	{
		if(a == 0)
		{
			x++;
			y = y + 2;
		}
		if(a == 1)
		{
			x --;
			
			y = y + 2;
		}
		if(x >= Config.window_width - w)
		{
			a = 1;
		}
		if(x <= 0)
		{
			a = 0;
		}
		
	}
}
