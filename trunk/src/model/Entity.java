package model;

import view.*;

public class Entity implements Drawable {
	private Stats stats;
	private Inventory inventory;
	private Occupation occupation;
//    Location
//    Name
//    EquippedItems
	
	public Entity(Occupation occupation) {
		this.occupation = occupation;
	}
	
	public void draw(Drawer d) {
		d.drawEntity(this);
	}
}

