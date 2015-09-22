package com.bomberman.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Player extends Character {
	public static final String STAY_POSITION = "/stay.png";
	public static final String WALK_PACK = "/walk.pack";
	public static final float FRAME_DURATION = 1/3f;
	
	private Texture playerTexture;
	private boolean isMoving;
	
	public Player(String initialImageSource, float initialXPos, float initialYPos) {
		this.playerTexture = new Texture(Gdx.files.internal(initialImageSource + "right" + STAY_POSITION));
		this.textureWalk = new TextureAtlas(Gdx.files.internal(initialImageSource + "right" + WALK_PACK));
		this.Animation = new Animation(FRAME_DURATION, this.textureWalk.getRegions());
		this.setX(initialXPos);
		this.setY(initialYPos);
		this.setDirection("right");
		setMoving(false);
	}
	
	//PROPERTIES
	//MOVEMENT
	public boolean isMoving() {
		return this.isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	//GRAPHICS
	public Texture getPlayerTexture() {
		return this.playerTexture;
	}

	public void setPlayerTexture(Texture playerTexture) {
		this.playerTexture = playerTexture;
	}
	
	//METHODS
	public void update() {
		this.playerTexture = new Texture(Gdx.files.internal("images/character_walk_sheets/walk_" + this.Direction + STAY_POSITION));
		this.textureWalk = new TextureAtlas(Gdx.files.internal("images/character_walk_sheets/walk_" + this.Direction + WALK_PACK));
		this.Animation = new Animation(FRAME_DURATION, this.textureWalk.getRegions());
	}

	public void moveLeft() {
		setX(getX() - STEP_IN_PIXELS);
		setMoving(true);
	}

	public void moveRight() {
		setX(getX() + STEP_IN_PIXELS);
		setMoving(true);
	}
	
	public void moveDown() {
		setY(getY() - STEP_IN_PIXELS);
		setMoving(true);
	}
	
	public void moveUp() {
		setY(getY() + STEP_IN_PIXELS);
		setMoving(true);
	}
}
