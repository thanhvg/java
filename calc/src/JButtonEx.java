import javax.swing.JButton;


public class JButtonEx extends JButton {
public JButtonEx(String s){
	super(s);
	this.setFocusable(false); //So it will not steal the focus from the frame
	
}
}
