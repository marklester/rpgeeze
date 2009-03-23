package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.HighlightableWrapper;
import rpgeeze.gl.Text;
import rpgeeze.gl.effect.BrushColorChange;
import rpgeeze.gl.geom.Rectangle;
import rpgeeze.gl.geom.TextRectangle;
import rpgeeze.gl.geom.TexturedRectangle;
import rpgeeze.log.LogManager;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.item.Item;
import rpgeeze.model.item.TakeableItem;
import rpgeeze.util.ArrayIterator;
import rpgeeze.util.ListIterator;

public class GameplayView extends View<GameplayView.State> {
	private TextRenderer renderer = new TextRenderer(new Font(Font.SANS_SERIF, Font.PLAIN, 24), true, true);

	private final static double MAP_Z = -8;
	private final static double INVENTORY_Z = -5;
	private final static double INV_ITEM_SIZE = 0.6;

	private BrushColorChange fadeIn;
	private Text fpsText;

	private MapDrawer mapDrawer = new MapDrawer();

	public enum State implements View.State { NEW, FADING_IN, NORMAL, HIDDEN; }

	private Iterator<TexturedRectangle> inventory; 
	private List<Rectangle> rects = new ArrayList<Rectangle>();

	private boolean inventoryVisible = true;

	public GameplayView(GameManager manager) {
		super(manager);
		fadeIn = new BrushColorChange(new Color(0, 0, 0, 1f), new Color(1, 1, 1, 1f), 1);
		fpsText = new Text("", Color.RED, renderer, 0.0025f);

		TexturedRectangle prototype = new TexturedRectangle(null, INV_ITEM_SIZE, INV_ITEM_SIZE);
		prototype.setColor(Color.WHITE);
		prototype.setXYZ(0, 0, INVENTORY_Z);

		GLUtil glutil = new GLUtil();
		inventory = glutil.objectGrid(prototype, 5, 5, 1.2 * prototype.getWidth(), -1.2 * prototype.getHeight());
		List<String> invList = new ArrayList<String>();
		for(int i = 0; i < 25; ++i)
			invList.add("Inventory Item " + i);
		Iterator<String> names = new ListIterator<String>(invList);

		for(inventory.reset(), names.reset(); !inventory.isDone(); inventory.advance(), names.advance()) {
			TexturedRectangle trect = inventory.current();
			put(trect, names.current());
			Rectangle rect = new Rectangle(trect.getWidth(), trect.getHeight());
			rect.setXYZ(trect.getX(), trect.getY(), trect.getZ());// - 0.01);
			//rect.setColor(new Color(1f, 1f, 1f, 0.8f));
			rects.add(rect);
		}

		put(fpsText, null);

		changeState(State.NEW);
	}

	public boolean getInventoryVisible() {
		return inventoryVisible;
	}

	public void setInventoryVisible(boolean value) {
		this.inventoryVisible = value;
	}

	public void toggleInventoryVisible() {
		setInventoryVisible(!getInventoryVisible());
	}

	public void render(GL gl, Point point) {
		GLUtil glutil = new GLUtil(gl);
		glutil.standardFrustum(gl, point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glClearColor(1, 1, 1, 1);
		gl.glDisable(GL.GL_DEPTH);

		if(getState() == State.FADING_IN) {
			if(point == null) {
				fadeIn.apply(gl);
				if(fadeIn.isDone())
					changeState(State.NORMAL);
			}
		}
		else
			glutil.color(fadeIn.getFinalColor());

		mapDrawer.setGL(gl);
		mapDrawer.setSize(1);

		double widthInTiles = Math.ceil(-2 * MAP_Z * glutil.getViewportAspectRatio());
		double heightInTiles = Math.ceil(-2 * MAP_Z);

		Entity avatar = getManager().getModel().getAvatar();
		int centerX = avatar.getTile().getX();
		int centerY = avatar.getTile().getY();

		int minX = (int) Math.floor(centerX - (1 + 0.5 * widthInTiles));
		int maxX = (int) Math.ceil(centerX + (1 + 0.5 * widthInTiles));
		int minY = (int) Math.floor(centerY - (1 + 0.5 * heightInTiles));
		int maxY = (int) Math.ceil(centerY + (1 + 0.5 * heightInTiles));

		Iterator<Tile> iter = getManager().getModel().getMap().getTiles(minX, minY, maxX, maxY);
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			Tile tile = iter.current();
			gl.glPushMatrix();
			double dx = tile.getX() - centerX - 0.5;
			double dy = tile.getY() - centerY - 0.5;
			gl.glTranslated(dx, dy, MAP_Z);
			tile.accept(mapDrawer);
			gl.glPopMatrix();
		}

		Iterator<TakeableItem> items = getManager().getModel().getAvatar().getInventory().iterator();
		for(items.reset(), inventory.reset(); !items.isDone(); items.advance(), inventory.advance())
			inventory.current().setTexture(mapDrawer.textureForItem(items.current()));

		while(!inventory.isDone()) {
			inventory.current().setTexture(null);
			inventory.advance();
		}

		if(getInventoryVisible()) {
			gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);
			//gl.glBlendFunc(GL.GL_SRC_COLOR, GL.GL_SRC_ALPHA);
			gl.glBindTexture(0, GL.GL_TEXTURE_2D);
			gl.glPushMatrix();
			inventory.reset();
			double invX = INVENTORY_Z * glutil.getViewportAspectRatio() + 0.2 * inventory.current().getHeight();
			double invY = INVENTORY_Z + 5 * inventory.current().getHeight();
			gl.glTranslated(invX, invY, 0);
			for(Rectangle r: rects) {
				inventory.current().setVisible(true);
				inventory.current().setXY(r.getX() + invX, r.getY() + invY);
				r.setColor(new Color(0f, 1f, 1f, 0.4f));
				r.render(gl);
				inventory.advance();
			}
			gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
			gl.glPopMatrix();
			glutil.color(fadeIn.getFinalColor());

			fpsText.setVisible(getState() == State.NORMAL);
			if(getState() == State.NORMAL) {
				fpsText.setText(String.format("FPS: %.1f", getManager().getFPS()));
				fpsText.setXYZ(glutil.getViewportAspectRatio() - fpsText.getWidth() - fpsText.getHeight() / 2, 1 - 3 * fpsText.getHeight() / 2, -1);
			}
		}
		else {
			for(inventory.reset(); !inventory.isDone(); inventory.advance())
				inventory.current().setVisible(false);
		}

		renderObjects(gl);
	}

	public void zoom(double dz) {
		/*
		zoom += dz;
		if(zoom > ZOOM_MAX)
			zoom = ZOOM_MAX;
		else if(zoom < ZOOM_MIN)
			zoom = ZOOM_MIN;
		 */
	}

	public void changeFrom() {
		LogManager.getInstance().log(this + " had changeFrom() invoked, current state " + getState(), "VIEW");
		changeState(State.HIDDEN);
	}

	public void changeTo() {
		LogManager.getInstance().log(this + " has state " + getState(), "VIEW");
		if(getState() == State.NEW)
			changeState(State.FADING_IN);
		else
			changeState(State.NORMAL);
	}
}
