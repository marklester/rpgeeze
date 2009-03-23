package util;

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
		int num = first.hashCode() * 23;
		num += second.hashCode();
		return num;
	}
	
	public boolean equals(Object o) {
		boolean ret = false;
		if(o instanceof Pair) {
			Pair<T, U> p = (Pair<T, U>) o;
			
			if(first.equals(p.first) && second.equals(p.second)) 
				ret = true;
		}
		return ret;
	}
	public String toString()
	{
		return first + " " + second;
	}
	
}
