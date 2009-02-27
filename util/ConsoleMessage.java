package util;

import java.awt.Color;

public class ConsoleMessage {
	private final String message;
	private int timer;
	private final Color color;
	public ConsoleMessage(String message,Color color,int timer){
		this.message=message;
		this.timer = timer;
		this.color = color;
	}
	public void decrement(){
		if(timer<0){
			timer=0;
		}else if(timer>0){
			timer--;
		}
	}
	public String toString(){
		this.decrement();
		return this.message;
	}
	public int getTimer(){
		return this.timer;
	}
	public boolean isValid(){
		return (timer==0)?false:true;
	}
	public Color getColor(){
		return this.color;
	}
}
