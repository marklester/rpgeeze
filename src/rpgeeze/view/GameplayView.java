package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;

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

	public TexturedRectangle entity = new TexturedRectangle(ResourceLoader.getInstance().getTexture("entity/entity.png"), 1, 1);;
	
	private TextRenderer renderer = new TextRenderer(new Font(Font.SANS_SERIF, Font.PLAIN, 24), true, true);
	private String fpsText;
	
	private double ZOOM_MIN = -64;
	private double ZOOM_MAX = -2;
	private double zoom = -5;
	
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
		GL gl = GLContext.getCurrent().getGL();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		gl.glShadeModel(GL.GL_SMOOTH);

		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);

		// textures and blending
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		int[] vp = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, vp, 0);
		double width = vp[2] <= 0 ? 1 : vp[2];
		gl.glFrustum(-vp[2] / width, vp[2] / width, -vp[3] / width, vp[3] / width, 1, 128);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		
		gl.glClearColor(0, 0, 0, 0);

		gl.glLoadIdentity();
		gl.glTranslated(-0.5, -0.5, zoom);
		
		// get viewport dimensions in tiles that have to be displayed
		int widthInTiles = (int) Math.ceil(-2 * zoom);
		int heightInTiles = (int) Math.ceil(-2 * zoom * vp[3] / width);
		
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
	
//		Text fps = new Text(fpsText, Color.RED, renderer);
//		fps.align(1, 1);
//		fps.setX(fps.getX() - fps.getHeight() / 2);
//		fps.setY(fps.getY() - fps.getHeight() / 2);
//		fps.render();
		
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
