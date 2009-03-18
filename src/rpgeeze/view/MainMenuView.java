package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.gl.GL;
import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableSet;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.TextRectangle;
import rpgeeze.gl.TexturedRectangle;
import rpgeeze.util.ResourceLoader;

/**
 * The main menu screen.
 */
public class MainMenuView extends HighlightableView {
	public static final Color PLAIN = new Color(1.0f, 1.0f, 1.0f, 0.0f);
	public static final Color HIGHLIGHTED = new Color(1.0f, 1.0f, 1.0f, 0.25f);

	public final static float MIN_INTENSITY = 0.0f;
	public final static float MAX_INTENSITY = 0.75f;

	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);

	private float intensity = MIN_INTENSITY;

	private TexturedRectangle introImage;

	private HighlightableSet buttons = new HighlightableSet();

	public enum MainMenuButton {
		NEW_GAME("New Game", 1, -10, 0),
		LOAD_GAME("Load Game", 2, 0, 0),
		OPTIONS("Options", 3, 10, 0),
		HELP("Help", 4, -10, -3),
		CREDITS("Credits", 5, 0, -3),
		QUIT("Quit", 6, 10, -3);

		private final String text;
		private final int glName;
		private final double x, y;

		private MainMenuButton(String text, int glName, double x, double y) {
			this.text = text;
			this.glName = glName;
			this.x = x;
			this.y = y;
		}

		public Highlightable getButton() {
			TextRectangle rect = new TextRectangle(new Text(text, renderer, 0.05f), 10, 3);
			rect.setGLName(glName);
			rect.alignText(0.5, 0.5);
			rect.setXY(x, y);
			return new HighlightableWrapper(rect, PLAIN, HIGHLIGHTED);
		}
		
		public static MainMenuButton fromGLName(int glName) {
			for(MainMenuButton button: values())
				if(button.glName == glName)
					return button;
			return null;
		}
	}

	public MainMenuView() {
		ResourceLoader loader = ResourceLoader.getInstance();	
		introImage = new TexturedRectangle(loader.getTexture("intro.png"), 25, 25, -12.5, -8, -20);
		introImage.setColor(PLAIN);
		for(MainMenuButton button: MainMenuButton.values())
			buttons.put(button.getButton());
	}

	/**
	 * Renders the main menu screen.
	 */
	public void render(Point point) {
		GL gl = GL.getCurrent();
		
		gl.glShadeModel(GL.GL_SMOOTH);

		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);

		// textures and blending
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

		gl.glClearColor(intensity, 0, 0, 1.0f);

		gl.glLoadIdentity();
		introImage.render();

		gl.glTranslated(-5, -7.5, -19);

		buttons.render();

		gl.glFlush();
	}

	public void highlight(int glName) {
		buttons.highlight(glName);
	}
	
	public void unhighlight() {
		buttons.unhighlight();
	}

	/**
	 * Changes the intensity of the background color.
	 * 
	 * @param dc the amount by which to change the intensity
	 */
	public void changeIntensity(float dc) {
		intensity += dc;
		if(intensity > MAX_INTENSITY)
			intensity = MAX_INTENSITY;
		if(intensity < MIN_INTENSITY)
			intensity = MIN_INTENSITY;
	}

	public void changeFrom() {
		super.changeFrom();
		intensity = MAX_INTENSITY;
	}
}
