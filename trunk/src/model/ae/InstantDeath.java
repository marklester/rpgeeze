package model.ae;
import util.ResourceLoader;
import model.Entity;
import model.Stats;

public class InstantDeath extends AreaEffect {

	public InstantDeath() {
		super("Instant Death");
	}

	public InstantDeath(int rate) {
		super(rate, "Instant Death");
	}
	
	public void applyEffect(Entity e) {
		ResourceLoader.getInstance().playAudioClip(this.name);
		e.getStats().decLife(Stats.MAX_LIFE);
		
		
	}

}
