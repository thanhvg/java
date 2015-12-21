package pong;


public class Hello {

	/**
	 * @param args
	 */
	
	//double x1,x2;
	String text;
	enum ENUM {PLUS, MINUS, MUL, DIV}
	ENUM mathCode;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hi there");
		double x1 = 135;
		double x2 = 5678;
		Hello hi = new Hello();
		System.out.println(hi.math(x1, x2, ENUM.PLUS));
		
	}
	double math(double a1, double a2, ENUM a3){
		double result;
		
		switch (a3)
		{
		case PLUS: result = a1+a2; break;
		case MINUS: result = a1-a2; break;
		case MUL: result = a1*a2; break;
		case DIV: result = a1/a2; break;
		default: result = 1;
		}
		
		return result;
	}
}
