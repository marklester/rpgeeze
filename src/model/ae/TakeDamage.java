package model.ae;
import java.awt.Color;

import model.entity.Stats;
import model.entity.StatsModifiable;

import view.Console;

public class TakeDamage extends AreaEffect {

	public TakeDamage() {
		super("Take Damage");
	}

	public TakeDamage(int rate) {
		super(rate, "Take Damage");
	}

	public void applyEffect(StatsModifiable sm) {
		if(--counter == 0) {
			//e.getStats().decLife(rate);
			sm.addHealth(0 - rate);
			
			counter = UPDATE_RATE; //reset
			if(!messageSent) {
				Console.getInstance().writeLine("Yo dog, you're dying", Color.red);
				messageSent = true;
			}
		}
	}
}
