package ball;

import java.awt.event.ActionEvent;

import net.BallPos;
import net.Client;

public class ClientBall extends Ball{
	Client client;
	
	public ClientBall(Client client) {
		super();
		this.client = client;
	}
	
	public ClientBall() { 
		super();
		client = new Client();
		client.setClientBall(this);
		client.openConnection();
	}
	
	public void importBallPos(BallPos ballPos) {
		x = ballPos.getX();
		y = ballPos.getY();
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		//super.actionPerformed(e);
		Thread thread = new Thread(client);
		thread.start();
		repaint();
	}				

}
