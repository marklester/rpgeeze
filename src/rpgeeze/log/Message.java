package rpgeeze.log;

import java.awt.Color;

public class Message {
	private final String author;
	private final String message;
	private final Color color;
	private final MessageType type;
	
	public Message(String message, String author) {
		this(message, author, MessageType.DEBUG);
	}
	
	public Message(String message, String author, MessageType type) {
		this(message, author, type.defaultColor(), type);
	}
	
	public Message(String message, String author, Color color, MessageType type) {
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
	
	public MessageType getType() {
		return type;
	}
}
