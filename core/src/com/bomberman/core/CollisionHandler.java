package com.bomberman.core;

import java.util.ArrayList;

import com.bomberman.objects.Character;
import com.bomberman.objects.Wall;

public final class CollisionHandler {
	
	private CollisionHandler(){
	}
	
	public static boolean checkInTheBox(Character character){
		switch(character.getDirection()){
		case "left":
			if(character.getX() - character.STEP_IN_PIXELS < 98){
			return false;
			}
			break;
		
		case "right":
			if(character.getX() + 38 + character.STEP_IN_PIXELS > 1260){
			return false;
			}
			break;
		
		case "down":
			if(character.getY() - character.STEP_IN_PIXELS < 75){
			return false;
			}
			break;
		
		case "up":
			if(character.getY() + 38 + character.STEP_IN_PIXELS > 600){
			return false;
			}
			break;
		}
		return true;
	}
	
	public static boolean checkCollision(Character character, ArrayList<Wall> walls, Integer[][] brickPositions){

		if(!wallCheck(character, walls) || !brickCheck(character, brickPositions)){
			return false;
		}
		
		return true;
	}
	
	public static boolean wallCheck(Character character, ArrayList<Wall> walls){
		for (int i = 0; i < walls.size(); i++) {
			Wall currentWall = walls.get(i);
			switch(character.getDirection()){
			case "left":
				if(!checkLeft(character, currentWall)){
					return false;
				}
				break;
			case "right":
				if(!checkRight(character, currentWall)){
					return false;
				}
				break;
			case "up":
				if(!checkTop(character, currentWall)){
					return false;
				}
				break;
			case "down":
				if(!checkBottom(character, currentWall)){
					return false;
				}
				break;
			}
		}
		
		return true;
	}
	
	public static boolean brickCheck(Character character, Integer[][] brickPositions){
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 29; j++) {
				if(brickPositions[i][j] > 0){
					Wall currentWall = new Wall((j * 40) + 100, (i * 40) + 80);
					switch(character.getDirection()){
					case "left":
						if(!checkLeft(character, currentWall)){
							return false;
						}
						break;
					case "right":
						if(!checkRight(character, currentWall)){
							return false;
						}
						break;
					case "up":
						if(!checkTop(character, currentWall)){
							return false;
						}
						break;
					case "down":
						if(!checkBottom(character, currentWall)){
							return false;
						}
						break;
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean checkLeft(Character character, Wall currentWall){
		
		if(character.getX() - character.STEP_IN_PIXELS < currentWall.wallRight &&
		   character.getX() - character.STEP_IN_PIXELS > currentWall.wallLeft &&
		   character.getY() < currentWall.wallTop - 5 &&
		   character.getY() > currentWall.wallBottom){
			return false;
		}
		
		if(character.getX() - character.STEP_IN_PIXELS < currentWall.wallRight &&
		   character.getX() - character.STEP_IN_PIXELS > currentWall.wallLeft &&
		   character.getY() + 35 < currentWall.wallTop &&
		   character.getY() + 35 > currentWall.wallBottom + 5){
			return false;
		}
		
		return true;
	}
	
	public static boolean checkRight(Character character, Wall currentWall){

		if(character.getX() + 35 + character.STEP_IN_PIXELS > currentWall.wallLeft &&
		   character.getX() + 35 + character.STEP_IN_PIXELS < currentWall.wallRight &&
		   character.getY() < currentWall.wallTop - 5 &&
		   character.getY() > currentWall.wallBottom){
			return false;
		}
		
		if(character.getX() + 35 + character.STEP_IN_PIXELS > currentWall.wallLeft &&
		   character.getX() + 35 + character.STEP_IN_PIXELS < currentWall.wallRight &&
		   character.getY() + 35 < currentWall.wallTop &&
		   character.getY() + 35 > currentWall.wallBottom + 5){
			return false;
		}
		
		return true;
	}

	public static boolean checkBottom(Character character, Wall currentWall){
	
		if(character.getY() + 5 - character.STEP_IN_PIXELS < currentWall.wallTop &&
		   character.getY() + 5 - character.STEP_IN_PIXELS > currentWall.wallBottom &&
		   character.getX() < currentWall.wallRight &&
		   character.getX() > currentWall.wallLeft - 5){
			return false;
		}
		
		if(character.getY() - character.STEP_IN_PIXELS < currentWall.wallTop &&
		   character.getY() - character.STEP_IN_PIXELS > currentWall.wallBottom &&
		   character.getX() + 35 < currentWall.wallRight + 5 &&
		   character.getX() + 35 > currentWall.wallLeft){
			return false;
		}
		
		return true;
	}
	
	public static boolean checkTop(Character character, Wall currentWall){

		if(character.getY() + 35 + character.STEP_IN_PIXELS < currentWall.wallTop &&
		   character.getY() + 35 + character.STEP_IN_PIXELS > currentWall.wallBottom &&
		   character.getX() < currentWall.wallRight &&
		   character.getX() > currentWall.wallLeft - 5){
			return false;
		}
		
		if(character.getY() + 35 + character.STEP_IN_PIXELS < currentWall.wallTop &&
		   character.getY() + 35 + character.STEP_IN_PIXELS > currentWall.wallBottom &&
		   character.getX() + 35 < currentWall.wallRight + 5 &&
		   character.getX() + 35 > currentWall.wallLeft){
			return false;
		}
		
		return true;
	}	
}