import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.geom.Ellipse2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BadGuy extends Thread{

Point position;
int lives = 5;
Point GoodGuy;
Shoot shoot1[] = new Shoot[10];
Player player;
boolean dead = false;
Integer Offset[] = {0,-100,50,100,-50,0};

Scanner input;

    public BadGuy(Point p, Point g, Player m) {
    	position = p;
    	GoodGuy = g;
    	player = m;

      	for(int n = 0; n < shoot1.length; n++)
      		shoot1[n] = new Shoot(position, position);
    }
    
    public void run() {
        int i = 0;
        int j = 0;
        Integer x;
        Integer y;
        Double distance1;
        Integer distance;

        try {
    		input = new Scanner(new File("script.txt"));
    	}catch(FileNotFoundException e) {
    		System.out.println("Error opening file.");
    		System.exit(1);
    	}
		int value = 1;;
        while(!dead && !Player.dead) {
        	
    		try {
    			if(input.hasNext()){
    				value = input.nextInt();
    			} else{
    				input = new Scanner(new File("script.txt"));
    				value = input.nextInt();
    			}
    		} catch (Exception e){System.out.println("Error");}
        	
 			switch(value){//movement[j++]
 				case 1: position.x = position.x+5;//Right
 					break;
 				case 2: position.y = position.y+5;//Down
 					break;
 				case 3:position.x = position.x-5;//Left
 					break;
 				case 4:position.y = position.y-5;//Up
 					break;
 				default:
					Random randomNumber = new Random();
					Point attack = new Point(GoodGuy.x,GoodGuy.y+Offset[randomNumber.nextInt(5)]);
					shoot1[i] = new Shoot(position, attack);
 					shoot1[i++].start();
 			}
 			if(i >= shoot1.length)
 				i = 0;
 			//if(j >= movement.length)
 			//	j = 0;
        	try{
        	Thread.sleep(100);   
        	} catch( Exception e){}  
        	

        	for(int a = 0; a < Player.shoot.length; a++){
        		x = player.shoot[a].position.x - position.x;
        		y = player.shoot[a].position.y - position.y;
				distance1 = Math.sqrt(Math.pow(y,2) + Math.pow(x,2));
				distance = distance1.intValue();
				if(distance <= 27 && player.shoot[a].active){
					player.shoot[a].active = false;
					lives--;
					if(lives <=0)
						dead = true;
				}
        	}      
        }
    }
    
    public void getGuy(Graphics g) {
    	
    	Graphics2D g2d = (Graphics2D) g;
    	for(int i = 0; i < shoot1.length; i++) {
			if(shoot1[i].active == true){
				shoot1[i].getArrow1(g2d);
			}
		}
		if(!dead) {
    	g.setColor(Color.BLACK);
    	g.fillOval( position.x-27, position.y-27, 54, 54 );//Shell
    	g2d.setPaint(new GradientPaint(5,30,Color.ORANGE,35,100,Color.MAGENTA,true));
    	g2d.fill(new Ellipse2D.Double(position.x-25,position.y-25, 50, 50));
      	g.setColor(Color.WHITE);    
      	g.fillOval( position.x-9, position.y-10, 6, 6 );//Left Eye
      	g.fillOval( position.x+4, position.y-10, 6, 6 );//Right Eye
      	g.setColor(Color.black);
      	g.fillOval( position.x-9, position.y-10, 4, 4 );//Left Eye
      	g.fillOval( position.x+3, position.y-10, 4, 4 );//Right Eye
      	g.fillOval( position.x-13, position.y+10, 6, 6 );//Smile
     	g.fillOval( position.x+7, position.y+10, 6, 6 );//Smile
      	g.fillOval( position.x-12, position.y+8, 24, 6 );//Mouth
		} else {
		g.setColor(Color.BLACK);
    	g.fillOval( position.x-27, position.y-27, 54, 54 );//Shell
      	g.setColor(Color.GRAY);
      	g.fillOval( position.x-25, position.y-25, 50, 50 );//Body
      	g.setColor(Color.black);
      	g.fillOval( position.x-9, position.y-10, 4, 4 );//Left Eye
      	g.fillOval( position.x+3, position.y-10, 4, 4 );//Right Eye
      	g.fillOval( position.x-13, position.y+10, 6, 6 );//Smile
     	g.fillOval( position.x+7, position.y+10, 6, 6 );//Smile
      	g.fillOval( position.x-12, position.y+8, 24, 6 );//Mouth
		}
    }
    
    
}