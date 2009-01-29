import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.Color;




    public class Shoot extends Thread {
    	
    	Point position;
    	Point end;
    	Boolean active = false;
	 	Integer value = 0;
	 	Integer speed = 10;
	 	Integer range = 900;
	 	
    	
    	public Shoot(Point s, Point e){
    		end = e;
    		position = new Point(s.x, s.y);
    		//laser = ;
    		//System.out.println(laser); 
    		
    	}
    	
    	public void run(){
    		
			Integer dx = position.x - end.x;
			Integer dy = position.y - end.y;
			Double y = dy.doubleValue();
			Double x = dx.doubleValue();
			Double m = y/x;
			Double theta = Math.atan(m);
			if(dx >= 0)
				theta = theta + Math.PI;
			//System.out.println("Theta = " + theta + ",Y = " + y + ", X = " + x + ", M = " + m);
			//Double distance1 = Math.sqrt(Math.pow(y,2) + Math.pow(x,2));
			//Integer distance = distance1.intValue();
			x = Math.cos(theta)*speed;
			y = Math.sin(theta)*speed;
			dx = x.intValue();
			dy = y.intValue();
			active = true;
    		while(range >= value && active == true){
				position.x = position.x + dx;
				position.y = position.y + dy;
				value = value + speed;
        		try{
        		Thread.sleep(50);   
        		} catch( Exception e){}
    		}
    		active = false;
    	}
    		
    	public void getArrow(Graphics2D g2d) {

    		g2d.setPaint(new GradientPaint(5,30,Color.YELLOW,35,100,Color.GREEN,true));
    		g2d.fill(new Ellipse2D.Double(position.x-8,position.y-8, 16, 16));
			g2d.setPaint(new GradientPaint(5,30,Color.ORANGE,35,100,Color.RED,true));
    		g2d.fill(new Ellipse2D.Double(position.x-5,position.y-5, 10, 10));

    	}
    	public void getArrow1(Graphics2D g2d) {	
    		g2d.setPaint(new GradientPaint(5,30,Color.MAGENTA,35,100,Color.PINK,true));
    		g2d.fill(new Ellipse2D.Double(position.x-8,position.y-8, 16, 16));
			g2d.setPaint(new GradientPaint(5,30,Color.ORANGE,35,100,Color.RED,true));
    		g2d.fill(new Ellipse2D.Double(position.x-5,position.y-5, 10, 10));
    	}
    	public void getStealthArrow(Graphics2D g2d) {
    		g2d.setPaint(new GradientPaint(5,30,Color.YELLOW,35,100,Color.GREEN,true));
    		g2d.fill(new Ellipse2D.Double(position.x-8,position.y-8, 16, 16));
			g2d.setPaint(new GradientPaint(5,30,Color.YELLOW,35,100,Color.GREEN,true));
    		g2d.fill(new Ellipse2D.Double(position.x-5,position.y-5, 10, 10));
    	}
    }