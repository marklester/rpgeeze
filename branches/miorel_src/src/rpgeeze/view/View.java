package rpgeeze.view;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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

public abstract class View {
	public Queue<Point> pickQueue = new LinkedList<Point>();

	private List<Controller> controllers = new ArrayList<Controller>();
	//private ArrayList<Pair<MouseEvent, Command<Controller>>> mouseEvents = new ArrayList<Pair<MouseEvent, Command<Controller>>>();
	
	
	public final void display() {
		GL gl = GLU.getCurrentGL();
		
		if(!pickQueue.isEmpty()) {
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

		render(null);

		gl.glFlush();
	}

	public abstract void render(Point point);

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
