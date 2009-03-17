package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.gl.Rectangle;
import rpgeeze.gl.Text;
import rpgeeze.gl.TextRectangle;
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

	private Font font = ResourceLoader.getInstance().getFont("DeutscheZierschrift.ttf", Font.PLAIN, 36);
	private TextRenderer renderer = new TextRenderer(font, true, true);
	
	private int highlightedButton = 0;

	private static final Color NORMAL = new Color(1.0f, 1.0f, 1.0f, 0.0f);
	private static final Color HIGHLIGHTED = new Color(1.0f, 1.0f, 1.0f, 0.25f);

	public final static float MIN_INTENSITY = 0.0f;
	public final static float MAX_INTENSITY = 0.75f;
	
	private float intensity = MIN_INTENSITY;
	
	private TexturedRectangle introImage;

	private TextRectangle newGameButton;
	private TextRectangle loadGameButton;
	private TextRectangle optionsButton;
	private TextRectangle helpButton;
	private TextRectangle creditsButton;
	private TextRectangle quitButton;
	
	private TextRectangle[] buttons;
	
	public MainMenuView() {
		ResourceLoader loader = ResourceLoader.getInstance();
		
		newGameButton = new TextRectangle(new Text("New Game", renderer, 0.05f), 10, 3, -10, 0, 0);
		newGameButton.setName(NEW_GAME_BUTTON);
		
		loadGameButton = new TextRectangle(new Text("Load Game", renderer, 0.05f), 10, 3, 0, 0, 0);
		loadGameButton.setName(LOAD_GAME_BUTTON);
		
		optionsButton = new TextRectangle(new Text("Options", renderer, 0.05f), 10, 3, 10, 0, 0);
		optionsButton.setName(OPTIONS_BUTTON);
		
		helpButton = new TextRectangle(new Text("Help", renderer, 0.05f), 10, 3, -10, -3, 0);
		helpButton.setName(HELP_BUTTON);
		
		creditsButton = new TextRectangle(new Text("Credits", renderer, 0.05f), 10, 3, 0, -3, 0);
		creditsButton.setName(CREDITS_BUTTON);
		
		quitButton = new TextRectangle(new Text("Quit", renderer, 0.05f), 10, 3, 10, -3, 0);
		quitButton.setName(QUIT_BUTTON);
		
		buttons = new TextRectangle[] {
				newGameButton, loadGameButton, optionsButton, helpButton, creditsButton, quitButton
		};
		
		for(TextRectangle button: buttons) {
			button.alignText(0.5, 0.5);
			button.getText().setY(buttons[0].getText().getY());
		}
		
		introImage = new TexturedRectangle(loader.getTexture("intro.png"), 25, 25, -12.5, -8, -20);
		introImage.setColor(NORMAL);
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
		double width = vp[2] <= 0 ? 1 : vp[2];
		gl.glFrustum(-vp[2] / width, vp[2] / width, -vp[3] / width, vp[3] / width, 1, 128);
		gl.glMatrixMode(GL.GL_MODELVIEW);		

		gl.glClearColor(intensity, 0, 0, 1.0f);

		gl.glLoadIdentity();
		introImage.render();

		gl.glTranslated(-5, -7.5, -19);

		newGameButton.render();
		loadGameButton.render();
		optionsButton.render();
		helpButton.render();
		creditsButton.render();
		quitButton.render();
		
		gl.glFlush();
	}

	/**
	 * Sets the button to be highlighted. Call with a value of zero to clear highlighting of all buttons. 
	 * 
	 * @param id identifier corresponding to the button to highlight
	 */
	public void setHighlightedButton(int id) {
		newGameButton.setColor(NORMAL);
		loadGameButton.setColor(NORMAL);
		optionsButton.setColor(NORMAL);
		helpButton.setColor(NORMAL);
		creditsButton.setColor(NORMAL);
		quitButton.setColor(NORMAL);
		switch(id) {
		case NEW_GAME_BUTTON:
			newGameButton.setColor(HIGHLIGHTED);
			break;
		case LOAD_GAME_BUTTON:
			loadGameButton.setColor(HIGHLIGHTED);
			break;
		case OPTIONS_BUTTON:
			optionsButton.setColor(HIGHLIGHTED);
			break;
		case HELP_BUTTON:
			helpButton.setColor(HIGHLIGHTED);
			break;
		case CREDITS_BUTTON:
			creditsButton.setColor(HIGHLIGHTED);
			break;
		case QUIT_BUTTON:
			quitButton.setColor(HIGHLIGHTED);
			break;
		}
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
