package rpgeeze.log;

/**
 * An acceptor of log messages.
 * 
 */

public interface Logger {
	/**
	 * Logs a message in whatever fashion this logger logs.
	 * 
	 * @param message
	 *            the message to be logged
	 */
	public void log(Message message);
}
