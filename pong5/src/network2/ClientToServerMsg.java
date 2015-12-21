package network2;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class ClientToServerMsg implements Serializable {
	int  keyCode;
	boolean isPressed; // true means pressed, false means released 
	public ClientToServerMsg(int k, boolean isPressed) {
		super();
		this.keyCode = k;
		this.isPressed = isPressed;
	}
	
	

}
