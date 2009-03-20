package rpgeeze.math;

/**
 * Utility class for mathematical operations on vectors.
 * 
 */
public class VectorMath {
	/**
	 * Adds two vectors.
	 * 
	 * @param v0
	 *            the first vector
	 * @param v1
	 *            the second vector
	 * @return v0 + v1
	 */
	public Vector add(Vector v0, Vector v1) {
		return new VectorImpl(v0.getX() + v1.getX(), v0.getY() + v1.getY());
	}

	/**
	 * Multiplies a vector by a scalar.
	 * 
	 * @param v
	 *            a vector
	 * @param s
	 *            a scalar
	 * @return s * v
	 */
	public Vector multiply(Vector v, double s) {
		return new VectorImpl(s * v.getX(), s * v.getY());
	}

	public boolean equals(Object o) {
		return o instanceof VectorMath;
	}

	public int hashCode() {
		return 0;
	}
}
