package rpgeeze.model.terrain;

import java.util.HashMap;

import rpgeeze.model.terrain.GrassTerrain;
import rpgeeze.model.terrain.MountainTerrain;
import rpgeeze.model.terrain.Terrain;
import rpgeeze.model.terrain.WaterTerrain;
import rpgeeze.model.Visitor;
import rpgeeze.model.entity.Entity;

public abstract class Terrain {
	private final String name;
	private static HashMap<String, Terrain> prototypes = new HashMap<String, Terrain>();
	
	static {
		for(Terrain t: new Terrain[] {
			MountainTerrain.getInstance(),
			GrassTerrain.getInstance(),
			WaterTerrain.getInstance(),
		})
			prototypes.put(t.getName(), t);
	}
	
	protected Terrain(String name) {
		this.name = name;
	}
	
	public void accept(Visitor visitor) {
		visitor.visitTerrain(this);
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return toString();
	}
	
	public boolean isPassable(Entity entity) {
		return true;
	}
	
	public static Terrain getTerrain(String key){
		return prototypes.get(key);
	}
}
