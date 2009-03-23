package rpgeeze.model.item;

import java.util.Hashtable;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Location;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.IllegalMoveException;
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
	
	public void setProperties(Hashtable<String,String> properties){
		super.setProperties(properties);
		String[] str = properties.get("location").split(",");
        where = new Location(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
	}	
	
	public void activate(Entity entity, Tile tile){
		if(entity.getEntityType().equals("Playable Character")){
			if(where != null){
				AudioThread at = AudioThread.getInstance();
				at.setKeyType(this.getName(), AudioThread.CLIP);
				at.start();
				
				entity.move(where.getX()+entity.getFacingDirection().getX(), where.getY()+entity.getFacingDirection().getY());
				LogManager.getInstance().log("A strange force sweeps you off your feet.", "", Message.Type.GAME);
				throw new IllegalMoveException();
			}
		}
	}
}
