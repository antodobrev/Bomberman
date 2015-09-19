package com.bomberman.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bomb {
	private Texture bombTexture;
	private Sprite bombSprite;
	private float bombX;
	private float bombY;
	
	public Bomb(String imageSource, float posX, float posY) {
		this.bombTexture = new Texture(Gdx.files.internal(imageSource));
		this.bombSprite = new Sprite(this.getBombTexture());
		this.bombSprite.setPosition(posX, posY);
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
	
	
}
