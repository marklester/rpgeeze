package rpgeeze.model.ae;

import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.StatsModifiable;
import rpgeeze.util.AudioThread;

public class InstantDeath extends AreaEffect {
	
	public InstantDeath() {
        super("Instant Death");
    }

    public InstantDeath(int rate) {
        super(rate, "Instant Death");
    }

    public void applyEffect(StatsModifiable e) {
		AudioThread at = AudioThread.getInstance();
		at.setKeyType(this.toString(), AudioThread.CLIP);
		at.start();
        //ResourceLoader.getInstance().playAudioClip(this.name);
        //e.getStats().decLife(Stats.MAX_LIFE);
        //Console.getInstance().writeLine("You just got Knocked The Hack Out", Color.RED);
    }

}

