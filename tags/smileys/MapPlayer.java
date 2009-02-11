
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class MapPlayer extends Thread{

	static Integer lives = 3;
	static Integer strength = 11;
	static Integer agility = 8;
	static Integer intellect = 10;
	static Integer hardness = 6;
	static Integer experience = 134;
	static Integer movement = 4;
	
	Point position;
	Point PanelPoint;
    static int code;
    static boolean moving = false;
    MapPanel board;
    public MapPlayer(MapPanel b) {
    	board = b;
		position = board.position;
		PanelPoint = new Point(position.x*20,position.y*20);
      	}
    
    public void run(){	
        char direction = 'S';
    		while(true){
    			if(moving) {
    				Point temp = new Point(position.x,position.y);
    				if(code == KeyEvent.VK_W | code == KeyEvent.VK_UP) {
    					direction = 'W';
    					position.y--;
       				} else if(code == KeyEvent.VK_D | code == KeyEvent.VK_RIGHT) {
       					direction = 'D';
       					position.x++;
        			} else if(code == KeyEvent.VK_X | code == KeyEvent.VK_DOWN) {
        				direction = 'X';
        			   	position.y++;
        			} else if(code == KeyEvent.VK_A | code == KeyEvent.VK_LEFT) {
        				direction = 'A';
        				position.x--;
        			} else if(code == KeyEvent.VK_Q) {
        				direction = 'Q';
        				position.x--;position.y--;
        			} else if(code == KeyEvent.VK_E) {
        				direction = 'E';
        			   	position.x++;position.y--;
        			} else if(code == KeyEvent.VK_Z) {
        				direction = 'Z';
        			   	position.x--;position.y++;
        			} else if(code == KeyEvent.VK_C) {
        				direction = 'C';
        			   	position.x++;position.y++;
        			} else {direction = 'S';}					
					
					if(position.x < 44 && position.x >= 0 && position.y < 22 && position.y >= 0 && board.map1[position.y][position.x] >= 0) {
						
						for(int i = 0; i < 20;i = i + movement) {
							switch(direction) {
								case 'W':	MapPanel.Offset.y = MapPanel.Offset.y-movement;
									break;
								case 'D':	MapPanel.Offset.x = MapPanel.Offset.x+movement;
									break;
								case 'X':	MapPanel.Offset.y = MapPanel.Offset.y+movement;
									break;
								case 'A':	PanelPoint.x = PanelPoint.x-movement;
											MapPanel.Offset.x = MapPanel.Offset.x-movement;
									break;
								case 'Q':	MapPanel.Offset.x = MapPanel.Offset.x-movement;
											MapPanel.Offset.y = MapPanel.Offset.y-movement;
									break;
								case 'E':	MapPanel.Offset.x = MapPanel.Offset.x+movement;
											MapPanel.Offset.y = MapPanel.Offset.y-movement;
									break;
								case 'Z':	MapPanel.Offset.x = MapPanel.Offset.x-movement;
											MapPanel.Offset.y = MapPanel.Offset.y+movement;											
									break;
								case 'C':	MapPanel.Offset.x = MapPanel.Offset.x+movement;
											MapPanel.Offset.y = MapPanel.Offset.y+movement;											
											
									break;
								default :
							}
							try{
        					Thread.sleep(50);   
        					} catch( Exception e){}
        					board.repaint();
						}
								
					} else {position = temp;}	
    			} else {
    				try{
        			Thread.sleep(50);   
        			} catch( Exception e){}	
        			
    			}
    		}
    }
 //   30,17
    public void getPlayer(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(22*20, 11*20, 20, 20);
		g.setColor(Color.BLACK);
		g.fillOval(22*20+5, 11*20+6, 3, 3);
		g.fillOval(22*20+12, 11*20+6, 3, 3);
		g.fillOval(22*20+5, 11*20+13, 10, 3);
		g.fillOval(22*20+4, 11*20+11, 3, 3);
		g.fillOval(22*20+13, 11*20+11, 3, 3);
    }
    
}
