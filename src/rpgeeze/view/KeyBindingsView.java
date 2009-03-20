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
		NE_ARROW(5) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(2, 1), new VectorImpl(0, 1), new VectorImpl(2, -1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		SE_ARROW(8) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(2, 1), new VectorImpl(0, -1), new VectorImpl(2, -1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
			}
		},
		E_ARROW(7) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(0, 0), new VectorImpl(0, 2), new VectorImpl(1, 1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
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
		W_ARROW(6) {
			public Highlightable doGetButton() {
				return new HighlightableWrapper(new Triangle(new VectorImpl(0, 0), new VectorImpl(0, 2), new VectorImpl(-1, 1)), Color.BLACK, MainMenuView.HIGHLIGHTED);
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
	
	public enum State implements View.State { NEW, NORMAL, ZOOMING, ZOOMED, HIDDEN; }
	
	private static final double ZOOM_MIN = -60;
	private static final double ZOOM_MAX = -1.1;
	private static final double ZOOM_STEP = 0.25; 
	private double zoom = ZOOM_MIN;
	
	public KeyBindingsView(GameManager manager) {
		super(manager);
		for(Button button: Button.values())
			putHighlightable(button.getButton());
		changeState(State.NEW);
	}

	/**
	 * Renders the key bindings screen.
	 */
	public void render(Point point) {
		GL gl = new GL();		
		gl.standardPrepare(point);
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
			
			Highlightable southArrow = Button.S_ARROW.getButton();
			southArrow.setXY(0, 8);
			putHighlightable(southArrow);
			
			Highlightable westArrow = Button.W_ARROW.getButton();
			westArrow.setXY(-3, 10);
			putHighlightable(westArrow);

			Highlightable eastArrow = Button.E_ARROW.getButton();
			eastArrow.setXY(3, 10);
			putHighlightable(eastArrow);
			
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
}
