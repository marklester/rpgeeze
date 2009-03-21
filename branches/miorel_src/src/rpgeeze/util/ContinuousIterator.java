package rpgeeze.util;

import rpgeeze.dp.Iterator;

/**
 * Wrapper for an iterator that makes it continuous.
 * 
 * @param <T>
 *            the type over which this iterator iterates
 */
public class ContinuousIterator<T> implements Iterator<T> {
	private final Iterator<? extends T> iterator;

	/**
	 * Constructs a continuous wrapper for the given iterator.
	 * 
	 * @param iterator
	 *            iterator to wrap
	 */
	public ContinuousIterator(Iterator<? extends T> iterator) {
		this.iterator = iterator;
	}

	public void advance() {
		iterator.advance();
		if(iterator.isDone())
			iterator.reset();
	}

	public T current() {
		return iterator.current();
	}

	public boolean isDone() {
		return false;
	}

	public void reset() {
		iterator.reset();
	}

}
