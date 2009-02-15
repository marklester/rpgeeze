package model.ae;
import model.Entity;

public abstract class AreaEffect implements Cloneable {

	public static final int UPDATE_RATE = 80;
	protected final String name;
	protected int rate;
	protected int counter = UPDATE_RATE;
	protected boolean f_msg_was_sent = false;

	// This will typically be used by LevelUp and InstantDeath - since their
	// rates are irrelevant
	public AreaEffect(String name) {
		this.name = name;
		this.rate = 1;
	}

	public AreaEffect(int rate, String name) {
		this.name = name;
		this.rate = rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public AreaEffect clone() throws CloneNotSupportedException {
		return (AreaEffect) super.clone();
	}
	
	public void applyEffect(Entity e) {
			
	}

}
