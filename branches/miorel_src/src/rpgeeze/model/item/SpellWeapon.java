package rpgeeze.model.item;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;


/**
 * An Item that is added to an Entity's Inventory on touch.
 */

public abstract class SpellWeapon extends EquipmentWeapon {	
	public SpellWeapon(String name) {
        super(name, "Summoner");
	}
}
