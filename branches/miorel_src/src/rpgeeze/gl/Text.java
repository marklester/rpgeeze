package rpgeeze.gl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;

public class Text {
	private int x;
	private int y;
	private String text;
	private Color color;
	private TextRenderer renderer; 
	
	public Text(String text, TextRenderer renderer) {
		this(text, Color.BLACK, renderer);
	}
	
	public Text(String text, Color color, TextRenderer renderer) {
		this.text = text;
		this.color = color;
		this.renderer = renderer;
	}
	
	public String getText() {
		return text;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color newColor) {
		color = newColor;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return (int) getDimension().getWidth();
	}

	public int getHeight() {
		return (int) getDimension().getHeight();
	}
	
	public Dimension getDimension() {
		Rectangle2D rect = renderer.getBounds(getText());
		return new Dimension((int) rect.getWidth(), (int) rect.getHeight());		
	}
	
	public int getViewportWidth() {
		return (int) getViewportDimension().getWidth();
	}

	public int getViewportHeight() {
		return (int) getViewportDimension().getHeight();
	}
	
	public Dimension getViewportDimension() {
		final GL gl = GLU.getCurrentGL();
		int[] buf = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, buf, 0);
		return new Dimension(buf[2], buf[3]);		
	}
	
	public void setY(int newY) {
		y = newY;
	}
	
	public int renderMode() {
		GL gl = GLU.getCurrentGL();
		int[] mode = new int[1];
		gl.glGetIntegerv(GL.GL_RENDER_MODE, mode, 0);
		return mode[0];
	}
	
	public void render() {
		renderer.beginRendering(getViewportWidth(), getViewportHeight());
		renderer.setColor(color);
		renderer.draw(getText(), getX(), getY());
		renderer.endRendering();
	}
	
	public void alignHorizontally(double fractionLeft) {
		setX((int) (fractionLeft * (getViewportWidth() - getWidth())));
	}

	public void alignVertically(double fractionBottom) {
		setY((int) (fractionBottom * (getViewportHeight() - getHeight())));
	}
	
	public void align(double fractionLeft, double fractionBottom) {
		alignHorizontally(fractionLeft);
		alignVertically(fractionBottom);
	}
	
	public String toString() {
		return getText();
	}
}
