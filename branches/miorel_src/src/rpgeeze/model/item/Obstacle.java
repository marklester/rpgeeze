package rpgeeze.model.item;

import rpgeeze.model.Entity;
import rpgeeze.model.InaccessibleAreaException;

/**
 * Makes the Tile that contains it permanently impassable.
 */


public abstract class Obstacle extends Item {
    public Obstacle(String name) {
            super(name);
    }

    public final void activate(Entity e) throws InaccessibleAreaException {
            
    }
    
}

