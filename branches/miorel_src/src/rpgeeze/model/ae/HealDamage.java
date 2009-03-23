package rpgeeze.model.ae;

import rpgeeze.model.entity.Entity;
import rpgeeze.model.StatsModifiable;

public class HealDamage extends AreaEffect {
	
	public HealDamage() {
	    super("Heal Damage");
	}
	
	public HealDamage(int rate) {
	    super(rate, "Heal Damage");
	}

	public void applyEffect(StatsModifiable e) {
	    if(--counter == 0) {
	            //int life = e.getStats().getLife();
	            //e.getStats().incLife(rate);
	            //if(life != e.getStats().getLife()) { 
	            counter = UPDATE_RATE; //reset
	            if(!messageSent) {
	                   // Console.getInstance().writeLine("Congrats. Enjoy some life.", Color.GREEN);
	                    messageSent = true;
	            }
	            //}
	    }
	}
}






