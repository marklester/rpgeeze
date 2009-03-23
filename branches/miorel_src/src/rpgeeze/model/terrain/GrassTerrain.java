package rpgeeze.model.terrain;

public class GrassTerrain extends Terrain {
	private static Terrain instance;
	
	private GrassTerrain() {
		super("Grass Terrain");
	}
	
	public static Terrain getInstance() {
		if(instance == null)
			instance = new GrassTerrain();
		return instance;
	}
}
