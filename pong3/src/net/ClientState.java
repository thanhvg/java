package net;

import java.io.Serializable;

public class ClientState implements Serializable {
	int y;
	
	public ClientState(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


}
