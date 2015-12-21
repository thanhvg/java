package game;

import java.util.ArrayList;
import java.util.List;

public class Test {
	String str;
	int y;
	
	public Test (String str, int y) {
		this.str = str;
		this.y = y;
	}
	
	public static void main(String[] args) {
	
		Test a;

		List<Test> l = new ArrayList<>();
		a = new Test("Test 1", 1);
		l.add(a);
		a = new Test("Test 2", 2);
		l.add(a);
		a = null;
		
		for (Test b : l) {
			System.out.println(b.toString());
		}

	}
}
