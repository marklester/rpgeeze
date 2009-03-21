package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.GameProperties;
import rpgeeze.gl.GL;
import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.util.ResourceLoader;
import rpgeeze.view.overlay.TextureOverlay;

import static rpgeeze.GameProperties.LOGO_SIZE;
import static rpgeeze.GameProperties.LOGO_Y;
import static rpgeeze.GameProperties.LOGO_Z;

/**
 * The main menu screen.
 */
public final class MainMenuView extends HighlightableView<MainMenuView.State> {
	public static final Color PLAIN = new Color(1.0f, 1.0f, 1.0f, 0.0f);
	public static final Color HIGHLIGHTED = new Color(1.0f, 1.0f, 1.0f, 0.25f);

	public final static float MIN_INTENSITY = 0.0f;
	public final static float MAX_INTENSITY = 0.75f;

	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);

	private float intensity = MIN_INTENSITY;
	
	private TextureOverlay logo;

	public enum Button {
		NEW_GAME("New Game", 1, -10, 0),
		LOAD_GAME("Load Game", 2, 0, 0),
		OPTIONS("Options", 3, 10, 0),
		HELP("Help", 4, -10, -3),
		CREDITS("Credits", 5, 0, -3),
		QUIT("Quit", 6, 10, -3);

		private final String text;
		private final int glName;
		private final double x, y;

		private Button(String text, int glName, double x, double y) {
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
		
		public static Button fromGLName(int glName) {
			for(Button button: values())
				if(button.glName == glName)
					return button;
			return null;
		}
	}

	public enum State implements View.State { NEW, FADING_IN, NORMAL, HIDDEN; }
	
	public MainMenuView(GameManager manager) {
		super(manager);
		ResourceLoader loader = ResourceLoader.getInstance();
		GameProperties prop = GameProperties.getInstance();
	
		logo = new TextureOverlay(loader.getTexture(prop.getProperty("img.logo")));
		
		for(Button button: Button.values())
			putHighlightable(button.getButton());
		changeState(State.NEW);
	}

	/**
	 * Renders the main menu screen.
	 */
	public void render(GL gl, Point point) {
		boolean pick = point != null;
		setup(gl, point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);
		
		if(getState() == State.FADING_IN) {
			gl.glClearColor(0, intensity, intensity, 1.0f);
			if(point == null) {
				intensity += 0.01f;
				if(intensity > MAX_INTENSITY)
					changeState(State.NORMAL);
			}
		}
		else
			gl.glClearColor(0, MAX_INTENSITY, MAX_INTENSITY, 1.0f);
		
		
		gl.color(PLAIN);
		gl.glTranslated(0, LOGO_Y, 0);
		logo.render(gl, LOGO_SIZE, LOGO_SIZE, LOGO_Z, LOGO_Z, pick);
		gl.glTranslated(0, -LOGO_Y, 0);
		
		gl.glTranslated(-5, -9.5, -14.5);
		renderHighlightables();
		
		gl.glFlush();
	}

	public void changeFrom() {
		super.changeFrom();
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		super.changeTo();
		if(getState() == State.NEW)
			changeState(State.FADING_IN);
		else
			changeState(State.NORMAL);		
	}
}