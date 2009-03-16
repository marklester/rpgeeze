package rpgeeze.view;

import java.awt.Point;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.texture.Texture;

import rpgeeze.gl.TexturedRectangle;
import rpgeeze.util.ResourceLoader;

public class GameplayView extends View {
	private TexturedRectangle grass = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/grass.png"), 1, 1);
	private TexturedRectangle mountain = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/mountain.png"), 1, 1);;
	private TexturedRectangle water = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/water.png"), 1, 1);
	
	private double zoom = -5;
	
	private double ZOOM_MIN = -50;
	private double ZOOM_MAX = -1.5;
	private double ZOOM_STEP = 0.05;
	
	public void render(Point point) {
		GL gl = GLContext.getCurrent().getGL();

		gl.glShadeModel(GL.GL_SMOOTH);

		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);

		// textures and blending
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);

		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		int[] vp = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, vp, 0);
		GLU glu = new GLU();
		if(point != null)
			glu.gluPickMatrix((double) point.x, (double) (vp[3] - point.y), 1e-3, 1e-3, vp, 0);				
		glu.gluPerspective(45, ((double) vp[2]) / ((double) vp[3]), 0.1, 100);
		gl.glMatrixMode(GL.GL_MODELVIEW);		

		gl.glClearColor(0, 0, 0, 1.0f);

		gl.glLoadIdentity();
		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glTranslated(0, 0, zoom);
		
		grass.render();
		
		gl.glFlush();
	}
	
	public void zoomIn(double factor) {
		zoom += ZOOM_STEP * factor;
		if(zoom > ZOOM_MAX)
			zoom = ZOOM_MAX;
	}
	
	public void zoomOut(double factor) {
		zoom -= ZOOM_STEP * factor;
		if(zoom < ZOOM_MIN)
			zoom = ZOOM_MIN;
	}
}
