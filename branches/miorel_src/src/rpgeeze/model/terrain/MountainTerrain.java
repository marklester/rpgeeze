package rpgeeze.model.terrain;

import rpgeeze.model.entity.Entity;

public class MountainTerrain extends Terrain {
	private static Terrain instance;
	
	private MountainTerrain() {
	}
	
	public static Terrain getInstance() {
		if(instance == null)
			instance = new MountainTerrain();
		return instance;
	}
	
	public boolean isPassable(Entity e) {
		return false;
	}
	
	public String getName() {
		return "Mountain Terrain";
	}
}
