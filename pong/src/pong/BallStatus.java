package pong;

public enum BallStatus {
	MOVELEFT,
	STOPPED,
	MOVERIGHT,
	TOUCHLEFT,
	TOUCHRIGHT;
	
	
	BallStatus flip() {
		if (this == MOVELEFT) {return MOVERIGHT;}
		if (this == MOVERIGHT) { return MOVELEFT;}
		else 
			return this;
		
	}
}
