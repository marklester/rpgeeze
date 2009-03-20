package rpgeeze.log;

import java.awt.Color;

/**
 * Simple <code>Message</code> interface implementation.
 * 
 */
public class MessageImpl implements Message {
	private final String author;
	private final String message;
	private final Color color;
	private final Message.Type type;

	/**
	 * Constructs a message with the the specified text and author. The message
	 * will be automatically assigned a default type. This is currently set to
	 * DEBUG, but the only allowed assumption is that the default message type
	 * will not be severe but diagnostic.
	 * 
	 * @param message
	 *            the message string
	 * @param author
	 *            an identifier for the author of this message
	 */
	public MessageImpl(String message, String author) {
		this(message, author, Message.Type.DEBUG);
	}

	/**
	 * Constructs a message with the the specified text, author, and message
	 * type. The message will be assigned a default color dependent on the type.
	 * 
	 * @param message
	 *            the message string
	 * @param author
	 *            an identifier for the author of this message
	 * @param type
	 *            the message type
	 */
	public MessageImpl(String message, String author, Message.Type type) {
		this(message, author, type.defaultColor(), type);
	}

	/**
	 * Constructs a message with the the specified text, author, color, and
	 * message type.
	 * 
	 * @param message
	 *            the message string
	 * @param author
	 *            an identifier for the author of this message
	 * @param color
	 *            the message color
	 * @param type
	 *            the message type
	 */
	public MessageImpl(String message, String author, Color color,
			Message.Type type) {
		this.message = message;
		this.author = author;
		this.color = color;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public String getAuthor() {
		return author;
	}

	public Color getColor() {
		return color;
	}

	public Message.Type getType() {
		return type;
	}
}
