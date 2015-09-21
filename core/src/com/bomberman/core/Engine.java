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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint;
import com.bomberman.objects.Bomb;
import com.bomberman.objects.Player;
import com.bomberman.objects.Wall;

public class Engine extends ApplicationAdapter {
	private SpriteBatch batch;
	
	//MAP ELEMENTS
	private Texture wall;
	private Texture background;
	private Texture brickwall;
	private Integer[][] brickPositions;
	private ArrayList<Wall> walls;
	private BitmapFont font;
	
	//PLAYER ELEMENTS
	private Player hero;
	private float elapsedTime = 0;
	
	//BOMB ELEMENTS
	private List<Bomb> bombs;
	private int maxBombs = 1;
	private float putBombInterval = 1f;
	private float lastBombInitialized;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		wall = new Texture("images/wall.png");
		background = new Texture("images/background.jpg");
		brickwall = new Texture("images/brickwall.jpg");
		font = new BitmapFont();
		
		hero = new Player("images/character_walk_sheets/walk_", 100, 555);
		bombs = new ArrayList<>();
		
		brickPositions = new Integer[13][29];
		Init.brickInit(brickPositions);
		walls = new ArrayList<Wall>();
		walls = Init.wallInit(walls);
	}

	public void update(){
		this.handleUserInput();
		this.hero.update();
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.update();
		
		batch.begin();
		batch.draw(background, 0, 0);
		elapsedTime += Gdx.graphics.getDeltaTime();
		renderMap();
		if (hero.isMoving()) {
			batch.draw(hero.getPlayerAnimation().getKeyFrame(elapsedTime, true), hero.getPlayerX(), hero.getPlayerY());
		}
		else {
			batch.draw(hero.getPlayerTexture(), hero.getPlayerX(), hero.getPlayerY());
		}
		for (Bomb bomb : bombs) {
			bomb.update(elapsedTime, batch);
		}
		String coordinateX = String.format("Player X - %.1f", hero.getPlayerX());
		String coordinateY = String.format("Player Y - %.1f", hero.getPlayerY());
		font.draw(batch, coordinateX, 100, 700);
		font.draw(batch, coordinateY, 100, 680);
		batch.end();
	}

	private void handleUserInput() {
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			hero.setPlayerDirection("left");
			if(CollisionHandler.checkInTheBox(hero) && CollisionHandler.checkCollision(hero, walls, brickPositions)){
				hero.moveLeft();
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			hero.setPlayerDirection("right");
			if(CollisionHandler.checkInTheBox(hero) && CollisionHandler.checkCollision(hero, walls, brickPositions)){
				hero.moveRight();
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			hero.setPlayerDirection("down");
			if(CollisionHandler.checkInTheBox(hero) && CollisionHandler.checkCollision(hero, walls, brickPositions)){
				hero.moveDown();
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			hero.setPlayerDirection("up");
			if(CollisionHandler.checkInTheBox(hero) && CollisionHandler.checkCollision(hero, walls, brickPositions)){
				hero.moveUp();
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && elapsedTime - lastBombInitialized > putBombInterval) {
			// TODO drop bomb
			this.bombs.add(new Bomb("images/Bomb.png", hero.getPlayerX(), hero.getPlayerY(), elapsedTime));
			lastBombInitialized = this.elapsedTime;
		}
		
		if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
		   !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
		   !Gdx.input.isKeyPressed(Input.Keys.DOWN) &&
		   !Gdx.input.isKeyPressed(Input.Keys.UP)){
			hero.setMoving(false);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			this.dispose();
			System.exit(0);
		}
	}
	
	public void renderMap(){
		Renderer.wallRender(batch, wall);
		Renderer.brickRender(batch, brickwall, brickPositions);
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
