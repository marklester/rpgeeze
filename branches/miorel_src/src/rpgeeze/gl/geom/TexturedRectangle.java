package rpgeeze.gl.geom;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;

public class TexturedRectangle extends Rectangle {
	private Texture texture;
	
	public TexturedRectangle(Texture texture, double width, double height, double x, double y, double z) {
		super(width, height, x, y, z);
		this.texture = texture;
	}
	
	public TexturedRectangle(Texture texture, double width, double height) {
		super(width, height);
		this.texture = texture;
	}

	public void doRender(GL gl) {
		gl.glEnable(GL.GL_TEXTURE_2D);
		texture.bind();
		gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2i(0, 0);
        gl.glVertex2d(0, getHeight());
        gl.glTexCoord2i(0, 1);
        gl.glVertex2i(0, 0);
        gl.glTexCoord2i(1, 1);
        gl.glVertex2d(getWidth(), 0);
        gl.glTexCoord2i(1, 0);
        gl.glVertex2d(getWidth(), getHeight());
		gl.glEnd();
	}
	
	public TexturedRectangle clone() {
		return (TexturedRectangle) super.clone();
	}
}
