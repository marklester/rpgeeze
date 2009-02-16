package model.ae;
import java.awt.Color;

import model.Entity;

import view.Console;

public class HealDamage extends AreaEffect {

	public HealDamage() {
		super("Heal Damage");
	}

	public HealDamage(int rate) {
		super(rate, "Heal Damage");
	}
	
	public void applyEffect(Entity e) {
		if(--counter == 0) {
			e.getStats().incLife(rate);
			counter = UPDATE_RATE; //reset
			if (!f_msg_was_sent) {
				Console.getInstance().writeLine("Congrats. Enjoy some life", Color.GREEN);
				f_msg_was_sent = true;
			}
		}
	}

}
