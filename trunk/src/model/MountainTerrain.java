package model;

import view.Drawer;

public class MountainTerrain extends Terrain {
	
	public MountainTerrain() {
		super("Mountain Terrain");
	}
	
	public boolean isPassable(Entity e) {
		return false;
		//For now, we return false... But, in the next iteration, Dave will probably
		//have the ability to move past mountains. Having the entity passed to us like 
		//this will allow us to check the inventory for certain "tools" that will allow 
		//the bypass of mountains... Maybe the entity will later have a "Transportation 
		//Vehicle" which will allow for easy checking within this method
	}

	public void draw(Drawer d) {
		d.drawMountainTerrain(this);
	}
}

