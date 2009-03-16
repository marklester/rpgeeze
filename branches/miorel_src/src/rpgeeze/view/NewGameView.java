package rpgeeze.view;

import java.awt.Point;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import rpgeeze.gl.TexturedRectangle;
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

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		int[] vp = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, vp, 0);
		GLU glu = new GLU();
		if(point != null)
			glu.gluPickMatrix((double) point.x, (double) (vp[3] - point.y), 1e-3, 1e-3, vp, 0);
		glu.gluPerspective(45, ((double) vp[2]) / ((double) vp[3]), 0.1, 100);
		gl.glMatrixMode(GL.GL_MODELVIEW);		

		gl.glClearColor(intensity, 0, 0, 1.0f);

		gl.glLoadIdentity();
		gl.glTranslated(-12.5, -10, -32.0f);
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.0f);
		introImage.render();

		gl.glLoadIdentity();
		gl.glTranslated(-5, -8.5, -30);

		gl.glPushMatrix();
		gl.glTranslated(-10, 0, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == SMASHER_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(SMASHER_BUTTON);
		smasherButton.render();
		gl.glPopMatrix();

		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == SUMMONER_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(SUMMONER_BUTTON);
		summonerButton.render();

		gl.glPushMatrix();
		gl.glTranslated(10, 0, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == SNEAK_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(SNEAK_BUTTON);
		sneakButton.render();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(-10, -3, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == OPTIONS_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(OPTIONS_BUTTON);
		optionsButton.render();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(0, -3, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == BACK_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(BACK_BUTTON);
		backButton.render();
		gl.glPopMatrix();

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