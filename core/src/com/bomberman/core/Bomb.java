package com.bomberman.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bomb {
	private Texture bombTexture;
	private Sprite bombSprite;
	private float bombX;
	private float bombY;
	private float timeInitialized;
	private boolean hasExploded;
	
	public Bomb(String imageSource, float posX, float posY, float timeInitialized) {
		this.bombTexture = new Texture(Gdx.files.internal(imageSource));
		this.bombSprite = new Sprite(this.getBombTexture());
		this.bombSprite.setPosition(posX, posY);
		this.timeInitialized = timeInitialized;
		this.hasExploded = false;
	}

	public boolean hasExploded() {
		return hasExploded;
	}

	public Texture getBombTexture() {
		return bombTexture;
	}

	public void setBombTexture(Texture bombTexture) {
		this.bombTexture = bombTexture;
	}

	public float getBombX() {
		return bombX;
	}

	public void setBombX(float bombX) {
		this.bombX = bombX;
	}

	public float getBombY() {
		return bombY;
	}

	public void setBombY(float bombY) {
		this.bombY = bombY;
	}

	public Sprite getBombSprite() {
		return bombSprite;
	}

	public void setBombSprite(Sprite bombSprite) {
		this.bombSprite = bombSprite;
	}
	
	public void isToExplode(float timeElapsed) {
		if (timeElapsed - this.timeInitialized >= 3f) {
			this.hasExploded = true;
		}
		else {
			this.hasExploded = false;
		}
	}
}
