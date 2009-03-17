package rpgeeze.log;

import java.util.ArrayList;
import java.util.List;

public class LogManager {
	private static LogManager instance;
	
	private List<Logger> loggers = new ArrayList<Logger>();
	
	private LogManager() {
	}
	
	public static LogManager getInstance() {
		if(instance == null)
			instance = new LogManager();
		return instance;
	}
	
	public void registerLogger(Logger logger) {
		loggers.add(logger);
	}
	
	public void unregisterLogger(Logger logger) {
		loggers.remove(logger);
	}
	
	public void log(Message message) {
		for(Logger logger: loggers)
			logger.log(message);
	}
}
