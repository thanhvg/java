import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class CalculatorEngine implements ActionListener {
	double firstOperand, secondOperand, result;
	String operator;	
	//String operator_type;
		
	Calculator parent; // a reference to the Calculator
	
	// Constructor stores the reference to the Calculator window in the member varial parent
	CalculatorEngine (Calculator parent){
		this.parent = parent;
	}
	
	/*
	// Doing math stuff
	double math(double var1, double var2, String operator) {
		if (operator == "+") 	return var1+var2;
		if (operator == "-") 	return var1-var2;
		if (operator == "*") 	return var1*var2;
		if (operator == "/") 	return var1/var2;
	}
	*/
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the source of the action
		JButton clickedButton = (JButton) e.getSource();
		// Get the existing text from the Calculator's display field
		String dispFieldText = parent.getDisplayvalue();
		// Get the button label
		String clickedButtonLabel = clickedButton.getText();
		
		// If number ?
		if (clickedButtonLabel == "1"||
			clickedButtonLabel == "2"||
			clickedButtonLabel == "3"||
			clickedButtonLabel == "4"||
			clickedButtonLabel == "5"||
			clickedButtonLabel == "6"||
			clickedButtonLabel == "7"||
			clickedButtonLabel == "8"||
			clickedButtonLabel == "9"||
			clickedButtonLabel == "0"||
			clickedButtonLabel == ".")	{
		
		// Set new value
		parent.setDislayValue(dispFieldText + clickedButtonLabel);
		}
		
		
		// Math operator
		if (clickedButtonLabel == "+"||
			clickedButtonLabel == "-"||
			clickedButtonLabel == "*"||
			clickedButtonLabel == "/") {
			// Save the first operand
			firstOperand = Double.parseDouble(parent.getDisplayvalue());
			// Save operator
			operator = clickedButtonLabel;
			// Clear the display field
			parent.setDislayValue(null);			
		}
		// if = 
		if (clickedButtonLabel =="="){
			secondOperand = Double.parseDouble(parent.getDisplayvalue());
			if (operator == "+") 	result = firstOperand+secondOperand;
			if (operator == "-") 	result = firstOperand-secondOperand;
			if (operator == "*") 	result = firstOperand*secondOperand;
			if (operator == "/") 	result = firstOperand/secondOperand;
			parent.setDislayValue(Double.toString(result));
			dispFieldText = null;
			
		}
	}
}
