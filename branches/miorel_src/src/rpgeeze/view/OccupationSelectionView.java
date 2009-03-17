package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;
import com.sun.opengl.util.j2d.TextRenderer.DefaultRenderDelegate;
import com.sun.opengl.util.j2d.TextRenderer.RenderDelegate;

import rpgeeze.gl.Rectangle;
import rpgeeze.gl.Text;
import rpgeeze.gl.TextRectangle;
import rpgeeze.gl.TexturedRectangle;
import rpgeeze.gl.Triangle;
import rpgeeze.math.Vector;
import rpgeeze.util.ResourceLoader;
import rpgeeze.view.MainMenuView.MainMenuButton;

/**
 * The occupation selection screen.
 */
public class OccupationSelectionView extends View {
	private static final Font font = ResourceLoader.getInstance().getFont("DeutscheZierschrift.ttf", Font.PLAIN, 36);
	private static final TextRenderer renderer = new TextRenderer(font, true, true);
	
	public enum OccupationSelectionButton {
		OK("OK", 1),
		CANCEL("Cancel", 2);

		private final int glName;
		private final TextRectangle rect;

		static {
			for(OccupationSelectionButton button: values()) {
				button.rect.getText().setY(OK.rect.getText().getY());
			}
		}
		
		private OccupationSelectionButton(String text, int glName) {
			this.glName = glName;
			this.rect = new TextRectangle(new Text(text, renderer, 0.05f), 10, 3);
			this.rect.setName(glName);
			this.rect.setColor(NORMAL);
			this.rect.alignText(0.5, 0.5);
		}
	
		public Rectangle getRectangle() {
			return rect;
		}
		
		public static OccupationSelectionButton fromGLName(int name) {
			OccupationSelectionButton ret = null;
			for(OccupationSelectionButton button: values())
				if(button.glName == name) {
					ret = button;
					break;
				}
			return ret;
		}
	}

	private OccupationSelectionButton highlighted = null;

	private static final Color NORMAL = new Color(1.0f, 1.0f, 1.0f, 0.0f);
	private static final Color HIGHLIGHTED = new Color(1.0f, 1.0f, 1.0f, 0.25f);

	private TexturedRectangle introImage;

	public OccupationSelectionView() {
		ResourceLoader loader = ResourceLoader.getInstance();

		OccupationSelectionButton.OK.getRectangle().setXY(-5, -3);
		OccupationSelectionButton.CANCEL.getRectangle().setXY(5, -3);
		
		introImage = new TexturedRectangle(loader.getTexture("intro.png"), 25, 25, -12.5, -8, -20);
		introImage.setColor(NORMAL);
	}

	/**
	 * Renders the main menu screen.
	 */
	public void render(Point point) {
		GL gl = GLContext.getCurrent().getGL();

		gl.glShadeModel(GL.GL_SMOOTH);

		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);

		// textures and blending
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);

		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		int[] vp = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, vp, 0);
		GLU glu = new GLU();
		if(point != null)
			glu.gluPickMatrix((double) point.x, (double) (vp[3] - point.y), 1e-3, 1e-3, vp, 0);
		double width = vp[2] <= 0 ? 1 : vp[2];
		gl.glFrustum(-vp[2] / width, vp[2] / width, -vp[3] / width, vp[3] / width, 1, 128);
		gl.glMatrixMode(GL.GL_MODELVIEW);		

		gl.glClearColor(MainMenuView.MAX_INTENSITY, 0, 0, 1.0f);

		gl.glLoadIdentity();
		introImage.render();

		gl.glTranslated(-5, -7.5, -19);

		for(OccupationSelectionButton button: OccupationSelectionButton.values())
			button.getRectangle().render();

		gl.glFlush();
	}

	/**
	 * Sets the button to be highlighted. Call with a value of zero to clear highlighting of all buttons. 
	 * 
	 * @param id identifier corresponding to the button to highlight
	 */
	public void setHighlightedButton(int id) {
		if(highlighted != null)
			highlighted.getRectangle().setColor(NORMAL);
		highlighted = OccupationSelectionButton.fromGLName(id);
		if(highlighted != null)
			highlighted.getRectangle().setColor(HIGHLIGHTED);
	}

	public void changeFrom() {
		setHighlightedButton(0);
	}
	
	
	public void changeTo() {
		setHighlightedButton(0);
	}
}
