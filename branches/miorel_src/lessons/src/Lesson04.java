import java.awt.event.KeyAdapter;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Lesson04 extends KeyAdapter implements GLEventListener {
	private float rtri;
	private float rquad;
	
	public void display(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		gl.glTranslatef(-1.5f, 0.0f, -6.0f);
		
		gl.glRotatef(rtri, 0.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3f( 0.0f, 1.0f, 0.0f);
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(-1.0f,-1.0f, 0.0f);
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glVertex3f( 1.0f,-1.0f, 0.0f);
		gl.glEnd();
		
		gl.glLoadIdentity();
		gl.glTranslatef(1.5f, 0.0f, -6.0f);

		gl.glRotatef(rquad, 1.0f, 0.0f, 0.0f);
		gl.glColor3f(0.5f, 0.5f, 1.0f);
		gl.glBegin(GL.GL_QUADS);
		gl.glVertex3f(-1.0f, 1.0f, 0.0f);
		gl.glVertex3f( 1.0f, 1.0f, 0.0f);
		gl.glVertex3f( 1.0f,-1.0f, 0.0f);
		gl.glVertex3f(-1.0f,-1.0f, 0.0f);
		gl.glEnd();
		
		gl.glFlush();
		
		rtri += 0.2f;
		rquad -= 0.15f;
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