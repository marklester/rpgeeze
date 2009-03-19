package rpgeeze.log;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A centralized interface for logging messages.
 * 
 */
public class LogManager {
	private static LogManager instance;

	private List<Logger> loggers = new ArrayList<Logger>();

	private LogManager() {
	}

	/**
	 * Get an instance of a log manager. This is guaranteed to be the same one
	 * across calls to this method.
	 * 
	 * @return the singleton log manager
	 */
	public static LogManager getInstance() {
		if (instance == null)
			instance = new LogManager();
		return instance;
	}

	/**
	 * Register a logger with this log manager.
	 * 
	 * @param logger
	 *            the logger to register
	 */
	public void registerLogger(Logger logger) {
		loggers.add(logger);
	}

	/**
	 * Unregister a logger from this log manager.
	 * 
	 * @param logger
	 *            the logger to unregister
	 */
	public void unregisterLogger(Logger logger) {
		loggers.remove(logger);
	}

	/**
	 * Log a message. This is performed by informing all currently registered
	 * loggers about the message.
	 * 
	 * @param message
	 *            the message
	 */
	public void log(Message message) {
		for (Logger logger : loggers)
			logger.log(message);
	}

	/**
	 * Convenience method: equivalent to calling log with a message that has the
	 * specified text and author.
	 * 
	 * @param message
	 *            the message string
	 * @param author
	 *            an identifier for the author of this message
	 */
	public void log(String message, String author) {
		log(new MessageImpl(message, author));
	}

	/**
	 * Convenience method: equivalent to calling log with a message that has the
	 * specified text, author, and message type.
	 * 
	 * @param message
	 *            the message string
	 * @param author
	 *            an identifier for the author of this message
	 * @param type
	 *            the message type
	 */
	public void log(String message, String author, Message.Type type) {
		log(new MessageImpl(message, author, type));
	}

	/**
	 * Convenience method: equivalent to calling log with a message that has the
	 * specified text, author, color, and message type.
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
	public void log(String message, String author, Color color,
			Message.Type type) {
		log(new MessageImpl(message, author, color, type));
	}
}
