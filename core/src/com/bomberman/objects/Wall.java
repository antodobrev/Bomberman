package com.bomberman.objects;

public class Wall {

	public float wallTop;
	public float wallBottom;
	public float wallRight;
	public float wallLeft;
	
	public Wall(float X, float Y){
		this.wallBottom = Y;
		this.wallLeft = X;
		this.wallRight = this.wallLeft + 40;
		this.wallTop = this.wallBottom + 40;
	}
}
