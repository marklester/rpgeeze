package rpgeeze.model.map;

import rpgeeze.model.Entity;
import rpgeeze.model.Visitor;

public abstract class Map {
	public abstract Entity getAvatar();

	public void accept(Visitor visitor) {
		visitor.visitMap(this);
	}
}
