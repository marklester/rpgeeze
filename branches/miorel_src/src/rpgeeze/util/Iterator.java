package rpgeeze.util;

/**
 * The Gang of Four's favorite Iterator interface.
 * 
 * "Provide a way to access the elements of an aggregate object sequentially
 * without exposing its underlying implementation."
 * 
 * Intended to be used like this:
 * 
 * <pre>Iterator<SomeType> iter = someObject.methodThatReturnsIterator();
 * for(iter.reset(); !iter.isDone(); iter.advance()) {
 *     iter.current().doSomething();
 * }</pre>
 * 
 */

public interface Iterator<T> {
	/**
	 * Resets the Iterator. Should be explicitly called since the Iterator may
	 * do extra initialization work in this method. 
	 */
	public void reset();

	/**
	 * Advances to the next element.
	 */
	public void advance();

	/**
	 * Returns whether or not the Iterator has exhausted all of its elements. 
	 */
	public boolean isDone();

	/**
	 * Returns the current element. This doesn't automatically advance the
	 * Iterator, you'll have to explicitly call advance().
	 */
	public T current();
}
