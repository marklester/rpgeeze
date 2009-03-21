package rpgeeze.math;

/**
 * A vector that does not change over time.
 * 
 */
public class StaticVector implements Vector {
	private final double x, y, z;

	/**
	 * Constructs a zero vector;
	 * 
	 */
	public StaticVector() {
		this(0, 0, 0);
	}
	
	/**
	 * Constructs a vector with the specified coordinates in 2D space and a
	 * z-coordinate of zero.
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 */
	public StaticVector(double x, double y) {
		this(x, y, 0);
	}

	/**
	 * Constructs a vector with the specified coordinates in 3D space.
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @param z
	 *            the z-coordinate
	 */
	public StaticVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
}
