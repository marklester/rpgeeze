package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.gl.GLUtil;
import rpgeeze.gl.Text;
import rpgeeze.gl.effect.BrushColorChange;
import rpgeeze.log.LogManager;
import rpgeeze.model.Entity;
import rpgeeze.model.Tile;

public class GameplayView extends View<GameplayView.State> {
	private TextRenderer renderer = new TextRenderer(new Font(Font.SANS_SERIF, Font.PLAIN, 24), true, true);

	private final static double MAP_Z = -8;
	
	private BrushColorChange fadeIn;
	private Text fpsText;

	private Drawer drawer = new Drawer();
	
	public enum State implements View.State { NEW, FADING_IN, NORMAL, HIDDEN; }

	public GameplayView(GameManager manager) {
		super(manager);
		fadeIn = new BrushColorChange(new Color(0, 0, 0, 1f), new Color(1, 1, 1, 1f), 1);
		fpsText = new Text("", Color.RED, renderer, 0.0025f);
		put(fpsText, null);
		changeState(State.NEW);
	}

	public void render(GL gl, Point point) {		
		GLUtil glutil = new GLUtil(gl);
		glutil.standardFrustum(gl, point);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		//gl.glClearColor(0, 0, 0, 1);
		
		if(getState() == State.FADING_IN) {
			if(point == null) {
				fadeIn.apply(gl);
				if(fadeIn.isDone())
					changeState(State.NORMAL);
			}
		}
		else
			glutil.color(fadeIn.getFinalColor());
		
		drawer.setGL(gl);
		drawer.setSize(1);
		
		// zoom
//		double myZoom = zoom;
//		gl.glTranslated(-0.5, -0.5, myZoom);
		
		// get viewport dimensions in tiles that have to be displayed
		double widthInTiles = Math.ceil(-2 * MAP_Z * glutil.getViewportAspectRatio());
		double heightInTiles = Math.ceil(-2 * MAP_Z);

		Entity avatar = getManager().getModel().getAvatar();
		int centerX = avatar.getTile().getX();
		int centerY = avatar.getTile().getY();
		
		//System.out.println("Centering on " + centerX + " " + centerY);
		//gl.glTranslated(-centerX, -centerY, 0);
		
		int minX = (int) Math.floor(centerX - (1 + 0.5 * widthInTiles));
		int maxX = (int) Math.ceil(centerX + (1 + 0.5 * widthInTiles));
		int minY = (int) Math.floor(centerY - (1 + 0.5 * heightInTiles));
		int maxY = (int) Math.ceil(centerY + (1 + 0.5 * heightInTiles));
		
		Iterator<Tile> iter = getManager().getModel().getMap().getTiles(minX, minY, maxX, maxY);
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			Tile tile = iter.current();
			gl.glPushMatrix();
			gl.glTranslated(tile.getX() - centerX, tile.getY() - centerY, MAP_Z);
			tile.accept(drawer);
			gl.glPopMatrix();
		}
		
		gl.glTranslated(centerX, centerY, 0);
		fpsText.setVisible(getState() == State.NORMAL);
		if(getState() == State.NORMAL) {
			fpsText.setText(String.format("FPS: %.1f", getManager().getFPS()));
			fpsText.setXYZ(glutil.getViewportAspectRatio() - fpsText.getWidth() - fpsText.getHeight() / 2, 1 - 3 * fpsText.getHeight() / 2, -1);
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
