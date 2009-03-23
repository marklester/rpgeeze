package rpgeeze.model.item;

import java.util.Hashtable;

import rpgeeze.model.item.Item;
import rpgeeze.model.entity.Entity;

import rpgeeze.model.Tile;
import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;

public abstract class Item implements Visitable, Cloneable {
	private final String name;
	private Hashtable<String, String> properties;
    
	private static Hashtable<String, Item> prototypes = new Hashtable<String, Item>();
    static {
		for(Item i: new Item[] {
				new Boots(),
				new Boulder(),
				new Crossbow(),
				new HealthPack(),
				new Helmet(),
				new ManaPotion(),
				new Portal(),
				new HealthPotion(),
				new RedArmor(),
				new Shield(),
				new Sword(),
				new Trap(),
				new LittleGun(),
				new BigGun(),
				new Spear(),
				new Axe(),
				new LightningSpell(),
				new WaterSpell(),
				new FireSpell()
		})
			prototypes.put(i.getName(), i);
	}

    protected Item(String name){
		this.name = name;
	}
	
	public void accept(Visitor visitor) {
		visitor.visitItem(this);
	}
	
	public abstract void activate(Entity entity, Tile tile);

	public Item clone() {
		Item ret = null;
		try {
			ret = (Item) super.clone();
		}
		catch(CloneNotSupportedException e) {}
		return ret;
	}
		
	/**
	 * Used to set extra properties in the Item dynamically
	 * @param properties is the Hashtable with (name,value) pairs for properties
	 * defined in the xml
	 * @return
	 */
	public void setProperties(Hashtable<String,String> properties){
         this.properties = properties;
	}
	
	public Hashtable<String,String> getProperties(){
         return properties;
	}
	
	public static Item getItem(String key) {
        return prototypes.get(key).clone();
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
