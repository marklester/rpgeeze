package rpgeeze.model.decal;

import java.util.Hashtable;
import rpgeeze.model.decal.*;
import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;

public class Decal implements Visitable {
	protected final String name;
	private static Hashtable<String, Decal> prototypes = new Hashtable<String, Decal>();
	static {
		for(Decal d: new Decal[] {
			new GoldStar(),
			new RedCross(),
			new SkullAndCrossbones(),
			new FireDecal()
		})
			prototypes.put(d.toString(), d);
	}
	protected Decal(String name) {
		this.name = name;
	}
	public void accept(Visitor visitor) {
		visitor.visitDecal(this);
	}
	public static Decal getDecal(String key){
		return (Decal)prototypes.get(key).clone();
	}
	public Decal clone() {
		Decal clone = null;
		try {
			clone = (Decal) super.clone();
		}
		catch(CloneNotSupportedException e) {
		}
		return clone;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return this.name;
	}
}
