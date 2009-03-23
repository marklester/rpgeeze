package rpgeeze.model.item;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.PC;

/** 
 * I haven't really looked into how this is being used, whoever added this
 * should document it (i.e. I am too lazy to do it.)
 */


public class ManaPotion extends TakeableItem {
	public ManaPotion() {
		super("Mana Potion");
	}

	public void use(Entity entity) {
//		entity.addMana(200);
		entity.removeItem(this);
		LogManager.getInstance().log("Used Mana Potion.", "MODEL", Message.Type.GAME);
	}

}
