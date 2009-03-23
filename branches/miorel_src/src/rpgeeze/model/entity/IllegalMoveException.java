package rpgeeze.model.entity;

public class IllegalMoveException extends RuntimeException {
	private static final long serialVersionUID = 7734009496324509208L;

	public IllegalMoveException() {
		super();
	}

	public IllegalMoveException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalMoveException(String message) {
		super(message);
	}

	public IllegalMoveException(Throwable cause) {
		super(cause);
	}
}
