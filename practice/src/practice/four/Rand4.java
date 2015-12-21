package practice.four;

import java.util.ArrayList;
import java.util.Collections;

public class Rand4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> al = new ArrayList<Integer>(6);
		al.add(genRand());
		for (int i = 1; i < 6; i++) {
			al.add(genRand());
			for (int j = 0; j < i; j++) {
				if (al.get(i) == al.get(j)) {
					al.set(i, genRand());
					j = 0;
				}
			}
		}
		Collections.sort(al);
		System.out.println(al);

	}
	
	static int genRand() {
		return (int) (Math.random()*49 + 1);
	}

}
