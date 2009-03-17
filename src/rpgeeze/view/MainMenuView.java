package rpgeeze.view;

import java.awt.Color;
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
	public static final int OPTIONS_BUTTON = 3;
	public static final int HELP_BUTTON = 4;
	public static final int CREDITS_BUTTON = 5;
	public static final int QUIT_BUTTON = 6;

	private int highlightedButton = 0;

	private float HIGHLIGHT_ALPHA = 0.25f;

	private float MIN_INTENSITY = 0.0f;
	private float MAX_INTENSITY = 0.75f;
	
	private float intensity = MIN_INTENSITY;
	
	private TexturedRectangle introImage;

	private TexturedRectangle newGameButton;
	private TexturedRectangle loadGameButton;
	private TexturedRectangle optionsButton;
	private TexturedRectangle helpButton;
	private TexturedRectangle creditsButton;
	private TexturedRectangle quitButton;

	public MainMenuView() {
		ResourceLoader loader = ResourceLoader.getInstance();
		
		newGameButton = new TexturedRectangle(loader.getTexture("buttons/new_game.png"), 10, 3, -10, 0, 0);
		newGameButton.setName(NEW_GAME_BUTTON);
		
		loadGameButton = new TexturedRectangle(loader.getTexture("buttons/load_game.png"), 10, 3, 0, 0, 0);
		loadGameButton.setName(LOAD_GAME_BUTTON);
		
		optionsButton = new TexturedRectangle(loader.getTexture("buttons/options.png"), 10, 3, 10, 0, 0);
		optionsButton.setName(OPTIONS_BUTTON);
		
		helpButton = new TexturedRectangle(loader.getTexture("buttons/help.png"), 10, 3, -10, -3, 0);
		helpButton.setName(HELP_BUTTON);
		
		creditsButton = new TexturedRectangle(loader.getTexture("buttons/credits.png"), 10, 3, 0, -3, 0);
		creditsButton.setName(CREDITS_BUTTON);
		
		quitButton = new TexturedRectangle(loader.getTexture("buttons/quit.png"), 10, 3, 10, -3, 0);
		quitButton.setName(QUIT_BUTTON);
		
		introImage = new TexturedRectangle(loader.getTexture("intro.png"), 25, 25, -12.5, -10, -32);
		introImage.setColor(new Color(1.0f, 1.0f, 1.0f, 0.0f));
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
		introImage.render();

		gl.glTranslated(-5, -8.5, -30);

		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == NEW_GAME_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		newGameButton.render();

		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == LOAD_GAME_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		loadGameButton.render();

		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == OPTIONS_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		optionsButton.render();

		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == HELP_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		helpButton.render();

		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == CREDITS_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		creditsButton.render();

		gl.glColor4f(1.0f, 1.0f, 1.0f, highlightedButton == QUIT_BUTTON ? HIGHLIGHT_ALPHA : 0.0f);
		quitButton.render();

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
	 * Changes the intensity of the background color.
	 * 
	 * @param dc the amount by which to change the intensity
	 */
	public void changeIntensity(float dc) {
		intensity += dc;
		if(intensity > MAX_INTENSITY)
			intensity = MAX_INTENSITY;
		if(intensity < MIN_INTENSITY)
			intensity = MIN_INTENSITY;
	}
	
	public void changeFrom() {
		setHighlightedButton(0);
		intensity = MAX_INTENSITY;
	}
}
