package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Location {
	private final int x;
	private final int y;

	private static Pattern pattern = Pattern.compile("<location><x>(.*?)</x><y>(.*)</y></location>");
	
	public Location() {
		this.x = 0;
		this.y = 0;
	}

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

	
	public String toXml() {
		return toXml("");
	}
	
	public String toXml(String indent) {
		StringBuilder sb = new StringBuilder();
		sb.append(indent + "<location>\n");
		sb.append(indent + "\t<x>" + x + "</x>\n");
		sb.append(indent + "\t<y>" + y + "</y>\n");
		sb.append(indent + "</location>");
		return sb.toString();
	}
	
	public Direction closestDirection() {
		Direction ret = Direction.forXY(getX(), getY());
		if(ret == null) ret = Direction.NORTH;
		return ret;
	}
	
	public static Location fromXml(String xml) {
		Matcher mat = pattern.matcher(xml);
		if(!mat.matches())
			throw new RuntimeException("Bad XML for Location");
		int x = Integer.parseInt(mat.group(1));
		int y = Integer.parseInt(mat.group(2));
		return new Location(x, y);
	}
}
