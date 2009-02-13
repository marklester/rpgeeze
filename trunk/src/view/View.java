package view;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class View extends Thread {

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
	
	public View() {
		
	}

	public void run() {
		//waits for an update to occur
		synchronized(this) {			
			try {
				this.wait();
			}
			catch(InterruptedException e) {}
		}
		//getModelState
		//draw/Render all Drawable Objects onto image buffer-- responsibility of Drawer
		//paint image buffer on screen
		//		--Jose
		
	}
}

