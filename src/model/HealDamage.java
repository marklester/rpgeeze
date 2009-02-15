package model;

import view.Console;

public class HealDamage extends AreaEffect {

	public HealDamage() {
		super("Heal Damage");
	}

	public HealDamage(float rate) {
		super(rate, "Heal Damage");
	}
	
	public void applyEffect(Entity e) {
		if (--counter == 0) {
			e.getStats().incLife(1);
			counter = UPDATE_RATE; //reset
			if (!f_msg_was_sent) {
				Console.getInstance().writeLine("Congrats. Enjoy some life");
				f_msg_was_sent = true;
			}
		}
	}

}
