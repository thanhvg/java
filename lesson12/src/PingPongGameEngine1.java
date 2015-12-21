import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class PingPongGameEngine1 implements MouseListener, MouseMotionListener, GameConstants1 {

	PingPongGreenTable1 table;
	public int kidRacketY = KID_RACKET_Y_START;
	
	// Constructor stores a reference to the UI object
	public PingPongGameEngine1(PingPongGreenTable1 greenTable){
		table = greenTable;
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int mouseY = e.getY();
		// if a mouse is above the kid's racket
		// and the racket did not go over the table top
		// move it up, otherwise move it down
		if (mouseY < kidRacketY && kidRacketY > TABLE_TOP){
			kidRacketY -= RACKET_INCREMENT;			
		} else if (kidRacketY < TABLE_BOTTOM) {
			kidRacketY += RACKET_INCREMENT;
		}
		// Set the new position of the racket table class
		table.setKidRacketY(kidRacketY);
		table.repaint();

	}

	@Override
	// Method required by the MouseListener interface
	public void mouseClicked(MouseEvent e) {
		// Get X and Y coordinates of the mouse pointer
		// and set it to the "white point" on the table
		// bad practice. Fix it later
		table.point.x = e.getX();
		table.point.y = e.getY();
		// The method repaint internally calls the table's 
		// method paintComponent() that refreshes the window
		table.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
