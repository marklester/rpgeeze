package rpgeeze.model.item;

import java.util.Hashtable;

import rpgeeze.model.entity.PC;
import rpgeeze.model.item.Item;
import rpgeeze.model.entity.Entity;

import rpgeeze.model.Tile;
import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;

public abstract class Item implements Visitable, Cloneable {
	protected final String name;
	protected Hashtable<String, String> properties;
    private static Hashtable<String, Item> prototypes = new Hashtable<String, Item>();
    static {
		for(Item i: new Item[] {
			new Sword(),
			new Boulder(),
			new CrossBow(),
			new HealthPack(),
			new Shield(),
			new RedArmor(),
			new Boots(),
			new Arrows(),
			new Mana(),
			new HealthPack(),
			new Helmet(),
			new PortalItem()
		})
			prototypes.put(i.toString(), i);
	}
	public Item(String name){
		this.name = name;
	}
	
	public void accept(Visitor visitor) {
		visitor.visitItem(this);
	}
	
	public abstract void activate(Entity entity, Tile tile);
	//public void deActivate(Entity e){}

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
         this.properties=properties;
	}
	public Hashtable<String,String> getProperties(){
         return properties;
	}
	
	public static Item getItem(String key){
        return (Item)prototypes.get(key).clone();
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
	public boolean isPassable() {
		return true;
	}

	public abstract void activate(PC pc);
	
	public abstract void use(PC pc);
	
	public void deActivate(PC pc)
	{
		
	}
	
}