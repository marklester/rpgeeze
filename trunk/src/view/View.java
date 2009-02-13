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
		//frame.add(controller);
		frame.addKeyListener(controller);
/*		frame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyChar() + " was pressed.");
			}
			public void keyReleased(KeyEvent e) {
				System.out.println(e.getKeyChar() + " was released.");
			}
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyChar() + " was typed.");
			}
		});*/
		
	}
	
/*
 * THIS STUFF NEEDS TO BE PART OF THE CONTROLLER
 * 		KEYPRESSES AND HANDLERS
 *		FULL SCREEN FRAME WILL ALSO BE PART OF THE CONTROLLER
 *			--Jose
 */
/*
	public View() {
		
    	super("RPG Game");


    	BorderLayout layout = new BorderLayout();
    	MenuBar bar = new MenuBar();
    	this.setLayout(layout);
    	this.setJMenuBar(bar);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(995,495);
    	this.setLocation(125,100);
    	this.setResizable(false);
    	this.setVisible(true);

		//*****************************************************************
		KeyListener keyHandler = new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
            	//code = e.getKeyCode();
                //moving = true;
            } 
            public void keyReleased(KeyEvent e) {
				//moving = false;
            } 
        };
        addKeyListener(keyHandler);
		//*****************************************************************
	}
*/

	public void run() {
		frame.setVisible(true);
		frame.requestFocus();
		long sleepNanos = 1000000000L / GOAL_FPS; 
		running = true;
		while(running) {
			long beforeTime = System.nanoTime();
			//waits for an update to occur
			/*synchronized(this) {			
				try {
					this.wait();
				}
				catch(InterruptedException e) {}
			}*/
			render();
			paint();
			long afterTime = System.nanoTime();
			int sleepMillis = (int) ((sleepNanos - (afterTime - beforeTime)) / 1000000L);
			if(sleepMillis > 0) {
				try {
					Thread.sleep(10);
				}
				catch(InterruptedException e) {}
			}
			
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
		
		new Drawer(g).doDraw(model.getMap(), model.getAvatar(), frame.getWidth(), frame.getHeight());
		
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
