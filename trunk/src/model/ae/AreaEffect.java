package model.ae;

import model.entity.*;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AreaEffect implements Cloneable {
	private final static Pattern pattern = Pattern.compile("<ae><name>(.*)</name><rate>(.*)</rate></ae>");
	protected final static Hashtable<String, AreaEffect> prototypes = new Hashtable<String, AreaEffect>();
	
	static {
		for(AreaEffect ae: new AreaEffect[] {
			new LevelUp(),
			new TakeDamage(),
			new HealDamage(),
			new InstantDeath(),
			new LaunchAE()
		})
			prototypes.put(ae.toString(), ae);
	}
	
	public static final int UPDATE_RATE = 80;

	protected final String name;
	protected int rate;
	protected int counter = UPDATE_RATE;
	protected boolean messageSent;
	
	public AreaEffect(String name) {
		this(10, name);
	}

	public AreaEffect(int rate, String name) {
		this.name = name;
		this.rate = rate;
		messageSent = false;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getRate(){
		return rate;
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
	
	public abstract void applyEffect(StatsModifiable sm);

	public String toString() {
		return name;
	}
	
	public void setMessageSentFlag(boolean flag){
		messageSent = flag;
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
	public static AreaEffect getAreaEffect(String key){
		return (AreaEffect)prototypes.get(key).clone();
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
