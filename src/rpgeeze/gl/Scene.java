package rpgeeze.gl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.media.opengl.GL;

import rpgeeze.dp.Iterator;
import rpgeeze.util.ListIterator;

public class Scene {
	private final HashMap<String, GLObject> nameObj = new HashMap<String, GLObject>();
	private final HashMap<GLObject, String> objName = new HashMap<GLObject, String>();
	private final List<GLObject> objects = new ArrayList<GLObject>();

	public void add(GLObject object, String name) {
		if(nameObj.get(name) == null)
			nameObj.put(name, object);
		else
			throw new RuntimeException("Duplicate names!");

		if(objName.get(object) == null)
			objName.put(object, name);
		else
			throw new RuntimeException("Duplicate objects!");
		
		objects.add(object);
	}
	
	public void add(Scene scene) {
		for(GLObject obj: scene.objects)
			add(obj, scene.getNameForObject(obj));
	}

	public String getNameForGLName(int glName) {
		String ret = null;
		if(glName > 0 && glName <= objects.size())
			return objName.get(objects.get(glName - 1));
		return ret;
	}
	
	public String getNameForObject(GLObject object) {
		return objName.get(object);
	}

	public GLObject getObjectForName(String name) {
		return nameObj.get(name);
	}
	
	public void render(GL gl) {
		Iterator<GLObject> iter = new ListIterator<GLObject>(objects);
		int glName = 0;
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			gl.glLoadName(++glName);
			iter.current().render(gl);
		}
		gl.glLoadName(-1);
	} 
}
