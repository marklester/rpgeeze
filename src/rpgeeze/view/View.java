package rpgeeze.view;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.TraceGL;
import javax.media.opengl.glu.GLU;

import rpgeeze.EventProcessor;
import rpgeeze.GameManager;
import rpgeeze.MouseHit;
import rpgeeze.controller.Controller;
import rpgeeze.util.cmd.Command;

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
	private GameManager manager;
	
	public View(GameManager manager) {
		this.manager = manager;
	}
	
	public void display() {
		GL gl = GLContext.getCurrent().getGL();
		//pick();
		render(null);
		gl.glFlush();
	}

	public void pick() {
		GL gl = GLContext.getCurrent().getGL();
		
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
	}
	
	public abstract void render(Point point);

	public void changeFrom() {
	}

	public void changeTo() {
	}

	public void keyPressed(final KeyEvent e) {
		EventProcessor.getInstance().queueEvent(new Command<Controller>() {
			public void execute(Controller c) {
				c.keyPressed(e);
			}
		});
	}

	public void keyReleased(final KeyEvent e) {
		EventProcessor.getInstance().queueEvent(new Command<Controller>() {
			public void execute(Controller c) {
				c.keyReleased(e);
			}
		});
	}

	public void keyTyped(final KeyEvent e) {
		EventProcessor.getInstance().queueEvent(new Command<Controller>() {
			public void execute(Controller c) {
				c.keyTyped(e);
			}
		});
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("Click! " + e);
		manager.eventContext.makeCurrent();
		pick();
		EventProcessor.getInstance().queueEvent(new MouseHit(e) {
			public void execute(Controller c) {
				c.mouseClicked(getMouseEvent());
			}
		});
	}

	public void mouseEntered(final MouseEvent e) {
		EventProcessor.getInstance().queueEvent(new MouseHit(e) {
			public void execute(Controller c) {
				c.mouseEntered(e);
			}
		});
	}

	public void mouseExited(MouseEvent e) {
		EventProcessor.getInstance().queueEvent(new MouseHit(e) {
			public void execute(Controller c) {
				c.mouseExited(getMouseEvent());
			}
		});
	}

	public void mousePressed(MouseEvent e) {
		EventProcessor.getInstance().queueEvent(new MouseHit(e) {
			public void execute(Controller c) {
				c.mousePressed(getMouseEvent());
			}
		});
	}

	public void mouseReleased(MouseEvent e) {
		EventProcessor.getInstance().queueEvent(new MouseHit(e) {
			public void execute(Controller c) {
				c.mouseReleased(getMouseEvent());
			}
		});
	}
}
