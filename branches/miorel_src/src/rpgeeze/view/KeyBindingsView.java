package rpgeeze.view;

import static rpgeeze.RunGame.BACKGROUND_COLOR;
//import static rpgeeze.RunGame.keyControls;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.GameProperties;
import rpgeeze.RunGame;
import rpgeeze.dp.Iterator;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.gl.geom.Triangle;
import rpgeeze.log.LogManager;
import rpgeeze.math.StaticVector;
import rpgeeze.util.ArrayIterator;
import rpgeeze.util.ResourceLoader;


import java.util.HashMap;
import java.util.Map.Entry;

/**
 * The key bindings screen.
 */
public class KeyBindingsView extends HighlightableView<KeyBindingsView.State> {
	private static final double Y_SHIFT = 3;
	private static final double X_SHIFT = 22;
	private static final double ARROW_DISPLACE = 4;
	
	private TextRenderer renderer;

	private Iterator<Highlightable> wheel;
	
	private HashMap<String, String> keyControls;
	private HashMap<String, Text> strText = new HashMap<String, Text>();

	
	private final Font font = ResourceLoader.getInstance().getFont(GameProperties.getInstance().getProperty("app.font"), Font.PLAIN, 100);
	
	public KeyBindingsView(GameManager manager) {
		super(manager);

		GLUtil glutil = new GLUtil();
		Iterator<String> names;
		TextRectangle rect;
		Iterator<HighlightableWrapper<TextRectangle>> grid;
		
		renderer = new TextRenderer(font.deriveFont(36f), true, true);
		rect = new TextRectangle(new Text("X", renderer, 0.05f), 10, 3);
		rect.alignText(0.5, 0.5);
		
		HighlightableWrapper<TextRectangle> button = new HighlightableWrapper<TextRectangle>(rect, MainMenuView.PLAIN, MainMenuView.HIGHLIGHTED);
		
		rect.setXYZ(-15, -12.5 - Y_SHIFT, -14.5);
		grid = glutil.objectGrid(button, 1, 3, rect.getWidth(), rect.getHeight());
		names = new ArrayIterator<String>("OK", "Defaults", "Cancel");
		for(grid.reset(), names.reset(); !grid.isDone(); grid.advance(), names.advance()) {
			put(grid.current(), names.current());
			putHighlightable(grid.current(), names.current());
			grid.current().getWrappedObject().getText().setText(names.current());
			grid.current().getWrappedObject().alignText(0.5, 0.5);
			grid.current().getWrappedObject().getText().setY(button.getWrappedObject().getText().getY());
		}
		
		rect.setWidth(14);
		rect.setXYZ(-14 - X_SHIFT, -1.5 - Y_SHIFT, -25);
		grid = glutil.objectGrid(button, 4, 2, rect.getWidth() + 2 * X_SHIFT, rect.getHeight() + 3.5);
		names = new ArrayIterator<String>("Help", "Options", "Save Game", "Show Skills", "Load Game", "Show Stats", "New Game", "Show Inventory");
		for(grid.reset(), names.reset(); !grid.isDone(); grid.advance(), names.advance()) {
			put(grid.current(), names.current());
			putHighlightable(grid.current(), names.current());
			grid.current().getWrappedObject().getText().setText(names.current());
			grid.current().getWrappedObject().alignText(0.5, 0.5);
			grid.current().getWrappedObject().getText().setY(button.getWrappedObject().getText().getY());
			Text text = new Text("", renderer, 0.05f);
			strText.put(names.current(), text);
			put(text, null);
		}
		
		Highlightable arrow = new HighlightableWrapper<Triangle>(new Triangle(new StaticVector(0, -1.5), new StaticVector(0, 1.5), new StaticVector(1.5, 0)), Color.BLACK, MainMenuView.HIGHLIGHTED);
		arrow.setZ(-14.5);
		wheel = glutil.objectWheel(arrow, 8);
		names = new ArrayIterator<String>("Move East", "Move Northeast", "Move North", "Move Northwest", "Move West", "Move Southwest", "Move South", "Move Southeast");

		for(wheel.reset(), names.reset(); !wheel.isDone(); wheel.advance(), names.advance()) {
			wheel.current().setX(ARROW_DISPLACE);
			put(wheel.current(), names.current());
			putHighlightable(wheel.current(), names.current());
			Text text = new Text("", renderer, 0.05f);
			strText.put(names.current(), text);
			put(text, null);
		}
	}

	public enum State implements View.State { NEW, NORMAL, HIDDEN; }

	public HashMap<String,String> getkeyControls(){
		return keyControls;
	}
	
	public void setCommand(String key, String value){
		LogManager lm = LogManager.getInstance();
		if(keyControls.containsValue(value)) {
			lm.log("Duplicate value, swapping", "VIEW");
			
			for(Entry<String, String> entry: keyControls.entrySet()) {
				if(entry.getValue().equals(value))
					entry.setValue(keyControls.get(key));

				System.out.println(entry + " " + entry.getKey() + " " + entry.getValue());

			}
		}
		keyControls.put(key, value);
		lm.log("Setting " + key + " to " + value, "VIEW");
	}
	
	public void changeFrom() {
		changeState(State.HIDDEN);		
	}

	public void changeTo() {
		keyControls = (HashMap<String, String>) RunGame.KEY_CONTROLS.clone();
		changeState(State.NORMAL);
	}
	
	/**
	 * Renders the key bindings screen.
	 */

	public void render(GL gl, Point point) {
		GLUtil glutil = new GLUtil(gl);
		glutil.standardFrustum(gl, point);
		glutil.clearColor(BACKGROUND_COLOR);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);		
		glutil.color(MainMenuView.PLAIN);
		gl.glTranslated(0, Y_SHIFT, 0);
		
		Iterator<Highlightable> iter = getHighlightables();
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			String key = getNameForObject(iter.current());
			String value = keyControls.get(key);
			if(value != null) {
				Text text = strText.get(key);
				text.setText(value.replaceAll("Pad", ""));

				if(!key.matches("Move .+"))
					text.setXYZ(iter.current().getX() + 7 - text.getWidth() / 2, iter.current().getY() - 2, iter.current().getZ());

			}
		}
		int i = 0;
		for(wheel.reset(); !wheel.isDone(); wheel.advance(), ++i) {
			String key = getNameForObject(wheel.current());
			String value = keyControls.get(key);			
			Text text = strText.get(key);
			text.setText(value.replaceAll("Pad", ""));
			double angle = Math.PI * 2 * i / 8;
			text.setXYZ(12.5 * Math.cos(angle) - text.getWidth() / 2, 12.5 * Math.sin(angle) - text.getHeight() / 2 + 2.3, -25);
		}
		

		glutil.color(MainMenuView.PLAIN);
		renderObjects(gl);
	}
}
