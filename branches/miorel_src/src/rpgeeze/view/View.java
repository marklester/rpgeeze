package rpgeeze.view;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import rpgeeze.controller.Controller;

import com.sun.opengl.util.BufferUtil;

/**
 * Superclass for the various screens that the game will have to display. The
 * most important method is display(), which will be called whenever the screen
 * must be painted. Additionally, each View must provide a CommandHandler so
 * that it may participate in the command framework. The methods changeFrom()
 * and changeTo() are called when the game state changes from and to this view,
 * respectively. They should be used, for example, to pause/resume the game when
 * moving away from the game screen.
 */

public abstract class View implements KeyListener, MouseListener {
	public Queue<Point> pickQueue = new LinkedList<Point>();
	private List<Controller> controllers = new ArrayList<Controller>();
	
	public final void display() {
		GL gl = GLU.getCurrentGL();
	
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		if(!pickQueue.isEmpty())
			pick();
		
		render(null);
		
		gl.glFlush();
	}

	public void pick() {
		GL gl = GLU.getCurrentGL();
		int BUFSIZE = 512;
		
		Point pickPoint = pickQueue.poll();
		int[] selectBuf = new int[BUFSIZE];
		IntBuffer selectBuffer = BufferUtil.newIntBuffer(BUFSIZE);
		
		gl.glSelectBuffer(BUFSIZE, selectBuffer);
		gl.glRenderMode(GL.GL_SELECT);

		gl.glInitNames();
		gl.glPushName(-1);

		render(pickPoint);
		gl.glFlush();
		
		int hits = gl.glRenderMode(GL.GL_RENDER);
				
		selectBuffer.get(selectBuf);

		System.out.println("hits = " + hits);
		System.out.println(Arrays.toString(selectBuf));
		System.out.println();
	}
		
	public void render(Point point) {
		GL gl = GLU.getCurrentGL();

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
	
	public void changeFrom() {
	}

	public void changeTo() {
	}

	public void keyPressed(KeyEvent e) {
		for(Controller c: controllers)
			c.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		for(Controller c: controllers)
			c.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		for(Controller c: controllers)
			c.keyTyped(e);
	}

	public void mouseClicked(MouseEvent e) {
		for(Controller c: controllers)
			c.mouseClicked(e);
	}

	public void mouseEntered(MouseEvent e) {
		for(Controller c: controllers)
			c.mouseEntered(e);
	}

	public void mouseExited(MouseEvent e) {
		for(Controller c: controllers)
			c.mouseExited(e);
	}

	public void mousePressed(MouseEvent e) {
		for(Controller c: controllers)
			c.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		for(Controller c: controllers)
			c.mouseReleased(e);
	}
	
	public void addController(Controller c) {
		controllers.add(c);
	}
	
	public void removeController(Controller c) {
		controllers.remove(c);
	}
}
