package rpgeeze.model.terrain;

import rpgeeze.model.Visitor;
import rpgeeze.model.entity.Entity;

public abstract class Terrain {
	public void accept(Visitor visitor) {
		visitor.visitTerrain(this);
	}
	
	public boolean isPassable(Entity e) {
		return true;
	}
	
	public abstract String getName();
}
