package network2;

import java.io.Serializable;

public class ServerToClientMsg implements Serializable {
	int ballPosX;
	int ballPoxY;
	int barLeftPos;
	int barRightPos;
	public ServerToClientMsg(int ballPosX, int ballPoxY, int barLeftPos, int barRightPos) {
		super();
		this.ballPosX = ballPosX;
		this.ballPoxY = ballPoxY;
		this.barLeftPos = barLeftPos;
		this.barRightPos = barRightPos;
	}
	public int getBallPosX() {
		return ballPosX;
	}
	public void setBallPosX(int ballPosX) {
		this.ballPosX = ballPosX;
	}
	public int getBallPoxY() {
		return ballPoxY;
	}
	public void setBallPoxY(int ballPoxY) {
		this.ballPoxY = ballPoxY;
	}
	public int getBarLeftPos() {
		return barLeftPos;
	}
	public void setBarLeftPos(int barLeftPos) {
		this.barLeftPos = barLeftPos;
	}
	public int getBarRightPos() {
		return barRightPos;
	}
	public void setBarRightPos(int barRightPos) {
		this.barRightPos = barRightPos;
	}
	
	
	

}
