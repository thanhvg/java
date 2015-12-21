package com.practicaljava.lesson12.common;

public class CurrentScore {

	String winner;
	int kidScore;
	int computerScore;

	// Constructor
	public CurrentScore(String w, int k, int c) {
		winner = w;
		kidScore = k;
		computerScore = c;
	}
	
	

	public String getWinner() {
		return winner;
	}



	public void setWinner(String winner) {
		this.winner = winner;
	}



	public int getKidScore() {
		return kidScore;
	}



	public void setKidScore(int kidScore) {
		this.kidScore = kidScore;
	}



	public int getComputerScore() {
		return computerScore;
	}



	public void setComputerScore(int computerScore) {
		this.computerScore = computerScore;
	}



	public String toString() {
		return 	winner + " won by "
		+ Math.abs(kidScore - computerScore)
		+ " points " + " : " + kidScore + "/"
		+ computerScore;
	}
}

