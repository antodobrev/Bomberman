package com.bomberman.core.characters;

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
	private TextureAtlas playerWalkDown;
	private Animation playerAnimation;
	
	public Player(String initialImageSource, String direction, float initialXPos, float initialYPos) {
		this.playerTexture = new Texture(Gdx.files.internal(initialImageSource + direction + STAY_POSITION));
		this.playerWalkDown = new TextureAtlas(Gdx.files.internal(initialImageSource + direction + WALK_PACK));
		this.playerAnimation = new Animation(FRAME_DURATION, this.playerWalkDown.getRegions());
		setPlayerX(initialXPos);
		setPlayerY(initialYPos);
		setMoving(false);
	}
	
	public float getPlayerX() {
		return playerX;
	}

	public void setPlayerX(float playerX) {
		this.playerX = playerX;
	}

	public float getPlayerY() {
		return playerY;
	}

	public void setPlayerY(float playerY) {
		this.playerY = playerY;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public Texture getPlayerTexture() {
		return playerTexture;
	}

	public void setPlayerTexture(Texture playerTexture) {
		this.playerTexture = playerTexture;
	}

	
	public Animation getPlayerAnimation() {
		return playerAnimation;
	}

	public void setPlayerAnimation(Animation playerAnimation) {
		this.playerAnimation = playerAnimation;
	}

	public void update(String direction) {
		this.playerTexture = new Texture(Gdx.files.internal("images/character_walk_sheets/walk_" + direction + STAY_POSITION));
		this.playerWalkDown = new TextureAtlas(Gdx.files.internal("images/character_walk_sheets/walk_" + direction + WALK_PACK));
		this.playerAnimation = new Animation(FRAME_DURATION, this.playerWalkDown.getRegions());
	}

	public void movedLeft() {
		setPlayerX(getPlayerX() - STEP_IN_PIXELS);
		setMoving(true);
	}

	public void movedRight() {
		setPlayerX(getPlayerX() + STEP_IN_PIXELS);
		setMoving(true);
	}
	
	public void movedDown() {
		setPlayerY(getPlayerY() - STEP_IN_PIXELS);
		setMoving(true);
	}
	
	public void movedUp() {
		setPlayerY(getPlayerY() + STEP_IN_PIXELS);
		setMoving(true);
	}
}
