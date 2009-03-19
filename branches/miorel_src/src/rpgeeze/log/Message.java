package rpgeeze.log;

import java.awt.Color;

/**
 * Object representation of a log message. In other words, this is a glorified
 * string.
 * 
 */
public interface Message {
	/**
	 * Enumerates the different types of messages that can be logged.
	 * 
	 */
	public enum Type {
		DEBUG(), ERROR(Color.RED);

		private final Color defaultColor;

		private Type() {
			this(Color.BLACK);
		}

		private Type(Color color) {
			defaultColor = color;
		}

		/**
		 * Get a default color for this type of message. This is simply here for
		 * the convenience of message interface implementers, as it's unlikely
		 * anyone else will need to query it.
		 * 
		 * @return a default color for this type of message
		 */
		Color defaultColor() {
			return defaultColor;
		}
	}

	/**
	 * Get the message string.
	 * 
	 * @return the message string
	 */
	public String getMessage();

	/**
	 * Get a string identifying the author of this message.
	 * 
	 * @return a string identifying the author of this message
	 */
	public String getAuthor();

	/**
	 * Get the color associated with this message. Loggers which support colored
	 * output will make use of this method.
	 * 
	 * @return the color associated with this message
	 */
	public Color getColor();

	/**
	 * Get the type of this message.
	 * 
	 * @return the type of this message
	 */
	public Type getType();
}
