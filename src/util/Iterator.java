package util;

/*
 * Gang of Four Iterator. Use like this:
 * 
 * Iterator<Blah> iter = someObject.methodThatReturnsIterator();
 * for(iter.reset(); !iter.isDone(); iter.advance()) {
 *     iter.current().doSomething();
 * }
 * 
 */

public interface Iterator<T> {
	public void reset();

	public void advance();

	public boolean isDone();

	public T current();
}
