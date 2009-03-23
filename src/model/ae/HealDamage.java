package model.ae;

import view.Console;
import model.entity2.*;

public class HealDamage extends AreaEffect {

	public HealDamage() {
		super("Heal Damage");
	}

	public HealDamage(int rate) {
		super(rate, "Heal Damage");
	}
	
	public void applyEffect(StatsModifiable sm) {
		if(--counter == 0) {
			sm.addHealth(rate);
//			int life = e.getStats().getLife();
//			e.getStats().incLife(rate);
			//e.addHealth(rate);
//			if(life != e.getStats().getLife()) { 
			counter = UPDATE_RATE; //reset
			if(!messageSent) {
				Console.getInstance().writeItemEvent("Congrats. Enjoy some life.");
				messageSent = true;
			}
		}
	}
}
