package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.gl.GL;
import rpgeeze.gl.GLObject;
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
public class OccupationSelectionView extends HighlightableView {
	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);
	
	public enum OccupationSelectionButton {
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
		
		private OccupationSelectionButton(int glName) {
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
		
		public static OccupationSelectionButton fromGLName(int glName) {
			for(OccupationSelectionButton button: values())
				if(button.glName == glName)
					return button;
			return null;
		}
	}	
	
	private TexturedRectangle introImage;

	public OccupationSelectionView() {
		ResourceLoader loader = ResourceLoader.getInstance();
		
		introImage = new TexturedRectangle(loader.getTexture("intro.png"), 25, 25, -12.5, -8, -15);
		introImage.setColor(MainMenuView.PLAIN);
		for(OccupationSelectionButton button: OccupationSelectionButton.values())
			putHighlightable(button.getButton());
	}

	/**
	 * Renders the occupation selection screen.
	 */
	public void render(Point point) {
		GL gl = GL.getCurrent();		
		gl.standardPrepare(point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);
		gl.glClearColor(MainMenuView.MAX_INTENSITY, 0, 0, 1.0f);
		
		introImage.render();
	
		Highlightable leftArrow = OccupationSelectionButton.LEFT_ARROW.getButton();
		leftArrow.setXY(-14.5 * gl.getViewportAspectRatio() + 3, 8);
		putHighlightable(leftArrow);

		Highlightable rightArrow = OccupationSelectionButton.RIGHT_ARROW.getButton();
		rightArrow.setXY(14.5 * gl.getViewportAspectRatio() - 3, 8);
		putHighlightable(rightArrow);
		
		gl.glTranslated(0, -9.5, -14.5);
		renderHighlightables();		
		
		gl.glFlush();
	}
}
