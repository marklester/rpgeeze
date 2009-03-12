package rpgeeze.view;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import rpgeeze.util.cmd.CommandHandler;

public class MainMenu extends View {
	public void display() {
		final GL gl = GLU.getCurrentGL();
		gl.glClearColor(0.0f, 0.5f, 0.0f, 0.0f);
		gl.glFlush();
	}

	public CommandHandler handler() {
		return null;
	}
}
