package rpgeeze.view;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.gl.GL;
import rpgeeze.gl.Text;
import rpgeeze.util.ResourceLoader;

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
	
	public enum State implements rpgeeze.dp.State { NEW, NORMAL, HIDDEN; }
	
	public CreditsView(GameManager manager) {
		super(manager);
		String devs = getManager().getProperties().getProperty("developers");
		
		// lowercase K's are silly in usual font, make them uppercase
		devs = devs.replaceAll("k", "K");
		
		developers = devs.split(",");
		changeState(State.NEW);
	}
	
	public void render(Point point) {
		GL gl = new GL();
		gl.standardPrepare(point);

		gl.glClearColor(0, MainMenuView.MAX_INTENSITY, MainMenuView.MAX_INTENSITY, 1.0f);
		
		title.setXYZ(-title.getWidth() / 2, 4 - title.getHeight(), -4);
		title.render();
	
		subtitle.setXYZ(-subtitle.getWidth() / 2, title.getY() - subtitle.getHeight() - 0.1, -4);
		subtitle.render();
		
		developer.setXYZ(-developer.getWidth() / 2, -developer.getHeight() / 2, -4);
		developer.render();
		
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
