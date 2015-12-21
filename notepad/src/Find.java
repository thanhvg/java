import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.beans.*; //property change stuff
import java.awt.*;
import java.awt.event.*;

public class Find extends JDialog implements ActionListener, PropertyChangeListener {

	private String btnStr1 = "Find";
	private String btnStr2 = "Cancel";
	private String typedText = null;
	private JOptionPane optionPane ; 
	private JTextField textField; 
	private Notepad notepadInstance;
	
	public Find(Notepad aFrame){
		super (aFrame);
		notepadInstance = aFrame;
		textField = new JTextField(10);
		setTitle("Find");
		Object[] array ={"Find what?", textField};
		Object[] options ={btnStr1,btnStr2};
		optionPane = new JOptionPane(array, 
									JOptionPane.QUESTION_MESSAGE,
									JOptionPane.YES_NO_OPTION, 
									null, 
									options, 
									options[0]);
			
		setContentPane(optionPane);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	                public void windowClosing(WindowEvent we) {
	                /*
	                 * Instead of directly closing the window,
	                 * we're going to change the JOptionPane's
	                 * value property.
	                 */
	                    optionPane.setValue(new Integer(
	                                        JOptionPane.CLOSED_OPTION));
	            }
	        });
	 
	        //Ensure the text field always gets the first focus.
	    addComponentListener(new ComponentAdapter() {
	            public void componentShown(ComponentEvent ce) {
	                textField.requestFocusInWindow();
	            }
	        });
	 
	    //Register an event handler that puts the text into the option pane.
	    textField.addActionListener(this);
	 
	     //Register an event handler that reacts to option pane state changes.
	    optionPane.addPropertyChangeListener(this);
	  
	    pack();
	    setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent e) {
	    optionPane.setValue(btnStr1);
	}
    public void propertyChange(PropertyChangeEvent e) {
    	String prop =e.getPropertyName();
    	if (isVisible()
    	         && (e.getSource() == optionPane)
    	         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
    	             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
    	            Object value = optionPane.getValue();

    	            if (value == JOptionPane.UNINITIALIZED_VALUE) {
    	                //ignore reset
    	                return;
    	            }
    	          //Reset the JOptionPane's value.
    	          //If you don't do this, then if the user
    	          //presses the same button next time, no
    	          //property change event will be fired.
    	          optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
    	          if (btnStr1.equals(value)) {
    	        	  //clearAndHide();
    	        	  //get the search term
    	        	  String str = textField.getText();
    	        	  notepadInstance.find(str); 
    	          } else {
    	        	  clearAndHide();
    	          }
    	          
    	}
    }
    	
    	public void clearAndHide() {
    		textField.setText(null);
            setVisible(false);
    	}
    	

	
	 
}
