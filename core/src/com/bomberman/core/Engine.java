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
import com.bomberman.objects.Enemy;
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
	private ArrayList<Bomb> bombs;
	private int maxBombs = 1;
	private float putBombInterval = 1f;
	private float lastBombInitialized;
	
	//ENEMY DATA
	private ArrayList<Enemy> enemies;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		wall = new Texture("images/wall.png");
		background = new Texture("images/background.jpg");
		brickwall = new Texture("images/brickwall.jpg");
		font = new BitmapFont();
		
		hero = new Player("images/character_walk_sheets/walk_", 100, 555);
		bombs = new ArrayList<Bomb>();
		enemies = new ArrayList<Enemy>();
		
		
		brickPositions = new Integer[13][29];
		Init.brickInit(brickPositions);
		walls = new ArrayList<Wall>();
		walls = Init.wallInit(walls);
		enemies = Init.enemyInit(enemies, brickPositions);
	}

	public void update(){
		CollisionHandler.returnInBox(hero);
		this.handleUserInput();
		this.hero.update();
		if(!CollisionHandler.enemyCheck(hero, enemies)){
			System.exit(0);
		}
		
		for(Enemy e : enemies){
			e.update(walls, brickPositions, bombs);
		}
		
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
			batch.draw(hero.getAnimation().getKeyFrame(elapsedTime, true), hero.getX(), hero.getY());
		}
		else {
			batch.draw(hero.getPlayerTexture(), hero.getX(), hero.getY());
		}
		
		Renderer.enemyRender(batch, elapsedTime, enemies);
		
		for (Bomb bomb : bombs) {
			bomb.update(elapsedTime, batch);
			if(bomb.hasExploded()){
				bomb.explode(brickPositions, enemies, hero);
			}
		}
		
		for (int i = 0; i < bombs.size(); i++) {
			if(bombs.get(i).hasExploded()){
				bombs.remove(i);
			}
		}
		
		batch.end();
	}

	private void handleUserInput() {
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			hero.setDirection("left");
			if(CollisionHandler.checkInTheBox(hero) && CollisionHandler.checkCollision(hero, walls, brickPositions, bombs)){
				hero.moveLeft();
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			hero.setDirection("right");
			if(CollisionHandler.checkInTheBox(hero) && CollisionHandler.checkCollision(hero, walls, brickPositions, bombs)){
				hero.moveRight();
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			hero.setDirection("down");
			if(CollisionHandler.checkInTheBox(hero) && CollisionHandler.checkCollision(hero, walls, brickPositions, bombs)){
				hero.moveDown();
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			hero.setDirection("up");
			if(CollisionHandler.checkInTheBox(hero) && CollisionHandler.checkCollision(hero, walls, brickPositions, bombs)){
				hero.moveUp();
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && elapsedTime - lastBombInitialized > putBombInterval) {
			// TODO drop bomb
			this.bombs.add(new Bomb("images/Bomb.png", hero.getX(), hero.getY(), elapsedTime));
			lastBombInitialized = this.elapsedTime;
		}
		
		if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
		   !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
		   !Gdx.input.isKeyPressed(Input.Keys.DOWN) &&
		   !Gdx.input.isKeyPressed(Input.Keys.UP)){
			hero.setMoving(false);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.B)){
			for (int i = 0; i < 14; i++) {
				brickPositions[12][i] = 0;
			}
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
