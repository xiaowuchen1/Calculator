package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Plane.Config;

public class PlaneJFrame extends JFrame implements ActionListener
{
	/**
	 * 游戏JFrame
	 */
	private static final long serialVersionUID = 1L;
	
	//创建卡片布局
	public static CardLayout card;
	//创建JPanel
	public static JPanel mainJPanel;
	
	public GameJPanel gameJPanel;
	
	/**
	 * 构造方法
	 */
	public PlaneJFrame()
	{
		// 设置窗口位置和大小
		this.setBounds(Config.location_x, Config.location_y, Config.window_width, Config.window_height);
		// 设置标题
		this.setTitle("全民飞机大战");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setIconImage(new ImageIcon(Config.icon).getImage());
		
		//创建菜单栏
		JMenuBar menuBar = new JMenuBar();

		JMenu menu2 = new JMenu("选项");
		
		JMenuItem item1 = new JMenuItem("返回主界面");
		JMenuItem item2 = new JMenuItem("退出");
		
		//添加鼠标监听事件
		item1.addActionListener(this);
		item2.addActionListener(this);
		
		//添加菜单栏
		menuBar.add(menu2);
		
		menu2.add(item1);
		menu2.add(item2);

		this.setJMenuBar(menuBar);
		
		// 顶层JPanel
		mainJPanel = new JPanel();
		// 设置卡片布局
		card = new CardLayout();
		mainJPanel.setLayout(card);
		
		// 游戏页面
		gameJPanel = new GameJPanel(this);
		//加载页面
		LoadGame loadJPanel = new LoadGame(gameJPanel);
		// 欢迎页面
		JPanel welcomeJPanel = new WelcomeJPanel(loadJPanel);
		//排行页面
		JPanel rankJPanel = new RankJPanel();
		//帮助页面
		JPanel helpJPanel = new HelpJPanel();
		//商店页面
		JPanel storeJPanel = new StoreJPanel();
		//游戏设置
		JPanel setJPanel = new SetJPanel();

		/**
		 * 添加页面
		 */
		// 欢迎页面
		mainJPanel.add("welcomeJPanel", welcomeJPanel);
		// 加载游戏页面
		mainJPanel.add("LoadGame", loadJPanel);
		// 游戏页面
		mainJPanel.add("gameJPanel", gameJPanel);
		//添加排行页面
		mainJPanel.add("rankJPanel",rankJPanel);
		//添加帮助页面
		mainJPanel.add("helpJPanel",helpJPanel);
		//添加商店页面
		mainJPanel.add("storeJPanel",storeJPanel);
		//添加游戏设置
		mainJPanel.add("setJPanel",setJPanel);
		// 添加主页面
		this.add(mainJPanel);
		
		this.setAlwaysOnTop(true);
		// 设置不能改变大小
		this.setResizable(false);
		// 设置可见
		this.setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand() == "返回主界面")
		{
			//游戏结束
			gameJPanel.GameOver();
			PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "welcomeJPanel");
		}
		if(e.getActionCommand() == "退出")
		{
			//退出游戏
			System.exit(0);
		}
	}
}
