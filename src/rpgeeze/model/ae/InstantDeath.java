package rpgeeze.model.ae;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.StatsModifiable;
import rpgeeze.util.AudioThread;
import rpgeeze.model.entity.Stats;

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
        e.getStats().decLife(Stats.MAX_LIFE);
        
        LogManager.getInstance().log("You just got Knocked The Hack Out", "", Message.Type.GAME);
    }

}

