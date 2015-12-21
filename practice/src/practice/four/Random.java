package practice.four;

public class Random {	
	public static void main(String[] args) {
		int rand = (int)(20*Math.random() + 1) ;
		int[] a = new int[rand];
		for (int i = 0; i < rand; i++) {
			a[i] = i + 1;
			System.out.println(a[i]);
		}
		double[] b = new double[rand];
		for (int i = 0; i < rand; i++) {
			b[i] = a[i]*Math.PI;
			System.out.println(b[i]);
		}
	}

}
