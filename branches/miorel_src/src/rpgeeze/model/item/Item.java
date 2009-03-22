package rpgeeze.model.item;

import java.util.Hashtable;
import java.util.regex.Pattern;

import rpgeeze.model.Entity;
import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;

public abstract class Item implements Visitable, Cloneable {
	
	
	protected final String name;
	
	protected Hashtable<String, String> properties;
    
    private static Pattern pattern = Pattern.compile("<item><type>(.*)</type>(.*)</item>");
    private static Hashtable<String, Item> prototypes = new Hashtable<String, Item>();


	public Item(String name){
		this.name = name;
	}
	
	public void accept(Visitor visitor) {
		visitor.visitItem(this);
	}
	
	public abstract void activate(Entity entity);
	
	public void deActivate(Entity e)
    {
    }

	
	public Item clone() {
         Item ret = null;
         try {
                 ret = (Item) super.clone();
         }
         catch(CloneNotSupportedException e) {}
         return ret;
	}
	 
	public void setProperties(Hashtable<String,String> properties){
         this.properties=properties;
	}
	 
	public Hashtable<String,String> getProperties(){
         return properties;
	}
	
	public static Item getItem(String key){
        return (Item)prototypes.get(key).clone();
	}


	public String toString() {
		return this.name;
	}
}
