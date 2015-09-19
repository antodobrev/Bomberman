package com.bomberman.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint;
import com.bomberman.core.characters.Player;

public class Engine extends ApplicationAdapter {
	private SpriteBatch batch;
	private Player hero;
	private List<Bomb> bombs;
	
	
	//MAP ELEMENTS
	private Texture wall;
	private Texture background;
	private Texture brickwall;
	private Integer[][] brickPositions;
	
	//PLAYER ELEMENTS
	private String playerDirection;
	private float elapsedTime = 0;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		wall = new Texture("images/wall.png");
		background = new Texture("images/background.jpg");
		brickwall = new Texture("images/brickwall.jpg");
		
		playerDirection = "right";
		
		hero = new Player("images/character_walk_sheets/walk_", playerDirection, 100, 515);
		bombs = new ArrayList<>();
		
		brickPositions = new Integer[13][29];
		Init.brickInit(brickPositions);
	}

	public void update(){
		this.hero.update(playerDirection);
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update();
		handleUserInput();
		
		batch.begin();
		batch.draw(background, 0, 0);
		elapsedTime += Gdx.graphics.getDeltaTime();
		
		if (hero.isMoving()) {
			batch.draw(hero.getPlayerAnimation().getKeyFrame(elapsedTime, true), hero.getPlayerX(), hero.getPlayerY());
		}
		else {
			batch.draw(hero.getPlayerTexture(), hero.getPlayerX(), hero.getPlayerY());
		}
		for (Bomb bomb : bombs) {
			bomb.getBombSprite().draw(batch);
		}
		renderMap();
		batch.end();
	}

	private void handleUserInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			playerDirection = "left";
			hero.movedLeft();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			playerDirection = "right";
			hero.movedRight();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			playerDirection = "down";
			hero.movedDown();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			playerDirection = "up";
			hero.movedUp();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			// TODO drop bomb
			this.bombs.add(new Bomb("images/Bomb.png", hero.getPlayerX(), hero.getPlayerY()));
		}
		
		if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
		   !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
		   !Gdx.input.isKeyPressed(Input.Keys.DOWN) &&
		   !Gdx.input.isKeyPressed(Input.Keys.UP)){
			hero.setMoving(false);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			System.exit(0);
		}
	}
	
	public void renderMap(){
		Renderer.wallRender(batch, wall);
		Renderer.brickRender(batch, brickwall, brickPositions);
	}
}
