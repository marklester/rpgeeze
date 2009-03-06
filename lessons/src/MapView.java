import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

import com.sun.opengl.util.texture.*;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class MapView extends KeyAdapter implements GLEventListener {
	private float dx = 0.0f, dy = 0.0f, dz = -50.0f;
	
	private Texture[] textures = new Texture[3];
	
	private int filter;
		
	private float yrot;
	
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_F) {
			filter = (filter + 1) % textures.length;
		}
		else if(k == KeyEvent.VK_PAGE_DOWN) {
			dz -= 1.0f;
		}
		else if(k == KeyEvent.VK_PAGE_UP) {
			dz += 1.0f;
		}
		else if(k == KeyEvent.VK_UP) {
			yrot -= 1.0f;
		}
		else if(k == KeyEvent.VK_DOWN) {
			yrot += 1.0f;
		}
		else if(k == KeyEvent.VK_W) {
			dy += 0.2f;
		}
		else if(k == KeyEvent.VK_S) {
			dy -= 0.2f;
		}
		else if(k == KeyEvent.VK_D) {
			dx += 0.2f;
		}
		else if(k == KeyEvent.VK_A) {
			dx -= 0.2f;
		}
	}
	
	public void display(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		
		gl.glTranslatef(dx, dy, dz);
		gl.glRotatef(yrot, 1.0f, 0.0f, 0.0f);
		
		textures[filter].bind();

		for(int i = -100; i <= 100; ++i) {
			for(int j = -100; j <= 100; ++j) {
				drawSquare(gl, i, j);
			}
			
		}

        gl.glEnable(GL.GL_DEPTH_TEST);
        
		gl.glFlush();
	}

	private void drawSquare(final GL gl, float x, float y) {
		gl.glTranslatef(x, y, 0.0f);
        
		gl.glBegin(GL.GL_QUADS);
        
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(0.5f, 0.5f, 0.0f);
        
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-0.5f, 0.5f, 0.0f);
        
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-0.5f, -0.5f, 0.0f);
        
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(0.5f, -0.5f, 0.0f);
        
        gl.glEnd();
        
        gl.glTranslatef(-x, -y, 0.0f);
	}
	
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
	}

	public void init(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		
		gl.glEnable(GL.GL_TEXTURE_2D);
		
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
		
		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glDepthFunc(GL.GL_LEQUAL);
		
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		
        textures[0] = loadTexture(getClass().getClassLoader().getResourceAsStream("res/WaterTerrain.png"));
		textures[0].setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		textures[0].setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        
        textures[1] = loadTexture(getClass().getClassLoader().getResourceAsStream("res/GrassTerrain.png"));
		textures[1].setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		textures[1].setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        
        textures[2] = loadTexture(getClass().getClassLoader().getResourceAsStream("res/MountainTerrain.png"));
		textures[2].setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		textures[2].setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
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