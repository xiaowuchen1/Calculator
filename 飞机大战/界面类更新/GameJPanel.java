package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Plane.Config;
import Plane.MusicPlayer;
import entity.Boom;
import entity.Boss;
import entity.Bullet;
import entity.Enemy;
import entity.FileIO;
import entity.Plane;
import entity.Property;

/** 
 * 
 *  游戏页面
 * 
 */
public class GameJPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener,KeyListener
{
	
	private static final long serialVersionUID = 1L;
	
	//存放游戏背景图
	public ArrayList<Image> image = new ArrayList<>();
	//文件读写
	FileIO io = new FileIO();
	//道具包时间
	private int fireTime = 0;
	//关数
	public int level = 1;
	
	public int bgCount = 0;
	//显示过关图片
	public int nextLevelCount = 0;
	public boolean nextLevel = false;
	//是否暂停游戏
	public static boolean isPause = false;
	// 分数
	public Integer number = 0;
	// 背景图片的y坐标
	private int bg_y = 0;
	// 游戏逻辑线程
	private Thread thread;
	// 游戏是否结束
	private static boolean isGameOver = true;
	// 玩家飞机对象
	private Plane myPlane;
	//是否出现Boss
	boolean showBoss = false;
	//新建Boss对象
	public Boss boss;
	// 定义数组存放玩家子弹
	private ArrayList<Bullet> my_plane_bullet = new ArrayList<>();
	// 存放敌机
	private ArrayList<Enemy> enemy = new ArrayList<>();
	//存放爆炸效果
	private ArrayList<Boom> boom = new ArrayList<>();
	//存放敌机子弹
	private ArrayList<Bullet> enemy_bullet = new ArrayList<>();
	//存放游戏道具
	private ArrayList<Property> property = new ArrayList<>();
	//定义重绘次数
	private int repaintCount = 1;
	
	public MusicPlayer music = new MusicPlayer(Config.bgm);
	
	/**
	 * 
	 * 线程run方法
	 * 
	 */
	@Override
	public void run()
	{
		while (!isGameOver)
		{	
			repaint();
			//游戏暂停
			while(isPause)
			{
				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				if(!isPause)
				{
					break;
				}
			}
			
			//移动背景
			bg_y = bg_y + 1;
			if (bg_y >= Config.window_height)
			{
				bg_y = 0;
			}
			try
			{
				Thread.sleep(10);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			//Q键技能冷却时间
			if(skill_Q > 0)
			{
				skill_Q --;
			}
			//W键技能冷却时间
			if(skill_W > 0)
			{
				skill_W --;
			}
			//道具包时间
			if(fireTime > 0)
			{
				fireTime -- ;
			}
			//E技能冷却时间
			if(skill_E > 0)
			{
				skill_E --;
			}
		}	
	}
	

	/**
	 * 
	 * 构造方法
	 * @param gameJFrame
	 * 
	 */
	public GameJPanel(PlaneJFrame gameJFrame)
	{
		
		//设置鼠标
		Image ImageCursor = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(""));
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageCursor, new Point(0, 0), "cursor"));
		//玩家飞机
		myPlane = new Plane(0, 0, 120, 79, new ImageIcon(Config.myPlane).getImage());
		//Boss
		boss = new Boss(150,-300,120,113);
		//做鼠标点击监听
		this.addMouseListener(this);
		//添加键盘监听
		gameJFrame.addKeyListener(this);
		//鼠标移动监听
		this.addMouseMotionListener(this);
		
		image.add(new ImageIcon(Config.img_bg_level_2).getImage());
		image.add(new ImageIcon(Config.img_bg_level_1).getImage());
		image.add(new ImageIcon(Config.img_bg_level_3).getImage());
		
	}

	/**
	 * 开始游戏
	 */
	public void startGame()
	{
		level = 1;
		isPause = false;
		bgCount = 0;
		skill_Q = 0;
		skill_W = 0;
		skill_E = 0;
		//创建线程
		if (isGameOver)
		{
			myPlane.x = 200;
			myPlane.y = 600;
			/**
			 * 重新开始游戏
			 */
			//游戏运行状态
			isGameOver = false;
			thread = new Thread(this);
			//开始线程
			thread.start();
			//播放音乐
			music.loop();
		}
	}

	/**
	 * 绘制游戏界面
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//绘制游戏页面的背景（移动）
		g.drawImage(image.get(bgCount), 0, bg_y, Config.window_width, Config.window_height, null);
		g.drawImage(image.get(bgCount), 0, bg_y - 700, Config.window_width, Config.window_height, null);
		
		if(!nextLevel)
		{
			//画敌机
			drawEnemy(g);
			//画玩家飞机的子弹
			drawMyBullet(g);
			//画敌机子弹
			drawEnemyBullet(g);
			//画道具包
			drawProperty(g);
		}
		//显示过关图片
		else
		{
			g.drawImage(new ImageIcon(Config.guoguan).getImage(), (Config.window_width - 253)/2, (Config.window_height - 91)/2 - 150, 253, 91, null);
			nextLevelCount++;
			if(nextLevelCount == 300)
			{
				repaintCount -= nextLevelCount;
				nextLevel = false;
				nextLevelCount = 0;
				//清空玩家子弹和敌机子弹集合
				my_plane_bullet.clear();
				enemy_bullet.clear();
				//关数加1
				level++;
				bgCount++;
				if(bgCount == 3)
				{
					bgCount = 0;
				}
			}
		}
		//碰撞检测
		Collision(g);
		//画玩家飞机 
		myPlane.draw(g);
		//画护盾
		drawShield(g);
		//画爆炸效果
		drawBoom(g);
		//绘制分数
		drawNumber(g);
		//画技能图标
		drawSkill(g);
		//画技能插图
		drawSkillPicture(g);
		//重绘次数加一
		repaintCount++;
	}
	
	/**
	 * 画技能插图
	 * @param g
	 */
	private void drawSkillPicture(Graphics g)
	{
		if(skill_Q > 400)
		{
			g.drawImage(new ImageIcon(Config.skillQ).getImage(), (Config.window_width - 200)/2, (Config.window_height - 200)/2, 200, 200, null);
		}
		if(skill_W > 400)
		{
			g.drawImage(new ImageIcon(Config.skillW).getImage(), (Config.window_width - 200)/2, (Config.window_height - 200)/2, 200, 200, null);
		}
		if(skill_E > 800)
		{
			g.drawImage(new ImageIcon(Config.skillE).getImage(), (Config.window_width - 200)/2, (Config.window_height - 200)/2, 200, 200, null);
		}
		
	}


	/**
	 * 画护盾
	 * @param g
	 */
	private void drawShield(Graphics g)
	{
		if(skill_E > 500)
		{
			g.drawImage(new ImageIcon(Config.shield).getImage(), myPlane.x - myPlane.w/2, myPlane.y - 65, 120,120,null);
		}
		
	}


	/**
	 * 
	 * 画道具包
	 * @param g
	 */
	private void drawProperty(Graphics g)
	{
		if(repaintCount%1000 == 0)
		{
			//随机创建道具包
			Property p = new Property(new Random().nextInt(2));
			property.add(p);
		}
		
		//画道具包
		for(int i = 0; i < property.size(); i++)
		{
			Property p = property.get(i);
			p.draw(g);
			p.move();
			
			//判断是否获得道具包
			if(myPlane.isGetRect(p.x, p.y, p.w, p.h))
			{
				MusicPlayer music = new MusicPlayer(Config.jiaxue);
				music.play();
				//血量包
				if(p.type == 0)
				{
					property.remove(i);
					if(myPlane.hp <= 90)
					{
						myPlane.hp += 10;
					}
					else
					{
						myPlane.hp = 100;
					}
				}
				//子弹包
				if(p.type == 1)
				{
					property.remove(i);
					fireTime = 1000;
				}
			}
			
			//到达底部移除道具包
			if(p.x > Config.window_height)
			{
				property.remove(i);
			}
		}
	}


	/**
	 * 
	 * 创建玩家飞机子弹
	 * 
	 */
	private void drawMyBullet(Graphics g)
	{
		//创建Q技能子弹
		if(skill_Q > 300)
		{
			if(repaintCount%20 == 0)
			{
				for(int i = 0; i < 7; i++)
				{
					Bullet bullet = new Bullet(myPlane.x + i * 20 - 68,myPlane.y - 100, 15, 45,new ImageIcon(Config.bossBullet).getImage(),0);
					my_plane_bullet.add(bullet);
				}
			}
		}
		//创建W技能子弹
		if(skill_W > 300)
		{
			if(repaintCount%20 == 0)
			{
				for (int i = 0; i < 7; i++)
				{
					Bullet b = new Bullet(myPlane.x - 7, myPlane.y - 100, 15, 45, new ImageIcon(Config.bossBullet).getImage(),i - 3);
					my_plane_bullet.add(b);
				}
			}
		}
		
		//自动发射子弹
		if (repaintCount % 20 == 0)
		{
			//创建普通子弹
			Bullet b = new Bullet(myPlane.x - 7, myPlane.y - 100, 15, 45, new ImageIcon(Config.bossBullet).getImage(),0);
			my_plane_bullet.add(b);
			
			//创建获得道具包子弹
			if(fireTime > 0)
			{
				//左右各发射两列子弹
				Bullet b_l = new Bullet(myPlane.x - 7 - 30, myPlane.y - 100, 15, 20, new ImageIcon(Config.feidan).getImage(),0);
				Bullet b_r = new Bullet(myPlane.x - 7 + 30, myPlane.y - 100, 15, 20, new ImageIcon(Config.feidan).getImage(),0);
				my_plane_bullet.add(b_l);
				my_plane_bullet.add(b_r);
			}
		}

		//画子弹
		for (int i = 0; i < my_plane_bullet.size(); i++)
		{
			Bullet bullet = my_plane_bullet.get(i);
			bullet.draw(g);
			//子弹移动
			bullet.move(1);
			//子弹移除屏幕外
			if (bullet.y < 0)
			{
				my_plane_bullet.remove(bullet);
			}
		}
	}
	/**
	 * 创建敌机子弹
	 * @param g
	 */
	public void drawEnemyBullet(Graphics g)
	{
		if(showBoss)
		{
			//Boss发子弹
			if (repaintCount % 200 == 0)
			{
				MusicPlayer music = new MusicPlayer(Config.bossfashezidan1);
				music.play();
				//发射一排子弹
				if(boss.type == 0 && boss.y == 50)
				{
					for(int i = 0; i < 7; i++)
					{
						Bullet b = new Bullet(boss.x + boss.w/2 + i * 30 - 105, boss.y + boss.h, 15, 45, new ImageIcon(Config.bomb18).getImage(),0);
						enemy_bullet.add(b);
					}
				}
				//发射扇形子弹
				if((boss.type == 1 || boss.type == 2 || boss.type == 3 || boss.type == 4 ) && boss.y == 50)
				{
					for(int i = 0; i < 7; i++)
					{
						Bullet b = new Bullet(boss.x + boss.w/2, boss.y + boss.h, 15, 45, new ImageIcon(Config.bomb18).getImage(),i - 3);
						enemy_bullet.add(b);
					}
				}
				
			}
		}
		else
		{
			//新建普通敌机子弹
			if (repaintCount % 200 == 0)
			{
				for(int i = 0; i < enemy.size(); i++)
				{
					Enemy e = enemy.get(i);
					Bullet b = new Bullet(e.x + e.w/2, e.y + e.h, 10, 30, new ImageIcon(Config.bomb18).getImage(),0);
					enemy_bullet.add(b);
				}
				
			}
		}
	
		//画子弹
		for (int i = 0; i < enemy_bullet.size(); i++)
		{
			Bullet bullet = enemy_bullet.get(i);
			bullet.draw(g);
			//子弹移动
			bullet.move(0);
			//子弹移除屏幕外
			if (bullet.y > 700)
			{
				enemy_bullet.remove(bullet);
			}
		}
	}

	/**
	 * 
	 * 创建敌机
	 */
	public void drawEnemy(Graphics g)
	{
		//新建普通敌机
		if(!showBoss)
		{
			if (repaintCount % 50 == 0)
			{
				Enemy e = new Enemy(new Random().nextInt(Config.window_width - 100), -100,new Random().nextInt(5));
				enemy.add(e);
			}
			
			//重绘次数到2000出现boss
			if(repaintCount%2000 == 0)
			{
				showBoss = true;
				//Boss血量为重绘次数
				boss.count = repaintCount;
				boss.countAll = repaintCount;
				//定义Boss位置
				boss.x = (Config.window_width - boss.w)/2;
				boss.y = -200;
			}
		}
		//创建Boss
		else
		{
			boss.draw(g);
			//Boss移动
			boss.move();
		}
		
		//画敌机
		for (int i = 0; i < enemy.size(); i++)
		{
			Enemy enemy1 = enemy.get(i);
			enemy1.draw(g);
			//移动敌机
			enemy1.move();
			//屏幕外移除
			if (enemy1.x > 700)
			{
				enemy.remove(enemy1);
			}
		}
	}

	

	/**
	 * 碰撞检测
	 * @param g
	 */
	public void Collision(Graphics g)
	{
		//敌机子弹击中玩家飞机
		for(int i = 0; i < enemy_bullet.size(); i++)
		{
			Bullet b = enemy_bullet.get(i);
			
			if(b.isGetRect(myPlane.x - myPlane.w/2 + 30, myPlane.y - myPlane.h/2 + 30, myPlane.w - 50, myPlane.h - 50))
			{
				if(skill_E < 500)
				{
					//玩家血量减1
					myPlane.hp --;
					//添加爆炸效果
					boom.add(new Boom(myPlane.x - 25,myPlane.y - 25,50,50));
				}
				//移除子弹
				enemy_bullet.remove(b);
					
				//玩家血量为0 ， 游戏结束
				if(myPlane.hp <= 0)
				{
					GameOver();
				}
				
			}
			
		}
		//子弹击中Boss和敌机
		for (int i = 0; i < my_plane_bullet.size(); i++)
		{
			Bullet b = my_plane_bullet.get(i);
			
			//Boss碰撞检测
			if(showBoss)
			{	
					if(b.isGetRect(boss.x, boss.y, boss.w, boss.h))
					{
						//Boss血量减 10
						boss.count -= 10;
						//移除子弹
						my_plane_bullet.remove(b);
						//添加爆炸效果
						boom.add(new Boom(b.x - 25,b.y - 25,50,50));
					}
					if(boss.isGetRect(myPlane.x - myPlane.w/2, myPlane.y - myPlane.h/2, myPlane.w, myPlane.h))
					{
						//Boss血量减 10
						boss.count -= 10;
						//玩家血量减1
						if(skill_E < 500)
						{
							myPlane.hp -= 1;
						}
						//游戏结束
						if(myPlane.hp <= 0)
						{
							GameOver();
						}
					}
					if(boss.count <= 0)
					{
						//Boss不再出现
						showBoss = false;
						//添加爆炸效果
						boom.add(new Boom(boss.x, boss.y, 200, 200));
						repaintCount = boss.countAll;
						number += repaintCount;
						//改变Boss
						boss.changeBoss();
						//显示过关图片
						nextLevel = true;
						MusicPlayer music = new MusicPlayer(Config.polan);
						music.play();
					}
					
			}
			//击中敌机
			for (int j = 0; j < enemy.size(); j++)
			{
				Enemy e = enemy.get(j);
				
				if (b.isGetRect(e.x, e.y, e.w, e.h))
				{
					if(e.blood > 0)
					{
						//击中敌机敌机血量减少
						e.blood -= 50;
						my_plane_bullet.remove(b);
					}
					//敌机血量为零
					if(e.blood <= 0)
					{
						//分数增加100
						number += 100;
						//移除敌机
						enemy.remove(e);
						//移除子弹
						my_plane_bullet.remove(b);
						//添加爆炸效果
						boom.add(new Boom(e.x,e.y,100,100));
						MusicPlayer music = new MusicPlayer(Config.dabaozha);
						music.play();
					}
				}
				
				
				//玩家飞机与敌机碰撞
				if(e.isGetRect(myPlane.x - myPlane.w/2 + 30, myPlane.y - myPlane.h/2 + 30, myPlane.w - 50, myPlane.h - 50))
				{
					if(skill_E < 500)
					{
						myPlane.hp --;
					}
					enemy.remove(e);
					boom.add(new Boom(e.x,e.y,100,100));
					//血量为0，游戏结束
					if(myPlane.hp <= 0)
					{
						GameOver();
					}
				}
			}
		}
	}

	/**
	 * 
	 * 游戏结束
	 * 
	 */
	public void GameOver()
	{
		FileIO io = new FileIO();
		music.stop();
		isGameOver = true;
		isPause = false;
		boss = new Boss(150,-300,120,113);
		showBoss = false;
		repaintCount = 1;
		fireTime = 0;
		level = 1;
		//清空集合
		enemy.clear();
		my_plane_bullet.clear();
		enemy_bullet.clear();
		boom.clear();
		property.clear();
		//技能重置
		skill_Q = 0;
		skill_W = 0;
		skill_E = 0;
		try
		{
			//将分数写入文件
			io.writeNumber(number.toString());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		if(myPlane.hp <= 0)
		{
			int b = JOptionPane.showConfirmDialog(this,"游戏结束，是否重新开始？");
			if(b == JOptionPane.YES_OPTION)
			{
				//开始游戏
				startGame();
			}
			else
			{
				//显示欢迎页面
				PlaneJFrame.card.show(PlaneJFrame.mainJPanel,"welcomeJPanel");
			}
		}
		
		//将分数设为0
		number = 0;	
		//将血量设为100
		myPlane.hp = 100;
	}
	
	/**
	 * 画游戏分数和关数
	 * @param g
	 */
	public void drawNumber(Graphics g)
	{
		String s = number.toString();
		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.BOLD, 50));
		g.drawString(s, 10, 50);
		
		g.setFont(new Font("微软雅黑",Font.BOLD,20));
		
		g.drawString("第" + level + "关", 15, 80);
	}
	
	/**
	 * 画爆炸效果
	 */
	public void drawBoom(Graphics g)
	{
		for(int i = 0; i < boom.size(); i++)
		{
			Boom b = boom.get(i);
			
			b.draw(g);
			
			if(repaintCount%10 == 0)
			{
				b.type ++;
			}
			if(b.type == 4)
			{
				boom.remove(b);
			}
		}
	}
	
	/**
	 * 画技能图标
	 * 
	 * @param g
	 */
	//E技能冷却时间
	private int skill_E = 0;
	//Q技能冷却时间
	private int skill_Q = 0;
	//W技能冷却时间
	private int skill_W = 0;
	
	//画技能图标
	public void drawSkill(Graphics g)
	{
		//Q技能
		g.drawRect(Config.window_width - 81,Config.window_height - 151,52,52);
		g.drawImage(new ImageIcon(Config.m02c).getImage(),Config.window_width - 80,Config.window_height - 150,50,50,null);
		//W技能
		g.drawRect(Config.window_width - 81,Config.window_height - 221,52,52);
		g.drawImage(new ImageIcon(Config.m02d).getImage(),Config.window_width - 80,Config.window_height - 220,50,50,null);
		//E技能
		g.drawRect(Config.window_width - 81,Config.window_height - 291,52,52);
		g.drawImage(new ImageIcon(Config.shield).getImage(),Config.window_width - 80,Config.window_height - 290,50,50,null);
		//画时间
		if(skill_Q > 0)
		{
			g.setFont(new Font("",Font.BOLD,50));
			g.drawString(String.valueOf(skill_Q/100 + 1), Config.window_width - 70,Config.window_height - 105);
		}
		if(skill_W > 0)
		{
			g.setFont(new Font("",Font.BOLD,50));
			g.drawString(String.valueOf(skill_W/100 + 1), Config.window_width - 70,Config.window_height - 175);
		}
		if(skill_E > 0)
		{
			g.setFont(new Font("",Font.BOLD,50));
			g.drawString(String.valueOf(skill_E/100 + 1), Config.window_width - 70,Config.window_height - 245);
		}
	}
	
	/**
	 * 鼠标和键盘事件
	 */
	
	//鼠标点击发射扇形子弹
	@Override
	public void mouseClicked(MouseEvent g)
	{
		//增加一波扇形子弹
//		for (int i = 0; i < 7; i++)
//		{
//			Bullet b = new Bullet(myPlane.x - 7, myPlane.y - 100, 15, 45, new ImageIcon(Config.bossBullet).getImage(),i - 3);
//			my_plane_bullet.add(b);
//		}

	}
	//玩家飞机移动
	@Override
	public void mouseMoved(MouseEvent e)
	{
		//获取鼠标的坐标
		int x = e.getX();
		int y = e.getY();
		myPlane.move(x, y);
	}
	//键盘事件
	@Override
	public void keyPressed(KeyEvent e)
	{
		//按Q键触发事件(发射一排子弹)
		if(e.getKeyCode() == KeyEvent.VK_Q)
		{
			if(skill_Q == 0)
			{
				skill_Q = 500;
				MusicPlayer music = new MusicPlayer(Config.longshenzhili);
				music.play();
			}
		}
		//按W键触发事件
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			//增加一波扇形子弹
			if(skill_W == 0)
			{
				skill_W = 500;
				MusicPlayer music = new MusicPlayer(Config.jiluo);
				music.play();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_E)
		{
			//增加一波扇形子弹
			if(skill_E == 0)
			{
				skill_E = 900;
				MusicPlayer music = new MusicPlayer(Config.nice);
				music.play();
			}
		}
		
		//空格键事件(暂停游戏)
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(isPause == true)
			{
				isPause = false;
			}
			else
			{
				isPause = true;
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent g){}
	@Override
	public void mouseExited(MouseEvent g){}
	@Override
	public void mousePressed(MouseEvent g){}
	@Override
	public void mouseReleased(MouseEvent g){}
	@Override
	public void mouseDragged(MouseEvent e){}
	@Override
	public void keyTyped(KeyEvent e){}
	@Override
	public void keyReleased(KeyEvent e){}
}
