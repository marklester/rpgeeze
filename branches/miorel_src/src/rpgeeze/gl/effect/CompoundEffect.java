package rpgeeze.gl.effect;

import rpgeeze.dp.Iterator;
import rpgeeze.gl.GL;
import rpgeeze.util.ArrayIterator;

/**
 * An effect composed of several others.
 * 
 */
public class CompoundEffect implements Effect {
	private final Iterator<Effect> effects;

	/**
	 * Combines the effects into a compound effect.
	 * 
	 * @param effects
	 *            the effects to apply
	 */
	public CompoundEffect(Effect... effects) {
		this(new ArrayIterator<Effect>(effects));
	}

	/**
	 * Combines the effects into a compound effect.
	 * 
	 * @param effects
	 *            an iterator over the effects to apply
	 */
	public CompoundEffect(Iterator<Effect> effects) {
		this.effects = effects;
		effects.reset();
	}

	public boolean isDone() {
		return effects.isDone();
	}

	public void apply(GL gl) {
		while(!effects.isDone() && effects.current().isDone())
			effects.advance();
		if(!effects.isDone())
			effects.current().apply(gl);
	}
}
