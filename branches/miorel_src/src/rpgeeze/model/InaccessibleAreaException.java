package rpgeeze.model;

public class InaccessibleAreaException extends RuntimeException {
	private static final long serialVersionUID = 7734009496324509208L;

	public InaccessibleAreaException() {
		super();
	}

	public InaccessibleAreaException(String message, Throwable cause) {
		super(message, cause);
	}

	public InaccessibleAreaException(String message) {
		super(message);
	}

	public InaccessibleAreaException(Throwable cause) {
		super(cause);
	}
}
