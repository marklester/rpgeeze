package rpgeeze.dp;

/**
 * The observer design pattern.
 *
 * @param <T> the subject this observer observes 
 */
public interface Observer<T> {
	/**
	 * Reacts to a change in the state of the subject.
	 * 
	 */
	public void reactToChange();
}
