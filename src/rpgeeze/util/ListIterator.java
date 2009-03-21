package rpgeeze.util;

import java.util.List;

import rpgeeze.dp.Iterator;

/**
 * An iterator over a list.
 * 
 * @param <T>
 *            the type over which this iterator iterates
 */
public class ListIterator<T> implements Iterator<T> {
	private final List<T> list;
	private int pointer;

	/**
	 * Constructs an iterator which iterates over the specified list.
	 * 
	 * @param list
	 *            the list to iterate over
	 */
	public ListIterator(List<T> list) {
		this.list = list;
	}

	public void advance() {
		++pointer;
	}

	public T current() {
		return list.get(pointer);
	}

	public boolean isDone() {
		return pointer >= list.size();
	}

	public void reset() {
		pointer = 0;
	}

}
