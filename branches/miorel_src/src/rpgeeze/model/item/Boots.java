package rpgeeze.model.item;

import rpgeeze.model.entity.Entity;

public class Boots extends TakeableItem {
    public Boots() {
            super("Boots");
    }
    
    public void activate(Entity e) {
            use(e);
    }
    
    public void deActivate(Entity e)
    {
            //e.getStats().setMovement(15);
    }
    public void use(Entity e) {
          //  e.equipBoots(this);
           // e.getStats().setMovement(7);
           // view.Console.getInstance().writeLine("Boots have been equipped.");
    }
}

