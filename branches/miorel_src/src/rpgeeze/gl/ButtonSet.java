package rpgeeze.gl;

import java.awt.Color;
import java.util.HashMap;

import javax.media.opengl.GL;

import rpgeeze.gl.geom.TextRectangle;

public class ButtonSet {
	private HashMap<String, Highlightable> hashMap = new HashMap<String, Highlightable>();
	private Highlightable highlighted;

	public ButtonSet(TextRectangle prototype, Color plain, Color highlighted, int rowSize, double horizSpace, double vertSpace, String... names) {
		int row = 0;  
		int curRowSize = 0;
		for(String name: names)
			if(name != null) {
				TextRectangle button = prototype.clone();
				double xOffset = prototype.getX();
				double yOffset = prototype.getY();
				button.setXY(xOffset + curRowSize * (prototype.getWidth() + horizSpace), yOffset + row * (prototype.getHeight() + vertSpace));
				button.getText().setText(name);
				button.alignText(0.5, 0);
				button.getText().setY(prototype.getText().getY());
				hashMap.put(name, new HighlightableWrapper(button, plain, highlighted));
				if(++curRowSize >= rowSize) {
					curRowSize = 0;
					++row;
				}
			}
	}

	public void highlight(String name) {
		unhighlight();
		Highlightable h = hashMap.get(name);
		if(h != null)
			h.highlight();
		highlighted = h;
	}

	public void unhighlight() {
		if(highlighted != null)
			highlighted.unhighlight();
		highlighted = null;
	}

	public void render(GL gl) {
		for(Highlightable h: hashMap.values())
			h.render(gl);
	}

	public void addTo(Scene scene) {
		for(String name: hashMap.keySet())
			scene.add(hashMap.get(name), name);
	}
}
