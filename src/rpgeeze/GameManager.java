package rpgeeze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

import rpgeeze.view.View;

/**
 * Primary implementer of the event-listening interfaces. This class does very little work itself.
 */

public class GameManager implements GLEventListener, KeyListener, MouseListener {
	private final Stack<View> stateStack = new Stack<View>();
	
	public GameManager(GLCanvas canvas) {
	    canvas.addGLEventListener(this);
	    canvas.addKeyListener(this);
	    canvas.addMouseListener(this);
	}
	
	private View getView() {
		return stateStack.isEmpty() ? null : stateStack.peek();
	}
	
	public void keyPressed(KeyEvent e) {
		final View view = getView();
		if(view != null)
			view.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		final View view = getView();
		if(view != null)
			view.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		final View view = getView();
		if(view != null)
			view.keyTyped(e);
	}

	public void mouseClicked(MouseEvent e) {
		final View view = getView();
		if(view != null)
			view.mouseClicked(e);
	}

	public void mouseEntered(MouseEvent e) {
		final View view = getView();
		if(view != null)
			view.mouseEntered(e);
	}

	public void mouseExited(MouseEvent e) {
		final View view = getView();
		if(view != null)
			view.mouseExited(e);
	}

	public void mousePressed(MouseEvent e) {
		final View view = getView();
		if(view != null)
			view.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		final View view = getView();
		if(view != null)
			view.mouseReleased(e);
	}

	public void display(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		final View view = getView();
		if(view != null)
			view.display();
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
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

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL gl = drawable.getGL();
		
		// prevent division by zero
		if(height <= 0)
			height = 1;
		
		gl.glViewport(0, 0, width, height);
	}
	
	public void pushState(View newView) {
		final View view = getView();
		
		if(view != null)
			view.changeFrom();
		if(newView != null) 
			newView.changeTo();
		
		stateStack.push(newView);
	}
	
	public View popState() {
		return stateStack.pop();
	}
}
