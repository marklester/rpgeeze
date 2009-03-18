package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.gl.GL;
import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.TextRectangle;
import rpgeeze.gl.TexturedRectangle;
import rpgeeze.gl.Triangle;
import rpgeeze.math.Vector;
import rpgeeze.util.ResourceLoader;

/**
 * The occupation selection screen.
 */
public class CharacterCreationView extends HighlightableView<CharacterCreationView.State> {
	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);
	
	public enum Button {
		OK(1) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("OK", -10, -3), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		CANCEL(2) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Cancel", 0, -3), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		LEFT_ARROW(3) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new Vector(0, 0), new Vector(0, 8), new Vector(-2, 4)), Color.BLACK, MainMenuView.HIGHLIGHTED); 
			}
		},
		RIGHT_ARROW(4) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new Vector(0, 0), new Vector(0, 8), new Vector(2, 4)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		};
		
		private final int glName;
		private Highlightable button;
		
		private Button(int glName) {
			this.glName = glName;
		}
		
		private static TextRectangle getRectangle(String text, double x, double y) {
			TextRectangle rect = new TextRectangle(new Text(text, renderer, 0.05f), 10, 3);
			rect.alignText(0.5, 0.5);
			rect.setXY(x, y);
			if(!text.equals("OK"))
				rect.getText().setY(getRectangle("OK", 0, 0).getText().getY());
			return rect;
		}
		
		public Highlightable getButton() {
			if(button == null) {
				button = doGetButton();
				button.setGLName(glName);
			}
			return button;
		}
		
		public abstract Highlightable doGetButton();
		
		public static Button fromGLName(int glName) {
			for(Button button: values())
				if(button.glName == glName)
					return button;
			return null;
		}
	}	
	
	public enum State implements rpgeeze.dp.State { NEW, NORMAL, ZOOMING, ZOOMED, HIDDEN; }
	
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
	
	public CharacterCreationView() {
		for(TexturedRectangle rect: occupationImage) {
			rect.setColor(MainMenuView.PLAIN);
			rect.setVisible(false);
		}
		for(Button button: Button.values())
			putHighlightable(button.getButton());
		changeState(State.NEW);
	}

	/**
	 * Renders the occupation selection screen.
	 */
	public void render(Point point) {
		GL gl = GL.getCurrent();		
		gl.standardPrepare(point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);
		gl.glClearColor(MainMenuView.MAX_INTENSITY, 0, 0, 1.0f);
		
		gl.glTranslated(0, 0, zoom);
		for(TexturedRectangle rect: occupationImage)
			rect.render();
			
		switch(getState()) {
		case NORMAL:
			gl.glLoadIdentity();
			gl.glTranslated(0, -9.5, -14.5);
			
			Highlightable leftArrow = Button.LEFT_ARROW.getButton();
			leftArrow.setXY(-14.5 * gl.getViewportAspectRatio() + 3, 8);
			putHighlightable(leftArrow);

			Highlightable rightArrow = Button.RIGHT_ARROW.getButton();
			rightArrow.setXY(14.5 * gl.getViewportAspectRatio() - 3, 8);
			putHighlightable(rightArrow);
			
			renderHighlightables();
			gl.glLoadName(-1);
			
			// silly K's...
			String title = (characterName + " the " + occupation[occP]).replaceAll("k", "K");
			Text characterTitle = new Text(title, renderer, 0.075f);
			characterTitle.setXY(-characterTitle.getWidth() / 2, 1);
			characterTitle.render();
			
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
		occupationImage[occP].setVisible(false);
		occP = newOccupation;
		occupationImage[newOccupation].setVisible(true);
	}
	
	public void randomName() {
		Scanner s = new Scanner(ResourceLoader.getInstance().getStream("txt/names.txt"));
		List<String> names = new ArrayList<String>();
		while(s.hasNextLine())
			names.add(s.nextLine());
		Random rnd = new Random();
		String newName;
		while((newName = names.get(rnd.nextInt(names.size()))).equals(characterName));
		setCharacterName(newName);
	}
	
	public String getCharacterName() {
		return characterName;
	}
	
	public void setCharacterName(String newName) {
		characterName = newName;
	}
	
	public void changeFrom() {
		super.changeFrom();
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		super.changeTo();
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
