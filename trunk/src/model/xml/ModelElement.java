package rpgeeze.model.xml;

public interface ModelElement {
	void accept(GameVisitor visitor);
}
