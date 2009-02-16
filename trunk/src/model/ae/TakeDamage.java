package model.ae;
import model.Entity;
import model.Stats;

import view.Console;

public class TakeDamage extends AreaEffect {

	public TakeDamage() {
		super("Take Damage");
	}

	public TakeDamage(int rate) {
		super(rate, "Take Damage");
	}

	public void applyEffect(Entity e) {
		if (--counter == 0) {
			e.getStats().decLife(rate);
			System.out.println("rate is " + rate + " - and current life is: " + e.getStats().getLife());
			counter = UPDATE_RATE; //reset
			if (!f_msg_was_sent) {
				Console.getInstance().writeLine("Yo dog, you're dying");
				f_msg_was_sent = true;
			}
		}
	}
}
