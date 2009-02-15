package util;

public class ContinuousIterator<T> implements Iterator<T> {
	private T[] elements;
	private int cursor = 0;
	
	public ContinuousIterator(T... elements) {
		this.elements = elements;
	}
	
	public void advance() {
		cursor = (cursor + 1) % elements.length;
	}

	public T current() {
		return elements[cursor];
	}

	public boolean isDone() {
		return false;
	}

	public void reset() {}

}
