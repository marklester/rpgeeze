package rpgeeze.model.item;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.PC;

public class CrossBow extends TakeableItem {
	public CrossBow() {
		super("Crossbow");
	}

	public void activate(PC pc) {
		use(pc);
	}

	public void use(PC pc) {
		pc.equipWeapon(this);
		pc.unequipAuxiliary();
		LogManager.getInstance().log("Cross Bow has been equipped.", "", Message.Type.GAME);
	}

	@Override
	public void activate(Entity entity, Tile tile) {
		// TODO Auto-generated method stub
		
	}
}
