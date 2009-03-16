package rpgeeze.model.terrain;

public class MountainTerrain extends Terrain {
	private static Terrain instance;
	
	private MountainTerrain() {
	}
	
	public static Terrain getInstance() {
		if(instance == null)
			instance = new MountainTerrain();
		return instance;
	}
}
