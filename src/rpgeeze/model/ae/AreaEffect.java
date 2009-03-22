package rpgeeze.model.ae;

import java.util.Hashtable;

import rpgeeze.model.Entity;
import rpgeeze.model.Visitable;
import rpgeeze.model.Visitor;
import rpgeeze.model.entity.

public abstract class AreaEffect implements Visitable, Cloneable {
	
	public static final int UPDATE_RATE = 80;
	protected final static Hashtable<String, AreaEffect> prototypes = new Hashtable<String, AreaEffect>();
    protected final String name;
    protected int rate;
    protected int counter = UPDATE_RATE;
    protected boolean messageSent;
    
    static {
        for(AreaEffect ae: new AreaEffect[] {
                new LevelUp(),
                new TakeDamage(),
                new HealDamage(),
                new InstantDeath(),
        })
                prototypes.put(ae.toString(), ae);
}
    
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
    
		public abstract void applyEffect(StatsModifiable sm);
    
    public void setMessageSentFlag(boolean flag){
        messageSent = flag;
    }

    public static AreaEffect getAreaEffect(String key){
        return (AreaEffect)prototypes.get(key).clone();
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
    
	public void accept(Visitor visitor) {
		visitor.visitAreaEffect(this);
	}
	
	public String toString() {
        return name;
	}
}








        
       
        
       
        
       
        

        

        
        
        
       
        

        
       
        
        



