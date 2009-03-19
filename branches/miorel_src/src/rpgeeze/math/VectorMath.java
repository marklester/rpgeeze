package rpgeeze.math;

/**
 * Implement methods related to vector math here.
 *
 */
public class VectorMath {
	/**
	 * Add two vectors.
	 * 
	 * @param v0 the first vector
	 * @param v1 the second vector
	 * @return v0 + v1
	 */
	public Vector add(Vector v0, Vector v1) {
		return new VectorImpl(v0.getX() + v1.getX(), v0.getY() + v1.getY());
	}
	
	/**
	 * Multiply a vector by a scalar.
	 * 
	 * @param v a vector
	 * @param c a scalar
	 * @return v0 + v1
	 */
	public Vector multiply(Vector v, double s) {
		return new VectorImpl(s * v.getX(), s * v.getY());
	}
}
