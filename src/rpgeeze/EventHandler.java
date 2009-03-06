package rpgeeze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import rpgeeze.controller.Controller;
import rpgeeze.view.View;

public class EventHandler implements GLEventListener, KeyListener, MouseListener {
	private View view;
	private Controller controller;	
	
	public void keyPressed(KeyEvent e) {
		if(controller != null)
			controller.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		if(controller != null)
			controller.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		if(controller != null)
			controller.keyTyped(e);
	}

	public void mouseClicked(MouseEvent e) {
		if(controller != null)
			controller.mouseClicked(e);
	}

	public void mouseEntered(MouseEvent e) {
		if(controller != null)
			controller.mouseEntered(e);
	}

	public void mouseExited(MouseEvent e) {
		if(controller != null)
			controller.mouseExited(e);
	}

	public void mousePressed(MouseEvent e) {
		if(controller != null)
			controller.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		if(controller != null)
			controller.mouseReleased(e);
	}

	public void display(GLAutoDrawable drawable) {
		if(view != null)
			view.display();
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
	}

	public void init(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		final GL gl = drawable.getGL();
		final GLU glu = new GLU();
		
		// prevent division by zero
		if(height <= 0)
			height = 1;
		
		gl.glViewport(0, 0, width, height);
		
		// reset projection matrix
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();

		// calculate aspect ratio
		glu.gluPerspective(45.0f, ((float) width) / ((float) height), 0.1f, 100.0f);

		// select modelview matrix
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
}
