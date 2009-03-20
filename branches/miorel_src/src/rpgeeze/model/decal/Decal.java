package rpgeeze.model.decal;

import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;

public class Decal implements Visitable {
	public void accept(Visitor visitor) {
		visitor.visitDecal(this);
	}
}
