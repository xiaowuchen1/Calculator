package Plane;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 游戏音乐播放类
 * @author Administrator
 */
public class MusicPlayer 
{
	
	//声明音乐文件路径
	public String path;
	private Clip clip;
	
	public static boolean isPlay = true;
	
	/**
	 * 构造方法 
	 * @param path  文件路径
	 */
	public MusicPlayer(String path)
	{
		//参数实例化
		try {
			//创建音乐文件
			File file = new File(path);
			AudioInputStream stream = AudioSystem.getAudioInputStream(file) ;
			//音乐播放对象
			clip = AudioSystem.getClip();
			//加载音乐文件
			clip.open(stream);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
	
	/**
	 * 开始音乐播放
	 */
	public void play()
	{
		if(isPlay)
		{
			clip.start();
		}
	}
	/**
	 * 音乐停止播放
	 */
	public void stop()
	{
		if(isPlay)
		{
			clip.stop();
		}
	}

	/**
	 * 音乐循环播放
	 */
	public void loop()
	{
		if(isPlay)
		{
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
}
