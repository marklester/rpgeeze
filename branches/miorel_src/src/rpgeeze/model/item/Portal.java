package rpgeeze.model.item;

import java.util.Hashtable;

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
	
	public void setProperties(Hashtable<String,String> properties){
		super.setProperties(properties);
		String[] str = properties.get("location").split(",");
        where = new Location(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
	}	
	
	public void activate(Entity entity, Tile tile){
		if(entity.getEntityType().equals("Playable Character")){
			AudioThread at = AudioThread.getInstance();
			at.setKeyType(this.getName(), AudioThread.CLIP);
			at.start();
			if(where != null){
				LogManager.getInstance().log(where.getX() +" " +where.getY(), "", Message.Type.GAME);
				entity.move(where);
				LogManager.getInstance().log("A strange force sweeps you off your feet.", "", Message.Type.GAME); 
			}
		}
	}
}
