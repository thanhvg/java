package practice.four;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Rand5 {

	public static void main(String[] args) {
		TreeSet<Integer> a = new TreeSet<Integer>();
		while (a.size() < 6) {
			a.add(genRand());			
		}
		//Collections.sort(a);
		System.out.println(a);

	}
	
	static int genRand() {
		return (int) (Math.random()*49 + 1);
	}

}
