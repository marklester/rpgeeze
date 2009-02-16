package model.ae;

import model.Entity;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AreaEffect implements Cloneable {
	private final static Pattern pattern = Pattern.compile("<ae><name>(.*)</name><rate>(.*)</rate></ae>");
	private final static Hashtable<String, AreaEffect> prototypes = new Hashtable<String, AreaEffect>();
	
	static {
		for(AreaEffect ae: new AreaEffect[] {
			new LevelUp(),
			new TakeDamage(),
			new HealDamage(),
			new InstantDeath(),
		})
			prototypes.put(ae.toString(), ae);
	}
	
	public static final int UPDATE_RATE = 80;

	protected final String name;
	protected int rate;
	protected int counter = 1;
	protected boolean f_msg_was_sent = false;
	
	// This will typically be used by LevelUp and InstantDeath - since their
	// rates are irrelevant
	public AreaEffect(String name) {
		this(1, name);
	}

	public AreaEffect(int rate, String name) {
		this.name = name;
		this.rate = rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public AreaEffect clone() {
		AreaEffect ret = null;
		try {
			ret = (AreaEffect) super.clone();
		}
		catch(CloneNotSupportedException e) {
		}
		return ret;
	}
	
	public abstract void applyEffect(Entity e);

	public String toString() {
		return name;
	}
	
	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<ae>\n");
		sb.append(indent + "\t<name>" + name + "</name>\n");
		sb.append(indent + "\t<rate>" + rate + "</rate>\n");
		sb.append(indent + "</ae>");
		return sb.toString();
	}
	
	public static AreaEffect fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for AreaEffect");
		AreaEffect ret = prototypes.get(mat.group(1)).clone();
		ret.rate = Integer.parseInt(mat.group(2));
		return ret;
	}
}
