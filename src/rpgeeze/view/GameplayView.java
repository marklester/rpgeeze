package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.Text;
import rpgeeze.gl.effect.ClearColorChange;
import rpgeeze.gl.geom.TexturedRectangle;
import rpgeeze.math.Scalar;
import rpgeeze.math.StaticVector;
import rpgeeze.model.Entity;
import rpgeeze.model.Tile;
import rpgeeze.model.terrain.*;
import rpgeeze.util.ResourceLoader;

public class GameplayView extends View<GameplayView.State> {
	private TexturedRectangle grass = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/grass.png"), 1, 1);
	private TexturedRectangle mountain = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/mountain.png"), 1, 1);;
	private TexturedRectangle water = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/water.png"), 1, 1);

	private TexturedRectangle entity = new TexturedRectangle(ResourceLoader.getInstance().getTexture("entity/entity.png"), 1, 1);;

	private TextRenderer renderer = new TextRenderer(new Font(Font.SANS_SERIF, Font.PLAIN, 24), true, true);
	private Scalar fps;

	private final static double ZOOM_MIN = -64;
	private final static double ZOOM_MAX = -2;
	private double zoom = -8;

	private ClearColorChange fadeIn;
	
	// currently public so the Controller can access it easily 
	// later someone will tell the Controller about the avatar differently
	public Entity avatar;

	public enum State implements View.State { NEW, FADING_IN, NORMAL, HIDDEN; }

	public GameplayView(GameManager manager, Entity avatar) {
		super(manager);
		this.avatar = avatar;
		fps = getManager().getFPS();
		fadeIn = new ClearColorChange(Color.BLACK, Color.WHITE, 1);
		changeState(State.NEW);
	}

	public void render(GL gl, Point point) {		
		setup(gl, point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		GLUtil glutil = new GLUtil(gl);

		// zoom
		gl.glTranslated(-0.5, -0.5, zoom);

		// get viewport dimensions in tiles that have to be displayed
		int widthInTiles = (int) Math.ceil(-2 * zoom * glutil.getViewportAspectRatio());
		int heightInTiles = (int) Math.ceil(-2 * zoom);

		int minX = (int) Math.floor(1 + widthInTiles / 2);
		int maxX = (int) Math.ceil(1 + widthInTiles / 2);
		int minY = (int) Math.floor(1 + heightInTiles / 2);
		int maxY = (int) Math.ceil(1 + heightInTiles / 2);

		if(getState() == State.FADING_IN) {
			if(point == null) {
				fadeIn.apply(gl);
				if(fadeIn.isDone())
					changeState(State.NORMAL);
			}
		}
		else
			glutil.clearColor(fadeIn.getFinalColor());

		for(int i = minX; i <= maxX; ++i)
			for(int j = minY; j <= maxY; ++j) {
				Tile t = avatar.getTile().getTile(new StaticVector(i, j, 0));
				gl.glPushMatrix();
				gl.glTranslated(i, j, 0);
				if(t.getTerrain() instanceof GrassTerrain)
					grass.render(gl);
				if(t.getTerrain() instanceof MountainTerrain)
					mountain.render(gl);
				if(t.getTerrain() instanceof WaterTerrain)
					water.render(gl);
				if(t.getEntity() != null)
					entity.render(gl);
				gl.glPopMatrix();
			}

		if(getState() == State.NORMAL) {
			gl.glLoadIdentity();
			Text fpsText = new Text(String.format("FPS: %.1f", fps.getValue()), Color.RED, renderer, 0.0025f);
			fpsText.setXYZ(glutil.getViewportAspectRatio() - fpsText.getWidth() - fpsText.getHeight() / 2, 1 - 3 * fpsText.getHeight() / 2, -1);
			fpsText.render(gl);
		}

		gl.glFlush();
	}

	public void zoom(double dz) {
		zoom += dz;
		if(zoom > ZOOM_MAX)
			zoom = ZOOM_MAX;
		else if(zoom < ZOOM_MIN)
			zoom = ZOOM_MIN;
	}

	public void changeFrom() {
		changeState(State.HIDDEN);
	}

	public void changeTo() {
		if(getState() == State.NEW)
			changeState(State.FADING_IN);
		else
			changeState(State.NORMAL);
	}
}
