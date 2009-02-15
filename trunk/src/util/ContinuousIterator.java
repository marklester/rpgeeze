package util;

public class ContinuousIterator<T> implements Iterator<T> {
	private final T[] elements;
	private int cursor = 0;

	public ContinuousIterator(T... elements) {
		this.elements = elements;
	}

	public void advance() {
		this.cursor = (this.cursor + 1) % this.elements.length;
	}

	public T current() {
		return this.elements[this.cursor];
	}

	public boolean isDone() {
		return false;
	}

	public void reset() {
	}

}
