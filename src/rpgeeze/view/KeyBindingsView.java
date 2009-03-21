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
	private static final TextRenderer smallRenderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 14);
	private Character north, south, east, west, northEast, northWest, southEast, southWest, saveGame, loadGame, newGame, inventoryView, statsView, skillsView, optionsView;
	private String[] command = new String[16];
	
	public enum Button {
		
		N_ARROW(1) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(-1, 0), new VectorImpl(1, 0), new VectorImpl(0, 1)), Color.BLACK, MainMenuView.HIGHLIGHTED); 
			}
		},
		S_ARROW(2) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(-1, 0), new VectorImpl(1, 0), new VectorImpl(0, -1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		
		E_ARROW(3) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(0, 0), new VectorImpl(0, 2), new VectorImpl(1, 1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		
		W_ARROW(4) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(0, 0), new VectorImpl(0, 2), new VectorImpl(-1, 1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		NE_ARROW(5) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(2, 1), new VectorImpl(0, 1), new VectorImpl(2, -1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		SE_ARROW(6) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(2, 1), new VectorImpl(0, -1), new VectorImpl(2, -1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		NW_ARROW(7) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(-2, 1), new VectorImpl(-2, -1), new VectorImpl(0, 1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		SW_ARROW(8) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(-2, 1), new VectorImpl(-2, -1), new VectorImpl(0, -1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		SAVE_GAME(9) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Save Game", -19, 18), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		LOAD_GAME(10) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Load Game", -19, 10), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		NEW_GAME(11) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("New Game", -19, 2), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		INVENTORY_VIEW(12) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Inventory View", 5, 18), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		STATS_VIEW(13) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Stats View", 9, 10), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		SKILLS_VIEW(14) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Skills View", 8, 2), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		OPTIONS_VIEW(15) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Options View", -6.5, 2), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		OK(16) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("OK", -10, -3), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		CANCEL(17) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Cancel", 5, -3), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		},
		DEFAULTS(18) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(getRectangle("Defaults", -5, -3), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
			}
		};
		
		private final int glName;
		private Highlightable button;
		private boolean highlighted;
		
		private Button(int glName) {
			this.glName = glName;
			this.highlighted = false;
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
		
		public boolean getHighlighted(){
			return highlighted;
		}
		
		public void setHighlighted(boolean highlighted){
			this.highlighted = highlighted;
		}
		
		public abstract Highlightable doGetButton();
		
		public static Button fromGLName(int glName) {
			for(Button button: values())
				if(button.glName == glName)
					return button;
			return null;
		}
		
		public int getGLName(){
			return this.glName;
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
			String buttonText;
			gl.glLoadIdentity();
			gl.glTranslated(0, -9.5, -14.5);
			
			Highlightable northArrow = Button.N_ARROW.getButton();
			northArrow.setXY(0, 14);
			putHighlightable(northArrow);
			
			Text northCommand = new Text(command[1], smallRenderer, 0.075f);
			northCommand.setXY(-0.5 - command[1].length()/2, 15.5);
			northCommand.render();
			
			Highlightable southArrow = Button.S_ARROW.getButton();
			southArrow.setXY(0, 8);
			putHighlightable(southArrow);
			
			Text southCommand = new Text(command[2], smallRenderer, 0.075f);
			southCommand.setXY(-0.5 - command[2].length()/2, 5.5);
			southCommand.render();
			
			Highlightable eastArrow = Button.E_ARROW.getButton();
			eastArrow.setXY(3, 10);
			putHighlightable(eastArrow);
			
			Text eastCommand = new Text(command[3], smallRenderer, 0.075f);
			eastCommand.setXY(4.5, 10.5);
			eastCommand.render();
			
			Highlightable westArrow = Button.W_ARROW.getButton();
			westArrow.setXY(-3, 10);
			putHighlightable(westArrow);
			
			Text westCommand = new Text(command[4], smallRenderer, 0.075f);
			westCommand.setXY(-5 - command[4].length()/2, 10.5);
			westCommand.render();
			
			Highlightable northeastArrow = Button.NE_ARROW.getButton();
			northeastArrow.setXY(1, 13);
			putHighlightable(northeastArrow);
			
			Text northEastCommand = new Text(command[5], smallRenderer, 0.075f);
			northEastCommand.setXY(3.5, 14);
			northEastCommand.render();
			
			Highlightable southeastArrow = Button.SE_ARROW.getButton();
			southeastArrow.setXY(1, 9);
			putHighlightable(southeastArrow);
			
			Text southEastCommand = new Text(command[6], smallRenderer, 0.075f);
			southEastCommand.setXY(3.5, 7);
			southEastCommand.render();
			
			Highlightable northwestArrow = Button.NW_ARROW.getButton();
			northwestArrow.setXY(-1, 13);
			putHighlightable(northwestArrow);
			
			Text northWestCommand = new Text(command[7], smallRenderer, 0.075f);
			northWestCommand.setXY(-4, 14);
			northWestCommand.render();
			
			Highlightable southwestArrow = Button.SW_ARROW.getButton();
			southwestArrow.setXY(-1, 9);
			putHighlightable(southwestArrow);
			
			Text southWestCommand = new Text(command[8], smallRenderer, 0.075f);
			southWestCommand.setXY(-4, 7);
			southWestCommand.render();
			
			Text saveGameCommand = new Text(command[9], smallRenderer, 0.075f);
			buttonText = "SAVE_GAME";
			saveGameCommand.setXY(-18.5 + (buttonText.length() - command[9].length())/2, 16.5);
			saveGameCommand.render();
			
			Text loadGameCommand = new Text(command[10], smallRenderer, 0.075f);
			buttonText = "LOAD_GAME";
			loadGameCommand.setXY(-18.5 + (buttonText.length() - command[10].length())/2, 8.5);
			loadGameCommand.render();
			
			Text newGameCommand = new Text(command[11], smallRenderer, 0.075f);
			buttonText = "NEW_GAME";
			newGameCommand.setXY(-18.5 + (buttonText.length() - command[11].length())/2, 0.5);
			newGameCommand.render();
			
			Text inventoryViewCommand = new Text(command[12], smallRenderer, 0.075f);
			buttonText = "INVENTORY_VIEW";
			inventoryViewCommand.setXY(7 + (buttonText.length() - command[12].length())/2, 16.5);
			inventoryViewCommand.render();
			
			Text statsViewCommand = new Text(command[13], smallRenderer, 0.075f);
			buttonText = "STATS_VIEW";
			statsViewCommand.setXY(10.5 + (buttonText.length() - command[13].length())/2, 8.5);
			statsViewCommand.render();
			
			Text skillsViewCommand = new Text(command[14], smallRenderer, 0.075f);
			buttonText = "SKILLS_VIEW";
			skillsViewCommand.setXY(8.5 + (buttonText.length() - command[14].length())/2, 0.5);
			skillsViewCommand.render();
			
			Text optionsViewCommand = new Text(command[15], smallRenderer, 0.075f);
			buttonText = "OPTIONS_VIEW";
			optionsViewCommand.setXY(-5.5 + (buttonText.length() - command[15].length())/2, 0.5);
			optionsViewCommand.render();
			
			
			
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
	
	public void setCommand(int i, String comm){
		command[i] = comm; 
	}
	
	public void defaults(){
		command[0] ="";
		command[1] = "8"; //north
		command[2] = "2"; //south
		command[3] = "6"; //east
		command[4] = "4"; //west
		command[5] = "9"; //ne
		command[6] = "3"; //se
		command[7] = "7"; //nw
		command[8] = "1"; //sw
		command[9] = "S"; //save
		command[10] = "L"; //load
		command[11] = "N"; //new
		command[12] = "I"; //inventory
		command[13] = "Q"; //stats
		command[14] = "W"; //skills
		command[15] = "O"; //options
	}
}
