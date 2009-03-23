package rpgeeze.model.item;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.entity.Entity;

public class ManaPotion extends TakeableItem {
	public ManaPotion() {
		super("Mana Potion");
	}

	public void use(Entity entity) {
		entity.addMana(200);
		entity.removeItem(this);
		LogManager.getInstance().log("Used Mana Potion.", "MODEL", Message.Type.GAME);
	}

}
