package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.gl.GL;
import rpgeeze.gl.Text;
import rpgeeze.gl.TexturedRectangle;
import rpgeeze.model.map.Map;
import rpgeeze.model.Tile;
import rpgeeze.model.terrain.*;
import rpgeeze.dp.Iterator;
import rpgeeze.util.ResourceLoader;

public class GameplayView extends View {
	private TexturedRectangle grass = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/grass.png"), 1, 1);
	private TexturedRectangle mountain = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/mountain.png"), 1, 1);;
	private TexturedRectangle water = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/water.png"), 1, 1);

	private TexturedRectangle entity = new TexturedRectangle(ResourceLoader.getInstance().getTexture("entity/entity.png"), 1, 1);;
	
	private TextRenderer renderer = new TextRenderer(new Font(Font.SANS_SERIF, Font.PLAIN, 24), true, true);
	private String fpsText;
	
	private double ZOOM_MIN = -64;
	private double ZOOM_MAX = -2;
	private double zoom = -8;
	
	private float MIN_INTENSITY = 0.0f;
	private float MAX_INTENSITY = 1.0f;
	private float intensity = MIN_INTENSITY;
	
	private double centerX = 0;
	private double centerY = 0;
	
	private Map map;
	
	public GameplayView(Map map) {
		this.map = map;
	}
	
	public void render(Point point) {
		GL gl = GL.getCurrent();		
		gl.standardPrepare(point);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glClearColor(0, 0, 0, 0);

        // zoom
        gl.glTranslated(-0.5, -0.5, zoom);
		
		// get viewport dimensions in tiles that have to be displayed
		int widthInTiles = (int) Math.ceil(-2 * zoom * gl.getViewportAspectRatio());
		int heightInTiles = (int) Math.ceil(-2 * zoom);
		
		int minX = (int) Math.floor(centerX - (1 + widthInTiles / 2));
		int maxX = (int) Math.ceil(centerX + (1 + widthInTiles / 2));
		int minY = (int) Math.floor(centerY - (1 + heightInTiles / 2));
		int maxY = (int) Math.ceil(centerY + (1 + heightInTiles / 2));
		
		gl.glColor4f(intensity, intensity, intensity, 1.0f);
		
		Iterator<Tile> iter = map.getTiles(minX, maxX, minY, maxY);
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			Tile t = iter.current();
			gl.glPushMatrix();
			gl.glTranslated(t.getX(), t.getY(), 0);
			if(t.getTerrain() instanceof GrassTerrain)
				grass.render();
			if(t.getTerrain() instanceof MountainTerrain)
				mountain.render();
			if(t.getTerrain() instanceof WaterTerrain)
				water.render();
			if(t.getEntity() != null)
				entity.render();
			gl.glPopMatrix();
		}

		// report FPS
		gl.glLoadIdentity();
		Text fps = new Text(fpsText, Color.RED, renderer, 0.0025f);
		fps.setXYZ(gl.getViewportAspectRatio() - fps.getWidth() - fps.getHeight() / 2, 1 - 3 * fps.getHeight() / 2, -1);
		fps.render();
		
		gl.glFlush();
	}
	
	public void zoom(double dz) {
		zoom += dz;
		if(zoom > ZOOM_MAX)
			zoom = ZOOM_MAX;
		else if(zoom < ZOOM_MIN)
			zoom = ZOOM_MIN;
	}
	
	public void setFpsText(String value) {
		fpsText = value;
	}
	
	public void changeIntensity(float dc) {
		intensity += dc;
		if(intensity > MAX_INTENSITY)
			intensity = MAX_INTENSITY;
		if(intensity < MIN_INTENSITY)
			intensity = MIN_INTENSITY;
	}
	
	public void changeFrom() {
		intensity = MAX_INTENSITY;
	}
}
