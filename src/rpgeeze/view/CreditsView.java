package rpgeeze.view;

import java.awt.Font;
import java.awt.Point;
import java.util.Random;

import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.GameProperties;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.Text;
import rpgeeze.util.ResourceLoader;

import static rpgeeze.RunGame.BACKGROUND_COLOR;

public class CreditsView extends View<CreditsView.State> {
	private Font plain = ResourceLoader.getInstance().getFont("DeutscheZierschrift.ttf", Font.PLAIN, 100);
	private Font italic = plain.deriveFont(Font.ITALIC);

	private TextRenderer plainRenderer = new TextRenderer(plain, true, true);
	private TextRenderer italicRenderer = new TextRenderer(italic, true, true);

	private Text title = new Text("RPGEEZE", plainRenderer, 0.015f);
	private Text subtitle = new Text("is brought to you by", italicRenderer, 0.005f);
	
	private final String[] developers;
	private Text developer;
	private int pointer;
	
	private final static double SECONDS_PER_DEVELOPER = 1.2;
	
	private int frames;
	
	public enum State implements View.State { NEW, NORMAL, HIDDEN; }
	
	public CreditsView(GameManager manager) {
		super(manager);
		String devs = GameProperties.getInstance().getProperty("app.developers");
		
		// lowercase K's are silly in usual font, make them uppercase
		devs = devs.replaceAll("k", "K");
		
		developers = devs.split(",");
		changeState(State.NEW);
	}
	
	public void render(GL gl, Point point) {
		GLUtil glutil = new GLUtil(gl);
		glutil.standardFrustum(gl, point);
		glutil.clearColor(BACKGROUND_COLOR);
		
		title.setXYZ(-title.getWidth() / 2, 4 - title.getHeight(), -4);
		title.render(gl);
	
		subtitle.setXYZ(-subtitle.getWidth() / 2, title.getY() - subtitle.getHeight() - 0.1, -4);
		subtitle.render(gl);
		
		developer.setXYZ(-developer.getWidth() / 2, -developer.getHeight() / 2, -4);
		developer.render(gl);
		
		// change this to read property value
		if(point == null && ++frames >= 80 * SECONDS_PER_DEVELOPER)
			nextDeveloper();
		
		gl.glFlush();
	}
	
	public void nextDeveloper() {
		pointer = (pointer + 1) % developers.length;
		developer = new Text(developers[pointer], plainRenderer, 0.01f);
		frames = 0;
	}

	public void changeFrom() {
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		Random rnd = new Random();
		pointer = rnd.nextInt(developers.length);
		nextDeveloper();
		changeState(State.NORMAL);
	}
}
