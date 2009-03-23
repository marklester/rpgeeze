package rpgeeze.model.item;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Location;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.util.AudioThread;




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
			AudioThread at = AudioThread.getInstance();
			at.setKeyType(this.getName(), AudioThread.CLIP);
			at.start();
			if(where != null){
				entity.move(where);
				LogManager.getInstance().log("A strange force sweeps you off your feet.", "", Message.Type.GAME); 
			}
		}
	}
}
