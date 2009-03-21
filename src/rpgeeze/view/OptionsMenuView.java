package rpgeeze.view;

import static rpgeeze.RunGame.BACKGROUND_COLOR;
import static rpgeeze.RunGame.LOGO_SIZE;
import static rpgeeze.RunGame.LOGO_Y;
import static rpgeeze.RunGame.LOGO_Z;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.GameProperties;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.util.ResourceLoader;
import rpgeeze.view.overlay.Overlay;
import rpgeeze.view.overlay.TextureOverlay;

/**
 * The main menu screen.
 */
public final class OptionsMenuView extends HighlightableView<OptionsMenuView.State> {
	public static final Color PLAIN = new Color(1.0f, 1.0f, 1.0f, 0.0f);
	public static final Color HIGHLIGHTED = new Color(1.0f, 1.0f, 1.0f, 0.25f);

	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);	
	private Overlay logo;

	public enum Button {
		KEY_BINDINGS("Key Bindings", 1, -6, 0),
		SOUND_OPTIONS("Sound Options", 2, 6, 0),
		VIDEO_OPTIONS("Video Options", 3, -6, -3),
		BACK("Back", 4, 6, -3);

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
			TextRectangle rect = new TextRectangle(new Text(text, renderer, 0.05f), 12, 3);
			rect.setGLName(glName);
			rect.alignText(0.5, 0.5);
			rect.setXY(x, y);
			if(this != KEY_BINDINGS)
				rect.getText().setY(KEY_BINDINGS.getRectangle().getText().getY());
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
	
	public OptionsMenuView(GameManager manager) {
		super(manager);
		ResourceLoader loader = ResourceLoader.getInstance();	
		GameProperties prop = GameProperties.getInstance();
	
		logo = new TextureOverlay(loader.getTexture(prop.getProperty("img.logo")));
		
		for(Button button: Button.values())
			putHighlightable(button.getButton());
		changeState(State.NEW);
	}

	/**
	 * Renders the options menu screen.
	 */
	public void render(GL gl, Point point) {
		boolean pick = point != null;
		setup(gl, point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);
		
		GLUtil glutil = new GLUtil(gl);
		glutil.clearColor(BACKGROUND_COLOR);
		
		glutil.color(PLAIN);
		gl.glTranslated(0, LOGO_Y, 0);
		logo.render(gl, LOGO_SIZE, LOGO_SIZE, LOGO_Z, LOGO_Z, pick);
		gl.glTranslated(0, -LOGO_Y, 0);
		
		gl.glTranslated(-5, -9.5, -14.5);
		renderHighlightables(gl);
		
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
