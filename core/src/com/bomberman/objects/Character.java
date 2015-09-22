package com.bomberman.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public abstract class Character {
	
	public static final float STEP_IN_PIXELS = 1.75f;
	
	protected TextureAtlas textureWalk;
	protected Animation Animation;

	protected float X;
	protected float Y;
	protected String Direction;
	
	//PROPERTIES
	//GRAPHICS
	public TextureAtlas getWalk() {
		return this.textureWalk;
	}
	public void setWalk(TextureAtlas Walk) {
		this.textureWalk = Walk;
	}
	public Animation getAnimation() {
		return this.Animation;
	}

	public void setAnimation(Animation Animation) {
		this.Animation = Animation;
	}

	//POSITIONS
	public float getX() {
		return this.X;
	}
	public void setX(float X) {
		this.X = X;
	}
	public float getY() {
		return this.Y;
	}
	public void setY(float Y) {
		this.Y = Y;
	}
	public String getDirection() {
		return this.Direction;
	}
	public void setDirection(String Direction) {
		this.Direction = Direction;
	}

}