package model;

import model.entity2.Entity;
import view.Drawer;

public class WaterTerrain extends Terrain {
	private static WaterTerrain instance = null;

	private WaterTerrain() {
		super("Water Terrain");
	}

	public static WaterTerrain getInstance() {
		if(instance == null)
			instance = new WaterTerrain();
		return instance;
	}

	public boolean isPassable(Entity e) {
		return false;
		// For now, we return false... But, in the next iteration, Dave will
		// probably
		// have the ability to move over/thru water. Having the entity passed to
		// us like
		// this will allow us to check the inventory for certain "tools" that
		// will allow
		// the bypass (e.g. water shoes)... Maybe the entity will later have a
	}

}
