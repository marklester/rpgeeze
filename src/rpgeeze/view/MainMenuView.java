package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.gl.GL;
import rpgeeze.gl.Highlightable;
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

		private TextRectangle getRectangle() {
			TextRectangle rect = new TextRectangle(new Text(text, renderer, 0.05f), 10, 3);
			rect.setGLName(glName);
			rect.alignText(0.5, 0.5);
			rect.setXY(x, y);
			if(this != NEW_GAME)
				rect.getText().setY(NEW_GAME.getRectangle().getText().getY());
			return rect;
		}
		
		public Highlightable getButton() {
			return new HighlightableWrapper(getRectangle(), PLAIN, HIGHLIGHTED);
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
		introImage = new TexturedRectangle(loader.getTexture("intro.png"), 25, 25, -12.5, -8, -15);
		introImage.setColor(PLAIN);
		for(MainMenuButton button: MainMenuButton.values())
			putHighlightable(button.getButton());
	}

	/**
	 * Renders the main menu screen.
	 */
	public void render(Point point) {
		GL gl = GL.getCurrent();		
		gl.standardPrepare(point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);
		gl.glClearColor(intensity, 0, 0, 1.0f);
		
		introImage.render();
		
		gl.glTranslated(-5, -9.5, -14.5);
		renderHighlightables();
		
		gl.glFlush();
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
