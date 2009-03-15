package rpgeeze.view;

import java.awt.Point;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import rpgeeze.gl.TexturedRectangle;
import rpgeeze.util.ResourceLoader;

/**
 * The main menu screen.
 */
public class MainMenuView extends View {
	public static final int NEW_GAME_BUTTON = 1;
	public static final int LOAD_GAME_BUTTON = 2;
	public static final int QUIT_GAME_BUTTON = 3;

	private boolean newGameHighlight = false;
	private boolean loadGameHighlight = false;
	private boolean quitGameHighlight = false;

	private float HIGHLIGHT_ALPHA = 0.25f;

	private float color = 0.0f;

	private TexturedRectangle introImage = new TexturedRectangle(ResourceLoader.getInstance().getTexture("img/Intro.png"), 25, 25);
	
	private TexturedRectangle newGameButton = new TexturedRectangle(ResourceLoader.getInstance().getTexture("img/buttons/NewGame.png"), 10, 3);
	private TexturedRectangle loadGameButton = new TexturedRectangle(ResourceLoader.getInstance().getTexture("img/buttons/LoadGame.png"), 10, 3);
	private TexturedRectangle quitGameButton = new TexturedRectangle(ResourceLoader.getInstance().getTexture("img/buttons/QuitGame.png"), 10, 3);

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

		if(point == null) {
			gl.glClearColor(color, 0, 0, 1.0f);
			if(color <= 0.75f)
				color += 0.01f / 3;
		}

		gl.glLoadIdentity();
		gl.glTranslated(-12.5, -10, -32);
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.0f);
		introImage.render();

		gl.glLoadIdentity();
		gl.glTranslated(-5, -8.5, -30);

		gl.glPushMatrix();
		gl.glTranslated(-10, 0, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, newGameHighlight ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(NEW_GAME_BUTTON);
		newGameButton.render();
		gl.glPopMatrix();

		gl.glColor4f(1.0f, 1.0f, 1.0f, loadGameHighlight ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(LOAD_GAME_BUTTON);
		loadGameButton.render();

		gl.glPushMatrix();
		gl.glTranslated(10, 0, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, quitGameHighlight ? HIGHLIGHT_ALPHA : 0.0f);
		gl.glLoadName(QUIT_GAME_BUTTON);
		quitGameButton.render();
		gl.glPopMatrix();

		gl.glFlush();
	}

	/**
	 * Sets whether or not the New Game button should be highlighted.
	 * 
	 * @param value whether or not the New Game button should be highlighted
	 */
	public void setNewGameHighlight(boolean value) {
		newGameHighlight = value; 
	}

	/**
	 * Sets whether or not the Load Game button should be highlighted.
	 * 
	 * @param value whether or not the Load Game button should be highlighted
	 */
	public void setLoadGameHighlight(boolean value) {
		loadGameHighlight = value; 
	}

	/**
	 * Sets whether or not the Quit Game button should be highlighted.
	 * 
	 * @param value whether or not the Quit Game button should be highlighted
	 */
	public void setQuitGameHighlight(boolean value) {
		quitGameHighlight = value; 
	}
}
