package rpgeeze.log;

import java.io.PrintStream;

public class PrintStreamLogger implements Logger {
	private PrintStream stream;
	
	public PrintStreamLogger(PrintStream stream) {
		this.stream = stream;
	}
	
	public void log(Message message) {
		stream.printf("[%s %s] %s\n", message.getAuthor(), message.getType(), message.getMessage());
		stream.flush();
	}
}
