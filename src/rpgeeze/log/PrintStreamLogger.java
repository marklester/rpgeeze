package rpgeeze.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * A logger that prints out all messages it receives to a PrintStream.
 * 
 */
public class PrintStreamLogger implements Logger {
	private final PrintStream stream;

	/**
	 * Construct a logger which outputs to the file with the specified name.
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
	 * Construct a logger which outputs to the specified file.
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
	 * Construct a logger which outputs to the specified OutputStream.
	 * 
	 * @param stream
	 *            the output destination
	 */
	public PrintStreamLogger(OutputStream stream) {
		this(new PrintStream(stream));
	}

	/**
	 * Construct a logger which outputs to the specified PrintStream.
	 * 
	 * @param stream
	 *            the output destination
	 */
	public PrintStreamLogger(PrintStream stream) {
		this.stream = stream;
	}

	public void log(Message message) {
		stream.printf("[%s %s] %s\n", message.getAuthor(), message.getType(),
				message.getMessage());
		stream.flush();
	}

	/**
	 * Get the backing PrintStream of this logger.
	 * 
	 * @return the backing PrintStream
	 */
	public PrintStream getPrintStream() {
		return stream;
	}
}
