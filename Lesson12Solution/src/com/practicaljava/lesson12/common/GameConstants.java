package com.practicaljava.lesson12.common;


/**
 * This interface contains all definitions of the 
 * final variables that are used in the game 
 */ 
public interface GameConstants {
  // Size of the ping pong table
  public final int TABLE_WIDTH =  320; 
  public final int TABLE_HEIGHT = 220;
  public final int TABLE_TOP = 10;
  public final int TABLE_BOTTOM = 180;

  // Ball movement increment in pixels 
  public final int BALL_INCREMENT = 5;
	
  // Maximum and minimum allowed ball coordinates  
  public final int BALL_MIN_X = 1 + BALL_INCREMENT;
  public final int BALL_MIN_Y = 1 + BALL_INCREMENT;
  public final int BALL_MAX_X = TABLE_WIDTH - BALL_INCREMENT;
  public final int BALL_MAX_Y = TABLE_HEIGHT - BALL_INCREMENT;
	
  // Starting coordinates of the ball
  public final int BALL_START_X = TABLE_WIDTH/2;
  public final int BALL_START_Y = TABLE_HEIGHT/2;
  public final int BALL_CENTER = 5;
     
  //Rackets' sizes, positions and movement increments 
  public final int KID_RACKET_X = 300;
  public final int KID_RACKET_Y_START = 100;
  public final int COMPUTER_RACKET_X = 15;
  public final int COMPUTER_RACKET_Y_START = 100;
  public final int RACKET_INCREMENT = 1;
  public final int RACKET_LENGTH = 30;
  public final int RACKET_WIDTH = 5;
  public final int RACKET_CENTER = RACKET_LENGTH / 2;
	
  public final int WINNING_SCORE = 2;
  public final String SCORES_FILE = "scores.txt";
  public final String PROPERTIES_FILE = "properties.txt";
  
//Slow down fast computers - change the value if needed 
  public final int SLEEP_TIME = 30; //time in milliseconds
  
  public void loadProperties ( String propFile );
	
}
