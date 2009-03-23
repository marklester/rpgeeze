package rpgeeze.model.item;

import java.util.Hashtable;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Location;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.util.AudioThread;

public class Gold extends OneShotItem {
	private int amount;
	public Gold() {
		super("Gold");
		this.amount= 20;
	}
	
	public void setProperties(Hashtable<String,String> properties){
		super.setProperties(properties);
		String str = properties.get("amount");
        this.amount = Integer.parseInt(str);
	}

	public void use(Entity entity, Tile tile) {
		AudioThread at = AudioThread.getInstance(getName(), AudioThread.CLIP);
		at.start();
		entity.addCash(amount);
		LogManager.getInstance().log("Gained " + amount + " Health", "", Message.Type.GAME);
	}

}
