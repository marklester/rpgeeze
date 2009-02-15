package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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
		return (LinkedList<String>)list.clone();
	}
	
	public void drawConsoleView(Graphics2D graphics, int width, int height){
		Queue<String> messages  = Console.getInstance().getStringList();//Messages to Show
		int stats_width=310;//only change this is stats window size is changed
		int console_width = 400; 
		int console_height = 100;
		int left_indent = 20;
		int top_indent = 20;
		int max_messages=4; //the max number of messages the Console can show at One Time;
		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		graphics.fillRoundRect(width-(console_width+stats_width), height-console_height, console_width, console_height, 3, 3);
		//graphics.drawImage(statsView,width-menu_width, height-menu_height,null);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		graphics.setColor(Color.white);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 16));
		//Text Formatting Numbers
		int text_width = width-(console_width+stats_width) + left_indent; 
		int text_height = height - console_height + top_indent;
		//Draws Messages on Console
		int current_line=0;
		if(messages!=null){
			while(messages.size()>0&&current_line<max_messages){
				graphics.drawString(messages.remove(), text_width, text_height+current_line*18);
				current_line++;
			}
		}
	}
}
