package rpgeeze.model.item;

import rpgeeze.model.Entity;

/** 
 * HealthPack is a One Shot Item that when used give
 * The entity Health
 */

public class HealthPack extends OneShotItem {
	private int amount;
	public HealthPack() {
		super("Health Pack");
		this.amount= 10;
	}

	public void activate(Entity e){
		/*int prev = e.getStats().getLife();
		e.getStats().incLife(amount);
		int end = e.getStats().getLife() - prev;
		if(end != 0)
			Console.getInstance().writeLine("Gained " + end + " Health", Color.GREEN);
		//might not want to kill it if it doesn't gain health from pack
		// yeah you do, it's a one-shot item by definition*/
	}

}
