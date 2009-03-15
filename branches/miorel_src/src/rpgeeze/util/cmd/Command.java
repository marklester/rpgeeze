package rpgeeze.util.cmd;

/**
 * The command design pattern, using generics!
 * 
 * @param <T> the type on which the Command operates 
 */

public interface Command<T> {
	/**
	 * Executes this Command on the given argument.
	 * 
	 * @param t receiver for the action encapsulated in this Command
	 */
	public void execute(T t);
}
