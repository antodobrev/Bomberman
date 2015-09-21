package com.bomberman.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
		return this.hasExploded;
	}

	public void setHasExploded(boolean hasExploded) {
		this.hasExploded = hasExploded;
	}

	//PROPERTIES
	
	//COORDINATES
	public float getBombX() {
		return this.bombX;
	}

	public void setBombX(float bombX) {
		this.bombX = bombX;
	}

	public float getBombY() {
		return this.bombY;
	}

	public void setBombY(float bombY) {
		this.bombY = bombY;
	}

	//GRAPHICS
	public Texture getBombTexture() {
		return this.bombTexture;
	}

	public void setBombTexture(Texture bombTexture) {
		this.bombTexture = bombTexture;
	}

	public Sprite getBombSprite() {
		return this.bombSprite;
	}

	public void setBombSprite(Sprite bombSprite) {
		this.bombSprite = bombSprite;
	}
	
	//METHODS
	public boolean isToExplode(float timeElapsed) {
		if (timeElapsed - this.timeInitialized >= 3f) {
			return true;
		}
		else {
			return false;
		}
	}

	public void update(float elapsedTime, SpriteBatch batch){
		if (!this.hasExploded()) {
			if (this.isToExplode(elapsedTime)) {
				this.setHasExploded(true);
			}
			this.getBombSprite().draw(batch);
		}
	}
}
