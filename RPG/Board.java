import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import bin.resource.images;
import java.util.Random;
import java.applet.AudioClip;
import java.applet.Applet;


public class Board extends JPanel implements Runnable
{
   Point position = new Point(243,223);
   Player player;
   BadGuy badguy[] = new BadGuy[6];
   int round = 0;
   Point grass[] = new Point[20];
   Point grass1[] = new Point[10];
   static AudioClip laser = Applet.newAudioClip(new images().getClass().getResource("laser.wav"));
   
   public Board()
   {
      	
      	Point points[] = {new Point(600, 150),
      					  new Point(650, 250),
      					  new Point(650, 50),
      					  new Point(700, 150),
      					  new Point(725, 75),
      					  new Point(725, 225)};
     	
      	for(int a = 0; a < badguy.length; a++) {
      		badguy[a] = new BadGuy(points[a],position, player);
      		badguy[a].start();      		
      	}
      	player = new Player(this);
      	player.start();
      	
      	setBackground(Color.GREEN);
      	Random randomNumber = new Random();
      	for(int j = 0; j < grass.length; j++)
      		grass[j] = new Point(randomNumber.nextInt(886),randomNumber.nextInt(446));
      	for(int l = 0; l < grass1.length; l++)
      		grass1[l] = new Point(randomNumber.nextInt(886),randomNumber.nextInt(446));

      	addMouseListener(
         	new MouseAdapter()
         	{  
            	public void mouseClicked( MouseEvent event )
            	{
                  	if(!player.dead) {
                  		player.shoot[round] = new Shoot(position, event.getPoint());
                  		player.shoot[round++].start();
                  		laser.play();
                  		if (round >= player.shoot.length)
                  			round = 0;
                  	}
            	} 
         	} 
      	);
   	} 

   	public void paintComponent( Graphics g )
   	{
    	super.paintComponent( g ); // clears drawing area
      	
		getGrass(g);
      	for(int b = 0; b < badguy.length; b++)
			badguy[b].getGuy(g);
		player.getPlayer(g);
		

   } // end method paint
   
   	public void getGrass(Graphics g) {
      	for(int k = 0; k < grass.length; k++)
      		littleGrass(g,grass[k]);
      	for(int m = 0; m < grass1.length; m++)
      		bigGrass(g,grass1[m]);   		
   	}
   	
   	public void littleGrass(Graphics g, Point grass) {
   		g.drawLine(grass.x, grass.y, grass.x+3, grass.y-5);
   		g.drawLine(grass.x+3,grass.y-5,grass.x+6,grass.y);
   		g.drawLine(grass.x+6,grass.y,grass.x+9,grass.y-7);
   		g.drawLine(grass.x+9,grass.y-7,grass.x+12,grass.y);
   		g.drawLine(grass.x+12,grass.y,grass.x+15,grass.y-3);
   		g.drawLine(grass.x+15,grass.y-3,grass.x+18,grass.y);
  		
   	}

   	public void bigGrass(Graphics g, Point grass) {
   		g.drawLine(grass.x, grass.y, grass.x+3, grass.y-3);
   		g.drawLine(grass.x+3,grass.y-3,grass.x+6,grass.y);
   		g.drawLine(grass.x+6,grass.y,grass.x+9,grass.y-4);
   		g.drawLine(grass.x+9,grass.y-4,grass.x+12,grass.y);
   		g.drawLine(grass.x+12,grass.y,grass.x+15,grass.y-9);
   		g.drawLine(grass.x+15,grass.y-9,grass.x+18,grass.y);
   		g.drawLine(grass.x+18,grass.y,grass.x+21,grass.y-2);
   		g.drawLine(grass.x+21,grass.y-2,grass.x+24,grass.y);
   		g.drawLine(grass.x+24,grass.y,grass.x+27,grass.y-5);
   		g.drawLine(grass.x+27,grass.y-5,grass.x+30,grass.y);
  		
   	}
   
   	public void run() {
        	while(true){
        		try{
        		Thread.sleep(50);   
        		} catch( Exception e){}
        		repaint();
   		}
	} // end class PaintPanel
}