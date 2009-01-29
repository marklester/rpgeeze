import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;

public class RPGFrame extends JFrame implements Runnable {
	
	Board board;
	
    public RPGFrame() {
    	
    	super("Medevil");
		/******************************************************************/
		KeyListener keyHandler = new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
            	Player.code = e.getKeyCode();
                Player.moving = true;		
            } 
            public void keyReleased(KeyEvent e) {
				Player.moving = false;
            } 
        };
        addKeyListener(keyHandler);
		/******************************************************************/
      	board = new Board();
      	new Thread(board).start();
      	board.setBorder(new EtchedBorder());
    	BorderLayout layout = new BorderLayout();
    	this.setLayout(layout);
      	this.add( board ,BorderLayout.CENTER); // in center

    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	this.setSize(1000,500);
    	this.setLocation(125,100);
    	this.setResizable(false);
    	this.setVisible(true);
    }

	public void run() {
		boolean done = false;
		while(!done) {
			done = true;
        	for(int h = 0; h < board.badguy.length;h++){
        		if(!board.badguy[h].dead) {
        			done = false;
        		}
        	}
        	if(board.player.dead)
        		done = true;
        	try{
        	Thread.sleep(1000);   
        	} catch( Exception e){}
        }
        dispose();
	}

}