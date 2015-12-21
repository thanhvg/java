import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class gui {

	/**
	 * @param args
	 */
	
	JPanel pMain, p1;
	JTextField displayField;
	JButtonEx b0, b1,b2,b3,b4,b5,b6,b7,b8,b9, bDot, bEqual, bClear, bPlus, bMin, bDiv, bMul;
	
	gui() {
		pMain = new JPanel();
		BorderLayout bl = new BorderLayout();
		pMain.setLayout(bl);
		displayField = new JTextField(30);
		displayField.setFocusable(false);
		displayField.setHorizontalAlignment(JTextField.RIGHT);
		pMain.add("North",displayField);
		engine engineInstant = new engine(this);
		
		b0 = new JButtonEx("0");
		b0.addActionListener(engineInstant.new b1to9Action());
		b1 = new JButtonEx("1");
		b1.addActionListener(engineInstant.new b1to9Action());
		b2 = new JButtonEx("2"); b2.addActionListener(engineInstant.new b1to9Action());
		b3 = new JButtonEx("3"); b3.addActionListener(engineInstant.new b1to9Action());
		b4 = new JButtonEx("4"); b4.addActionListener(engineInstant.new b1to9Action());
		b5 = new JButtonEx("5"); b5.addActionListener(engineInstant.new b1to9Action());
		b6 = new JButtonEx("6"); b6.addActionListener(engineInstant.new b1to9Action());
		b7 = new JButtonEx("7"); b7.addActionListener(engineInstant.new b1to9Action());
		b8 = new JButtonEx("8"); b8.addActionListener(engineInstant.new b1to9Action());
		b9 = new JButtonEx("9"); b9.addActionListener(engineInstant.new b1to9Action());
		bDot = new JButtonEx("."); bDot.addActionListener(engineInstant.new bDotAction());
		bPlus = new JButtonEx("+"); bPlus.addActionListener(engineInstant.new plusAction());
		bMin = new JButtonEx("-"); bMin.addActionListener(engineInstant.new minAction());
		bMul = new JButtonEx("*"); bMul.addActionListener(engineInstant.new mulAction());
		bDiv = new JButtonEx("/"); bDiv.addActionListener(engineInstant.new divAction());
		bEqual = new JButtonEx("="); bEqual.addActionListener(engineInstant.new equalAction());
		//bClear = new JButtonEx("C");
		
		GridLayout gl = new GridLayout(4,3);
		p1 = new JPanel();
		p1.setLayout(gl);
		p1.add(b0);
		p1.add(b1); 
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		p1.add(b6);
		p1.add(b7);
		p1.add(b8);
		p1.add(b9);
		p1.add(bDot);
		p1.add(bPlus);
		p1.add(bMin);
		p1.add(bMul);
		p1.add(bDiv);
		p1.add(bEqual);
		//p1.add(bClear);
		
		//pMain.addKeyListener(engineInstant.new keyAction());
		//pMain.setFocusable(true);
		
		pMain.add("Center", p1);
		
		JFrame frame = new JFrame("Calculator");
		frame.setContentPane(pMain);
		frame.addKeyListener(engineInstant.new keyAction());
		frame.setFocusable(true);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public String getString() {
		return displayField.getText();
	}
	public void setString(String text){
		displayField.setText(text);
	}

	public static void main(String[] args) {
		
		gui frontEnd = new gui();
		frontEnd.setString("0");
		

	}

}
