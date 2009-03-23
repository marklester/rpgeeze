package model.ae;
import java.awt.Color;
import util.AudioThread;
import util.ResourceLoader;
import view.Console;
import model.entity2.StatsModifiable;

public class InstantDeath extends AreaEffect {

	public InstantDeath() {
		super("Instant Death");
	}

	public InstantDeath(int rate) {
		super(rate, "Instant Death");
	}
	
	public void applyEffect(StatsModifiable sm) {
		AudioThread.getInstance().playAudioClip(this.name);
		sm.addHealth(sm.maxHealth());
		Console.getInstance().writeLine("You just got Knocked The Hack Out", Color.RED);
	}

}
