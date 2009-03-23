package rpgeeze.model.item;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Location;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;




public class Portal extends InteractiveItem {
	private Location where;
	
	public Portal() {
		this(null);
	}
	
	public Portal(Location loc) {
		super("Portal");
		this.where = loc;
	}
	
	
	public void activate(Entity entity, Tile tile){
		if(entity.getEntityType().equals("Playable Character")){
			entity.getTile().releaseEntity();
			//AudioThread.getInstance().run(this.name, AudioThread.CLIP);
			if(where != null){
				entity.move(where);
				LogManager.getInstance().log("A strange force sweeps you off your feet.", "", Message.Type.GAME); 
			}
		}
	}
}