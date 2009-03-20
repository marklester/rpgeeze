package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.gl.GL;
import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.gl.geom.Triangle;
import rpgeeze.math.VectorImpl;
import rpgeeze.util.ResourceLoader;

/**
 * The key bindings screen.
 */
public class KeyBindingsView extends HighlightableView<KeyBindingsView.State> {
	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);
	private static final TextRenderer smallRenderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 22);
	private Character north, south, east, west, saveGame, loadGame, newGame, inventoryView, statsView, skillsView;
	public enum Button {
		OK(1) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("OK", -10, -3), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		CANCEL(2) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Cancel", 5, -3), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		N_ARROW(3) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(-1, 0), new VectorImpl(1, 0), new VectorImpl(0, 1)), Color.BLACK, MainMenuView.HIGHLIGHTED); 
			}
		},
		S_ARROW(4) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(-1, 0), new VectorImpl(1, 0), new VectorImpl(0, -1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		
		E_ARROW(5) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(0, 0), new VectorImpl(0, 2), new VectorImpl(1, 1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		
		W_ARROW(6) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(0, 0), new VectorImpl(0, 2), new VectorImpl(-1, 1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		NE_ARROW(7) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(2, 1), new VectorImpl(0, 1), new VectorImpl(2, -1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		SE_ARROW(8) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(2, 1), new VectorImpl(0, -1), new VectorImpl(2, -1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		NW_ARROW(9) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(-2, 1), new VectorImpl(-2, -1), new VectorImpl(0, 1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		SW_ARROW(10) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(-2, 1), new VectorImpl(-2, -1), new VectorImpl(0, -1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		SAVE_GAME(11) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Save Game", -19, 18), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		LOAD_GAME(12) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Load Game", -19, 10), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		NEW_GAME(13) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("New Game", -19, 2), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		INVENTORY_VIEW(14) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Inventory View", 5, 18), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		STATS_VIEW(15) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Stats View", 9, 10), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		SKILLS_VIEW(16) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Skills View", 8, 2), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		DEFAULTS(17) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Defaults", -5, -3), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		};
		
		private final int glName;
		private Highlightable button;
		
		private Button(int glName) {
			this.glName = glName;
		}
		
		private static TextRectangle getRectangle(String text, double x, double y) {
			TextRectangle rect = new TextRectangle(new Text(text, renderer, 0.05f), text.length() + 1, 3);
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
	
	public enum State implements View.State { NEW, NORMAL, ZOOMING, ZOOMED, HIDDEN; }
	
	private static final double ZOOM_MIN = -60;
	//private static final double ZOOM_MAX = -1.1;
	//private static final double ZOOM_STEP = 0.25; 
	private double zoom = ZOOM_MIN;
	
	public KeyBindingsView(GameManager manager) {
		super(manager);
		for(Button button: Button.values())
			putHighlightable(button.getButton());
		changeState(State.NEW);
		defaults();
	}

	/**
	 * Renders the key bindings screen.
	 */
	public void render(GL gl, Point point) {
		setup(gl, point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);
		gl.glClearColor(0, MainMenuView.MAX_INTENSITY, MainMenuView.MAX_INTENSITY, 1.0f);
		
		gl.glTranslated(0, 0, zoom);
		
			
		switch(getState()) {
		case NORMAL:
			gl.glLoadIdentity();
			gl.glTranslated(0, -9.5, -14.5);
			
			Highlightable northArrow = Button.N_ARROW.getButton();
			northArrow.setXY(0, 14);
			putHighlightable(northArrow);
			
			Text northCommand = new Text(north.toString(), smallRenderer, 0.075f);
			northCommand.setXY(-0.5, 15.5);
			northCommand.render();
			
			Highlightable southArrow = Button.S_ARROW.getButton();
			southArrow.setXY(0, 8);
			putHighlightable(southArrow);
			
			Text southCommand = new Text(south.toString().toString(), smallRenderer, 0.075f);
			southCommand.setXY(-0.5, 5.5);
			southCommand.render();
			
			Highlightable eastArrow = Button.E_ARROW.getButton();
			eastArrow.setXY(3, 10);
			putHighlightable(eastArrow);
			
			Text eastCommand = new Text(east.toString(), smallRenderer, 0.075f);
			eastCommand.setXY(4.5, 10.5);
			eastCommand.render();
			
			Highlightable westArrow = Button.W_ARROW.getButton();
			westArrow.setXY(-3, 10);
			putHighlightable(westArrow);
			
			Text westCommand = new Text(west.toString(), smallRenderer, 0.075f);
			westCommand.setXY(-5.5, 10.5);
			westCommand.render();
			
			Highlightable northeastArrow = Button.NE_ARROW.getButton();
			northeastArrow.setXY(1, 13);
			putHighlightable(northeastArrow);
			
			Highlightable southeastArrow = Button.SE_ARROW.getButton();
			southeastArrow.setXY(1, 9);
			putHighlightable(southeastArrow);
			
			Highlightable northwestArrow = Button.NW_ARROW.getButton();
			northwestArrow.setXY(-1, 13);
			putHighlightable(northwestArrow);
			
			Highlightable southwestArrow = Button.SW_ARROW.getButton();
			southwestArrow.setXY(-1, 9);
			putHighlightable(southwestArrow);
			
			Text saveGameCommand = new Text(saveGame.toString(), smallRenderer, 0.075f);
			saveGameCommand.setXY(-15, 16.5);
			saveGameCommand.render();
			
			Text loadGameCommand = new Text(loadGame.toString(), smallRenderer, 0.075f);
			loadGameCommand.setXY(-15, 8.5);
			loadGameCommand.render();
			
			Text newGameCommand = new Text(newGame.toString(), smallRenderer, 0.075f);
			newGameCommand.setXY(-15, 0.5);
			newGameCommand.render();
			
			Text inventoryViewCommand = new Text(inventoryView.toString(), smallRenderer, 0.075f);
			inventoryViewCommand.setXY(13, 16.5);
			inventoryViewCommand.render();
			
			Text statsViewCommand = new Text(statsView.toString(), smallRenderer, 0.075f);
			statsViewCommand.setXY(13, 8.5);
			statsViewCommand.render();
			
			Text skillsViewCommand = new Text(skillsView.toString(), smallRenderer, 0.075f);
			skillsViewCommand.setXY(13, 0.5);
			skillsViewCommand.render();
			
			
			
			renderHighlightables();
			gl.glLoadName(-1);
			break;
		case ZOOMING:
			break;
		case ZOOMED:
			break;
		}
		
		gl.glFlush();
	}
	public void changeFrom() {
		super.changeFrom();
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		super.changeTo();
		changeState(State.NORMAL);
	}
	
	public void startZoom() {
		if(getState() != State.ZOOMING && getState() != State.ZOOMED)
			changeState(State.ZOOMING);
	}
	
	public void setNorthCommand(Character c){
		north = c;
	}
	
	public void setSouthCommand(Character c){
		south = c;
	}
	
	public void setEastCommand(Character c){
		east = c;
	}
	
	public void setWestCommand(Character c){
		west = c;
	}
	
	public void setSaveGameCommand(Character c){
		saveGame = c;
	}
	
	public void setLoadGameCommand(Character c){
		loadGame = c;
	}
	
	public void setNewGameCommand(Character c){
		newGame = c;
	}
	
	public void setInventoryView(Character c){
		inventoryView = c;
	}
	
	public void setStatsView(Character c){
		statsView = c;
	}
	
	public void setSkillsView(Character c){
		skillsView = c;
	}
	
	public void defaults(){
		north = '8';
		south = '2';
		east = '6';
		west = '4';
		saveGame = 's';
		loadGame = 'l';
		newGame = 'n';
		inventoryView = 'i';
		statsView = 'q';
		skillsView = 'w';
	}
}
