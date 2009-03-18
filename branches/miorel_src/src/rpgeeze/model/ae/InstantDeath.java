package rpgeeze.model.ae;

import rpgeeze.model.Entity;

public class InstantDeath implements AreaEffect {
	public void apply(Entity entity) {
		// kill the entity
		
		/*
		 * Idea: deal "infinite damage" to the entity
		 * by TDA, entity will only take as much damage as it has health and trigger
		 * whatever death mechanism it has: respawn, end game, simply disappear, etc. 
		 * 
		 */
	}
}
