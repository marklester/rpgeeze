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
import rpgeeze.gl.ButtonSet;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Scene;
import rpgeeze.gl.Text;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.gl.geom.TexturedRectangle;
import rpgeeze.gl.geom.Triangle;
import rpgeeze.math.StaticVector;
import rpgeeze.util.ResourceLoader;

/**
 * The character creation screen.
 * 
 */
public class CharacterCreationView extends HighlightableView<CharacterCreationView.State> {
	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);

	private Scene pickables;
	private ButtonSet buttons;
	private Highlightable leftArrow;
	private Highlightable rightArrow;	
	
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
		
		pickables = new Scene();
		TextRectangle prototype = new TextRectangle(new Text("X", renderer, 0.05f), 10, 3);
		prototype.setXY(-10, -3);
		prototype.alignText(0.5, 0.5);
		buttons = new ButtonSet(prototype, MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED, 3, 0, 0, "OK", "Cancel");
		buttons.addTo(pickables);
		
		leftArrow = new HighlightableWrapper(new Triangle(new StaticVector(0, 0), new StaticVector(0, 8), new StaticVector(-2, 4)), Color.BLACK, MainMenuView.HIGHLIGHTED);
		rightArrow = new HighlightableWrapper(new Triangle(new StaticVector(0, 0), new StaticVector(0, 8), new StaticVector(2, 4)), Color.BLACK, MainMenuView.HIGHLIGHTED);
		
		pickables.add(leftArrow, "Left Arrow");
		pickables.add(rightArrow, "Right Arrow");
		
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
	
//			gl.glTranslated(-15, -3, 0);
			pickables.render(gl);
			
			// silly K's...
			String title = (characterName + " the " + occupation[occP]).replaceAll("k", "K");
			Text characterTitle = new Text(title, renderer, 0.075f);
			characterTitle.setXY(-characterTitle.getWidth() / 2, 1);
			characterTitle.render(gl);
			
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
		case ZOOMED:
			break;
		}
		
		gl.glFlush();
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
		unhighlight();
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		unhighlight();
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

	public void highlight(String name) {
		buttons.highlight(name);
		if(name != null) {
			if(name.equals("Left Arrow"))
				leftArrow.highlight();
			else if(name.equals("Right Arrow"))
				rightArrow.highlight();
		}
	}

	public void unhighlight() {
		buttons.unhighlight();
		leftArrow.unhighlight();
		rightArrow.unhighlight();
	}

	protected String getNameForGLName(int glName) {
		return pickables.getNameForGLName(glName);
	}
}
