package rpgeeze.model.terrain;

import java.util.Hashtable;

import rpgeeze.model.terrain.GrassTerrain;
import rpgeeze.model.terrain.MountainTerrain;
import rpgeeze.model.terrain.Terrain;
import rpgeeze.model.terrain.WaterTerrain;
import rpgeeze.model.Visitor;
import rpgeeze.model.entity.Entity;

public abstract class Terrain {
	
	private static Hashtable<String, Terrain> prototypes = new Hashtable<String, Terrain>();
	protected String name;
	
	static {
		for(Terrain t: new Terrain[] {
			MountainTerrain.getInstance(),
			GrassTerrain.getInstance(),
			WaterTerrain.getInstance(),
		})
			prototypes.put(t.toString(), t);
	}
	
	public void accept(Visitor visitor) {
		visitor.visitTerrain(this);
	}
	
	public String toString() {
		return this.name;
	}
	
	public String getName() {
		return toString();
	}
	
	public boolean isPassable(Entity e) {
		return true;
	}
	
	//Used to get a Terrain Prototype
	public static Terrain getTerrain(String key){
		return (Terrain)prototypes.get(key);
	}
}
