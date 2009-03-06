import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Random;

import com.sun.opengl.util.texture.*;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Lesson09 extends KeyAdapter implements GLEventListener {
	private boolean twinkle = false;
	private final int num = 35;
	
	class Star {
		float dist;
		float angle;
		byte r, g, b;
		
		public Star() {
			Random rnd = new Random();
			this.r = (byte) rnd.nextInt(256);
			this.g = (byte) rnd.nextInt(256);
			this.b = (byte) rnd.nextInt(256);
			this.angle = 0.0f;
		}
	}
	
	private Star[] star = new Star[num];
	
	private float spin = 0.0f;
	private float tilt = 90.0f;
	private float zoom = -15.0f;
	
	private Texture texture;
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_T) {
			twinkle = !twinkle;
		}
		else if(k == KeyEvent.VK_UP) {
			tilt -= 0.5f;
		}
		else if(k == KeyEvent.VK_DOWN) {
	        tilt += 0.5f;
		}
		else if(k == KeyEvent.VK_PAGE_DOWN) {
	        zoom -= 0.2f;
		}
		else if(k == KeyEvent.VK_PAGE_UP) {
	        zoom += 0.2f;
		}
	}
	
	public void display(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		texture.bind();
		
		for(int i = 0; i != num; ++i) {
			Star s = star[i];
			gl.glLoadIdentity();
			gl.glTranslatef(0.0f, 0.0f, zoom);
			gl.glRotatef(tilt, 1.0f, 0.0f, 0.0f);
			gl.glRotatef(s.angle, 0.0f, 1.0f, 0.0f);
			gl.glTranslatef(s.dist, 0.0f, 0.0f);
			gl.glRotatef(-s.angle, 0.0f, 1.0f, 0.0f);
			gl.glRotatef(-tilt, 1.0f, 0.0f, 0.0f);
			if(twinkle) {
				Star st = star[num - i - 1];
				gl.glColor4ub(st.r, st.g, st.b, (byte) 255);
				gl.glBegin(GL.GL_QUADS);
				gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, 0.0f);
				gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, 0.0f);
				gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);
				gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);
				gl.glEnd();
			}
			gl.glRotatef(spin, 0.0f, 0.0f, 1.0f);
			gl.glColor4ub(s.r, s.g, s.b, (byte) 255);
			gl.glBegin(GL.GL_QUADS);
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, 0.0f);
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, 0.0f);
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);
			gl.glEnd();
			s.dist -= 0.01f;
			s.angle += ((float) i) / num;
			spin += 0.01f;
			if(s.dist < 0.0f) {
				star[i] = new Star();
				star[i].dist = s.dist + 20.0f;
			}
		}
		
		gl.glFlush();
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
	}

	public void init(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		
		gl.glEnable(GL.GL_TEXTURE_2D);

		texture = loadTexture(getClass().getClassLoader().getResourceAsStream("res/Star.bmp"));
		texture.setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		texture.setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
		
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		
		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glDepthFunc(GL.GL_LEQUAL);
		
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE);
		gl.glEnable(GL.GL_BLEND);
		
		for(int i = 0; i != num; ++i) {
			star[i] = new Star();
			star[i].dist = (20.0f * i) / num;
		}
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
	
	public static Texture loadTexture(InputStream stream){
		Texture text = null;
		try {
			text = TextureIO.newTexture(ImageIO.read(stream), true);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			//System.out.println("Error loading texture " + fileName);
		}
		return text;
	}
}