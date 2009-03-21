package rpgeeze.gl;

import javax.media.opengl.GL;

import rpgeeze.math.Vector;
import rpgeeze.util.Pair;

public abstract class GLObjectImpl implements GLObject {
	private double x, y, z;
	private double preAngle, preX, preY, preZ;
	private double postAngle, postX, postY, postZ;
	private int glName = -1;
	private boolean hasName = false;

	public GLObjectImpl() {
		this(0, 0, 0);
	}

	public GLObjectImpl(double x, double y, double z) {
		setXYZ(x, y, z);
	}

	public final void render(GL gl) {
		gl.glPushMatrix();
		GLUtil glutil = new GLUtil(gl);
		gl.glRotated(preAngle, preX, preY, preZ);
		glutil.translate(getXYZ());
		gl.glRotated(postAngle, postX, postY, postZ);
		if(hasName)
			gl.glLoadName(glName);
		doRender(gl);
		gl.glPopMatrix();
	}

	protected abstract void doRender(GL gl);

	public int getGLName() {
		return glName;
	}

	public void setGLName(int newGLName) {
		glName = newGLName;
		hasName = true;
	}

	public boolean hasGLName() {
		return hasName;
	}

	public void removeGLName() {
		hasName = false;
	}

	public double getX() {
		return x;
	}

	public void setX(double newX) {
		x = newX;
	}

	public double getY() {
		return y;
	}

	public void setY(double newY) {
		y = newY;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double newZ) {
		z = newZ;
	}

	public void setXY(double newX, double newY) {
		setX(newX);
		setY(newY);
	}

	public void setXYZ(double newX, double newY, double newZ) {
		setX(newX);
		setY(newY);
		setZ(newZ);
	}

	public Vector getXYZ() {
		return new Vector() {
			public double getX() {
				return GLObjectImpl.this.getX();
			}

			public double getY() {
				return GLObjectImpl.this.getY();
			}

			public double getZ() {
				return GLObjectImpl.this.getZ();
			}
		};
	}

	public Pair<Double, Vector> getPostTranslateRotation() {
		return new Pair<Double, Vector>(
				postAngle,
				new Vector() {
					public double getX() {
						return postX;
					}

					public double getY() {
						return postY;
					}

					public double getZ() {
						return postZ;
					}
				});
	}

	public Pair<Double, Vector> getPreTranslateRotation() {
		return new Pair<Double, Vector>(
				preAngle,
				new Vector() {
					public double getX() {
						return preX;
					}

					public double getY() {
						return preY;
					}

					public double getZ() {
						return preZ;
					}
				});
	}

	public void setPostTranslationRotation(double angle, double x, double y,
			double z) {
		postAngle = angle;
		postX = x;
		postY = y;
		postZ = z;
	}

	public void setPreTranslationRotation(double angle, double x, double y,
			double z) {
		preAngle = angle;
		preX = x;
		preY = y;
		preZ = z;
	}

	public GLObjectImpl clone() {
		GLObjectImpl ret = null;
		try {
			ret = (GLObjectImpl) super.clone();
		}
		catch (CloneNotSupportedException e) {
		}
		return ret;
	}
}
