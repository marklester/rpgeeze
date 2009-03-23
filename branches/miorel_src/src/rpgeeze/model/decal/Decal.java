package rpgeeze.model.decal;

import java.util.Hashtable;
import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;

public class Decal implements Visitable, Cloneable {
	private final String name;
	private static Hashtable<String, Decal> prototypes = new Hashtable<String, Decal>();
	static {
		for(Decal d: new Decal[] {
			new GoldStar(),
			new RedCross(),
			new SkullAndCrossbones(),
			new Fire()
		})
			prototypes.put(d.getName(), d);
	}
	
	protected Decal(String name) {
		this.name = name;
	}

	public void accept(Visitor visitor) {
		visitor.visitDecal(this);
	}

	public static Decal getDecal(String key){
		return prototypes.get(key).clone();
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
		return name;
	}
}
