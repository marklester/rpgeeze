package rpgeeze.gl.geom;

import javax.media.opengl.GL;

import rpgeeze.gl.GLUtil;

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

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture newTexture) {
		texture = newTexture;
	}

	public void doRender(GL gl) {
		if(texture != null) {
			gl.glEnable(GL.GL_TEXTURE_2D);
			texture.bind();
			gl.glBegin(GL.GL_QUADS);
			//if(texture != null)
			gl.glTexCoord2i(0, 0);
			gl.glVertex2d(0, getHeight());
			//if(texture != null)
			gl.glTexCoord2i(0, 1);
			gl.glVertex2i(0, 0);
			//if(texture != null)
			gl.glTexCoord2i(1, 1);
			gl.glVertex2d(getWidth(), 0);
			//if(texture != null)
			gl.glTexCoord2i(1, 0);
			gl.glVertex2d(getWidth(), getHeight());
			gl.glEnd();
			//texture.disable();
		}
	}

	public TexturedRectangle clone() {
		return (TexturedRectangle) super.clone();
	}
}
