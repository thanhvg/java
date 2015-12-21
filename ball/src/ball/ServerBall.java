package ball;

import java.awt.event.ActionEvent;

import net.BallPos;
import net.Server;



public class ServerBall extends Ball{
	//Thread thread;
	Server server;
	
	public ServerBall(Server server) {
		super();
		this.server = server;
	}
	
	public ServerBall() {
		super();
		server = new Server();
		server.setServerBall(this);
		server.openConnection();
	}
	
	public BallPos exportBallPos() {
		return new BallPos((int)x,(int) y);
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Thread thread = new Thread(server);
		thread.start();
	}

}
