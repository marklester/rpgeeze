package rpgeeze.dp;

/**
 * The Gang of Four's favorite iterator interface. Intended to be used like
 * this:
 * 
 * <pre>
 * Iterator&lt;SomeType&gt; iter = someObject.methodThatReturnsIterator();
 * for(iter.reset(); !iter.isDone(); iter.advance()) {
 *     iter.current().doSomething();
 * }
 * </pre>
 * 
 */

public interface Iterator<T> {
	/**
	 * Resets this iterator. Should be explicitly called since this Iterator may
	 * need to do extra initialization work in this method.
	 * 
	 */
	public void reset();

	/**
	 * Advances to the next element.
	 * 
	 */
	public void advance();

	/**
	 * Returns whether or not this iterator has exhausted all of its elements.
	 * 
	 */
	public boolean isDone();

	/**
	 * Returns the current element. Doesn't automatically advance this iterator,
	 * <code>advance()</code> should be explicitly called.
	 * 
	 */
	public T current();
}
