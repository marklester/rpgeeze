package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Queue;

import util.ConsoleMessage;
import util.ResourceLoader;

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

	public void writeItemEvent(String s) {
		writeLine(s, Color.YELLOW);
	}
	public void writeLifeEvent(String s) {
		writeLine(s, Color.GREEN);
	}
	public void writeHarmfulEvent(String s){
		writeLine(s, Color.RED);
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
		int max_messages = 5; // the max number of messages the Console can
		// Draws Messages on Console
		int current_line = 0;
		int xpos = width/2 - 200;
		int ypos =0;
		if(messages != null)
			while(messages.size() > 0 && current_line < max_messages) {
				ConsoleMessage message = messages.remove();
				float message_decay = message.getTimer() / (float)timer_default;
				float message_box_decay = .3f *  message_decay;
				//Message Box
				ypos =ypos +55;
				graphics.setColor(Color.black);
				graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, message_box_decay));
				graphics.drawImage(ResourceLoader.getInstance().getImage("/img/messagebg.png"), xpos, ypos, null);
				graphics.drawRect(xpos, ypos, 400, 50);
				
				graphics.setColor(message.getColor());
				graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,message_decay));				
				if(message.isValid()){
					graphics.drawString(message.toString(), xpos+50, ypos+25);
				}
				current_line++;
			}
	}
}
