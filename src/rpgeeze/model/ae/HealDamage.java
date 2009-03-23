package rpgeeze.model.ae;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.StatsModifiable;

public class HealDamage extends AreaEffect {
	
	public HealDamage() {
	    super("Heal Damage");
	}
	
	public HealDamage(int rate) {
	    super(rate, "Heal Damage");
	}

	public void applyEffect(StatsModifiable e) {
	    if(--counter == 0) {
	            
	            e.addHealth(rate);
	            	             
	            counter = UPDATE_RATE; 
	            if(!messageSent) {
	            	LogManager.getInstance().log("Congrats. Enjoy some life.", "", Message.Type.GAME);    	                    messageSent = true;
	            }
	            messageSent = true;
	            }
	    }
	}







