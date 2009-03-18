package rpgeeze.math;

public class VectorImpl implements Vector {
	private final double x, y, z;
	
	public VectorImpl(double x, double y) {
		this(x, y, 0);
	}
	
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
}
