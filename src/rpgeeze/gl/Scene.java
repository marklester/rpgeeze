package rpgeeze.gl;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;

import rpgeeze.dp.Iterator;
import rpgeeze.util.ListIterator;

public class Scene {
	private final List<String> names = new ArrayList<String>(); 
	private final List<GLObject> objects = new ArrayList<GLObject>();

	public void add(GLObject object, String name) {
		names.add(name);
		objects.add(object);
	}
	
	public void render(GL gl) {
		Iterator<GLObject> iter = new ListIterator<GLObject>(objects);
		int glName = 0;
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			gl.glLoadName(++glName);
			iter.current().render(gl);
		}
	}
	
//	public 
}
