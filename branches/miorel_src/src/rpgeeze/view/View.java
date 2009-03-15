package rpgeeze.view;

import java.awt.Point;
import java.nio.IntBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;

import rpgeeze.util.Iterator;

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
	public Iterator<Integer> pick(Point pickPoint) {
		return pick(pickPoint, 16);
	}
	
	public Iterator<Integer> pick(Point pickPoint, int bufSize) {
		GL gl = GLContext.getCurrent().getGL();

		final int[] selectBuf = new int[bufSize];
		IntBuffer selectBuffer = BufferUtil.newIntBuffer(bufSize);

		gl.glSelectBuffer(bufSize, selectBuffer);
		gl.glRenderMode(GL.GL_SELECT);

		gl.glInitNames();
		gl.glPushName(-1);

		render(pickPoint);

		final int hits = gl.glRenderMode(GL.GL_RENDER);
		
		selectBuffer.get(selectBuf);
		
		return new Iterator<Integer>() {
			private int hit;
			private int name;
			private int ptr;
			
			public void advance() {
				++name;
				if(name == selectBuf[ptr]) {
					++hit;
					ptr += 3 + selectBuf[ptr];
					name = 0;
				}
			}

			public Integer current() {
				return selectBuf[ptr + 3 + name];
			}

			public boolean isDone() {
				return hit == hits;
			}

			public void reset() {
				hit = 0;
				name = 0;
				ptr = 0;
			}
		};
	}

	public abstract void render(Point point);

	public void changeFrom() {
	}

	public void changeTo() {
	}
}
