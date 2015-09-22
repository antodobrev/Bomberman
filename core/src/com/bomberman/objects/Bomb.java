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
		this.setBombX(posX + 20);
		this.setBombY(posY + 20);
		float x = (int)((this.getBombX() - 100) / 40) * 40 + 100;
		float y = (int)((this.getBombY() - 80) / 40) * 40 + 80;
		this.bombSprite.setPosition(x, y);
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
	
	public void explode(Integer[][] brickPositions){
		int positionX = ((int)(this.bombY - 80)) / 40;
		int positionY = ((int)(this.bombX - 100)) / 40;
	
		
		
		int leftBrickX = positionX == 0 ? 1 : positionX;
		
		int leftBrickY = positionY == 0 ? 1 : positionY;
		
		int rightBrickX = positionX == 12 ? 11 : positionX;
		
		int rightBrickY = positionY == 12 ? 11 : positionY;
		

		
		brickPositions[leftBrickX - 1][positionY] = 0;
		brickPositions[rightBrickX + 1][positionY] = 0;
		brickPositions[positionX][leftBrickY - 1] = 0;
		brickPositions[positionX][rightBrickY + 1] = 0;
	}
}
