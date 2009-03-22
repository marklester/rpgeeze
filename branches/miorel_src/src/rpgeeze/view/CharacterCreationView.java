package rpgeeze.view;

import static rpgeeze.RunGame.BACKGROUND_COLOR;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.Random;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.GameProperties;
import rpgeeze.dp.Iterator;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.gl.geom.TexturedRectangle;
import rpgeeze.gl.geom.Triangle;
import rpgeeze.math.StaticVector;
import rpgeeze.util.ArrayIterator;
import rpgeeze.util.ResourceLoader;

/**
 * The character creation screen.
 * 
 */
public class CharacterCreationView extends HighlightableView<CharacterCreationView.State> {
	private static double Y_SHIFT = 2;
	
	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);

	private Text characterTitle = new Text("", renderer, 0.075f);
	private Iterator<Highlightable> wheel;
	
	public enum State implements View.State { NEW, NORMAL, ZOOMING, ZOOMED, HIDDEN; }
	
	private String characterName;
	private final String[] occupation = {"Smasher", "Summoner", "Sneak"};
	private final TexturedRectangle[] occupationImage = {
		new TexturedRectangle(ResourceLoader.getInstance().getTexture("occupation/smasher.png"), 100, 100),
		new TexturedRectangle(ResourceLoader.getInstance().getTexture("occupation/summoner.png"), 100, 100),
		new TexturedRectangle(ResourceLoader.getInstance().getTexture("occupation/sneak.png"), 100, 100),
	};
	private int occP;
	
	public CharacterCreationView(GameManager manager) {
		super(manager);
		
		GLUtil glutil = new GLUtil();
		glutil.standardFrustum(GLU.getCurrentGL(), null);
		Iterator<String> names;
		
		TextRectangle rect = new TextRectangle(new Text("X", renderer, 0.05f), 10, 3);
		rect.setXYZ(-10, -12.5 - Y_SHIFT, -14.5);
		rect.alignText(0.5, 0.5);
		
		HighlightableWrapper<TextRectangle> button = new HighlightableWrapper<TextRectangle>(rect, MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
		Iterator<HighlightableWrapper<TextRectangle>> grid = glutil.objectGrid(button, 1, 2, rect.getWidth(), rect.getHeight());
		names = new ArrayIterator<String>("OK", "Cancel");
		
		for(grid.reset(), names.reset(); !grid.isDone(); grid.advance(), names.advance()) {
			put(grid.current(), names.current());
			putHighlightable(grid.current(), names.current());
			grid.current().getWrappedObject().getText().setText(names.current());
			grid.current().getWrappedObject().alignText(0.5, 0.5);
			grid.current().getWrappedObject().getText().setY(button.getWrappedObject().getText().getY());
		}
		
		Highlightable arrow = new HighlightableWrapper<Triangle>(new Triangle(new StaticVector(0, -4), new StaticVector(0, 4), new StaticVector(2, 0)), Color.BLACK, MainMenuView.HIGHLIGHTED);
		arrow.setZ(-14.5);
		wheel = glutil.objectWheel(arrow, 2);
		names = new ArrayIterator<String>("Right Arrow", "Left Arrow");
		
		for(wheel.reset(), names.reset(); !wheel.isDone(); wheel.advance(), names.advance()) {
			put(wheel.current(), names.current());
			putHighlightable(wheel.current(), names.current());
		}
		
		put(characterTitle, null);		
		
		changeState(State.NEW);
	}

	/**
	 * Renders the occupation selection screen.
	 */
	public void render(GL gl, Point point) {
		GLUtil glutil = new GLUtil(gl);
		glutil.standardFrustum(gl, point);
		glutil.clearColor(BACKGROUND_COLOR);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);		
		gl.glTranslated(0, Y_SHIFT, 0);
		
		glutil.color(MainMenuView.PLAIN);
		//occupationImage[occP].render(gl);
			
		switch(getState()) {
		case NORMAL:
			double disp = 14.5 * glutil.getViewportAspectRatio() - 3;
			for(wheel.reset(); !wheel.isDone(); wheel.advance())
				wheel.current().setX(disp);
			
			// silly K's...
			String title = (characterName + " the " + occupation[occP]).replaceAll("k", "K");
			characterTitle.setText(title);
			characterTitle.setXY(-characterTitle.getWidth() / 2, 1);
		
			renderObjects(gl);
			
			break;
		case ZOOMING:
			if(point == null) {
			}
			break;
		}
	}
	
	public void nextOccupation() {
		setOccupation((occP + 1) % occupation.length);
	}
	
	public void previousOccupation() {
		setOccupation((occP - 1 + occupation.length) % occupation.length);
	}
	
	private void setOccupation(int newOccupation) {
		occP = newOccupation;
	}
	
	public void randomName() {
		String names = GameProperties.getInstance().getProperty("game.names");
		
		// lowercase K's are silly in usual font, make them uppercase
		names = names.replaceAll("k", "K");
		
		String[] arr = names.split(",");
		
		Random rnd = new Random();
		String newName;
		while((newName = arr[rnd.nextInt(arr.length)]).equals(characterName));
		setCharacterName(newName);
	}
	
	public String getCharacterName() {
		return characterName;
	}
	
	public void setCharacterName(String newName) {
		characterName = newName;
	}
	
	public void changeFrom() {
		clearAll();
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		clearAll();
		Random rnd = new Random();
		setOccupation(rnd.nextInt(occupation.length));
		randomName();
//		zoom = ZOOM_MIN;
//		for(TexturedRectangle rect: occupationImage)
//			rect.setXYZ(-50, -32, 0);
		changeState(State.NORMAL);
	}
	
	public void startZoom() {
		if(getState() != State.ZOOMING && getState() != State.ZOOMED)
			changeState(State.ZOOMING);
	}
}
