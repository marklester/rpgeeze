package rpgeeze.view;

import java.awt.Color;
import java.awt.Point;

import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.GameProperties;
import rpgeeze.dp.Iterator;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.effect.ClearColorChange;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.gl.geom.TexturedRectangle;
import rpgeeze.util.ArrayIterator;
import rpgeeze.util.ResourceLoader;

import static rpgeeze.RunGame.APP_FONT;
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
	
	private ClearColorChange fadeIn;
	
	public enum State implements View.State { NEW, FADING_IN, NORMAL, HIDDEN; }
	
	public MainMenuView(GameManager manager) {
		super(manager);
		ResourceLoader loader = ResourceLoader.getInstance();
		GameProperties prop = GameProperties.getInstance();
	
		TexturedRectangle logo = new TexturedRectangle(loader.getTexture(prop.getProperty("img.logo")), LOGO_SIZE, LOGO_SIZE, -LOGO_SIZE / 2, LOGO_Y - LOGO_SIZE / 2, LOGO_Z);
		put(logo, null);
		
		fadeIn = new ClearColorChange(Color.BLACK, BACKGROUND_COLOR, 1);

		TextRenderer renderer = new TextRenderer(APP_FONT.deriveFont(36f), true, true);
		TextRectangle rect = new TextRectangle(new Text("X", renderer, 0.05f), 10, 3);
		rect.setXYZ(-15, -12.5, -14.5);
		rect.alignText(0.5, 0.5);
		
		HighlightableWrapper<TextRectangle> prototype = new HighlightableWrapper<TextRectangle>(rect, PLAIN, HIGHLIGHTED);
		GLUtil glutil = new GLUtil();
		Iterator<HighlightableWrapper<TextRectangle>> obj = glutil.objectGrid(prototype, 2, 3, rect.getWidth(), rect.getHeight());
		Iterator<String> names = new ArrayIterator<String>("Help", "Credits", "Quit", "New Game", "Load Game", "Options");
		
		for(obj.reset(), names.reset(); !obj.isDone(); obj.advance(), names.advance()) {
			put(obj.current(), names.current());
			putHighlightable(obj.current(), names.current());
			obj.current().getWrappedObject().getText().setText(names.current());
			obj.current().getWrappedObject().alignText(0.5, 0.5);
			obj.current().getWrappedObject().getText().setY(prototype.getWrappedObject().getText().getY());
		}
		
		changeState(State.NEW);
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
		
		glutil.color(PLAIN);
		
		renderObjects(gl);
		
		gl.glFlush();
	}

	public void changeFrom() {
		clearAll();
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		clearAll();
		if(getState() == State.NEW)
			changeState(State.FADING_IN);
		else
			changeState(State.NORMAL);		
	}
}
