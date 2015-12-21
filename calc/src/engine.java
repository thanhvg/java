import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;


public class engine  {
	
	double x1,x2;
	gui guiPointer;
	String text;
	enum ENUM {PLUS, MINUS, MUL, DIV, NOTHING}
	ENUM mathCode;
	//boolean newCalFlag = false;
		
	public engine(gui guiP){
		this.guiPointer = guiP;
		mathCode = ENUM.NOTHING;
		text = "";
	}
	
	// math stuff
	
	double math(double a1, double a2,ENUM a3) /*throws Exception*/ {
		double result;
		switch (a3)
		{
		case PLUS: result = a1+a2; break;
		case MINUS: result = a1-a2; break;
		case MUL: result = a1*a2; break;
		case DIV: result = a1/a2; break;
		case NOTHING: result = a1; break;
		default: result = a1; break;   
		}
		return result;
		// return a1+a2;
	}

	class b1to9Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			JButton bTemp = (JButton) e.getSource();
			if (text != "") 
			text = guiPointer.getString();
			text += bTemp.getText();
			guiPointer.setString(text);
		}
		
	}
	class bDotAction implements ActionListener {
		@Override 
		public void actionPerformed (ActionEvent e){
			if (text.indexOf('.') == -1) {
				text += '.';
				guiPointer.setString(text);
			}
		}
	}
	class plusAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			String temp = guiPointer.getString();
			x1 = Double.parseDouble(temp);
			guiPointer.setString(null);
			mathCode = ENUM.PLUS;
			
		}		
	}
	
	class minAction implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent e){
			String temp = guiPointer.getString();
			x1 = Double.parseDouble(temp);
			guiPointer.setString(null);
			mathCode = ENUM.MINUS;
		}
	}
	
	class divAction implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent e){
			String temp = guiPointer.getString();
			x1 = Double.parseDouble(temp);
			guiPointer.setString(null);
			mathCode = ENUM.DIV;
		}
	}
	
	class mulAction implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent e){
			String temp = guiPointer.getString();
			x1 = Double.parseDouble(temp);
			guiPointer.setString(null);
			mathCode = ENUM.MUL;
		}
	}
	class equalAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			//String temp = guiPointer.getString();
			// if (temp =="") return;
			// x2 = Double.parseDouble(temp);
			if (text =="") return;
			x2 = Double.parseDouble(text);
			Double x3 = math(x1,x2,mathCode);
			if ((x3 % 1)== 0){
				int t = x3.intValue();
				guiPointer.setString(Integer.toString(t));				
			} 
			else
				guiPointer.setString(Double.toString(x3));
			text = "";
			x1 = x3;
		}
	}
	class keyAction extends KeyAdapter  {
		public void keyPressed(KeyEvent e){
			char c = e.getKeyChar();
			switch (c) {
			case '1': guiPointer.b1.doClick(); break;
			case '2': guiPointer.b2.doClick(); break;
			case '3': guiPointer.b3.doClick(); break;
			case '4': guiPointer.b4.doClick(); break;
			case '5': guiPointer.b5.doClick(); break;
			case '6': guiPointer.b6.doClick(); break;
			case '7': guiPointer.b7.doClick(); break;
			case '8': guiPointer.b8.doClick(); break;
			case '9': guiPointer.b9.doClick(); break;
			case '0': guiPointer.b0.doClick(); break;
			case '.': guiPointer.bDot.doClick(); break;
			case '*': guiPointer.bMul.doClick(); break;
			case '/': guiPointer.bDiv.doClick(); break;
			case '-': guiPointer.bMin.doClick(); break;
			case '+': guiPointer.bPlus.doClick(); break;
			case KeyEvent.VK_ENTER: guiPointer.bEqual.doClick(); break;
			case KeyEvent.VK_BACK_SPACE: 
				if (text != "") { 
					text = text.substring(0, text.length()-1); //Cut off the latest char
					guiPointer.setString(text);
				}
			default: break;
			}
		}
	}
	
}
