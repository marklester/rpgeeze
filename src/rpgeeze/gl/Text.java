package rpgeeze.gl;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;
import com.sun.opengl.util.j2d.TextRenderer.DefaultRenderDelegate;
import com.sun.opengl.util.j2d.TextRenderer.RenderDelegate;

public class Text extends GLObjectImpl {
	private String text;
	private TextRenderer renderer;
	private float scaleFactor;
	
	private static final RenderDelegate delegate = new DefaultRenderDelegate();
	
	public Text(String text, TextRenderer renderer, float scaleFactor) {
		this(text, Color.BLACK, renderer, scaleFactor);
	}
	
	public Text(String text, Color color, TextRenderer renderer, float scaleFactor) {
		super(color);
		this.text = text;
		this.renderer = renderer;
		this.scaleFactor = scaleFactor;
	}
	
	public String getText() {
		return text;
	}

	protected void doRender(GL gl) {
		renderer.begin3DRendering();
		renderer.setColor(getColor());
		renderer.draw3D(getText(), 0, 0, 0, scaleFactor);
		renderer.end3DRendering();
	}
	
	public double getWidth() {
		return getBounds().getWidth();
	}

	public double getHeight() {
		return getBounds().getHeight();
	}
	
	public Rectangle2D getBounds() {
		Rectangle2D ret = delegate.getBounds(getText(), renderer.getFont(), renderer.getFontRenderContext());
		ret.setRect(0, 0, ret.getWidth() * scaleFactor, ret.getHeight() * scaleFactor);
		return ret;
	}
	
	public Text clone() {
		return (Text) super.clone();
	}
}
