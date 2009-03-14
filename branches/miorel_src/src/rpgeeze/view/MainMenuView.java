package rpgeeze.view;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import rpgeeze.gl.TexturedRectangle;
import rpgeeze.util.ResourceLoader;

public class MainMenuView extends View {
	private boolean newGameHighlight = false;
	private boolean loadGameHighlight = false;
	private boolean quitGameHighlight = false;
	
	protected void doDisplay() {
		final GL gl = GLU.getCurrentGL();

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
