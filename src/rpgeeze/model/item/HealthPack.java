package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */


import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;

public class HealthPack extends OneShotItem {
	private int amount;
	public HealthPack() {
		super("Health Pack");
		this.amount= 10;
		setPrice(50);
	}

	public void use(Entity entity, Tile tile) {
		entity.addHealth(amount);
		LogManager.getInstance().log("Gained " + amount + " Health", "", Message.Type.GAME);
	}

}
