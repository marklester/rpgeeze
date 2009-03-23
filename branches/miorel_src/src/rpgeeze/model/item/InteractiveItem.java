package rpgeeze.model.item;

/**
 * An Item that is (potentially) activated on touch. Activation may require
 * possession of a specific Item or completion of a sequence of actions
 * (e.g., puzzle).
 */

public abstract class InteractiveItem extends Item {
	public InteractiveItem(String name) {
		super(name);
	}
}
