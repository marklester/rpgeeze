package model.items;

import model.Location;
import view.Drawer;

public class CrossBow extends InteractiveItem {
	public CrossBow(Location location) {
		super("Cross Bow",location);
	}
	
	public void draw(Drawer d) {
		d.drawCrossBow(this);
	}
	
	public void activate() {
		
	}
	
	

}
