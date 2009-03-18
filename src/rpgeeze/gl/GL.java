package rpgeeze.gl;

import javax.media.opengl.glu.GLU;

public class GL extends DelegatingGL {
	private GL(javax.media.opengl.GL delegate) {
		super(delegate);
	}
	
	public static GL getCurrent() {
		return new GL(GLU.getCurrentGL());
	}
}
