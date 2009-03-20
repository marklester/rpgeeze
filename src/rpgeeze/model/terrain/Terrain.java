package rpgeeze.model.terrain;

import rpgeeze.model.Visitor;

public abstract class Terrain {
	public void accept(Visitor visitor) {
		visitor.visitTerrain(this);
	}
}
