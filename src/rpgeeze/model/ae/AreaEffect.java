package rpgeeze.model.ae;

import rpgeeze.model.Entity;
import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;

public abstract class AreaEffect implements Visitable {
	public abstract void apply(Entity entity);
	
	public void accept(Visitor visitor) {
		visitor.visitAreaEffect(this);
	}
}
