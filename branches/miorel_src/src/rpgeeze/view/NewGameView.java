package rpgeeze.view;

import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.gl.GL;
import rpgeeze.gl.TexturedRectangle;
import rpgeeze.gl.Triangle;
import rpgeeze.math.Vector;
import rpgeeze.util.ResourceLoader;

public class NewGameView extends View {
	public static final int SMASHER_BUTTON = 1;
	public static final int SUMMONER_BUTTON = 2;
	public static final int SNEAK_BUTTON = 3;
	public static final int OPTIONS_BUTTON = 4;
	public static final int BACK_BUTTON = 5;

	private int highlightedButton = 0;

	private float HIGHLIGHT_ALPHA = 0.25f;

	private float intensity;

	private TexturedRectangle introImage = new TexturedRectangle(ResourceLoader.getInstance().getTexture("intro.png"), 25, 25);

	private TexturedRectangle smasherButton = new TexturedRectangle(ResourceLoader.getInstance().getTexture("buttons/smasher.png"), 10, 3);
	private TexturedRectangle summonerButton = new TexturedRectangle(ResourceLoader.getInstance().getTexture("buttons/summoner.png"), 10, 3);
	private TexturedRectangle sneakButton = new TexturedRectangle(ResourceLoader.getInstance().getTexture("buttons/sneak.png"), 10, 3);
	private TexturedRectangle optionsButton = new TexturedRectangle(ResourceLoader.getInstance().getTexture("buttons/options.png"), 10, 3);
	private TexturedRectangle backButton = new TexturedRectangle(ResourceLoader.getInstance().getTexture("buttons/back.png"), 10, 3);
	
	private Font font = ResourceLoader.getInstance().getFont("DeutscheZierschrift.ttf", Font.PLAIN, 100);
	private TextRenderer small = new TextRenderer(font.deriveFont(60.0f), true, true);
	private TextRenderer medium = new TextRenderer(font.deriveFont(90.0f), true, true);
	//private Text title = new Text("Select Occuptation", small);
	/**
	 * Renders the new game screen.
	 */
	public void render(Point point) {
		GL gl = GL.getCurrent();

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

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		int[] vp = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, vp, 0);
		GLU glu = new GLU();
		if(point != null)
			glu.gluPickMatrix((double) point.x, (double) (vp[3] - point.y), 1e-3, 1e-3, vp, 0);
		double width = vp[2] <= 0 ? 1 : vp[2];
		gl.glFrustum(-vp[2] / width, vp[2] / width, -vp[3] / width, vp[3] / width, 1, 128);
		//glu.gluPerspective(45, ((double) vp[2]) / ((double) vp[3]), 0.1, 100);
		gl.glMatrixMode(GL.GL_MODELVIEW);		

		gl.glClearColor(intensity, 0, 0, 1.0f);

		/*gl.glLoadIdentity();
		gl.glTranslated(-12.5, -10, -32.0f);
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.0f);
		introImage.render();*/

		gl.glLoadIdentity();
		gl.glTranslated(-5, -8.5, -30);
		
		gl.glPushMatrix();
		//title.align(0.5, 0.25);
		//title.render();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(-5, -3, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == OPTIONS_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(OPTIONS_BUTTON);
		optionsButton.render();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(5, -3, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == BACK_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(BACK_BUTTON);
		backButton.render();
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(-10, 5, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == SMASHER_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(SMASHER_BUTTON);
		smasherButton.render();
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslated(10, 5, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == SNEAK_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(SNEAK_BUTTON);
		sneakButton.render();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(0, 10, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == SUMMONER_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(SUMMONER_BUTTON);
		summonerButton.render();

//		Triangle triangle = new Triangle(new Vector(), new Vector, new Vector());
//		triangle.render();
		

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

	/**
	 * Sets the intensity of the background color.
	 * 
	 * @param newIntensity the new intensity
	 */
	public void brighten() {
		if(intensity < 0.75f)
			intensity += 0.02f;
	}
	
	public void changeFrom() {
		setHighlightedButton(0);
	}

}
