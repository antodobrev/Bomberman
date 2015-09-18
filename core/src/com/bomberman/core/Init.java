package com.bomberman.core;

import java.util.Random;

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
		
		return brickPositions;
	}
}

