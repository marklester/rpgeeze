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
	public Vector add(final Vector v0, final Vector v1) {
		return new Vector() {
			public double getX() {
				return v0.getX() + v1.getX();
			}

			public double getY() {
				return v0.getY() + v1.getY();
			}

			public double getZ() {
				return v0.getZ() + v1.getZ();
			}
		};
	}

	/**
	 * Adds two vectors.
	 * 
	 * @param v0
	 *            the first vector
	 * @param v1
	 *            the second vector
	 * @return v0 - v1
	 */
	public Vector subtract(final Vector v0, final Vector v1) {
		return new Vector() {
			public double getX() {
				return v0.getX() - v1.getX();
			}

			public double getY() {
				return v0.getY() - v1.getY();
			}

			public double getZ() {
				return v0.getZ() - v1.getZ();
			}
		};
	}

	/**
	 * Multiplies a vector by a scalar.
	 * 
	 * @param v
	 *            the vector
	 * @param s
	 *            the scalar
	 * @return s * v
	 */
	public Vector multiply(final Vector v, final double s) {
		return multiply(v, new StaticScalar(s));
	}

	/**
	 * Multiplies a vector by a scalar.
	 * 
	 * @param v
	 *            the vector
	 * @param s
	 *            the scalar
	 * @return s * v
	 */
	public Vector multiply(final Vector v, final Scalar s) {
		return new Vector() {
			public double getX() {
				return s.getValue() * v.getX();
			}

			public double getY() {
				return s.getValue() * v.getY();
			}

			public double getZ() {
				return s.getValue() * v.getZ();
			}
		};
	}
	
	/**
	 * Skews a vector by the specified factors.
	 * 
	 * @param v
	 *            the vector
	 * @param xSkew
	 *            skew in the x direction
	 * @param ySkew
	 *            skew in the y direction
	 * @param zSkew
	 *            skew in the z direction
	 * @return (xSkew * v_x, ySkew * v_y, zSkew * v_z)
	 */
	public Vector skew(final Vector v, final double xSkew, final double ySkew,
			final double zSkew) {
		return new Vector() {
			public double getX() {
				return xSkew * v.getX();
			}

			public double getY() {
				return ySkew * v.getY();
			}

			public double getZ() {
				return zSkew * v.getZ();
			}
		};
	}
}
