package com.bomberman.core;

import java.util.ArrayList;
import java.util.Random;

import com.bomberman.objects.Enemy;
import com.bomberman.objects.Wall;

final class Init {
	
	private Init(){	
	}
	
	public static Integer[][] brickInit(Integer[][] brickPositions){
		
		//INITIALISING THE MATRIX ITSELF
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
						int shouldSpawn = rndSpawner.nextInt(10);
						if(shouldSpawn > 0 && shouldSpawn < 6){
							brickPositions[i][j] = 1;
						}
					}
				}
				else{
					//IF ITS A LINE WITH WALLS
					for (int j = 2; j < 29; j+=2) {
						int shouldSpawn = rndSpawner.nextInt(10);
						if(shouldSpawn > 0 && shouldSpawn < 6){
							brickPositions[i][j] = 1;
						}
					}
				}
				continue;
			}
			
			if(i%2 == 0){
				//IF ITS A BLANK LINE
				for (int j = 0; j < 29; j++) {
					int shouldSpawn = rndSpawner.nextInt(10);
					if(shouldSpawn > 0 && shouldSpawn < 6){
						brickPositions[i][j] = 1;
					}
				}
			}
			else{
				//IF ITS A LINE WITH WALLS
				for (int j = 0; j < 29; j+=2) {
					int shouldSpawn = rndSpawner.nextInt(10);
					if(shouldSpawn > 0 && shouldSpawn < 6){
						brickPositions[i][j] = 1;
					}
				}
			}
		}
		//END INITIALISE OF BRICKS
		
		return brickPositions;
	}

	public static ArrayList<Wall> wallInit(ArrayList<Wall> wallPositions){
		//BORDER RENDERING
		for (int i = 0; i < 15; i++) {
			wallPositions.add(new Wall(60, 40 + (i * 40)));
			wallPositions.add(new Wall(1260, 40 + (i * 40)));
		}
		
		for (int i = 0; i < 29; i++) {
			wallPositions.add(new Wall(100 + i * 40, 40));
			wallPositions.add(new Wall(100 + i * 40, 600));
		}
		
		//INNER WALL RENDERING
		for (int i = 120; i < 600; i+= 80) {
			for (int j = 140; j < 1200; j+= 80) {
				wallPositions.add(new Wall(j, i));
			}
		}
		
		return wallPositions;
	}

	public static ArrayList<Enemy> enemyInit(ArrayList<Enemy> enemies, Integer[][] brickPositions){
		
		Random rndSpawner = new Random();
		
		for (int i = 0; i < 13; i++) {
			if(i % 2 == 0){
				for (int j = 2; j < 29; j++) {
					if(brickPositions[i][j] != 1){
						int shouldSpawn = rndSpawner.nextInt(5);
						if(shouldSpawn == 0){
							enemies.add(new Enemy(((j * 40) + 100), (i * 40) + 80));
						}
					}
				}
			}
			else{
				for (int j = 2; j < 29; j+=2) {
					if(brickPositions[i][j] != 1){
						int shouldSpawn = rndSpawner.nextInt(5);
						if(shouldSpawn == 0){
							enemies.add(new Enemy(((j * 40) + 100), (i * 40) + 80));
						}
					}
				}
			}
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).getX() < 500 && enemies.get(i).getY() > 300){
				enemies.remove(enemies.get(i));
			}
		}
		
		return enemies;
	}
}

