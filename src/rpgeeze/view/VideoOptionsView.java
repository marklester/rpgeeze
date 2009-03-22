package rpgeeze.view;

import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.GameProperties;
import rpgeeze.dp.Iterator;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.gl.geom.TexturedRectangle;
import rpgeeze.util.ArrayIterator;
import rpgeeze.util.ResourceLoader;

import static rpgeeze.RunGame.BACKGROUND_COLOR;
import static rpgeeze.RunGame.LOGO_Y;
import static rpgeeze.RunGame.LOGO_Z;
import static rpgeeze.RunGame.LOGO_SIZE;

/**
 * The main menu screen.
 */
public class VideoOptionsView extends HighlightableView<VideoOptionsView.State> {

	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);
	
	public enum State implements View.State { NEW, FADING_IN, NORMAL, HIDDEN; }
	
	public VideoOptionsView(GameManager manager) {
		super(manager);
		ResourceLoader loader = ResourceLoader.getInstance();
		GameProperties prop = GameProperties.getInstance();
	
		TexturedRectangle logo = new TexturedRectangle(loader.getTexture(prop.getProperty("img.logo")), LOGO_SIZE, LOGO_SIZE, -LOGO_SIZE / 2, LOGO_Y - LOGO_SIZE / 2, LOGO_Z);
		put(logo, null);
		
		TextRectangle rect = new TextRectangle(new Text("X", renderer, 0.05f), 15, 3);
		rect.setXYZ(-22, -12.5, -14.5);
		rect.alignText(0.5, 0.5);

		HighlightableWrapper<TextRectangle> prototype = new HighlightableWrapper<TextRectangle>(rect, MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
		GLUtil glutil = new GLUtil();
		Iterator<HighlightableWrapper<TextRectangle>> obj = glutil.objectGrid(prototype, 1, 3, rect.getWidth(), rect.getHeight());
		Iterator<String> names = new ArrayIterator<String>("Full Screen", "Window Screen", "Back");
		
		for(obj.reset(), names.reset(); !obj.isDone(); obj.advance(), names.advance()) {
			put(obj.current(), names.current());
			putHighlightable(obj.current(), names.current());
			obj.current().getWrappedObject().getText().setText(names.current());
			obj.current().getWrappedObject().alignText(0.5, 0.5);
			obj.current().getWrappedObject().getText().setY(prototype.getWrappedObject().getText().getY());
		}
		
		changeState(State.NEW);
	}
	
	public void render(GL gl, Point point) {
		GLUtil glutil = new GLUtil(gl);
		glutil.standardFrustum(gl, point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);
		glutil.clearColor(BACKGROUND_COLOR);
		glutil.color(MainMenuView.PLAIN);
		renderObjects(gl);		
		gl.glFlush();
	}

	public void changeFrom() {
		clearAll();
		changeState(State.HIDDEN);		
	}

	public void changeTo() {
		clearAll();
		changeState(State.NORMAL);
	}
}