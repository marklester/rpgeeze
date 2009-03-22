package rpgeeze.model.ae;

import rpgeeze.model.Entity;

public class LevelUp extends AreaEffect {
	
	public LevelUp() {
        super("Level Up");
    }
 
    public LevelUp(int rate) {
        super(rate, "Level Up");
    }

    public void apply(Entity e) {
// gain a level
		
		/*
		 * Idea: give the entity "infinite experience"
		 * by TDA, entity will gain enough experience to get to the next level 
		 * 
		 */
        /*if(!messageSent) {
                e.getStats().incLevel();
                //e.getStats().getPrimary().setExperience(e.getStats().getLevel() * e.getStats().getLevel() * 5);
                //e.getStats().calculateLevel();
        
                if(e.getStats().getLevel() < Stats.MAX_LEVEL) {
                        Console.getInstance().writeLine("Woohoo! You leveled up!");
                        messageSent = true;
                        e.getTile().setAreaEffect(null);
                        e.getTile().setDecal(null);
                }
        }*/
    }

}
