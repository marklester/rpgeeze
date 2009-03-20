package rpgeeze.view.overlay;

import com.sun.opengl.util.texture.Texture;

import rpgeeze.gl.GL;

public class TextureOverlay implements Overlay {
	private Texture texture;
	private double scalingFactor;
	
	public TextureOverlay(Texture texture) {
		this(texture, 1);
	}
	
	public TextureOverlay(Texture texture, double scalingFactor) {
		this.texture = texture;
		this.scalingFactor = scalingFactor;
	}
	
	public void render(GL gl, double width, double height, double near,
			double far, boolean pick) {
		if(!pick) {
			gl.glEnable(GL.GL_TEXTURE_2D);
			texture.bind();
			gl.glBegin(GL.GL_QUADS);
	        gl.glTexCoord2d(0.5 - 0.5 / scalingFactor, 0.5 - 0.5 / scalingFactor);
	        gl.glVertex3d(-width / 2, height / 2, near);
	        gl.glTexCoord2d(0.5 - 0.5 / scalingFactor, 0.5 + 0.5 / scalingFactor);
	        gl.glVertex3d(-width / 2, -height / 2, near);
	        gl.glTexCoord2d(0.5 + 0.5 / scalingFactor, 0.5 + 0.5 / scalingFactor);
	        gl.glVertex3d(width / 2, -height / 2, near);
	        gl.glTexCoord2d(0.5 + 0.5 / scalingFactor, 0.5 - 0.5 / scalingFactor);
	        gl.glVertex3d(width / 2, height / 2, near);
			gl.glEnd();
		}
	}
	
	public double getScalingFactor() {
		return scalingFactor;
	}
	
	public void setScalingFactor(double newScalingFactor) {
		scalingFactor = newScalingFactor;
	}
}
