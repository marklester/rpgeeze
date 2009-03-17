package rpgeeze.gl;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class TextRectangle extends Rectangle {
	private Text text;

	public TextRectangle(Text text, double width, double height, double x, double y, double z) {
		super(width, height, x, y, z);
		this.text = text.clone();
		this.text.setXYZ(0, 0, 0);
		this.text.setNameless();
	}

	public TextRectangle(Text text, double width, double height) {
		this(text, width, height, 0, 0, 0);
	}

	public Text getText() {
		return text;
	}
	
	public void alignText(double horiz, double vert) {
		double horizSpace = getWidth() - text.getWidth() * 2;
		double vertSpace = getHeight() - text.getHeight() * 2;
		text.setXY(horizSpace * horiz / 2, vertSpace * vert / 2);
	}
	
	public void doRender() {
		GL gl = GLU.getCurrentGL();
		gl.glBegin(GL.GL_QUADS);
		gl.glVertex2d(0, getHeight());
		gl.glVertex2i(0, 0);
		gl.glVertex2d(getWidth(), 0);
		gl.glVertex2d(getWidth(), getHeight());
		gl.glEnd();
		text.render();
	}
}
