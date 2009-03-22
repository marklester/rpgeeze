package rpgeeze.view;

import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.Text;

import static rpgeeze.RunGame.APP_FONT;
import static rpgeeze.RunGame.BACKGROUND_COLOR;

public class HelpView extends View<HelpView.State> {
	private Font plain = APP_FONT.deriveFont(100f);
	private Font italic = plain.deriveFont(Font.ITALIC);

	private TextRenderer plainRenderer = new TextRenderer(plain, true, true);
	private TextRenderer italicRenderer = new TextRenderer(italic, true, true);

	private Text title = new Text("RPGEEZE", plainRenderer, 0.015f);
	private Text controls = new Text("Default Game Controls", italicRenderer, 0.005f);
	
	private Text save = new Text("Press 'S' to save the game", italicRenderer, 0.005f);
	private Text load = new Text("Press 'L' to load a game", italicRenderer, 0.005f);
	private Text newgame = new Text("Press 'N' start a new game", italicRenderer, 0.005f);
	private Text option = new Text("Press 'O' to view options", italicRenderer, 0.005f);
	private Text inventory = new Text("Press 'J' to view your inventory", italicRenderer, 0.005f);
	private Text statistics = new Text("Press 'Q' to view your statistics", italicRenderer, 0.005f);
	private Text skills = new Text("Press 'W' to view your skills", italicRenderer, 0.005f);
	
	private Text direction1 = new Text("- Move", italicRenderer, 0.005f);
	private Text direction2 = new Text("- North", italicRenderer, 0.005f);
	private Text direction3 = new Text("- South", italicRenderer, 0.005f);
	private Text direction4 = new Text("- East", italicRenderer, 0.005f);
	private Text direction5 = new Text("- West", italicRenderer, 0.005f);
	private Text direction6 = new Text("- NorthEast", italicRenderer, 0.005f);
	private Text direction7 = new Text("- NorthWest", italicRenderer, 0.005f);
	private Text direction8 = new Text("- SouthEast", italicRenderer, 0.005f);
	private Text direction9 = new Text("- SouthWest", italicRenderer, 0.005f);
	private Text num1 = new Text("1", italicRenderer, 0.005f);
	private Text num2 = new Text("2", italicRenderer, 0.005f);
	private Text num3 = new Text("3", italicRenderer, 0.005f);
	private Text num4 = new Text("4", italicRenderer, 0.005f);
	private Text num6 = new Text("6", italicRenderer, 0.005f);
	private Text num7 = new Text("7", italicRenderer, 0.005f);
	private Text num8 = new Text("8", italicRenderer, 0.005f);
	private Text num9 = new Text("9", italicRenderer, 0.005f);
	
	public enum State implements View.State { NEW, NORMAL, HIDDEN; }
	
	public HelpView(GameManager manager) {
		super(manager);
		changeState(State.NEW);
	}
	
	public void render(GL gl, Point point) {
		GLUtil glutil = new GLUtil(gl);
		glutil.standardFrustum(gl, point);
		glutil.clearColor(BACKGROUND_COLOR);
		
		title.setXYZ(-title.getWidth() / 2, 4 - title.getHeight(), -4);
		title.render(gl);
		
		controls.setXYZ(-controls.getWidth() / 2, title.getY() - controls.getHeight() - 0.1, -4);
		controls.render(gl);
		
		save.setXYZ(-1,1, -4);
		save.render(gl);
		load.setXYZ(-1,.25, -4);
		load.render(gl);
		newgame.setXYZ(-1,-0.5, -4);
		newgame.render(gl);
		option.setXYZ(-1,-1.25, -4);
		option.render(gl);
		inventory.setXYZ(-1,-2, -4);
		inventory.render(gl);
		statistics.setXYZ(-1,-2.75, -4);
		statistics.render(gl);
		skills.setXYZ(-1,-3.5, -4);
		skills.render(gl);
		
		direction1.setXYZ(-6,1, -4);
		direction1.render(gl);
		direction2.setXYZ(-7,0.7, -5);
		direction2.render(gl);
		num8.setXYZ(-4,0.7, -5);
		num8.render(gl);
		direction3.setXYZ(-7,0.2, -5);
		direction3.render(gl);
		num2.setXYZ(-4,0.2, -5);
		num2.render(gl);
		direction4.setXYZ(-7,-0.3, -5);
		direction4.render(gl);
		num4.setXYZ(-4,-0.3, -5);
		num4.render(gl);
		direction5.setXYZ(-7,-0.8, -5);
		direction5.render(gl);
		num6.setXYZ(-4,-0.8, -5);
		num6.render(gl);
		direction6.setXYZ(-7,-1.3, -5);
		direction6.render(gl);
		num7.setXYZ(-4,-1.3, -5);
		num7.render(gl);
		direction7.setXYZ(-7,-1.8, -5);
		direction7.render(gl);
		num9.setXYZ(-4,-1.8, -5);
		num9.render(gl);
		direction8.setXYZ(-7,-2.3, -5);
		direction8.render(gl);
		num1.setXYZ(-4,-2.3, -5);
		num1.render(gl);
		direction9.setXYZ(-7,-2.8, -5);
		direction9.render(gl);
		num3.setXYZ(-4,-2.8, -5);
		num3.render(gl);
		gl.glFlush();
	}
	
	public void changeFrom() {
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		changeState(State.NORMAL);
	}
}

