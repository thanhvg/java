package practice.four;

import java.util.Arrays;

public class Rand3 {

	public static void main(String[] args) {
		int[] a = new int[6];
		a[0] = genRand();
		for (int i = 1; i < 6; i++) {
			a[i] = genRand();
			//sort(a, i);
			int[] temp = new int[i];
			System.arraycopy(a, 0, temp, 0, i);
			Arrays.sort(temp);
			//int s;
			while (Arrays.binarySearch(temp, a[i]) >= 0) {
				a[i] = genRand();
				//Arrays.sort(temp);
				
			}
			//System.arraycopy(temp, 0, a, 0, i);
		}
		Arrays.sort(a);
		//sort(a,5);
		for (int i = 0; i < 6; i++) {
			System.out.println(a[i]);
		}

	}
	
	static int genRand() {
		return (int) (Math.random()*49 + 1);
	}
	
	static void sort(int[] a, int b){
		int temp;
		for (int i = 0; i <= b; i++ ) {
			for (int j = i; j <= b; j++) {
				if (a[i] > a[j]) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
			
		
	}
	

}
