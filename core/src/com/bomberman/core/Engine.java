package com.bomberman.core;

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

public class Engine extends ApplicationAdapter {
	private SpriteBatch batch;
	
	
	//MAP ELEMENTS
	private Texture wall;
	private Texture background;
	private Texture brickwall;
	private Integer[][] brickPositions;
	
	//PLAYER ELEMENTS
	private Texture player;
	private float playerX;
	private float playerY;
	private boolean isMoving;
	private String playerDirection;
	private TextureAtlas playerWalkDown;
	private Animation animation;
	private float elapsedTime = 0;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		wall = new Texture("images/wall.png");
		background = new Texture("images/background.jpg");
		brickwall = new Texture("images/brickwall.jpg");
		playerDirection = "right";
		player = new Texture(Gdx.files.internal("images/character_walk_sheets/walk_" + playerDirection + "/stay.png"));
		playerWalkDown = new TextureAtlas(Gdx.files.internal("images/character_walk_sheets/walk_" + playerDirection + "/walk.pack"));
		animation = new Animation(1/3f, playerWalkDown.getRegions());
		
		playerX = 100;
		playerY = 515;
		isMoving = false;
		
		brickPositions = new Integer[13][29];
		Init.brickInit(brickPositions);
	}

	public void update(){
		player = new Texture(Gdx.files.internal("images/character_walk_sheets/walk_" + playerDirection + "/stay.png"));
		playerWalkDown = new TextureAtlas(Gdx.files.internal("images/character_walk_sheets/walk_" + playerDirection + "/walk.pack"));
		animation = new Animation(1/3f, playerWalkDown.getRegions());
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
		if(isMoving){
			batch.draw(animation.getKeyFrame(elapsedTime, true), playerX , playerY);
		}
		else{
			batch.draw(player, playerX, playerY);
		}
		renderMap();
		batch.end();
	}

	private void handleUserInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			playerX-= 1.75f;
			playerDirection = "left";
			isMoving = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			playerX+= 1.75f;
			playerDirection = "right";
			isMoving = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			playerY-= 1.75f;
			playerDirection = "down";
			isMoving = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			playerY+= 1.75f;
			playerDirection = "up";
			isMoving = true;
		}
		
		if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
		   !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
		   !Gdx.input.isKeyPressed(Input.Keys.DOWN) &&
		   !Gdx.input.isKeyPressed(Input.Keys.UP)){
			isMoving = false;
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
