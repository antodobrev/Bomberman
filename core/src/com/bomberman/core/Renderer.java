package com.bomberman.core;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.bomberman.objects.Enemy;

public final class Renderer {
	
	private Renderer(){	
	}
	
	public static void wallRender(SpriteBatch batch, Texture wall){
		//BORDER RENDERING
		for (int i = 0; i < 15; i++) {
			batch.draw(wall, 60, 40 + (i * 40));
			batch.draw(wall, 1260, 40 + (i * 40));
		}
		
		for (int i = 0; i < 29; i++) {
			batch.draw(wall, 100 + i * 40, 40);
			batch.draw(wall, 100 + i * 40, 600);
		}
		
		//INNER WALL RENDERING
		for (int i = 120; i < 600; i+= 80) {
			for (int j = 140; j < 1200; j+= 80) {
				batch.draw(wall, j , i);
			}
		}
	}
	
	public static void brickRender(SpriteBatch batch, Texture brick, Integer[][] brickPositions){
		
		//BRICK WALL RENDERING
		for (int i = 0; i < 13; i++) {
			if(i%2 == 0){
				//IF ITS A BLANK LINE
				for (int j = 0; j < 29; j++) {
					//[(i / 40) - 80][(j / 40) - 100]
					if(brickPositions[i][j] > 0 && brickPositions[i][j] < 10){
						batch.draw(brick, (j * 40) + 100, (i * 40) + 80);
					}
				}
			}
			else{
				//IF ITS A LINE WITH WALLS
				for (int j = 0; j < 28; j+=2) {
					//[(i / 40) - 80][(j / 40) - 100]
					if(brickPositions[i][j] > 0 && brickPositions[i][j] < 10){
						batch.draw(brick, (j * 40) + 100, (i * 40) + 80);
					}
				}
			}
		}
	}

	public static void enemyRender(SpriteBatch batch, float elapsedTime, ArrayList<Enemy> enemies){
	
		for(Enemy e : enemies){
			batch.draw(e.getAnimation().getKeyFrame(elapsedTime, true), e.getX(), e.getY());
		}
	}
}
