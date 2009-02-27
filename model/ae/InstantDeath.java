package model.ae;
import java.awt.Color;

import util.ResourceLoader;
import view.Console;
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
		Console.getInstance().writeLine("You just got Knocked The Hack Out", Color.RED);
	}

}
