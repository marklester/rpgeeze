import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.io.BufferedInputStream;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import bin.resource.images;

public class MapPanel extends JPanel{

	Image image[] = new Image[9];//Import JPEGs
	int movement = 10;//10 pixel movement every 50ms
	static int code = 0;//Value of key pressed
	static boolean moving = false; //Value to stop player when key is released
	static boolean stop = false;//Pauses movement until battle is over
	static final int grid = 50;//pixel size of grid
	static final int shiftx = 14;//x shift to view player entity
	static final int shifty = 7;//y shidt to view player entity
	MapBadGuy badguy[] = new MapBadGuy[4];//Number of badguys moving around
   						//1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,17,18,19,20,21,22,23,24,25,26,27,28,29,30,32,33,34,35,36,37,38,39,40,41,42,43,44
   Integer map1[][] = {  {-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-7,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},//1
   						 {-7,-7,-2,-2,-2,-2,-2,-2,-2,-2,-2,-7, 4, 1, 3,-7,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-7,-7,-7,-7,-7,-7,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},//2
   						 { 1, 1, 3,-7,-7,-7,-7,-7,-7,-7, 4, 1, 1, 1, 1, 1, 3,-7,-7,-7,-7,-7,-7,-7,-7, 4, 1, 1, 1, 1, 1, 1, 3,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},//3
   						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},//4
   						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3,-2,-2,-2,-2,-2,-2,-2,-2,-2},//5
   						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,-6,-2,-2,-2,-2,-2,-2,-2,-2},//6
   						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3,-2,-2,-2,-2,-2,-2,-2,-2},//7
   						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3,-2,-2,-2,-2,-2,-2,-2},//8
   						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3,-2,-2,-2,-2,-2,-2},//9
						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,-6,-2,-2,-2,-2,-2},//10
						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6,-8,-8, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3,-7,-2,-2,-2,-2},//11
						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6,-8,-2,-2,-2,-9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3,-7,-2,-2},//12
						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,-6,-2,-2,-2,-2, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3,-2},//13
						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,-6,-2,-2,-2, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},//14
						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3,-7,-7, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//15
						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//16
						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//17
						 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//18
						 { 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//19
						 {-2,-8,-8,-8, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//20
						 {-2,-2,-2,-2,-2,-8,-8, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//21
						 {-2,-2,-2,-2,-2,-2,-2,-2, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};//22					
   Point position = new Point(24,20);
   Point Offset = new Point(position.x*grid-map1[1].length/2*grid+grid*shiftx,position.y*grid-map1.length/2*grid+grid*shifty);

    public MapPanel() {
    	setBackground(Color.BLACK);
		for(Integer i = 0; i < 9;i++)
			image[i] = loadImage(i.toString() + ".jpg");		

 		Point point[] = {new Point(30,10),
 						new Point(20,10),
 						new Point(10,20),
 						new Point(5,5)};
		for(int f = 0; f < badguy.length;f++){
			badguy[f] = new MapBadGuy(this, point[f]);
			badguy[f].start();			
		}
		
		PlayerThread p = new PlayerThread();
		p.start();
		BattleThread b = new BattleThread();
		b.start();
		PaintThread paint = new PaintThread();
		paint.start();
    }
    
    static Image loadImage(String imageName) {
        try {
            Image image = ImageIO.read(new BufferedInputStream(new images().getClass().getResourceAsStream(imageName)));
            return image;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
   	public void paintComponent( Graphics g )
   	{
    	super.paintComponent( g );
    	getTerrain(g,map1);
    	g.setColor(Color.BLACK);
      	
      	for(int j = 0; j < badguy.length;j++)
      		badguy[j].getPlayer(g);
		getPlayer(g);
   }
  
    public void getPlayer(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(22*grid-grid*shiftx, 11*grid-grid*shifty, grid, grid);
		g.setColor(Color.BLACK);
		g.fillOval(22*grid-grid*shiftx+10, 11*grid-grid*shifty+10, 10, 10);
		g.fillOval(22*grid-grid*shiftx+30, 11*grid-grid*shifty+10, 10, 10);
		g.fillOval(22*grid-grid*shiftx+10, 11*grid-grid*shifty+30, 30, 8);
		g.fillOval(22*grid-grid*shiftx+9, 11*grid-grid*shifty+27, 8, 8);
		g.fillOval(22*grid-grid*shiftx+33, 11*grid-grid*shifty+27, 8, 8);
    }

  	public void getGrid(Graphics g) {
  		for(int i = grid; i < 44*grid; i = i+grid)
  			g.drawLine(i, 0, i, 440);
  		for(int j = grid; j < 22*grid; j = j+grid)
  			g.drawLine(0, j, 880, j);
  		
  	}

   	public void getTerrain(Graphics g, Integer map[][]) {
   		
   		for(int k = 0; k < 22; k++) {
   			for(int j = 0; j < 44; j++) {
   				switch(map[k][j]) {
   					case 1:g.drawImage(image[8],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case -2:g.setColor(Color.BLUE);g.fillRect((j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid);
   						break;
   					case 3:g.drawImage(image[0],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 4:g.drawImage(image[1],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 5:g.drawImage(image[2],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case 6:g.drawImage(image[3],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case -7:g.drawImage(image[4],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;  
   					case -8:g.drawImage(image[5],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case -9:g.drawImage(image[6],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break;
   					case -6:g.drawImage(image[7],(j)*grid-Offset.x,(k)*grid-Offset.y,grid,grid,this);
   						break; 						
   					//case 8:g.setColor(Color.DARK_GRAY);g.fillOval((j)*20,(k)*20,40,40);
   					//	break;

   					//case 9:g.setColor(Color.CYAN);g.fillRect((j)*20,(k)*20,20,20);
   					//	break;   						
   					default:
   						System.out.println("Error, non-value terrain at " + "("+k+","+j+")");
   				}
   			}
   		}
   		
   	} 	
   	
	public class PlayerThread extends Thread {
		public void run() {
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
					
					if(position.x < 44 && position.x >= 0 && position.y < 22 && position.y >= 0 && map1[position.y][position.x] >= 0) {
						
						for(int i = 0; i < grid;i = i + movement) {
							switch(direction) {
								case 'W':	Offset.y = Offset.y-movement;
									break;
								case 'D':	Offset.x = Offset.x+movement;
									break;
								case 'X':	Offset.y = Offset.y+movement;
									break;
								case 'A':	Offset.x = Offset.x-movement;
									break;
								case 'Q':	Offset.x = Offset.x-movement;
											Offset.y = Offset.y-movement;
									break;
								case 'E':	Offset.x = Offset.x+movement;
											Offset.y = Offset.y-movement;
									break;
								case 'Z':	Offset.x = Offset.x-movement;
											Offset.y = Offset.y+movement;											
									break;
								case 'C':	Offset.x = Offset.x+movement;
											Offset.y = Offset.y+movement;											
											
									break;
								default :
							}
							try{
        					sleep(50);   
        					} catch( Exception e){}
        					//repaint();
						}
								
					} else {position = temp;}	
    			} else {
    				try{
        			sleep(50);   
        			} catch( Exception e){}	
        			
    			}
    		}
		}
	}

	public class BattleThread extends Thread {
   		public void run() {
   			Random randomNumber = new Random();
   			int count = 0;
        	while(true){
        		for(int u = 0; u < badguy.length; u++){
        			if(position.x == badguy[u].position.x && position.y == badguy[u].position.y){
						stop = true;
						moving = false;
						RPGFrame frame1 = new RPGFrame();
						new Thread(frame1).run();
						Player.dead = false;//Just for presenting
						
						try{
        				sleep(5000);   
        				} catch( Exception e){}
        				stop = false;        				
        			}
        		}
        		try{
        		sleep(50);   
        		} catch( Exception e){}
        	}    	
   		}
	}

	public class PaintThread extends Thread {
		public void run() {
			while(true) {
				try{
        		sleep(25);   
        		} catch( Exception e){}	
        		repaint();
			}
		}
	}

   	
}