import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Calculator {

	/**
	 * @param args
	 */

	// Declare all calculator's components.
	JPanel windowContent;
	private JTextField displayField;
	JButton button0;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton button8;
	JButton button9;
	JButton buttonPoint;
	JButton buttonEqual, buttonPlus,buttonSub, buttonMul, buttonDiv;
	JPanel p1;

	// Public API to set and retrieve display text
	
	public void setDislayValue(String val) {
		displayField.setText(val);	
	}
	public String getDisplayvalue(){
		return displayField.getText();
	}
	
	// Constructor creates the components
	// and adds the to the frame using combination of
	// Borderlayout and Gridlayout
	
	Calculator(){
		windowContent= new JPanel();
		
		// Set the layout manager for this panel
		BorderLayout bl = new BorderLayout();
		windowContent.setLayout(bl);
		
		// Create the display field and place it in the
		// North area of the window
		displayField = new JTextField(30);
		windowContent.add("North",displayField);
		
		// Create buttons using constructor of the
		// class JButton that takes the label of the
		// button as a parameter
		button0=new JButton("0");
		button1=new JButton("1");
		button2=new JButton("2");
		button3=new JButton("3");
		button4=new JButton("4");
		button5=new JButton("5");
		button6=new JButton("6");
		button7=new JButton("7");
		button8=new JButton("8");
		button9=new JButton("9");
		buttonPoint = new JButton(".");
		buttonEqual = new JButton("=");
		buttonPlus = new JButton("+");
		buttonSub = new JButton("-");
		buttonMul = new JButton("*");
		buttonDiv = new JButton("/");
		
		
		
		// Create the panel with the GridLayout with 12 buttons �
		//10 numeric ones, period, and the equal sign
		p1 = new JPanel();
		GridLayout gl =new GridLayout(4,4);
		p1.setLayout(gl);
		
		// Add window controls to the panel p1
		p1.add(button1);
		p1.add(button2);
		p1.add(button3);
		p1.add(buttonPlus);
		p1.add(button4);
		p1.add(button5);
		p1.add(button6);
		p1.add(buttonSub);
		p1.add(button7);
		p1.add(button8);
		p1.add(button9);
		p1.add(buttonMul);
		p1.add(button0);
		p1.add(buttonPoint);
		p1.add(buttonEqual);
		p1.add(buttonDiv);
		
		
		//Associate buttons with event handling engine
		CalculatorEngine calcEngine = new CalculatorEngine(this);
		button0.addActionListener(calcEngine);
		button1.addActionListener(calcEngine);
		button2.addActionListener(calcEngine);
		button3.addActionListener(calcEngine);
		button4.addActionListener(calcEngine);
		button5.addActionListener(calcEngine);
		button6.addActionListener(calcEngine);
		button7.addActionListener(calcEngine);
		button8.addActionListener(calcEngine);
		button9.addActionListener(calcEngine);
		buttonEqual.addActionListener(calcEngine);
		buttonPlus.addActionListener(calcEngine);
		buttonSub.addActionListener(calcEngine);
		buttonMul.addActionListener(calcEngine);
		buttonDiv.addActionListener(calcEngine);	
		buttonPoint.addActionListener(calcEngine);
		
		
		// Add the panel p1 to the center of the window
		windowContent.add("Center",p1);
		
		//Create the frame and set its content pane
		JFrame frame = new JFrame("Calculator");
		frame.setContentPane(windowContent);
		
		// Set the size of the window big enough to accommodate all controls
		frame.pack();
		// Display the window
		frame.setVisible(true);
	}
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Align display field on the left
		// after calculation, new input will not be added to previous value
		// get input from keyboard
		// only accept dot one time
		// dell key 
		Calculator calc = new Calculator();

	}

}
