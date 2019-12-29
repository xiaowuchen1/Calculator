package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Plane.Config;

public class WelcomeJPanel extends JPanel implements ActionListener
{
	/**
	 * 游戏首页
	 */
	private static final long serialVersionUID = 1L;

	public LoadGame loadJPanel;

	// 绘制背景
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(new ImageIcon(Config.img_bg_level_2).getImage(), 0, 0, 480, 700, null);
		g.drawImage(new ImageIcon(Config.myPlane).getImage(), (Config.window_width - 116)/2, 30,120,79, null);
		g.setFont(new Font("微软雅黑",Font.BOLD,40));
		g.setColor(Color.white);
		g.drawString("全民飞机大战", (Config.window_width - 240)/2, 165);
	}

	/**
	 * 构造方法
	 * 
	 * @param loadJPanel
	 * 
	 */
	public WelcomeJPanel(LoadGame loadJPanel)
	{
		this.loadJPanel = loadJPanel;
		// 定义字体
		Font f = new Font("", Font.BOLD, 20);
		// logo 图片
		JLabel logo = new JLabel(new ImageIcon(Config.Logo));

		// 按钮区域 拍成一列
		JButton button1 = new PlaneButton("开始游戏");
		JButton button2 = new PlaneButton("游戏帮助");
		JButton button3 = new PlaneButton("游戏设置");
		JButton button4 = new PlaneButton("游戏商店");
		JButton button5 = new PlaneButton("排行榜");
		JButton button6 = new PlaneButton("退出游戏");

		// 版权所有
		JLabel plane_info = new JLabel("版权所有");
		// 设置字体
		plane_info.setFont(f);
		//设置颜色
		plane_info.setForeground(Color.white);

		// 设置边界布局
		this.setOpaque(false);

		// 设置布局
		this.setLayout(null);

		// 设置位置
		logo.setBounds((Config.window_width - 230) / 2, 40, 230, 140);
		button1.setBounds((Config.window_width - 160) / 2, 200, 160, 45);
		button2.setBounds((Config.window_width - 160) / 2, 260, 160, 45);
		button3.setBounds((Config.window_width - 160) / 2, 320, 160, 45);
		button4.setBounds((Config.window_width - 160) / 2, 380, 160, 45);
		button5.setBounds((Config.window_width - 160) / 2, 440, 160, 45);
		button6.setBounds((Config.window_width - 160) / 2, 500, 160, 45);
		plane_info.setBounds((Config.window_width - 90) / 2, 560, 90, 45);

		// 添加按钮事件
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);

		// 添加Logo
//		this.add(logo);
		// 添加按钮
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(button4);
		this.add(button5);
		this.add(button6);
		// 添加版权信息
		this.add(plane_info);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String text = e.getActionCommand();

		if (text == "开始游戏")
		{
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "LoadGame");
			loadJPanel.logoin();
		}
		if (text == "退出游戏")
		{
			System.exit(0);
		}
		if(text == "排行榜")
		{
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "rankJPanel");
		}
		if(text == "游戏帮助")
		{
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "helpJPanel");
		}
		if(text == "游戏商店")
		{
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "storeJPanel");
		}
		if(text == "游戏设置")
		{
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "setJPanel");
		}

	}
}
