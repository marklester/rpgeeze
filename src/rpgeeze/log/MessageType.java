package rpgeeze.log;

import java.awt.Color;

public enum MessageType {
	DEBUG(),
	ERROR(Color.RED);
	
	private final Color defaultColor;

	private MessageType() {
		this(Color.BLACK);
	}
	
	private MessageType(Color color) {
		defaultColor = color;
	}

	public Color defaultColor() {
		return defaultColor;
	}
	
	public static MessageType defaultType() {
		return DEBUG;
	}
}