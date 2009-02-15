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

public class View extends Thread implements Observer {	
	private final Model model;
	private volatile boolean running = false;

	private final static int GOAL_FPS = 80;
	
	private volatile Image dbImage = null;
	public final GameFrame frame;
	
	public View(Model model, Controller controller) {
		this.model = model;
		
		
		GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    	GraphicsConfiguration gc = dev.getDefaultConfiguration();
    	DisplayMode mode = new DisplayMode(1024, 768, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
    	if(dev.isDisplayChangeSupported())
    	{    		
    		frame = new GameFrame(gc);
    		dev.setDisplayMode(mode);
    	}else
    		frame = new GameFrame();
    		
		frame.add(controller);
		frame.addMouseListener(controller);
		model.register(this);
		
		
	}

	public void run() {
		//Handlers were intended to be in their own thread
		//Not on the drawing thread.
		frame.setVisible(true);
		frame.requestFocus();
		while(!interrupted()) {
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
		//g.setColor(Color.white);
		//g.fillRect (0, 0, PWIDTH, PHEIGHT);
		//g.setColor(Color.blue);
		Drawer.getInstance().doDraw(g, model, frame.getWidth(), frame.getHeight());
		g.dispose();
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

	public void update(Subject s)
	{
		Drawer.getInstance().update(s);
	}
}
