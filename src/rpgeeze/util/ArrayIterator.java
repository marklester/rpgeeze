package rpgeeze.util;

import rpgeeze.dp.Iterator;

/**
 * An iterator over an array.
 * 
 * @param <T>
 *            the type over which this iterator iterates
 */
public class ArrayIterator<T> implements Iterator<T> {
	private final T[] array;
	private int pointer;

	/**
	 * Constructs an iterator which iterates over the specified array.
	 * 
	 * @param array
	 *            the array to iterate over
	 */
	public ArrayIterator(T... array) {
		this.array = array;
	}

	public void advance() {
		++pointer;
	}

	public T current() {
		return array[pointer];
	}

	public boolean isDone() {
		return pointer >= array.length;
	}

	public void reset() {
		pointer = 0;
	}

}
