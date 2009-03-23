package rpgeeze.model.entity;

import rpgeeze.model.*;

public abstract class EntityEventManager{
	private Entity entity;
	
	public EntityEventManager(Entity e)
	{
		entity = e;
	}
	
	public void update()
	{
		if(!entity.isAlive())
		{
			EntityManagerCollection.getInstance().remove(this);
			entity.getTile().setEntity(null);
		}
		updateStatusOfAvatar();
	}
	
	public void move(Location loc)
	{
		entity.move(loc);
	}
	public void move(int xOffset, int yOffset)
	{
		entity.move(new Location(xOffset, yOffset));
	}
	
	public Entity getEntity()
	{
		return entity;
	}
	
	public void updateStatusOfAvatar() {
//		//He's dead, so drop a life and respawn
//		if(!entity.isAlive()) {
//			entity.decLife();
////			Now check if there are any lives remaining
//			int numOfLivesLeft = entity.getStats().getPrimary().livesLeft; 
//			
//			if(numOfLivesLeft == 0)
//				endGame();
//			else
//				respawn(numOfLivesLeft);
//		}
//		//Is the avatar now on a AE? If so, apply it!
//		else if(entity.getTile().hasAE())
//			entity.getTile().getAE().applyEffect(entity);
	}

	public abstract void killOnce();

}
