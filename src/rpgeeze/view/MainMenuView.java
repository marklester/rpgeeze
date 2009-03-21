package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.GameProperties;
import rpgeeze.gl.ButtonSet;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.Scene;
import rpgeeze.gl.Text;
import rpgeeze.gl.effect.ClearColorChange;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.util.ResourceLoader;
import rpgeeze.view.overlay.TextureOverlay;

import static rpgeeze.RunGame.BACKGROUND_COLOR;
import static rpgeeze.RunGame.LOGO_Y;
import static rpgeeze.RunGame.LOGO_Z;
import static rpgeeze.RunGame.LOGO_SIZE;

/**
 * The main menu screen.
 */
public class MainMenuView extends HighlightableView<MainMenuView.State> {
	public static final Color PLAIN = new Color(1.0f, 1.0f, 1.0f, 0.0f);
	public static final Color HIGHLIGHTED = new Color(1.0f, 1.0f, 1.0f, 0.25f);

	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);
	
	private TextureOverlay logo;

	private ClearColorChange fadeIn;
	
	public enum State implements View.State { NEW, FADING_IN, NORMAL, HIDDEN; }
	
	private Scene pickables;
	private ButtonSet buttons;
	
	public MainMenuView(GameManager manager) {
		super(manager);
		ResourceLoader loader = ResourceLoader.getInstance();
		GameProperties prop = GameProperties.getInstance();
	
		logo = new TextureOverlay(loader.getTexture(prop.getProperty("img.logo")));
		
		fadeIn = new ClearColorChange(Color.BLACK, BACKGROUND_COLOR, 1);
		
		pickables = new Scene();
		TextRectangle prototype = new TextRectangle(new Text("X", renderer, 0.05f), 10, 3);
		prototype.setXYZ(-15, -12.5, -14.5);
		prototype.alignText(0.5, 0.5);
		buttons = new ButtonSet(prototype, PLAIN, HIGHLIGHTED, 3, 0, 0, "Help", "Credits", "Quit", "New Game", "Load Game", "Options");
		buttons.addTo(pickables);
		
		changeState(State.NEW);
	}

	public void unhighlight() {
		buttons.unhighlight();
	}
	
	public void highlight(String name) {
		buttons.highlight(name);
	}
	
	/**
	 * Renders the main menu screen.
	 */
	public void render(GL gl, Point point) {
		GLUtil glutil = new GLUtil(gl);
		glutil.standardFrustum(gl, point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);
		
		if(getState() == State.FADING_IN) {
			if(point == null) {
				fadeIn.apply(gl);
				if(fadeIn.isDone())
					changeState(State.NORMAL);
			}
		}
		else
			glutil.clearColor(fadeIn.getFinalColor());
		
		boolean pick = point != null;
		
		glutil.color(PLAIN);
		gl.glTranslated(0, LOGO_Y, 0);
		logo.render(gl, LOGO_SIZE, LOGO_SIZE, LOGO_Z, LOGO_Z, pick);
		gl.glTranslated(0, -LOGO_Y, 0);
		
		pickables.render(gl);
		
		gl.glFlush();
	}

	public void changeFrom() {
		unhighlight();
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		unhighlight();
		if(getState() == State.NEW)
			changeState(State.FADING_IN);
		else
			changeState(State.NORMAL);		
	}

	protected String getNameForGLName(int glName) {
		return pickables.getNameForGLName(glName);
	}
}
