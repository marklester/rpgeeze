package model;

public class WaterTerrain extends Terrain {
	
	public WaterTerrain() {
		super("Water Terrain");
	}
	
	public boolean isPassable(Entity e) {
		return false;
		//For now, we return false... But, in the next iteration, Dave will probably
		//have the ability to move over/thru water. Having the entity passed to us like 
		//this will allow us to check the inventory for certain "tools" that will allow 
		//the bypass (e.g. water shoes)... Maybe the entity will later have a 
	}

}

