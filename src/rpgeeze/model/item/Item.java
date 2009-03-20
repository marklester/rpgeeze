package rpgeeze.model.item;

import rpgeeze.model.Entity;
import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;

public abstract class Item implements Visitable {
	public abstract void activate(Entity entity);
	
	public void accept(Visitor visitor) {
		visitor.visitItem(this);
	}
}
