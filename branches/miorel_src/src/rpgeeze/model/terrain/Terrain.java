package rpgeeze.model.terrain;

import rpgeeze.model.Entity;
import rpgeeze.model.Visitor;

public abstract class Terrain {
	public void accept(Visitor visitor) {
		visitor.visitTerrain(this);
	}
	
	public boolean isPassable(Entity e) {
		return true;
	}
	
	public abstract String getName();
}
