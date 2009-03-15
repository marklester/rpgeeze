package rpgeeze.view;

import java.awt.Point;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.TraceGL;
import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
import rpgeeze.gl.TexturedRectangle;
import rpgeeze.util.ResourceLoader;

public class MainMenuView extends View {
	private boolean newGameHighlight = false;
	private boolean loadGameHighlight = false;
	private boolean quitGameHighlight = false;
	
	public MainMenuView(GameManager manager) {
		super(manager);
	}
	
	public void render(Point point) {
		GL gl = GLContext.getCurrent().getGL();

		gl.glShadeModel(GL.GL_SMOOTH);
		
		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		int[] vp = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, vp, 0);
		GLU glu = new GLU();
		if(point != null)
			glu.gluPickMatrix((double) point.x, (double) (vp[3] - point.y), 1, 1, vp, 0);		
		glu.gluPerspective(45, ((double) vp[2]) / ((double) vp[3]), 0.1, 100);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		gl.glClearColor(0.0f, 0.5f, 0.0f, 0.0f);
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
		
		gl.glTranslated(0, 0, -10);
		
		gl.glLoadName(1);
		gl.glBegin(GL.GL_QUADS);
		gl.glColor3f(1.0f, 1.0f, 0.0f);
		gl.glVertex3i(2, 0, -2);
		gl.glVertex3i(2, 6, -2);
		gl.glVertex3i(6, 6, -2);
		gl.glVertex3i(6, 0, -2);
		gl.glEnd();
		
		gl.glLoadName(2);
		gl.glBegin(GL.GL_QUADS);
		gl.glColor3f(0.0f, 1.0f, 1.0f);
		gl.glVertex3i(3, 2, -1);
		gl.glVertex3i(3, 8, -1);
		gl.glVertex3i(8, 8, -1);
		gl.glVertex3i(8, 2, -1);
		gl.glEnd();
		
		gl.glLoadName(3);
		gl.glBegin(GL.GL_QUADS);
		gl.glColor3f(1.0f, 0.0f, 1.0f);
		gl.glVertex3i(0, 2, 0);
		gl.glVertex3i(0, 7, 0);
		gl.glVertex3i(5, 7, 0);
		gl.glVertex3i(5, 2, 0);
		gl.glEnd();
	}
	
	public void doDisplay() {
		GL gl = GLContext.getCurrent().getGL();

		gl.glClearColor(0.0f, 0.5f, 0.0f, 0.0f);
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
		
		gl.glTranslated(0, 0, -10);
		
		gl.glLoadName(1);
		gl.glBegin(GL.GL_QUADS);
		gl.glColor3f(1.0f, 1.0f, 0.0f);
		gl.glVertex3i(2, 0, -20);
		gl.glVertex3i(2, 6, -20);
		gl.glVertex3i(6, 6, -20);
		gl.glVertex3i(6, 0, -20);
		gl.glEnd();
		
		gl.glLoadName(2);
		gl.glBegin(GL.GL_QUADS);
		gl.glColor3f(0.0f, 1.0f, 1.0f);
		gl.glVertex3i(3, 2, -16);
		gl.glVertex3i(3, 8, -16);
		gl.glVertex3i(8, 8, -16);
		gl.glVertex3i(8, 2, -16);
		gl.glEnd();
		
		gl.glLoadName(3);
		gl.glBegin(GL.GL_QUADS);
		gl.glColor3f(1.0f, 0.0f, 1.0f);
		gl.glVertex3i(0, 2, -12);
		gl.glVertex3i(0, 7, -12);
		gl.glVertex3i(5, 7, -12);
		gl.glVertex3i(5, 2, -12);
		gl.glEnd();
		
/*
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);

		gl.glClearColor(0.0f, 0.5f, 0.0f, 0.0f);
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);

		gl.glTranslated(-5, -8.5, -25);

		gl.glPushMatrix();
		gl.glTranslated(-10, 0, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, newGameHighlight ? 0.5f : 0.0f);
		gl.glLoadName(1);
		new TexturedRectangle(ResourceLoader.getInstance().getTexture("img/buttons/NewGame.png"), 10, 3).render();
		gl.glPopMatrix();

		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.0f);
		gl.glLoadName(2);
		new TexturedRectangle(ResourceLoader.getInstance().getTexture("img/buttons/LoadGame.png"), 10, 3).render();

		gl.glPushMatrix();
		gl.glTranslated(10, 0, 0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.0f);
		gl.glLoadName(3);
		new TexturedRectangle(ResourceLoader.getInstance().getTexture("img/buttons/QuitGame.png"), 10, 3).render();
		gl.glPopMatrix();/**/
	}
	
	public void toggleNewGameHighlight() {
		newGameHighlight = !newGameHighlight; 
	}
}
