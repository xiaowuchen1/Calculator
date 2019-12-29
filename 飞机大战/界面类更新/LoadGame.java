package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Plane.Config;
import Plane.MusicPlayer;

/**
 * 游戏加载页面
 * 
 * @author Administrator
 *
 */
public class LoadGame extends JPanel implements Runnable
{

	private static final long serialVersionUID = 1L;
	// 背景图片
	public Image bg;
	// 声明飞机图片
	public Image logo_plane, bomb3;
	public Font f;
	// 飞机的xy
	private int x = 0;
	// 定义线程对象
	private Thread thread;
	// 加载动画的状态
	private boolean logo_type = true;
	
	public GameJPanel gameJPanel;

	/**
	 * 构造方法
	 * @param gameJPanel 
	 */
	public LoadGame(GameJPanel gameJPanel)
	{
		// 实例化背景图片
		bg = new ImageIcon(Config.img_bg_level_2).getImage();
		// 定义字体
		f = new Font("", Font.BOLD, 25);
		
		this.gameJPanel = gameJPanel;
		
		this.setLayout(null);

	}

	/**
	 * 开始线程，重绘
	 */
	public void logoin()
	{
		MusicPlayer music = new MusicPlayer(Config.loading_sound);
		music.play();
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * 绘制背景（自动调用） 多次运行 线程在后台更新ui repaint
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(bg, 0, 0,480,700, null);

		g.drawImage(new ImageIcon(Config.myPlane).getImage(),(Config.window_width - 116)/2, 30,120,79, null);
		g.setColor(Color.WHITE);
		g.drawRect(19,Config.window_height - 201, 440, 42);
		g.setColor(Color.ORANGE);
		g.fillRect(20,Config.window_height - 200, 440 * x/100, 40);
		g.setColor(Color.ORANGE);
		g.setFont(new Font("微软雅黑",Font.BOLD,40));
		g.drawString("全民飞机大战", (Config.window_width - 250)/2, 180);
		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("游戏加载中...", 150,400);
	}
	
	/**
	 * 线程run方法
	 */
	
	@Override
	public void run()
	{
		while (logo_type)
		{
			// 修改x坐标 重绘
			x = x + 1;
			repaint();
			try
			{
				Thread.sleep(10);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if (x > 100)
			{
				logo_type = false;//结束线程
			}
		}
		logo_type = true;
		x = 0;
		// 游戏页面
		PlaneJFrame.card.show(PlaneJFrame.mainJPanel, "gameJPanel");
		// 开始游戏
		gameJPanel.startGame();
	}

}
