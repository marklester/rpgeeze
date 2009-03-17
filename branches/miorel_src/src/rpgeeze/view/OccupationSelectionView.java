package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;
import com.sun.opengl.util.j2d.TextRenderer.DefaultRenderDelegate;
import com.sun.opengl.util.j2d.TextRenderer.RenderDelegate;

import rpgeeze.gl.Text;
import rpgeeze.gl.TexturedRectangle;
import rpgeeze.gl.Triangle;
import rpgeeze.math.Vector;
import rpgeeze.util.ResourceLoader;

/**
 * The main menu screen.
 */
public class OccupationSelectionView extends View {
	public static final int OK_BUTTON = 1;
	public static final int CANCEL_BUTTON = 2;
	public static final int LEFT_ARROW = 3;
	public static final int RIGHT_ARROW = 4;

	private static class Occ {
		public TexturedRectangle image;
		public String name;
	}
	
	private Occ[] occupation;
	
	private int highlightedButton = 0;

	private float HIGHLIGHT = 0.25f;

	private TexturedRectangle okButton;
	private TexturedRectangle cancelButton;

	private Triangle leftArrow;
	private Triangle rightArrow;
	
	private String characterName;
	private int occP;
	
	public OccupationSelectionView() {
		ResourceLoader loader = ResourceLoader.getInstance();
		
		okButton = new TexturedRectangle(loader.getTexture("buttons/ok.png"), 10, 3, -5, -3, 0);
		okButton.setName(OK_BUTTON);
		
		cancelButton = new TexturedRectangle(loader.getTexture("buttons/cancel.png"), 10, 3, 5, -3, 0);
		cancelButton.setName(CANCEL_BUTTON);

		occupation = new Occ[3];
		occupation[0] = new Occ();
		occupation[0].image = new TexturedRectangle(loader.getTexture("intro.png"), 25, 25, -12.5, -8, -20);
		occupation[0].image.setColor(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		occupation[0].name = "Smasher";
		occupation[1] = new Occ();
		occupation[1].image = new TexturedRectangle(loader.getTexture("buttons/new_game.png"), 25, 25, -12.5, -8, -20);
		occupation[1].image.setColor(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		occupation[1].name = "Summoner";
		occupation[2] = new Occ();
		occupation[2].image = new TexturedRectangle(loader.getTexture("buttons/new_game.png"), 25, 25, -12.5, -8, -20);
		occupation[2].image.setColor(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		occupation[2].name = "Sneak";
		
		leftArrow = new Triangle(new Vector(-14, 0, -20), new Vector(-14, 5, -20), new Vector(-16, 2.5, -20));
		leftArrow.setName(LEFT_ARROW);
		
		rightArrow = new Triangle(new Vector(14, 0, -20), new Vector(14, 5, -20), new Vector(16, 2.5, -20));
		rightArrow.setName(RIGHT_ARROW);
		
		characterName = "NoName";
		occP = 0;
	}
	
	/**
	 * Renders the main menu screen.
	 */
	public void render(Point point) {
		GL gl = GLContext.getCurrent().getGL();

		gl.glShadeModel(GL.GL_SMOOTH);

		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);

		// textures and blending
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);

		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl.glEnable(GL.GL_LINE_SMOOTH);
		
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		int[] vp = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, vp, 0);
		GLU glu = new GLU();
		if(point != null)
			glu.gluPickMatrix((double) point.x, (double) (vp[3] - point.y), 1e-3, 1e-3, vp, 0);
		double width = vp[2] <= 0 ? 1 : vp[2];
		gl.glFrustum(-vp[2] / width, vp[2] / width, -vp[3] / width, vp[3] / width, 1, 128);
		gl.glMatrixMode(GL.GL_MODELVIEW);		

		gl.glClearColor(MainMenuView.MAX_INTENSITY, 0, 0, 1.0f);

		gl.glLoadIdentity();
		occupation[occP].image.render();

		gl.glTranslated(-5, -7.5, -19);

		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == OK_BUTTON ? HIGHLIGHT : 0.0f);
		okButton.render();

		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == CANCEL_BUTTON ? HIGHLIGHT : 0.0f);
		cancelButton.render();

		gl.glLoadIdentity();
		
		if(highlightedButton == LEFT_ARROW)
			gl.glColor4f(1.0f, 1.0f, 1.0f, HIGHLIGHT);
		else
			gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
		leftArrow.render();
		
		if(highlightedButton == RIGHT_ARROW)
			gl.glColor4f(1.0f, 1.0f, 1.0f, HIGHLIGHT);
		else
			gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
		rightArrow.render();
		
		gl.glLoadIdentity();
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.0f);
		gl.glLoadName(-1);
		Font font = ResourceLoader.getInstance().getFont("DeutscheZierschrift.ttf", Font.PLAIN, 20);
		TextRenderer renderer = new TextRenderer(font, true, true);
		String text = "Hello world!";
		Text txt = new Text(text, renderer, 0.02f);
		txt.setXYZ(-txt.getWidth() / 2, -txt.getHeight() / 2, -1);
		txt.setColor(Color.WHITE);
		txt.render();
		
		gl.glFlush();
	}
	
	/**
	 * Sets the button to be highlighted. Call with a value of zero to clear highlighting of all buttons. 
	 * 
	 * @param id identifier corresponding to the button to highlight
	 */
	public void setHighlightedButton(int id) {
		highlightedButton = id;
	}
	
	public void changeFrom() {
		setHighlightedButton(0);
	}
	
	public void setCharacterName(String newName) {
		characterName = newName;
	}
	
	public String getCharacterName() {
		return characterName;
	}
}
