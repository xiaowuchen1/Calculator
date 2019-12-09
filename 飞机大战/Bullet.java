package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 
 * 子弹类
 * @author HE
 *
 */

public class Bullet
{
	// 属性
	public int x, y, w, h;
	// 图片
	public Image bg;
	//子弹偏移角度
	public int deg;

	/**
	 * 构造方法
	 */
	public Bullet(int x, int y, int w, int h, Image bg, int deg)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.bg = bg;
		this.deg = deg;
	}

	/**
	 * 画子弹
	 * @param g
	 */
	public void draw(Graphics g)
	{
		g.drawImage(bg, x, y, w, h, null);
	}

	/**
	 * 移动
	 * 
	 * @param i
	 */
	public void move(int i)
	{
		switch (i)
		{
			case 0:
				y = y + 3;
				x = x + deg;
				break;
			case 1:
				y = y - 5;
				x = x + deg;
				break;

			default:
				break;
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
}
