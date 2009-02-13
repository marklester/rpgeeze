package view;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;

import model.*;
import util.*;
import static view.GameFrame.*;

public class View extends Thread {	
	private final Model model;
	private volatile boolean running = false;

	private volatile Image dbImage = null;
	private Drawer drawer;
	private GameFrame frame;
	
	public View(Model model) {
		this.model = model;
		frame = new GameFrame();
		drawer = new Drawer();
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
		running = true;
		while(running) {
			//waits for an update to occur
			/*synchronized(this) {			
				try {
					this.wait();
				}
				catch(InterruptedException e) {}
			}*/
			render();
			paint();
			try {
				Thread.sleep(50);
			}
			catch(InterruptedException e) {}
			
			//getModelState
			//draw/Render all Drawable Objects onto image buffer-- responsibility of Drawer
			//paint image buffer on screen
			//		--Jose
		}
	}
	
	private void render() {
		if(dbImage == null) {
			dbImage = frame.createImage(PWIDTH, PHEIGHT);
			if(dbImage == null) {
				System.out.println("dbImage is null");
				return;
			}
		}
		Graphics g = dbImage.getGraphics();
		g.setColor(Color.white);
		g.fillRect (0, 0, PWIDTH, PHEIGHT);
		g.setColor(Color.blue);
		
		drawer.doDraw(model.getMap(), g);
		
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

