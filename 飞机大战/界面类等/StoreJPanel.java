package view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Plane.Config;

public class StoreJPanel extends JPanel
{

	/**
	 * 游戏商店页面
	 */
	
	private static final long serialVersionUID = 9017770422731913111L;
	
	/**
	 * 构造方法
	 */
	public StoreJPanel()
	{
		this.setLayout(null);
		JButton button = new PlaneButton("返回首页");
		button.setBounds((Config.window_width - 160)/2, Config.window_height - 150, 160, 45);
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
		g.drawImage(new ImageIcon(Config.img_bg_level_2).getImage(), 0, 0,Config.window_width,Config.window_height, null);
	}
}
