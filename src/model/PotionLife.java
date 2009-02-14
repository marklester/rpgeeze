package model;

import view.Drawer;

public class PotionLife extends OneShotItem  {
	
	public PotionLife(Location location) {
		super("Potion Life", location);
	}
	
	public void draw(Drawer d) {
		d.drawPotionLife(this);
	}
	
	public void activate()
	{
		//do nothing
	}

}
