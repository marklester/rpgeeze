package rpgeeze.view;

import java.awt.Font;
import java.awt.Point;

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
public class OccupationSelectionView extends HighlightableView {
	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);
	
	public enum OccupationSelectionButton {
		OK("OK", 1, -5, -3),
		CANCEL("Cancel", 2, 5, -3);

		private final String text;
		private final int glName;
		private final double x, y;

		private OccupationSelectionButton(String text, int glName, double x, double y) {
			this.text = text;
			this.glName = glName;
			this.x = x;
			this.y = y;
		}

		private TextRectangle getRectangle() {
			TextRectangle rect = new TextRectangle(new Text(text, renderer, 0.05f), 10, 3);
			rect.setGLName(glName);
			rect.alignText(0.5, 0.5);
			rect.setXY(x, y);
			if(this != OK)
				rect.getText().setY(OK.getRectangle().getText().getY());
			return rect;
		}
		
		public Highlightable getButton() {
			return new HighlightableWrapper(getRectangle(), MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
		}
		
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
		
		gl.glTranslated(-5, -9.5, -14.5);
		renderHighlightables();
		
		gl.glFlush();
	}
}
