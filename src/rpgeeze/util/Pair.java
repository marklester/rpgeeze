package rpgeeze.util;

public class Pair<T, U> {
	private final T first;
	private final U second;
	
	public Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}
	
	public T getFirst() {
		return first;
	}
	
	public U getSecond() {
		return second;
	}
	
	public int hashCode() {
		return first.hashCode() * 23 + second.hashCode();
	}
	
	public boolean equals(Object o) {
		boolean ret = false;
		if(o instanceof Pair) {
			Pair p = (Pair) o;
			if(first.equals(p.first) && second.equals(p.second)) 
				ret = true;
		}
		return ret;
	}
}
