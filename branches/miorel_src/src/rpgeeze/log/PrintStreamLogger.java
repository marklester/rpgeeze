package rpgeeze.log;

import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * A logger that prints out all messages it receives to a
 * <code>PrintStream</code>.
 * 
 */
public class PrintStreamLogger implements Logger {
	private final PrintStream stream;

	/**
	 * Constructs a logger which outputs to the file with the specified name.
	 * 
	 * @param fileName
	 *            the output destination
	 * @throws FileNotFoundException
	 *             if an error occurs while opening the file for writing
	 */
	public PrintStreamLogger(String fileName) throws FileNotFoundException {
		this(new PrintStream(new File(fileName)));
	}

	/**
	 * Constructs a logger which outputs to the specified file.
	 * 
	 * @param file
	 *            the output destination
	 * @throws FileNotFoundException
	 *             if an error occurs while opening the file for writing
	 */
	public PrintStreamLogger(File file) throws FileNotFoundException {
		this(new PrintStream(file));
	}

	/**
	 * Constructs a logger which outputs to the specified
	 * <code>OutputStream</code>.
	 * 
	 * @param stream
	 *            the output destination
	 */
	public PrintStreamLogger(OutputStream stream) {
		this(new PrintStream(stream));
	}

	/**
	 * Construct a logger which outputs to the specified
	 * <code>PrintStream</code>.
	 * 
	 * @param stream
	 *            the output destination
	 */
	public PrintStreamLogger(PrintStream stream) {
		this.stream = stream;
	}

	public void log(Message message) {
		if(message.getType() == Message.Type.GAME) {
			stream.println(message.getMessage());
			//stream.printf("[%s %s] %s%s", message.getAuthor(), message.getType(),
			//		message.getMessage(), System.getProperty("line.separator"));
			stream.flush();
		}
	}

	/**
	 * Gets the backing <code>PrintStream</code> of this logger.
	 * 
	 * @return the backing <code>PrintStream</code>
	 */
	public PrintStream getPrintStream() {
		return stream;
	}
}
