package rpgeeze.model.ae;

import rpgeeze.model.entity.Entity;
import rpgeeze.model.StatsModifiable;

public class InstantDeath extends AreaEffect {
	
	public InstantDeath() {
        super("Instant Death");
    }

    public InstantDeath(int rate) {
        super(rate, "Instant Death");
    }

    public void applyEffect(StatsModifiable e) {
// kill the entity
		
		/*
		 * Idea: deal "infinite damage" to the entity
		 * by TDA, entity will only take as much damage as it has health and trigger
		 * whatever death mechanism it has: respawn, end game, simply disappear, etc. 
		 * 
		 */
        //ResourceLoader.getInstance().playAudioClip(this.name);
        //e.getStats().decLife(Stats.MAX_LIFE);
        //Console.getInstance().writeLine("You just got Knocked The Hack Out", Color.RED);
    }
}

