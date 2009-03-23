package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */


import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Tile;
import rpgeeze.model.decal.Decal;
import rpgeeze.model.entity.Entity;

public class Trap extends OneShotItem {
	private int amount;
	public Trap() {
		super("Trap");
		this.amount= -10;
	}
	
	public void use(Entity entity, Tile tile) {
		entity.addHealth(amount);
		tile.setDecal(Decal.getDecal("Trap Decal"));
		LogManager.getInstance().log("Lost " + amount + " Health", "", Message.Type.GAME);
	}

}