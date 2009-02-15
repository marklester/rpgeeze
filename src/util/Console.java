package util;

import java.util.*;

public class Console {
	private static Console instance;
	private LinkedList<String> list = new LinkedList<String>();
	
	private Console()
	{
	}
	
	public static Console getInstance()
	{
		if (instance == null)
			instance = new Console();
		return instance;
	}
	public synchronized void writeLine(String s)
	{
		list.addFirst(s);
	}
	public synchronized Queue<String> getStringList()
	{
		try{ 
			return (LinkedList<String>)super.clone();
		}catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
	
}
