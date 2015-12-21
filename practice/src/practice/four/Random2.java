package practice.four;

import java.util.Arrays;

public class Random2 {
	public static void main(String[] args) {
		int[] a = new int[6];
		for (int i = 0; i < 6; i++) {
			a[i] = genRand();
			for (int j = 0; j < i; j++) {
				if (a[i] == a[j]) {
					a[i] = genRand();
					j = 0;
				}
			}
		}
		
		for (int i = 0; i < 6; i++) {
			System.out.println(a[i]);
		}
		Arrays.sort(a);
		System.out.println("Sorting");
		for (int i = 0; i < 6; i++) {
			System.out.println(a[i]);
		}
	}
	
	static int genRand() {
		return (int) (Math.random()*49 + 1);
	}

}
