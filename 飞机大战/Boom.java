package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Plane.Config;

public class Boom
{
	int x,y,w,h;
	
	public int type = 0;
	
	public ArrayList<Image> boomImage = new ArrayList<>();
	
	public Boom(int x,int y, int w,int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		boomImage.add(new ImageIcon(Config.boom1).getImage());
		boomImage.add(new ImageIcon(Config.boom2).getImage());
		boomImage.add(new ImageIcon(Config.boom3).getImage());
		boomImage.add(new ImageIcon(Config.boom4).getImage());
	}
	
	public void draw(Graphics g)
	{
		if(type < 4)
		{
			g.drawImage(boomImage.get(type),x,y,w,h,null);
		}
	}
}
