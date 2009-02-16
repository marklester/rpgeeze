package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Queue;

import util.ConsoleMessage;

public class Console {
	private static Console instance;
	private final LinkedList<ConsoleMessage> list = new LinkedList<ConsoleMessage>();
	public final int timer_default=40;
	private Console() {
	}

	public static Console getInstance() {
		if(instance == null)
			instance = new Console();
		return instance;
	}

	public synchronized void writeLine(String s,Color c) {
		this.list.addFirst(new ConsoleMessage(s,c,timer_default));
	}
	public synchronized void writeLine(String s) {
		this.list.addFirst(new ConsoleMessage(s,Color.WHITE,timer_default));
	}
	public synchronized Queue<ConsoleMessage> getStringList() {
		return (LinkedList<ConsoleMessage>) this.list.clone();
	}

	public void drawConsoleView(Graphics2D graphics, int width, int height) {
		Queue<ConsoleMessage> messages = Console.getInstance().getStringList();// Messages
		// to Show
		int stats_width = 310;// only change this is stats window size is
		// changed
		int console_width = 400;
		int console_height = 100;
		int left_indent = 20;
		int top_indent = 20;
		int max_messages = 5; // the max number of messages the Console can
		// show
		// at One Time;
		graphics.setColor(Color.black);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
		graphics.fillRoundRect(width - (console_width + stats_width), height - console_height, console_width, console_height, 3, 3);
		// graphics.drawImage(statsView,width-menu_width,
		// height-menu_height,null);
		
		graphics.setColor(Color.white);
		graphics.setFont(new Font("SansSerif", Font.BOLD, 16));
		// Text Formatting Numbers
		int text_width = width - (console_width + stats_width) + left_indent;
		int text_height = height - console_height + top_indent;
		// Draws Messages on Console
		int current_line = 0;
		if(messages != null)
			while(messages.size() > 0 && current_line < max_messages) {
				ConsoleMessage message = messages.remove();
				float v = message.getTimer() / (float)timer_default;
				graphics.setColor(message.getColor());
				graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,v));				
				if(message.isValid()){
					graphics.drawString(message.toString(), text_width, text_height + current_line * 18);
				}
				current_line++;
			}
	}
}
