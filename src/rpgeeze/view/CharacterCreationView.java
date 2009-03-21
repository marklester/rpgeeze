package rpgeeze.view;

import static rpgeeze.RunGame.BACKGROUND_COLOR;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.Random;
import javax.media.opengl.GL;

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
	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);

	private Highlightable leftArrow;
	private Highlightable rightArrow;	
	private Text characterTitle = new Text("", renderer, 0.075f);
	
	public enum State implements View.State { NEW, NORMAL, ZOOMING, ZOOMED, HIDDEN; }
	
	private String characterName;
	private final String[] occupation = {"Smasher", "Summoner", "Sneak"};
	private final TexturedRectangle[] occupationImage = {
		new TexturedRectangle(ResourceLoader.getInstance().getTexture("occupation/smasher.png"), 100, 100),
		new TexturedRectangle(ResourceLoader.getInstance().getTexture("occupation/summoner.png"), 100, 100),
		new TexturedRectangle(ResourceLoader.getInstance().getTexture("occupation/sneak.png"), 100, 100),
	};
	private int occP;
	
	private static final double ZOOM_MIN = -60;
	private static final double ZOOM_MAX = -1.1;
	private static final double ZOOM_STEP = 0.25; 
	private double zoom = ZOOM_MIN;
	
	public CharacterCreationView(GameManager manager) {
		super(manager);
		
		TextRectangle rect = new TextRectangle(new Text("X", renderer, 0.05f), 10, 3);
		rect.setXYZ(-10, -3, 0);
		rect.alignText(0.5, 0.5);
		
		HighlightableWrapper<TextRectangle> prototype = new HighlightableWrapper<TextRectangle>(rect, MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
		GLUtil glutil = new GLUtil();
		Iterator<HighlightableWrapper<TextRectangle>> obj = glutil.objectGrid(prototype, 1, 2, rect.getWidth(), rect.getHeight());
		Iterator<String> names = new ArrayIterator<String>("OK", "Cancel");
		
		for(obj.reset(), names.reset(); !obj.isDone(); obj.advance(), names.advance()) {
			put(obj.current(), names.current());
			putHighlightable(obj.current(), names.current());
			obj.current().getWrappedObject().getText().setText(names.current());
			obj.current().getWrappedObject().alignText(0.5, 0.5);
			obj.current().getWrappedObject().getText().setY(prototype.getWrappedObject().getText().getY());
		}
		
		leftArrow = new HighlightableWrapper<Triangle>(new Triangle(new StaticVector(0, 0), new StaticVector(0, 8), new StaticVector(-2, 4)), Color.BLACK, MainMenuView.HIGHLIGHTED);
		rightArrow = new HighlightableWrapper<Triangle>(new Triangle(new StaticVector(0, 0), new StaticVector(0, 8), new StaticVector(2, 4)), Color.BLACK, MainMenuView.HIGHLIGHTED);
		
		putHighlightable(leftArrow, "Left Arrow");
		put(leftArrow, "Left Arrow");
		putHighlightable(rightArrow, "Right Arrow");
		put(rightArrow, "Right Arrow");
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
		
		gl.glTranslated(0, 0, zoom);
		glutil.color(MainMenuView.PLAIN);
		occupationImage[occP].render(gl);
			
		switch(getState()) {
		case NORMAL:
			gl.glLoadIdentity();
			gl.glTranslated(0, -9.5, -14.5);
			
			leftArrow.setXY(-14.5 * glutil.getViewportAspectRatio() + 3, 8);
			rightArrow.setXY(14.5 * glutil.getViewportAspectRatio() - 3, 8);
			
			// silly K's...
			String title = (characterName + " the " + occupation[occP]).replaceAll("k", "K");
			characterTitle.setText(title);
			characterTitle.setXY(-characterTitle.getWidth() / 2, 1);
		
			renderObjects(gl);
			
			break;
		case ZOOMING:
			if(point == null) {
				zoom += ZOOM_STEP;
				occupationImage[occP].setY(occupationImage[occP].getY() + ZOOM_STEP * 18 / (ZOOM_MIN - ZOOM_MAX));
				if(zoom > ZOOM_MAX) {
					zoom = ZOOM_MAX;
					changeState(State.ZOOMED);
				}
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
		zoom = ZOOM_MIN;
		for(TexturedRectangle rect: occupationImage)
			rect.setXYZ(-50, -32, 0);
		changeState(State.NORMAL);
	}
	
	public void startZoom() {
		if(getState() != State.ZOOMING && getState() != State.ZOOMED)
			changeState(State.ZOOMING);
	}
}
