package rpgeeze.view;

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
public class OptionsMenuView extends HighlightableView<OptionsMenuView.State> {
	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);
	
	private TextureOverlay logo;
	
	public enum State implements View.State { NEW, NORMAL, HIDDEN; }
	
	private Scene pickables;
	private ButtonSet buttons;
	
	public OptionsMenuView(GameManager manager) {
		super(manager);
		ResourceLoader loader = ResourceLoader.getInstance();
		GameProperties prop = GameProperties.getInstance();
	
		logo = new TextureOverlay(loader.getTexture(prop.getProperty("img.logo")));
		
		pickables = new Scene();
		TextRectangle prototype = new TextRectangle(new Text("X", renderer, 0.05f), 15, 3);
		prototype.setXYZ(-15, -12.5, -14.5);
		prototype.alignText(0.5, 0.5);
		buttons = new ButtonSet(prototype, MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED, 2, 0, 0, "Sound Options", "Back", "Video Options", "Key Bindings");
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
		
		glutil.clearColor(BACKGROUND_COLOR);
		
		boolean pick = point != null;
		
		glutil.color(MainMenuView.PLAIN);
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
		changeState(State.NORMAL);		
	}

	protected String getNameForGLName(int glName) {
		return pickables.getNameForGLName(glName);
	}
}
