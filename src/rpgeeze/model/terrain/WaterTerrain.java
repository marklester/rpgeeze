package rpgeeze.model.terrain;

import rpgeeze.model.entity.Entity;

public class WaterTerrain extends Terrain {
	private static Terrain instance;
	
	private WaterTerrain() {
	}
	
	public static Terrain getInstance() {
		if(instance == null)
			instance = new WaterTerrain();
		return instance;
	}
	
	public boolean isPassable(Entity e) {
		return false;
	}
	
	public String getName() {
		return "Water Terrain";
	}
}
