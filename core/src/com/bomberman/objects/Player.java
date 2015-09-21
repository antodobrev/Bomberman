package com.bomberman.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Player {
	public static final String STAY_POSITION = "/stay.png";
	public static final String WALK_PACK = "/walk.pack";
	public static final float FRAME_DURATION = 1/3f;
	public static final float STEP_IN_PIXELS = 1.75f;
	
	private Texture playerTexture;
	private float playerX;
	private float playerY;
	private boolean isMoving;
	private String playerDirection;
	private TextureAtlas playerWalk;
	private Animation playerAnimation;
	
	public Player(String initialImageSource, float initialXPos, float initialYPos) {
		this.playerTexture = new Texture(Gdx.files.internal(initialImageSource + "right" + STAY_POSITION));
		this.playerWalk = new TextureAtlas(Gdx.files.internal(initialImageSource + "right" + WALK_PACK));
		this.playerAnimation = new Animation(FRAME_DURATION, this.playerWalk.getRegions());
		setPlayerX(initialXPos);
		setPlayerY(initialYPos);
		this.setPlayerDirection("right");
		setMoving(false);
	}
	
	//PROPERTIES
	
	//COORDINATES
	public float getPlayerX() {
		return this.playerX;
	}

	public void setPlayerX(float playerX) {
		this.playerX = playerX;
	}

	public float getPlayerY() {
		return this.playerY;
	}

	public void setPlayerY(float playerY) {
		this.playerY = playerY;
	}

	//DIRECTION
	public String getPlayerDirection() {
		return this.playerDirection;
	}

	public void setPlayerDirection(String playerDirection) {
		this.playerDirection = playerDirection;
	}

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

	
	public Animation getPlayerAnimation() {
		return this.playerAnimation;
	}

	public void setPlayerAnimation(Animation playerAnimation) {
		this.playerAnimation = playerAnimation;
	}
	
	//METHODS
	public void update() {
		this.playerTexture = new Texture(Gdx.files.internal("images/character_walk_sheets/walk_" + this.playerDirection + STAY_POSITION));
		this.playerWalk = new TextureAtlas(Gdx.files.internal("images/character_walk_sheets/walk_" + this.playerDirection + WALK_PACK));
		this.playerAnimation = new Animation(FRAME_DURATION, this.playerWalk.getRegions());
	}

	public void moveLeft() {
		setPlayerX(getPlayerX() - STEP_IN_PIXELS);
		setMoving(true);
	}

	public void moveRight() {
		setPlayerX(getPlayerX() + STEP_IN_PIXELS);
		setMoving(true);
	}
	
	public void moveDown() {
		setPlayerY(getPlayerY() - STEP_IN_PIXELS);
		setMoving(true);
	}
	
	public void moveUp() {
		setPlayerY(getPlayerY() + STEP_IN_PIXELS);
		setMoving(true);
	}
}
