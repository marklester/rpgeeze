package view;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

import controller.*;
import model.*;
import util.*;
import static view.GameFrame.*;

public class View extends Thread {	
	private final Model model;
	private volatile boolean running = false;

	private final static int GOAL_FPS = 80;
	
	private volatile Image dbImage = null;
	public final GameFrame frame;
	
	public View(Model model, Controller controller) {
		this.model = model;
		frame = new GameFrame();
		frame.add(controller);
		
	}

	public void run() {
		frame.setVisible(true);
		frame.requestFocus();
		//long sleepNanos = 1000000000L / GOAL_FPS; 
		//running = true;
		while(!interrupted()) {
			//long beforeTime = System.nanoTime();
			//waits for an update to occur
			synchronized(this) {			
				try {
					this.wait();
				}
				catch(InterruptedException e) {
					this.interrupt();
				}
			}
			render();
			paint();
			
			//getModelState
			//draw/Render all Drawable Objects onto image buffer-- responsibility of Drawer
			//paint image buffer on screen
			//		--Jose
		}
	}
	
	private void render() {
		if(dbImage == null) {
			dbImage = frame.createImage(frame.getWidth(), frame.getHeight());
			if(dbImage == null) {
				System.out.println("dbImage is null");
				return;
			}
		}
		Graphics g = dbImage.getGraphics();
		g.setColor(Color.white);
		g.fillRect (0, 0, PWIDTH, PHEIGHT);
		g.setColor(Color.blue);
		
		Drawer.getInstance().doDraw(g, model.getMap(), model.getAvatar(), frame.getWidth(), frame.getHeight());
		
		g.setFont(new Font("SansSerif", Font.BOLD, 16));
		g.drawString("System.nanoTime() returns " + System.nanoTime(), 50, 50);
	}
	
	private void paint() {
	    try {
	    	Graphics g = frame.getGraphics( );
	    	if(g != null && dbImage != null)
	    		g.drawImage(dbImage, 0, 0, null);
	    	Toolkit.getDefaultToolkit().sync();
	    	g.dispose();
	    }
	    catch (Exception e) {
	    	System.out.println("Graphics context error: " + e);
	    }
	}
}
