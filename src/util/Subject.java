package util;
import java.util.LinkedList;
import java.util.List;

public abstract class Subject {
	private List<Observer> list = new LinkedList<Observer>();
	
	public void register(Observer o)
	{
		if(!list.contains(o))
			list.add(o);
	}

	public void unregister(Observer o)
	{
		if(list.contains(o))
			list.remove(o);
	}

	public void updateObservers()
	{
		for(Observer o : list)
			o.update(this);
	}
}
