package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Plane.Config;

public class HelpJPanel extends JPanel
{
	/**
	 * 帮助页面
	 */
	private static final long serialVersionUID = -5506961801477244517L;

	/**
	 * 构造方法
	 */
	public HelpJPanel()
	{
		//设置布局
		this.setLayout(null);
		
		JButton button = new PlaneButton("返回首页");
		
		button.setBounds((Config.window_width - 160)/2,Config.window_height - 150,160,45);
		
		this.add(button);
		
		button.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "welcomeJPanel");
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(new ImageIcon(Config.img_bg_level_2).getImage(),0,0,Config.window_width,Config.window_height,null);
		g.setColor(Color.white);
		g.drawRect(19,149,52,52);
		g.drawImage(new ImageIcon(Config.m02c).getImage(),20,150,50,50,null);
		g.drawRect(19,249,52,52);
		g.drawImage(new ImageIcon(Config.m02d).getImage(),20,250,50,50,null);
		g.drawRect(19,349,52,52);
		g.drawImage(new ImageIcon(Config.shield).getImage(),20,350,50,50,null);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Font f = new Font("微软雅黑",Font.BOLD,30);
		g.setColor(Color.white);
		g.setFont(f);
		g.drawString("游戏帮助", (Config.window_width - 150)/2, 80);
		g.setFont(new Font("微软雅黑",Font.BOLD,15));

		g.drawString("E技能，获得无敌护盾，持续4秒，冷却时间9秒",100,380);
		
		g.drawString("游戏中按空格键暂停或开始游戏", 120, 480);
	}
}
