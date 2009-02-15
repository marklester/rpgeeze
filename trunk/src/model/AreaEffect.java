package model;

public abstract class AreaEffect implements Cloneable {

	protected final String name;
	protected float rate;

	// This will typically be used by LevelUp and InstantDeath - since their
	// rates are irrelevant
	public AreaEffect(String name) {
		this.name = name;
		this.rate = 0;
	}

	public AreaEffect(float rate, String name) {
		this.name = name;
		this.rate = rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public AreaEffect clone() throws CloneNotSupportedException {
		return (AreaEffect) super.clone();
	}
	
	public void applyEffect(Entity e) {
		
	}

}
