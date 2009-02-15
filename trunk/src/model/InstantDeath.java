package model;

public class InstantDeath extends AreaEffect {

	public InstantDeath() {
		super("Instant Death");
	}

	public InstantDeath(float rate) {
		super(rate, "Instant Death");
	}
	
	public void applyEffect(Entity e) {
		e.getStats().decLife(Stats.MAX_LIFE);
	}

}
