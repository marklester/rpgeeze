package rpgeeze.view;

import java.awt.Point;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.Queue;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

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
	
	public final void display() {
		GL gl = GLU.getCurrentGL();
	
		setup(null);
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		if(!pickQueue.isEmpty()) {
			pickRects(gl);
		}/**/
		
		drawRects();
		gl.glFlush();
	}

	public void pickRects(GL gl) {
		int BUFSIZE = 512;
		
		Point pickPoint = pickQueue.poll();
		int[] selectBuf = new int[BUFSIZE];
		IntBuffer selectBuffer = BufferUtil.newIntBuffer(BUFSIZE);
		
		gl.glSelectBuffer(BUFSIZE, selectBuffer);
		gl.glRenderMode(GL.GL_SELECT);

		gl.glInitNames();
		gl.glPushName(-1);

		setup(pickPoint);
		drawRects();
		gl.glFlush();
		
		int hits = gl.glRenderMode(GL.GL_RENDER);
				
		selectBuffer.get(selectBuf);

		setup(null);
		
		int names, ptr = 0;

		System.out.println("hits = " + hits);
		// ptr = (GLuint *) buffer;
		for (int i = 0; i < hits; i++)
		{ /* for each hit */
			names = selectBuf[ptr];
//			System.out.println(" number of names for hit = " + names);
			ptr++;
//			System.out.println("  z1 is " + selectBuf[ptr]);
			ptr++;
//			System.out.println(" z2 is " + selectBuf[ptr]);
			ptr++;
//			System.out.print("\tthe name is");
			for (int j = 0; j < names; j++) {
//				System.out.println(" " + selectBuf[ptr++]);
				ptr++;
			}
			//System.out.println();
		}
	}

	public void drawRects() {
		GL gl = GLU.getCurrentGL();
		
		//gl.glClearColor(0.0f, 0.5f, 0.0f, 0.0f);
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
	
	
	public void setup(Point point) {
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
	}
	
	protected abstract void doDisplay();
	
	public void changeFrom() {
	}

	public void changeTo() {
	}
}
