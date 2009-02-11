import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player extends Thread{

	static Integer lives = 10;
	static Integer strength = 11;
	static Integer agility = 8;
	static Integer intellect = 10;
	static Integer hardness = 6;
	static Integer experience = 134;
	static Integer movement = 5;
	Point position;
	static Integer size = 27;
	static Shoot shoot[] = new Shoot[10];
    static int code;
    static boolean moving = false;
    Board board;
    static boolean dead = false;

    public Player(Board b) {
    	lives = 3;
    	board = b;
		position = board.position;
	    for(int n = 0; n < shoot.length; n++) {
      		shoot[n] = new Shoot(position, position);
      	}
    }
    
    public void run(){	
        Integer x;
        Integer y;
        Double distance1;
        Integer distance;
    		while(dead == false){
    			if(moving == true){
    				Double halfspeed = (movement).doubleValue();
    				halfspeed = (halfspeed*(2)/3);
    				Integer value = halfspeed.intValue();
    				if(code == KeyEvent.VK_W | code == KeyEvent.VK_UP) {
        				position.y = position.y - movement;
       				} else if(code == KeyEvent.VK_D | code == KeyEvent.VK_RIGHT) {
        			    position.x = position.x + movement;
        			} else if(code == KeyEvent.VK_X | code == KeyEvent.VK_DOWN) {
        			    position.y = position.y + movement;
        			} else if(code == KeyEvent.VK_A | code == KeyEvent.VK_LEFT) {
        	    		position.x = position.x - movement;
        			} else if(code == KeyEvent.VK_Q) {
        			    position.x = position.x - value;
        			    position.y = position.y - value;
        			} else if(code == KeyEvent.VK_E) {
        			    position.x = position.x + value;
        			    position.y = position.y - value;
        			} else if(code == KeyEvent.VK_Z) {
        			    position.x = position.x - value;
        			    position.y = position.y + value;
        			} else if(code == KeyEvent.VK_C) {
        			    position.x = position.x + value;
        			    position.y = position.y + value;
        			}
        		
        			if(position.y > 446 - size)
        				position.y = 445 - size;
					if(position.y < 1 + size)
						position.y = 1 + size;
					if(position.x < 1 + size)
						position.x = 1 + size;
					if(position.x > 886 - size)
						position.x = 885 - size;

        		}else {}
        		
        		
        		for(int a = 0; a < board.badguy.length; a++){
        			for(int b = 0; b < board.badguy[a].shoot1.length; b++) {
        				x = board.badguy[a].shoot1[b].position.x - position.x;
        				y = board.badguy[a].shoot1[b].position.y - position.y;
						distance1 = Math.sqrt(Math.pow(y,2) + Math.pow(x,2));
						distance = distance1.intValue();
						if(distance <= size && board.badguy[a].shoot1[b].active){
							lives--;
							board.badguy[a].shoot1[b].active = false;
							if(lives <= 0)
								dead = true;
						}
        			}
        		}
        		
        		try{
        		Thread.sleep(50);   
        		} catch( Exception e){}
    		}
    }
    
    public void getPlayer(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
      	g.setColor(Color.RED);
		for(int i = 0; i < shoot.length; i++) {
			if(shoot[i].active == true){
				shoot[i].getArrow(g2d);
			}
		}
		if(dead == false) {
    	g.setColor(Color.BLACK);
    	g.fillOval( position.x-27, position.y-27, 54, 54 );//Shell  	
    	g2d.setPaint(new GradientPaint(5,30,Color.YELLOW,35,100,Color.GREEN,true));
    	g2d.fill(new Ellipse2D.Double(position.x-25,position.y-25, 50, 50));
      	g.setColor(Color.WHITE);    
      	g.fillOval( position.x-9, position.y-10, 6, 6 );//Left Eye
      	g.fillOval( position.x+4, position.y-10, 6, 6 );//Right Eye
      	g.setColor(Color.black);
      	g.fillOval( position.x-9, position.y-10, 4, 4 );//Left Eye
      	g.fillOval( position.x+3, position.y-10, 4, 4 );//Right Eye
      	g.fillOval( position.x-13, position.y+6, 6, 6 );//Smile
     	g.fillOval( position.x+7, position.y+6, 6, 6 );//Smile
      	g.fillOval( position.x-12, position.y+8, 24, 6 );//Mouth
		}
    }
    
}
