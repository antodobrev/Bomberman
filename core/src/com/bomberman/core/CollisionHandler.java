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
	
	public static boolean checkLeft(Player hero, ArrayList<Wall> walls){
		
		for (int i = 0; i < walls.size(); i++) {
			Wall currentWall = walls.get(i);
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
			}}
		return true;
	}
	
	public static boolean checkRight(Player hero, ArrayList<Wall> walls){
		
		for (int i = 0; i < walls.size(); i++) {
			Wall currentWall = walls.get(i);
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
			}}
		return true;
	}

	public static boolean checkBottom(Player hero, ArrayList<Wall> walls){
	
		for (int i = 0; i < walls.size(); i++) {
			Wall currentWall = walls.get(i);
			if(hero.getPlayerY() - hero.STEP_IN_PIXELS < currentWall.wallTop &&
			   hero.getPlayerY() - hero.STEP_IN_PIXELS > currentWall.wallBottom &&
			   hero.getPlayerX() < currentWall.wallRight &&
			   hero.getPlayerX() > currentWall.wallLeft - 5){
				return false;
			}
			
			if(hero.getPlayerY() - hero.STEP_IN_PIXELS < currentWall.wallTop &&
			   hero.getPlayerY() - hero.STEP_IN_PIXELS > currentWall.wallBottom &&
			   hero.getPlayerX() + 35 < currentWall.wallRight + 5 &&
			   hero.getPlayerX() + 35 > currentWall.wallLeft){
				return false;
			}}
		
		return true;
	}
	
	public static boolean checkTop(Player hero, ArrayList<Wall> walls){
		
		for (int i = 0; i < walls.size(); i++) {
			Wall currentWall = walls.get(i);
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
			}}
		
		return true;
	}	
}