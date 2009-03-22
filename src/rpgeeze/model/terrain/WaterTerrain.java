package rpgeeze.model.terrain;

public class WaterTerrain extends Terrain {
	private static Terrain instance;
	
	private WaterTerrain() {
	}
	
	public static Terrain getInstance() {
		if(instance == null)
			instance = new WaterTerrain();
		return instance;
	}
	
	public String getName() {
		return "Water Terrain";
	}
}
