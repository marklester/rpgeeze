package rpgeeze.model;

public class InventoryFullException extends RuntimeException {
	private static final long serialVersionUID = -1081321560373650066L;

	public InventoryFullException() {
	}

	public InventoryFullException(String message) {
		super(message);
	}

	public InventoryFullException(Throwable cause) {
		super(cause);
	}

	public InventoryFullException(String message, Throwable cause) {
		super(message, cause);
	}
}
