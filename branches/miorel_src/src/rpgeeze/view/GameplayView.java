package rpgeeze.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.gl.Text;
import rpgeeze.gl.TexturedRectangle;
import rpgeeze.util.ResourceLoader;

public class GameplayView extends View {
	private TexturedRectangle grass = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/grass.png"), 1, 1);
	private TexturedRectangle mountain = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/mountain.png"), 1, 1);;
	private TexturedRectangle water = new TexturedRectangle(ResourceLoader.getInstance().getTexture("terrain/water.png"), 1, 1);
	
	private TextRenderer renderer = new TextRenderer(new Font(Font.SANS_SERIF, Font.PLAIN, 36), true, true);
	
	private double zoom = -(1 << 5);
	
	private double ZOOM_MIN = -(1 << 6);
	private double ZOOM_MAX = -1;
	
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
/*
		if(point != null)
			glu.gluPickMatrix((double) point.x, (double) (vp[3] - point.y), 1e-3, 1e-3, vp, 0);
/**/
		double width = vp[2] <= 0 ? 1 : vp[2];
		gl.glFrustum(-vp[2] / width, vp[2] / width, -vp[3] / width, vp[3] / width, 1, -ZOOM_MIN);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		
		gl.glClearColor(0, 0, 0, 1.0f);

		gl.glLoadIdentity();
		gl.glTranslated(-0.5, -0.5, zoom);
		
		int widthInTiles = (int) Math.ceil(-2 * zoom);
		int heightInTiles = (int) Math.ceil(-2 * zoom * vp[3] / width);
		
		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		for(int i = 0; i <= 0; ++i)
			for(int j = 0; j <= 0; ++j) {
				gl.glPushMatrix();
				gl.glTranslated(i, j, 0);
				water.render();
				gl.glPopMatrix();
			}
		
		new Text(String.format("zoom = %.3f; dim = %d x %d", zoom, widthInTiles, heightInTiles), Color.RED, renderer).render();
		
		gl.glFlush();
	}
	
	public void zoom(double dz) {
		zoom += dz;
		if(zoom > ZOOM_MAX)
			zoom = ZOOM_MAX;
		else if(zoom < ZOOM_MIN)
			zoom = ZOOM_MIN;
	}
}
