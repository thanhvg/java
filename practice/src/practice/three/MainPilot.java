package practice.three;

import java.io.PrintWriter;

public class MainPilot {

	public static void main(String[] args) {
		CatPilot catPilot = null;

		for (int i = 0; i < args.length; i++) {
			try {
				catPilot = new CatPilot("file:" + args[i]);
				catPilot.copy(new PrintWriter(System.out));
			} catch (SubCatException e) {
				System.err.println(e);
			} catch (SubCatExceptionPrint e){
				System.err.println(e);
				return;
			}

		}

	}
}
