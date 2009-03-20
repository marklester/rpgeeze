package rpgeeze.math;

/**
 * Simple <code>Vector</code> interface implementation.
 * 
 */
public class VectorImpl implements Vector {
	private final double x, y, z;

	/**
	 * Constructs a zero vector;
	 * 
	 */
	public VectorImpl() {
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
	public VectorImpl(double x, double y) {
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
	public VectorImpl(double x, double y, double z) {
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

	public boolean equals(Object o) {
		boolean ret = false;
		if(o instanceof Vector) {
			Vector v = (Vector) o;
			ret = getX() == v.getX() && getY() == v.getY()
					&& getZ() == v.getZ();
		}
		return ret;
	}

	public int hashCode() {
		return (int) (getX() + getY() + getZ());
	}

	public String toString() {
		return String.format("(%.2f, %.2f, %.2f)", getX(), getY(), getZ());
	}
}
