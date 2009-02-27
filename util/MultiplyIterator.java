package util;

public class MultiplyIterator<T> implements Iterator<T> {
	private final Iterator<? extends T> iter;
	private final int factor;
	private int counter = 0;
	
	public MultiplyIterator(Iterator<? extends T> iterator, int factor) {
		this.iter = iterator;
		this.factor = factor;
	}
	
	public void reset() {
		iter.reset();
		counter = 0;
	}
	
	public boolean isDone() {
		return iter.isDone();
	}
	
	public void advance() {
		++counter;
		if(counter == factor) {
			counter = 0;
			iter.advance();
		}
	}
	
	public T current() {
		return iter.current();
	}
}
