package model.ae;

import model.entity.Entity;
import model.Location;

public class LaunchAE extends AreaEffect {

	public LaunchAE() {
		super("Launch AE");
	}
	
	public LaunchAE(int rate) {
		super(rate, "Launch AE");
	}
	
	@Override
	public void applyEffect(Entity e) {
		System.out.println("Launching Your Ass Now!");
		
		/*
		new Thread() {
			public void run(Entity e) {
				int oldX = e.getTile().getLocation().getX();
				int oldY = e.getTile().getLocation().getY();
				int t=0;
				int x=oldX; 
				int y=oldY;
				while (++t<1000) {
					double v0 = 10;
					double ang = .69; // 40deg 
					double g = 9.8;
					x += (int) v0 * Math.cos(ang) * t;
					y += (int) v0 * Math.sin(ang) * t - (g/2) * (t*t);
					e.move(new Location(x,y));
				}
			}
		}.run(e);
		*/

	}

}
