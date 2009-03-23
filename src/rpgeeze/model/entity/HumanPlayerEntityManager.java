package rpgeeze.model.entity;

import model.Command;
import model.Location;

public class HumanPlayerEntityManager extends EntityEventManager implements CommandHandler{
	private PC entity;
	
	public HumanPlayerEntityManager(PC entity)
	{
		super(entity);
		this.entity = entity;
	}

	public void killOnce() {
		entity.addLevel(-1);
	}

	public void execute(Command c) {
		// TODO Auto-generated method stub
		
	}
	
	public PC getEntity()
	{
		return entity;
	}
	
	private void respawn(int numOfLives) {
//		Location from = avatar.getTile().getLocation();
//		Location to = avatarStart;
//		avatar.resetStats(numOfLives); //reset all stats, except num of lives - that shouldn't be reset!
//		avatar.unequipAll();
//		avatar.move(to.subtract(from));
	}
}
