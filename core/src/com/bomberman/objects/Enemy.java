package com.bomberman.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.bomberman.core.CollisionHandler;

public class Enemy extends Character{

	public static final float STEP_IN_PIXELS = 1.0f; 
	private static final String TEXTURE_PATH = "images/enemy_walk_sheets/walk.pack";

	public Enemy(float x, float y){
		this.textureWalk = new TextureAtlas(TEXTURE_PATH);
		this.Animation = new Animation(1/3f, this.getWalk().getRegions());
		this.setX(x);
		this.setY(y);
		this.setDirection("left");
	}
	
	//METHODS
	public void update(ArrayList<Wall> walls, Integer[][] brickPositions, ArrayList<Bomb> bombs){
		if(!CollisionHandler.checkInTheBox(this) || !CollisionHandler.checkCollision(this, walls, brickPositions, bombs))
		{
			this.changeDirection();
		}
		
		switch(this.getDirection()){
		case "left":
			this.moveLeft();
			break;
		case "right":
			this.moveRight();
			break;
		case "up":
			this.moveUp();
			break;
		case "down":
			this.moveDown();
			break;
		}
	}
	
	private void changeDirection(){
		switch(this.getDirection()){
		case "left":
			this.setDirection("up");
			this.moveRight();
			break;
		case "right":
			this.setDirection("down");
			this.moveLeft();
			break;
		case "up":
			this.setDirection("right");
			this.moveDown();
			break;
		case "down":
			this.setDirection("left");
			this.moveUp();
			break;
		}
	}
	
	public void moveLeft() {
		setX(getX() - STEP_IN_PIXELS);
	}

	public void moveRight() {
		setX(getX() + STEP_IN_PIXELS);
	}
	
	public void moveDown() {
		setY(getY() - STEP_IN_PIXELS);
	}
	
	public void moveUp() {
		setY(getY() + STEP_IN_PIXELS);
	}
}
