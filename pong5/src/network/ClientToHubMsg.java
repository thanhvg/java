package network;

import java.io.Serializable;

public class ClientToHubMsg implements Serializable {
	int barPos;

	public int getBarPos() {
		return barPos;
	}

	public void setBarPos(int barPos) {
		this.barPos = barPos;
	}

	public ClientToHubMsg(int barPos) {
		super();
		this.barPos = barPos;
	}

}
