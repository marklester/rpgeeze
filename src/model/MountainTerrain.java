package model;

import model.entity.Entity;
import view.Drawer;

public class MountainTerrain extends Terrain {
	private static MountainTerrain instance = null;

	private MountainTerrain() {
		super("Mountain Terrain");
	}

	public static MountainTerrain getInstance() {
		if(instance == null)
			instance = new MountainTerrain();
		return instance;
	}

	public boolean isPassable(Entity e) {
		return false;
		// For now, we return false... But, in the next iteration, Dave will
		// probably
		// have the ability to move past mountains. Having the entity passed to
		// us like
		// this will allow us to check the inventory for certain "tools" that
		// will allow
		// the bypass of mountains... Maybe the entity will later have a
		// "Transportation
		// Vehicle" which will allow for easy checking within this method
	}

}
