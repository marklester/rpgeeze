package rpgeeze.math;

/**
 * An unchanging scalar value.
 * 
 */
public class StaticScalar implements Scalar {
	private final double value;

	/**
	 * Constructs a new scalar with the specified value.
	 * 
	 * @param value
	 *            the value of this scalar
	 */
	public StaticScalar(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
}
