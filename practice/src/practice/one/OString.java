package practice.one;

public class OString {
	String str = "The foxfox jumps over the lazy dog";
	
//	public OString(String str) {
//		this.str = str;
//	}
	private void stringTest(String testStr){
		int test = str.indexOf(testStr);
		if (test == -1) {
			System.out.printf("Aninmal %s not found \n", testStr);
			return;
		}
		System.out.printf("Found %s at %d \n", testStr, test);
	}
	StringBuilder sb = new StringBuilder(str);
	private boolean toCap(String str) {
		int count = sb.indexOf(str);
		if (count == -1) { 
			System.out.println("animal not found");
			return false;
		}
		while (sb.indexOf(str, count) != -1 ) {
			sb.replace(count, count + str.length(), str.toUpperCase());
			count = count + str.length();		
		}
		System.out.printf("%s\n",sb.toString());
		return true;
	}
	
	
	public static void main(String args[]){
		OString os = new OString();
		os.stringTest("fox");
		os.stringTest("tiger");
		os.toCap("fox");
		os.toCap("lion");	
		
	}

}
