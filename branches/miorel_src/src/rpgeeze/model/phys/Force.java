package rpgeeze.model.phys;

import rpgeeze.math.Vector;
import rpgeeze.model.Entity;
import rpgeeze.model.ae.AreaEffect;

public abstract class Force implements AreaEffect, Vector {
	public final void apply(GameObject object) {
		doApply(object);
	}

	public final void apply(Entity entity) {
		doApply(entity);
	}
	
	public final void doApply(GameObject object) {
		// simply add self / object's mass to object's acceleration
		// some physics engine will then update velocities and positions
	}
}
