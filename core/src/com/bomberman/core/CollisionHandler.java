package com.bomberman.core;

import java.util.ArrayList;

import com.bomberman.objects.Player;
import com.bomberman.objects.Wall;

public final class CollisionHandler {
	
	private CollisionHandler(){
	}
	
	public static boolean checkInTheBox(Player hero){
		switch(hero.getPlayerDirection()){
		case "left":
			if(hero.getPlayerX() - hero.STEP_IN_PIXELS < 98){
			return false;
			}
			break;
		
		case "right":
			if(hero.getPlayerX() + 38 + hero.STEP_IN_PIXELS > 1260){
			return false;
			}
			break;
		
		case "down":
			if(hero.getPlayerY() - hero.STEP_IN_PIXELS < 75){
			return false;
			}
			break;
		
		case "up":
			if(hero.getPlayerY() + 38 + hero.STEP_IN_PIXELS > 600){
			return false;
			}
			break;
		}
		return true;
	}
	
	public static boolean checkCollision(Player hero, ArrayList<Wall> walls, Integer[][] brickPositions){

		if(!wallCheck(hero, walls) || !brickCheck(hero, brickPositions)){
			return false;
		}
		
		return true;
	}
	
	public static boolean wallCheck(Player hero, ArrayList<Wall> walls){
		for (int i = 0; i < walls.size(); i++) {
			Wall currentWall = walls.get(i);
			switch(hero.getPlayerDirection()){
			case "left":
				if(!checkLeft(hero, currentWall)){
					return false;
				}
				break;
			case "right":
				if(!checkRight(hero, currentWall)){
					return false;
				}
				break;
			case "up":
				if(!checkTop(hero, currentWall)){
					return false;
				}
				break;
			case "down":
				if(!checkBottom(hero, currentWall)){
					return false;
				}
				break;
			}
		}
		
		return true;
	}
	
	public static boolean brickCheck(Player hero, Integer[][] brickPositions){
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 29; j++) {
				if(brickPositions[i][j] > 0){
					Wall currentWall = new Wall((j * 40) + 100, (i * 40) + 80);
					switch(hero.getPlayerDirection()){
					case "left":
						if(!checkLeft(hero, currentWall)){
							return false;
						}
						break;
					case "right":
						if(!checkRight(hero, currentWall)){
							return false;
						}
						break;
					case "up":
						if(!checkTop(hero, currentWall)){
							return false;
						}
						break;
					case "down":
						if(!checkBottom(hero, currentWall)){
							return false;
						}
						break;
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean checkLeft(Player hero, Wall currentWall){
		
		if(hero.getPlayerX() - hero.STEP_IN_PIXELS < currentWall.wallRight &&
		   hero.getPlayerX() - hero.STEP_IN_PIXELS > currentWall.wallLeft &&
		   hero.getPlayerY() < currentWall.wallTop - 5 &&
		   hero.getPlayerY() > currentWall.wallBottom){
			return false;
		}
		
		if(hero.getPlayerX() - hero.STEP_IN_PIXELS < currentWall.wallRight &&
		   hero.getPlayerX() - hero.STEP_IN_PIXELS > currentWall.wallLeft &&
		   hero.getPlayerY() + 35 < currentWall.wallTop &&
		   hero.getPlayerY() + 35 > currentWall.wallBottom + 5){
			return false;
		}
		
		return true;
	}
	
	public static boolean checkRight(Player hero, Wall currentWall){

		if(hero.getPlayerX() + 35 + hero.STEP_IN_PIXELS > currentWall.wallLeft &&
		   hero.getPlayerX() + 35 + hero.STEP_IN_PIXELS < currentWall.wallRight &&
		   hero.getPlayerY() < currentWall.wallTop - 5 &&
		   hero.getPlayerY() > currentWall.wallBottom){
			return false;
		}
		
		if(hero.getPlayerX() + 35 + hero.STEP_IN_PIXELS > currentWall.wallLeft &&
		   hero.getPlayerX() + 35 + hero.STEP_IN_PIXELS < currentWall.wallRight &&
		   hero.getPlayerY() + 35 < currentWall.wallTop &&
		   hero.getPlayerY() + 35 > currentWall.wallBottom + 5){
			return false;
		}
		
		return true;
	}

	public static boolean checkBottom(Player hero, Wall currentWall){
	
		if(hero.getPlayerY() + 5 - hero.STEP_IN_PIXELS < currentWall.wallTop &&
		   hero.getPlayerY() + 5 - hero.STEP_IN_PIXELS > currentWall.wallBottom &&
		   hero.getPlayerX() < currentWall.wallRight &&
		   hero.getPlayerX() > currentWall.wallLeft - 5){
			return false;
		}
		
		if(hero.getPlayerY() - hero.STEP_IN_PIXELS < currentWall.wallTop &&
		   hero.getPlayerY() - hero.STEP_IN_PIXELS > currentWall.wallBottom &&
		   hero.getPlayerX() + 35 < currentWall.wallRight + 5 &&
		   hero.getPlayerX() + 35 > currentWall.wallLeft){
			return false;
		}
		
		return true;
	}
	
	public static boolean checkTop(Player hero, Wall currentWall){

		if(hero.getPlayerY() + 35 + hero.STEP_IN_PIXELS < currentWall.wallTop &&
		   hero.getPlayerY() + 35 + hero.STEP_IN_PIXELS > currentWall.wallBottom &&
		   hero.getPlayerX() < currentWall.wallRight &&
		   hero.getPlayerX() > currentWall.wallLeft - 5){
			return false;
		}
		
		if(hero.getPlayerY() + 35 + hero.STEP_IN_PIXELS < currentWall.wallTop &&
		   hero.getPlayerY() + 35 + hero.STEP_IN_PIXELS > currentWall.wallBottom &&
		   hero.getPlayerX() + 35 < currentWall.wallRight + 5 &&
		   hero.getPlayerX() + 35 > currentWall.wallLeft){
			return false;
		}
		
		return true;
	}	
}