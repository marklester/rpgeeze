package rpgeeze.view;

import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.gl.Text;
import rpgeeze.util.ResourceLoader;

public class CreditsView extends View {
	private Font font = ResourceLoader.getInstance().getFont("DeutscheZierschrift.ttf", Font.PLAIN, 100);

	private TextRenderer large = new TextRenderer(font.deriveFont(120.0f), true, true);
	private TextRenderer medium = new TextRenderer(font.deriveFont(90.0f), true, true);
	private TextRenderer small = new TextRenderer(font.deriveFont(60.0f).deriveFont(Font.ITALIC), true, true);

	private Text title = new Text("RPGEEZE", large);
	private Text subtitle = new Text("is brought to you by", small);
	
	private Text developer;

	private int pointer;
	private final String[] names = new String[] {
			"Eric Hayden", 
			"Brennan Jones",
			"Matthew Lance",
			"Mark Lester",
			"Jason McAninley",
			"Jose Morales",
			"Miorel Palii",
	};
	
	public void render(Point point) {
		GL gl = GLContext.getCurrent().getGL();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(0.75f, 0, 0, 1.0f);

		title.align(0.5, 1);
		title.render();
		subtitle.alignHorizontally(0.5);
		subtitle.setY(title.getY() - 9 * subtitle.getHeight() / 8);
		subtitle.render();
		
		developer.align(0.5, 0.5);
		developer.render();
		
		gl.glFlush();
	}
	
	public void nextDeveloper() {
		pointer = (pointer + 1) % names.length;
		developer = new Text(names[pointer], medium);
	}

	public void changeTo() {
		pointer = -1;
		nextDeveloper();
	}
}
