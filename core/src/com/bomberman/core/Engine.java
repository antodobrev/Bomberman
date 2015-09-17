package com.bomberman.core;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint;

public class Engine extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture wall;
	private Texture background;
	private Texture brickwall;
	
	private Integer[][] brickPositions;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		wall = new Texture("images/wall.png");
		background = new Texture("images/background.jpg");
		brickwall = new Texture("images/brickwall.jpg");
		
		brickPositions = new Integer[13][29];
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 29; j++) {
				brickPositions[i][j] = 0;
			}
		}
		
		//INITIALISING THE POSITIONS OF BRICKS
		Random rndSpawner = new Random();
		
		for (int i = 0; i < 13; i++) {
			//NO BRICKS MUST BE SPAWNED WHERE THE PLAYER IS
			if(i >= 11){
				if(i%2 == 0){
					//IF ITS A BLANK LINE
					for (int j = 2; j < 29; j++) {
						int shouldSpawn = rndSpawner.nextInt(3);
						if(shouldSpawn > 0){
							brickPositions[i][j] = 1;
						}
					}
				}
				else{
					//IF ITS A LINE WITH WALLS
					for (int j = 2; j < 29; j+=2) {
						int shouldSpawn = rndSpawner.nextInt(3);
						if(shouldSpawn > 0){
							brickPositions[i][j] = 1;
						}
					}
				}
				continue;
			}
			
			if(i%2 == 0){
				//IF ITS A BLANK LINE
				for (int j = 0; j < 29; j++) {
					int shouldSpawn = rndSpawner.nextInt(3);
					if(shouldSpawn > 0){
						brickPositions[i][j] = 1;
					}
				}
			}
			else{
				//IF ITS A LINE WITH WALLS
				for (int j = 0; j < 29; j+=2) {
					int shouldSpawn = rndSpawner.nextInt(3);
					if(shouldSpawn > 0){
						brickPositions[i][j] = 1;
					}
				}
			}
		}
		//END INITIALISE OF BRICKS
	}
	
	public void keyCheck(){
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			System.exit(0);
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		keyCheck();
		batch.begin();
		batch.draw(background, 0, 0);
		renderMap();
		batch.end();
	}
	
	public void renderMap(){
		//BORDER RENDERING
		for (int i = 0; i < 15; i++) {
			batch.draw(wall, 60, i*40);
			batch.draw(wall, 1260, i*40);
		}
		
		for (int i = 0; i < 29; i++) {
			batch.draw(wall, 100 + i*40, 0);
			batch.draw(wall, 100 + i*40, 560);
		}
		
		//INNER WALL RENDERING
		for (int i = 0; i < 600; i+= 80) {
			for (int j = 140; j < 1200; j+= 80) {
				batch.draw(wall, j , i);
			}
		}
		
		//BRICK WALL RENDERING
		for (int i = 0; i < 13; i++) {
			if(i%2 == 0){
				//IF ITS A BLANK LINE
				for (int j = 0; j < 29; j++) {
					//[i - 40 / 40][j - 100 / 40]
					if(brickPositions[i][j] > 0){
						batch.draw(brickwall, (j * 40) + 100, (i * 40) + 40);
					}
				}
			}
			else{
				//IF ITS A LINE WITH WALLS
				for (int j = 0; j < 28; j+=2) {
					//[i - 40 / 40][j - 100 / 40]
					if(brickPositions[i][j] > 0){
						batch.draw(brickwall, (j * 40) + 100, (i * 40) + 40);
					}
				}
			}
		}
	}
}
