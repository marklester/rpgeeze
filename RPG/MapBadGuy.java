import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

//Possible variables: speed, proximaty distance

public class MapBadGuy extends Thread{

	Point position;//Location on Grid
	Point PanelPoint;//Location on Panel (Absolute)
	Integer movement = 5;//Movement speed
	Integer proximaty = 4;
	MapPanel board;//MapPanel
	boolean dead = false;
	
    public MapBadGuy(MapPanel b, Point p) {
    //Needs Offset, Player position, Start Point, and Grid
    	board = b;
		position = p;
		PanelPoint = new Point(position.x*MapPanel.grid,position.y*MapPanel.grid);
    	
    }
    public void run() {
    	Random randomNumber = new Random();
    	char direction = 'S';
    	int previous[] = {1,5,7};
    	Integer map[][] = board.getLevel();
    	
    	while(!dead){
    				
    				int value = previous[randomNumber.nextInt(3)];
    				Point temp = new Point(position.x,position.y);
    				int x = position.x - board.position.x;
        			int y = position.y - board.position.y;
					Double distance = Math.sqrt(Math.pow(y,2) + Math.pow(x,2));
					if(distance <= proximaty){
						if(x < 0 && y <0){
							value = 7;
						} else if(x < 0 && y > 0) {
							value = 5;
						} else if(x < 0 && y == 0) {
							value = 1;
						} else if(x > 0 && y < 0) {
							value = 6;
						} else if(x > 0 && y > 0) {
							value = 4;
						} else if(x > 0 && y == 0) {
							value = 3;
						} else if(x == 0 && y < 0) {
							value = 2;
						} else value = 0;
					}
    				
    				switch(value){
    					case 0: direction = 'W';position.y--;previous[0] = 0;previous[1] = 4;previous[2] = 5;
    						break;
    					case 1: direction = 'D';position.x++;previous[0] = 1;previous[1] = 5;previous[2] = 7;
    						break;
    					case 2: direction = 'X';position.y++;previous[0] = 2;previous[1] = 6;previous[2] = 7;
    						break;
    					case 3: direction = 'A';position.x--;previous[0] = 3;previous[1] = 4;previous[2] = 6;
    						break;
    					case 4: direction = 'Q';position.x--;position.y--;previous[0] = 4;previous[1] = 3;previous[2] = 0;
    						break;
    					case 5: direction = 'E';position.x++;position.y--;previous[0] = 5;previous[1] = 1;previous[2] = 0;
    						break;
    					case 6: direction = 'Z';position.x--;position.y++;previous[0] = 6;previous[1] = 3;previous[2] = 2;
    						break;
    					case 7: direction = 'C';position.x++;position.y++;previous[0] = 7;previous[1] = 2;previous[2] = 1;
    						break;
    					default: direction = 'S';
    				}
					
					if(position.x < 44 && position.x >= 0 && position.y < 22 && position.y >= 0 && map[position.y][position.x] <= 50 && !MapPanel.stop) {
						
						for(int i = 0; i < MapPanel.grid;i = i + movement) {
							switch(direction) {
								case 'W':PanelPoint.y = PanelPoint.y-movement;
									break;
								case 'D':PanelPoint.x = PanelPoint.x+movement;
									break;
								case 'X':PanelPoint.y = PanelPoint.y+movement;
									break;
								case 'A':PanelPoint.x = PanelPoint.x-movement;
									break;
								case 'Q':PanelPoint.x = PanelPoint.x-movement;PanelPoint.y = PanelPoint.y-movement;
									break;
								case 'E':PanelPoint.x = PanelPoint.x+movement;PanelPoint.y = PanelPoint.y-movement;
									break;
								case 'Z':PanelPoint.x = PanelPoint.x-movement;PanelPoint.y = PanelPoint.y+movement;
									break;
								case 'C':PanelPoint.x = PanelPoint.x+movement;PanelPoint.y = PanelPoint.y+movement;
									break;
								default :
							}
							try{
        					Thread.sleep(50);   
        					} catch( Exception e){}
        					//System.out.println(",Moving = "+moving+ ", X = " + PanelPoint.x +", Y =  "+PanelPoint.y);
        					//board.repaint();
						}		
					} else {position = temp;}	
    				try{
        			Thread.sleep(50);   
        			} catch( Exception e){}	
        			
    			}
    }
    
    public void getPlayer(Graphics g) {
    	if(dead) return;
		g.setColor(Color.ORANGE);
		g.fillOval(PanelPoint.x-board.Offset.x, PanelPoint.y-board.Offset.y, MapPanel.grid, MapPanel.grid);
		g.setColor(Color.BLACK);
		g.fillOval(PanelPoint.x+10-board.Offset.x, PanelPoint.y+10-board.Offset.y, 10, 10);
		g.fillOval(PanelPoint.x+30-board.Offset.x, PanelPoint.y+10-board.Offset.y, 10, 10);
		g.fillOval(PanelPoint.x+10-board.Offset.x, PanelPoint.y+27-board.Offset.y, 30, 8);
		g.fillOval(PanelPoint.x+9-board.Offset.x, PanelPoint.y+30-board.Offset.y, 8, 8);
		g.fillOval(PanelPoint.x+33-board.Offset.x, PanelPoint.y+30-board.Offset.y, 8, 8);
    }
    
}