package rpgeeze.view;

import static rpgeeze.RunGame.BACKGROUND_COLOR;
//import static rpgeeze.RunGame.keyControls;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.Set;
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
import rpgeeze.math.StaticVector;
import rpgeeze.util.ArrayIterator;
import rpgeeze.util.ResourceLoader;
import java.lang.StringBuilder;

import java.util.HashMap;

/**
 * The key bindings screen.
 */
public class KeyBindingsView extends HighlightableView<KeyBindingsView.State> {
	private static final double Y_SHIFT = 3;
	private static final double X_SHIFT = 18;
	private static final double ARROW_DISPLACE = 4;
	
	private HashMap<String, String> keyControls;
	
	private final Font font = ResourceLoader.getInstance().getFont(GameProperties.getInstance().getProperty("app.font"), Font.PLAIN, 100);
	
	public KeyBindingsView(GameManager manager) {
		super(manager);

		GLUtil glutil = new GLUtil();
		Iterator<String> names;
		TextRectangle rect;
		Iterator<HighlightableWrapper<TextRectangle>> grid;
		
		TextRenderer renderer = new TextRenderer(font.deriveFont(36f), true, true);
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
		}
		
		Highlightable arrow = new HighlightableWrapper<Triangle>(new Triangle(new StaticVector(0, -1.5), new StaticVector(0, 1.5), new StaticVector(1.5, 0)), Color.BLACK, MainMenuView.HIGHLIGHTED);
		arrow.setZ(-14.5);
		Iterator<Highlightable> wheel = glutil.objectWheel(arrow, 8);
		names = new ArrayIterator<String>("Move East", "Move Northeast", "Move North", "Move Northwest", "Move West", "Move Southwest", "Move South", "Move Southeast");

		for(wheel.reset(), names.reset(); !wheel.isDone(); wheel.advance(), names.advance()) {
			wheel.current().setX(ARROW_DISPLACE);
			put(wheel.current(), names.current());
			putHighlightable(wheel.current(), names.current());
		}
	}

	public enum State implements View.State { NEW, NORMAL, HIDDEN; }

	public HashMap<String,String> getkeyControls(){
		return keyControls;
	}
	
	public void changeFrom() {
		changeState(State.HIDDEN);		
	}

	public void changeTo() {
		keyControls = (HashMap<String, String>) RunGame.KEY_CONTROLS.clone();
		changeState(State.NORMAL);
	}

	public void render(GL gl, Point point) {
		GLUtil glutil = new GLUtil(gl);
		glutil.standardFrustum(gl, point);
		glutil.clearColor(BACKGROUND_COLOR);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);		
		glutil.color(MainMenuView.PLAIN);
		gl.glTranslated(0, Y_SHIFT, 0);
		renderObjects(gl);
	}

	
	
	//	private static final TextRenderer renderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 36);
//	private static final TextRenderer smallRenderer = ResourceLoader.getInstance().getTextRenderer("DeutscheZierschrift.ttf", Font.PLAIN, 14);
	//private HashMap<String,String> keyControls;
//	private String message = "";
		
/*
 * 
 * public KeyBindingsView(GameManager manager) {
 *
		super(manager);
		for(Button button: Button.values())
			putHighlightable(button.getButton());
		changeState(State.NEW);
		defaults();
	}
	
	public HashMap<String,String> getkeyControls(){
		return keyControls;
	}

	/**
	 * Renders the key bindings screen.
	 */
//	public void render(GL gl, Point point) {
//		GLUtil glutil = new GLUtil(gl);
//		glutil.standardFrustum(gl, point);
//		glutil.clearColor(BACKGROUND_COLOR);
//		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);
//		
//		gl.glTranslated(0, 0, zoom);
//		
/*		switch(getState()) {
		case NORMAL:
			
			gl.glLoadIdentity();
			gl.glTranslated(0, -9.5, -14.5);
			
			Highlightable N_ARROWArrow = Button.N_ARROW.getButton();
			N_ARROWArrow.setXY(0, 14);
			putHighlightable(N_ARROWArrow);
			
			Text N_ARROWCommand = new Text(keyControls.get("N_ARROW"), smallRenderer, 0.075f);
			N_ARROWCommand.setXY(-0.25 - 0.5*(keyControls.get("N_ARROW").length()/2), 15.5);
			N_ARROWCommand.render(gl);
			
			Highlightable southArrow = Button.S_ARROW.getButton();
			southArrow.setXY(0, 8);
			putHighlightable(southArrow);
			
			Text southCommand = new Text(keyControls.get("S_ARROW"), smallRenderer, 0.075f);
			southCommand.setXY(-0.25 - 0.5*(keyControls.get("S_ARROW").length()/2), 5.5);
			southCommand.render(gl);
			
			Highlightable eastArrow = Button.E_ARROW.getButton();
			eastArrow.setXY(3, 10);
			putHighlightable(eastArrow);
			
			Text eastCommand = new Text(keyControls.get("E_ARROW"), smallRenderer, 0.075f);
			eastCommand.setXY(4.5, 10.5);
			eastCommand.render(gl);
			
			Highlightable westArrow = Button.W_ARROW.getButton();
			westArrow.setXY(-3, 10);
			putHighlightable(westArrow);
			
			Text westCommand = new Text(keyControls.get("W_ARROW"), smallRenderer, 0.075f);
			westCommand.setXY(-6 - 0.75 *(keyControls.get("W_ARROW").length()/2), 10.5);
			westCommand.render(gl);
			
			Highlightable N_ARROWeastArrow = Button.NE_ARROW.getButton();
			N_ARROWeastArrow.setXY(1, 13);
			putHighlightable(N_ARROWeastArrow);
			
			Text northEastCommand = new Text(keyControls.get("NE_ARROW"), smallRenderer, 0.075f);
			northEastCommand.setXY(3.5 - 0.5*(keyControls.get("NE_ARROW").length()/2), 14.25);
			northEastCommand.render(gl);
			
			Highlightable southeastArrow = Button.SE_ARROW.getButton();
			southeastArrow.setXY(1, 9);
			putHighlightable(southeastArrow);
			
			Text southEastCommand = new Text(keyControls.get("SE_ARROW"), smallRenderer, 0.075f);
			southEastCommand.setXY(3.5 - 0.5*(keyControls.get("SE_ARROW").length()/2), 6.75);
			southEastCommand.render(gl);
			
			Highlightable northWestArrow = Button.NW_ARROW.getButton();
			northWestArrow.setXY(-1, 13);
			putHighlightable(northWestArrow);
			
			Text northWestCommand = new Text(keyControls.get("NW_ARROW"), smallRenderer, 0.075f);
			northWestCommand.setXY(-4 - 0.5*(keyControls.get("NW_ARROW").length()/2), 14.25);
			northWestCommand.render(gl);
			
			Highlightable southwestArrow = Button.SW_ARROW.getButton();
			southwestArrow.setXY(-1, 9);
			putHighlightable(southwestArrow);
			
			Text southWestCommand = new Text(keyControls.get("SW_ARROW"), smallRenderer, 0.075f);
			southWestCommand.setXY(-4 - 0.5*(keyControls.get("SW_ARROW").length()/2), 7);
			southWestCommand.render(gl);
			
			Text saveGameCommand = new Text(keyControls.get("SAVE_GAME"), smallRenderer, 0.075f);
			saveGameCommand.setXY(-14.5 - 0.5*(keyControls.get("SAVE_GAME").length()/2), 16.5);
			saveGameCommand.render(gl);
			
			Text loadGameCommand = new Text(keyControls.get("LOAD_GAME"), smallRenderer, 0.075f);
			loadGameCommand.setXY(-14.5 - 0.5*(keyControls.get("LOAD_GAME").length()/2), 8.5);
			loadGameCommand.render(gl);
			
			Text newGameCommand = new Text(keyControls.get("NEW_GAME"), smallRenderer, 0.075f);
			newGameCommand.setXY(-15 - 0.5*(keyControls.get("NEW_GAME").length()/2), 0.5);
			newGameCommand.render(gl);
			
			Text inventoryViewCommand = new Text(keyControls.get("INVENTORY_VIEW"), smallRenderer, 0.075f);
			inventoryViewCommand.setXY(12 - 0.5*(keyControls.get("INVENTORY_VIEW").length()/2), 16.5);
			inventoryViewCommand.render(gl);
			
			Text statsViewCommand = new Text(keyControls.get("STATS_VIEW"), smallRenderer, 0.075f);
			statsViewCommand.setXY(14 - 0.5*(keyControls.get("STATS_VIEW").length()/2), 8.5);
			statsViewCommand.render(gl);
			
			Text skillsViewCommand = new Text(keyControls.get("SKILLS_VIEW"), smallRenderer, 0.075f);
			skillsViewCommand.setXY(13.5 - 0.5*(keyControls.get("SKILLS_VIEW").length()/2), 0.5);
			skillsViewCommand.render(gl);
			
			Text optionsViewCommand = new Text(keyControls.get("OPTIONS_VIEW"), smallRenderer, 0.075f);
			optionsViewCommand.setXY(-0.5 - 0.5*(keyControls.get("OPTIONS_VIEW").length()/2), 0.5);
			optionsViewCommand.render(gl);
			
			Text errorMessage = new Text(message, smallRenderer, 0.075f);
			errorMessage.setXY(-5, 19);
			errorMessage.render(gl);
			

			renderHighlightables(gl);
			gl.glLoadName(-1);
			break;
		case ZOOMING:
			break;
		case ZOOMED:
			break;
		}
		
		gl.glFlush();
	}
	public void changeFrom() {
		super.changeFrom();
		changeState(State.HIDDEN);
	}
	
	public void changeTo() {
		super.changeTo();
		changeState(State.NORMAL);
	}
	
	public void startZoom() {
		if(getState() != State.ZOOMING && getState() != State.ZOOMED)
			changeState(State.ZOOMING);
	}
	
	public void setCommand(String key, String value){
		if(keyControls.containsValue(value)){
			Set s = keyControls.entrySet();
			Iterator a =  s.iterator();
			
			while(a.hasNext()){
				StringBuilder tempKey = new StringBuilder(a.next().toString());
				tempKey = tempKey.delete(tempKey.length()-2,tempKey.length());
		    
				if(value.equals((keyControls.get(tempKey.toString())))){
				  keyControls.put(tempKey.toString(), keyControls.get(key));
				}
			}
			
		}
			keyControls.remove(key);
			keyControls.put(key, value);
			System.out.println(keyControls.isEmpty());
		}
	
	
	public void defaults(){
		keyControls.put("N_ARROW", "8");
		keyControls.put("S_ARROW", "2");
		keyControls.put("E_ARROW", "6");
		keyControls.put("W_ARROW","4");
		keyControls.put("NE_ARROW", "9");
		keyControls.put("SE_ARROW", "3");
		keyControls.put("NW_ARROW", "7");
		keyControls.put("SW_ARROW", "1");
		keyControls.put("SAVE_GAME", "S");
		keyControls.put("LOAD_GAME", "L");
		keyControls.put("NEW_GAME", "N");
		keyControls.put("INVENTORY_VIEW", "I");
		keyControls.put("STATS_VIEW", "Q");
		keyControls.put("SKILLS_VIEW", "W");
		keyControls.put("OPTIONS_VIEW", "O");
	}*/
}
