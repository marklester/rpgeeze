import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

import com.sun.opengl.util.texture.*;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Lesson08 extends KeyAdapter implements GLEventListener {
	private float xrot, yrot;
	private float xspeed = 0.0f;
	private float yspeed = 0.0f;
	private float z = -5.0f;
	
	private Texture[] textures = new Texture[3];
	
	private int filter;
	
	private boolean light = true;
	private boolean blend = true;
	
	private float[] ambientLight = {0.5f, 0.5f, 0.5f, 1.0f};
	private float[] diffuseLight = {1.0f, 1.0f, 1.0f, 1.0f};
	
	private float[] lightPosition = {0.0f, 0.0f, 2.0f, 1.0f};
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_L) {
			light = !light;
		}
		else if(k == KeyEvent.VK_B) {
			blend = !blend;
		}
		else if(k == KeyEvent.VK_F) {
			filter = (filter + 1) % textures.length;
		}
		else if(k == KeyEvent.VK_PAGE_DOWN) {
			z -= 0.2f;
		}
		else if(k == KeyEvent.VK_PAGE_UP) {
			z += 0.2f;
		}
		else if(k == KeyEvent.VK_LEFT) {
			yspeed -= 0.01f;
		}
		else if(k == KeyEvent.VK_RIGHT) {
			yspeed += 0.01f;
		}
		else if(k == KeyEvent.VK_DOWN) {
			xspeed += 0.01f;
		}
		else if(k == KeyEvent.VK_UP) {
			xspeed -= 0.01f;
		}
	}
	
	public void display(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		gl.glTranslatef(0.0f, 0.0f, z);
		
		gl.glRotatef(xrot, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(yrot, 0.0f, 1.0f, 0.0f);
		//gl.glRotatef(zrot, 0.0f, 0.0f, 1.0f);
		
		textures[filter].bind();
		//gl.glBindTexture(GL.GL_TEXTURE_2D, texture);

        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        // Back Face
        gl.glNormal3f(0.0f, 0.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        // Top Face
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        // Bottom Face
        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        // Right Face
        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        // Left Face
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();

        if(light)
        	gl.glEnable(GL.GL_LIGHTING);
        else
        	gl.glDisable(GL.GL_LIGHTING);
        
        if(blend) {
        	gl.glEnable(GL.GL_BLEND);
        	gl.glDisable(GL.GL_DEPTH_TEST);
        }
        else {
            gl.glDisable(GL.GL_BLEND);
            gl.glEnable(GL.GL_DEPTH_TEST);
        }
        
		gl.glFlush();
        
		
        xrot += xspeed;
        yrot += yspeed;
        //zrot += 0.4f;
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
	}

	public void init(GLAutoDrawable drawable) {
		final GL gl = drawable.getGL();
		
		gl.glEnable(GL.GL_TEXTURE_2D);
		
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE);
		
		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glDepthFunc(GL.GL_LEQUAL);
		
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		
        textures[0] = loadTexture(getClass().getClassLoader().getResourceAsStream("res/glass.png"));
		textures[0].setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		textures[0].setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
        
        textures[1] = loadTexture(getClass().getClassLoader().getResourceAsStream("res/glass.png"));
		textures[1].setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		textures[1].setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        
        textures[2] = loadTexture(getClass().getClassLoader().getResourceAsStream("res/glass.png"));
		textures[2].setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		textures[2].setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR_MIPMAP_NEAREST);
        
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, ambientLight, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, diffuseLight, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightPosition, 0);
		
		gl.glEnable(GL.GL_LIGHT1);
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