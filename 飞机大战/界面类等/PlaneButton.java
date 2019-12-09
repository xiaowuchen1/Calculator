package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Plane.Config;
import Plane.MusicPlayer;

public class PlaneButton extends JButton
{
	/**
	 * 自定义按钮
	 */
	private static final long serialVersionUID = 1L;
	private boolean button_type = true;
	// 按钮显示文字
	private String text;

	// 构造方法
	public PlaneButton(String text)
	{
		this.text = text;

		this.setText(text);
		// 设置大小
		this.setPreferredSize(new Dimension(160, 45));
		this.setFocusable(false);
		// 不绘制背景
		this.setContentAreaFilled(false);
		// 不绘制边界
		this.setBorderPainted(false);

		// 按钮移进移出事件
		this.addMouseListener(new MouseListener()
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
				button_type = true;
			}
			@Override
			public void mouseEntered(MouseEvent e)
			{
				button_type = false;
				MusicPlayer music = new MusicPlayer(Config.button_sound);
				music.play();
			}
			@Override
			public void mouseClicked(MouseEvent e)
			{
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		// 绘制背景
		if (button_type == true)
		{
			g.drawImage(new ImageIcon(Config.button5).getImage(), 0, 0, 160, 45, null);
		} else
		{
			g.drawImage(new ImageIcon(Config.button6).getImage(), 0, 0, 160, 45, null);
		}
		// 绘制文字
		g.setFont(new Font("微软雅黑",Font.BOLD,15));
		g.drawString(text, 50, 27);

	}
}
