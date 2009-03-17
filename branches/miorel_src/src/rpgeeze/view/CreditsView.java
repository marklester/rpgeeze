package rpgeeze.view;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;

import rpgeeze.gl.Text;
import rpgeeze.util.ResourceLoader;

public class CreditsView extends View {
	private Font plain = ResourceLoader.getInstance().getFont("DeutscheZierschrift.ttf", Font.PLAIN, 100);
	private Font italic = plain.deriveFont(Font.ITALIC);

	private TextRenderer plainRenderer = new TextRenderer(plain, true, true);
	private TextRenderer italicRenderer = new TextRenderer(italic, true, true);

	private Text title = new Text("RPGEEZE", plainRenderer, 0.0075f);
	private Text subtitle = new Text("is brought to you by", italicRenderer, 0.0025f);
	
	private Text developer;

	private int pointer;
	private final List<String> developers = new ArrayList<String>();
	
	public CreditsView() {
		Scanner s = new Scanner(ResourceLoader.getInstance().getStream("txt/developers.txt"));
		while(s.hasNextLine()) // lowercase k looks silly in this font so replace it
			developers.add(s.nextLine().replaceAll("k", "K"));
	}
	
	public void render(Point point) {
		GL gl = GLContext.getCurrent().getGL();

		gl.glShadeModel(GL.GL_SMOOTH);

		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);

		// textures and blending
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_SRC_COLOR);

		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl.glEnable(GL.GL_LINE_SMOOTH);
		
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		int[] vp = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, vp, 0);
		GLU glu = new GLU();
		if(point != null)
			glu.gluPickMatrix((double) point.x, (double) (vp[3] - point.y), 1e-3, 1e-3, vp, 0);
		double width = vp[2] <= 0 ? 1 : vp[2];
		gl.glFrustum(-vp[2] / width, vp[2] / width, -vp[3] / width, vp[3] / width, 1, 128);
		gl.glMatrixMode(GL.GL_MODELVIEW);		

		gl.glClearColor(MainMenuView.MAX_INTENSITY, 0, 0, 1.0f);

		gl.glLoadIdentity();
		
		title.setXYZ(-title.getWidth() / 2, 4 * vp[3] / width - title.getHeight(), -4);
		title.render();
	
		subtitle.setXYZ(-subtitle.getWidth() / 2, title.getY() - subtitle.getHeight() - 0.1, -4);
		subtitle.render();
		
		developer.setXYZ(-developer.getWidth() / 2, -developer.getHeight() / 2, -4);
		developer.render();
		
		gl.glFlush();
	}
	
	public void nextDeveloper() {
		pointer = (pointer + 1) % developers.size();
		developer = new Text(developers.get(pointer), plainRenderer, 0.005f);
	}

	public void changeTo() {
		pointer = -1;
		nextDeveloper();
	}
}
