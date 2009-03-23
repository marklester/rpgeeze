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

public class HealthPack extends OneShotItem {
	private int amount;
	public HealthPack() {
		super("Health Pack");
		this.amount= 10;
	}

	public void activate(PC pc){
//		int prev = pc.getStats().getLife();
//		pc.getStats().incLife(amount);
//		int end = pc.getStats().getLife() - prev;
		pc.addHealth(amount);
		LogManager.getInstance().log("Gained " + amount + " Health", "", Message.Type.GAME);
//		if(end != 0)
//			
		//might not want to kill it if it doesn't gain health from pack
		// yeah you do, it's a one-shot item by definition
	}

	@Override
	public void use(PC pc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activate(Entity entity, Tile tile) {
		// TODO Auto-generated method stub
		
	}

}
