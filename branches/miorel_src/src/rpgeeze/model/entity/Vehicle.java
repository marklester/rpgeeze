package rpgeeze.model.entity;

import rpgeeze.model.Visitor;


public class Vehicle extends Entity{
	private int updateCounter;
	private int speed;
	
	public Vehicle(int speed){
		this.speed = speed;
	}
	public boolean isAlive(){
		return true;
	}
	
	public void update(){
	 if(this.updateCounter > 0)
		--this.updateCounter;

	}
	
	public void accept(Visitor visitor) {
		visitor.visitEntity(this);
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return this.speed;
	}
}