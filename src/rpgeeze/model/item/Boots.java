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

public class Boots extends TakeableItem {
	public Boots() {
		super("Boots");
	}
	
	public void deActivate(PC pc)
	{
		pc.addMovement(-10);
	}
	
	public void use(PC pc) {
		pc.equipBoots(this);
		pc.addMovement(10);
		LogManager.getInstance().log("Boots have been equipped.", "", Message.Type.GAME);
	}

	@Override
	public void activate(Entity entity, Tile tile) {
		// TODO Auto-generated method stub
		
	}
}