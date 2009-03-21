package rpgeeze.math;

/**
 * Utility class for mathematical operations on scalars.
 * 
 */
public class ScalarMath {
	/**
	 * Adds two scalars.
	 * 
	 * @param s0
	 *            the first scalar
	 * @param s1
	 *            the second scalar
	 * @return s0 + s1
	 */
	public Scalar add(final Scalar s0, final double s1) {
		return add(s0, new StaticScalar(s1));
	}
	
	/**
	 * Adds two scalars.
	 * 
	 * @param s0
	 *            the first scalar
	 * @param s1
	 *            the second scalar
	 * @return s0 + s1
	 */
	public Scalar add(final Scalar s0, final Scalar s1) {
		return new Scalar() {
			public double getValue() {
				return s0.getValue() + s1.getValue();
			}
		};
	}
	
	/**
	 * Multiplies two scalars.
	 * 
	 * @param s0
	 *            the first scalar
	 * @param s1
	 *            the second scalar
	 * @return s0 * s1
	 */
	public Scalar multiply(final Scalar s0, final Scalar s1) {
		return new Scalar() {
			public double getValue() {
				return s0.getValue() * s1.getValue();
			}
		};
	}
	
	/**
	 * Multiplies two scalars.
	 * 
	 * @param s0
	 *            the first scalar
	 * @param s1
	 *            the second scalar
	 * @return s0 * s1
	 */
	public Scalar multiply(final Scalar s0, final double s1) {
		return new Scalar() {
			public double getValue() {
				return s0.getValue() * s1;
			}
		};
	}
}
