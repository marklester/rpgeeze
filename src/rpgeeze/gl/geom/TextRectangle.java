package rpgeeze.gl.geom;

import javax.media.opengl.GL;

import rpgeeze.gl.GLUtil;
import rpgeeze.gl.Text;

public class TextRectangle extends Rectangle {
	private Text text;

	public TextRectangle(Text text, double width, double height, double x, double y, double z) {
		super(width, height, x, y, z);
		this.text = text.clone();
		this.text.setXYZ(0, 0, 0);
		this.text.removeGLName();
	}

	public TextRectangle(Text text, double width, double height) {
		this(text, width, height, 0, 0, 0);
	}

	public Text getText() {
		return text;
	}

	public void setText(Text newText) {
		text = newText;
	}
	
	public void alignText(double horiz, double vert) {
		text.setXY((getWidth() - text.getWidth()) * horiz, (getHeight() - text.getHeight()) * vert);
	}
	
	public void doRender(GL gl) {
		if(getColor() != null) {
			GLUtil glutil = new GLUtil(gl);
			glutil.color(getColor());
		}
		gl.glBegin(GL.GL_QUADS);
		gl.glVertex2d(0, getHeight());
		gl.glVertex2i(0, 0);
		gl.glVertex2d(getWidth(), 0);
		gl.glVertex2d(getWidth(), getHeight());
		gl.glEnd();
		text.render(gl);
	}
	
	public TextRectangle clone() {
		TextRectangle clone = (TextRectangle) super.clone();
		clone.text = text.clone();
		return clone;
	}
}
