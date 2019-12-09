package entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FileIO
{
	File f;
	
	public ArrayList<Integer> list = new ArrayList<>();
	
	public FileIO()
	{
		f = new File("rank.txt");
		try
		{
			readNumber();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeNumber(String s) throws IOException
	{
		list.add(Integer.valueOf(s));
		
		Collections.sort(list, Collections.reverseOrder());
		
		BufferedWriter write = new BufferedWriter(new FileWriter(f));
		
		for(int i = 0; i < 10; i++)
		{
			write.write(list.get(i).toString());
			write.newLine();
		}
		
		write.close();
	}
	
	
	public void readNumber() throws IOException
	{
		BufferedReader read = new BufferedReader(new FileReader(f));
		
		String s = null;
		
		while((s = read.readLine())!= null)
		{
			list.add(Integer.valueOf(s));
		}
		read.close();
	}
}
