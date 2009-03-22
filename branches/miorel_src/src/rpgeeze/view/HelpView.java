package rpgeeze.view;

import java.awt.Font;
import java.awt.Point;
import java.util.HashMap;


import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.GameProperties;
import rpgeeze.dp.Iterator;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.util.ResourceLoader;
import rpgeeze.util.ArrayIterator;

import static rpgeeze.RunGame.BACKGROUND_COLOR;

public class HelpView extends HighlightableView<HelpView.State> {
	private Font plain = ResourceLoader.getInstance().getFont(GameProperties.getInstance().getProperty("app.font"), Font.PLAIN, 100);
	private Font italic = plain.deriveFont(Font.ITALIC);

	private TextRenderer plainRenderer = new TextRenderer(plain, true, true);
	private TextRenderer italicRenderer = new TextRenderer(italic, true, true);

	private Text title = new Text("RPGEEZE", plainRenderer, 0.015f);
	private Text controls = new Text("Default Game Controls", italicRenderer, 0.005f);
	
	private static final double Y_SHIFT = 3;
	private static final double X_SHIFT = 18;
	
	private TextRenderer renderer;

	private final Font font = ResourceLoader.getInstance().getFont(GameProperties.getInstance().getProperty("app.font"), Font.PLAIN, 100);
	
	public enum State implements View.State { NEW, NORMAL, HIDDEN; }
	
	public HelpView(GameManager manager) {
		super(manager);
		
		GLUtil glutil = new GLUtil();
		Iterator<String> names;
		TextRectangle rect;
		Iterator<HighlightableWrapper<TextRectangle>> grid;
		
		
		renderer = new TextRenderer(font.deriveFont(36f), true, true);
		rect = new TextRectangle(new Text("X", renderer, 0.05f), 10, 3);
		
		rect.alignText(0.5, 0.5);
		HighlightableWrapper<TextRectangle> button = new HighlightableWrapper<TextRectangle>(rect, MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
		
		rect.setXYZ(-8, -9.25 - Y_SHIFT, -14.5);
		grid = glutil.objectGrid(button, 1, 1, rect.getWidth(), rect.getHeight());
		names = new ArrayIterator<String>("Back");
		
		for(grid.reset(), names.reset(); !grid.isDone(); grid.advance(), names.advance()) {
			put(grid.current(), names.current());
			putHighlightable(grid.current(), names.current());
			grid.current().getWrappedObject().getText().setText(names.current());
			grid.current().getWrappedObject().alignText(0.5, 0.5);
			grid.current().getWrappedObject().getText().setY(button.getWrappedObject().getText().getY());
		}
		changeState(State.NEW);
	}
	
	public void render(GL gl, Point point) {
		GLUtil glutil = new GLUtil(gl);
		glutil.standardFrustum(gl, point);
		glutil.clearColor(BACKGROUND_COLOR);
		
		double ascpectRatio = glutil.getViewportAspectRatio();
		
		
		title.setXYZ(-title.getWidth() / 2, 4 - title.getHeight(), -4);
		title.render(gl);
		
		Iterator<Text> views = new ArrayIterator<Text>(  new Text("Press 'S' to save the game", italicRenderer, 0.005f),
						                   				 new Text("Press 'L' to load a game", italicRenderer, 0.005f),
					                                     new Text("Press 'N' start a new game", italicRenderer, 0.005f),
					                                     new Text("Press 'O' to view options", italicRenderer, 0.005f),
					                                     new Text("Press 'J' to view your inventory", italicRenderer, 0.005f),
					                                     new Text("Press 'J' to view your inventory", italicRenderer, 0.005f),
					                                     new Text("Press 'Q' to view your statistics", italicRenderer, 0.005f),
					                                     new Text("Press 'W' to view your skills", italicRenderer, 0.005f),
					                                     new Text("Press 'F1' for the help menu", italicRenderer, 0.005f));
		
		Iterator<Text> commands = new ArrayIterator<Text>( new Text("- North 8", italicRenderer, 0.005f),
														   new Text("- South 2", italicRenderer, 0.005f),
														   new Text("- East 4", italicRenderer, 0.005f),
														   new Text("- West 6", italicRenderer, 0.005f),
														   new Text("- NorthEast 9", italicRenderer, 0.005f),
														   new Text("- NorthWest 7", italicRenderer, 0.005f),
														   new Text("- SouthEast 3", italicRenderer, 0.005f),
														   new Text("- SouthWest 1", italicRenderer, 0.005f));
												                
		controls.setXYZ(-controls.getWidth() / 2, title.getY() - controls.getHeight() - 0.1, -4);
		controls.render(gl);
		
		double y = 1;
		int z = -5;
		for(views.reset(); !views.isDone(); views.advance()){
			views.current().setXYZ(ascpectRatio - 3, y, z);
			views.current().render(gl);
			y = y - 0.5;
		}
		
		
		y = 1;
		for(commands.reset(); !commands.isDone(); commands.advance()){
			commands.current().setXYZ(-ascpectRatio + z, y, z);
			commands.current().render(gl);
			y = y - 0.5;
		}
		renderObjects(gl);
		gl.glFlush();
	}
	
	public void changeFrom() {
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		changeState(State.NORMAL);
	}
}

