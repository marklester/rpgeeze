package rpgeeze.model.ae;

import rpgeeze.model.Entity;
import rpgeeze.model.StatsModifiable;

public class TakeDamage extends AreaEffect {
	
	public TakeDamage() {
        super("Take Damage");
    }

    public TakeDamage(int rate) {
        super(rate, "Take Damage");
    }

    public void applyEffect(StatsModifiable e) {
        /*if(--counter == 0) {
                e.getStats().decLife(rate);
                counter = UPDATE_RATE; //reset
                if(!messageSent) {
                        Console.getInstance().writeLine("Yo dog, you're dying", Color.red);
                        messageSent = true;
                }
        }*/
    }

}
