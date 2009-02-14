package model.items;

import model.Location;
import view.Drawer;


public class Boulder extends Obstacle {
	
	public Boulder(Location location) {
		super("Boulder", location);
	}
	
	public void draw(Drawer d) {
		d.drawBoulder(this);
	}
	
	public void activate()
	{
		//do nothing
	}

}
